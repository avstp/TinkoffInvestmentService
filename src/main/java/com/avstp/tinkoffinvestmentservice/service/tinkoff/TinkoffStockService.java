package com.avstp.tinkoffinvestmentservice.service.tinkoff;

import com.avstp.tinkoffinvestmentservice.dto.FigiesDto;
import com.avstp.tinkoffinvestmentservice.dto.StockPricesDto;
import com.avstp.tinkoffinvestmentservice.dto.StocksDto;
import com.avstp.tinkoffinvestmentservice.dto.TickersDto;
import com.avstp.tinkoffinvestmentservice.exception.StockNotFoundException;
import com.avstp.tinkoffinvestmentservice.mapper.TinkoffStockMapper;
import com.avstp.tinkoffinvestmentservice.model.Stock;
import com.avstp.tinkoffinvestmentservice.model.StockPrice;
import com.avstp.tinkoffinvestmentservice.service.StockService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.tinkoff.invest.openapi.model.rest.MarketInstrument;
import ru.tinkoff.invest.openapi.model.rest.MarketInstrumentList;
import ru.tinkoff.invest.openapi.model.rest.Orderbook;

@Service
@RequiredArgsConstructor
@Slf4j
public class TinkoffStockService implements StockService {
    private final AsyncStockService asyncStockService;

    @Override
    public Stock getStockByTicker(String ticker) {
        CompletableFuture<MarketInstrumentList> instrumentListCompletableFuture = asyncStockService.getMarketInstrumentByTicker(ticker);
        MarketInstrumentList marketInstrumentList = instrumentListCompletableFuture.join();
        MarketInstrument marketInstrument = getMarketInstrument(marketInstrumentList);
        if (marketInstrument == null) {
            throw new StockNotFoundException(String.format("Stock with ticker %s not found", ticker));
        }
        return TinkoffStockMapper.toModel(marketInstrument);
    }

    public StocksDto getStocksByTickers(TickersDto tickers) {
        List<CompletableFuture<MarketInstrumentList>> marketInstruments = new ArrayList<>();
        tickers.getTickers().forEach(ticker -> marketInstruments.add(asyncStockService.getMarketInstrumentByTicker(ticker)));
        List<Stock> stocks = marketInstruments.stream()
                .map(CompletableFuture::join)
                .map(this::getMarketInstrument)
                .filter(Objects::nonNull)
                .map(TinkoffStockMapper::toModel)
                .collect(Collectors.toList());
        return new StocksDto(stocks);
    }

    @Override
    public StockPricesDto getPrices(FigiesDto figiesDto) {
        long start = System.currentTimeMillis();
        List<CompletableFuture<Optional<Orderbook>>> completableFutures = new ArrayList<>();
        figiesDto.getFigies()
                .forEach(figi -> completableFutures.add(asyncStockService.getOrderBookByFigi(figi)));
        List<StockPrice> stockPrices = completableFutures.stream()
                .map(CompletableFuture::join)
                .map(orderbook -> orderbook.orElseThrow(() -> new StockNotFoundException("Stock not found")))
                .map(orderbook -> new StockPrice(orderbook.getFigi(), orderbook.getLastPrice()))
                .collect(Collectors.toList());
        log.info("Time - {}", System.currentTimeMillis() - start);
        return new StockPricesDto(stockPrices);
    }


    private MarketInstrument getMarketInstrument(MarketInstrumentList marketInstrumentList) {
        List<MarketInstrument> instruments = marketInstrumentList.getInstruments();
        if (instruments.isEmpty()) {
            return null;
        }
        return instruments.get(0);
    }
}
