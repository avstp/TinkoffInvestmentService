package com.avstp.tinkoffinvestmentservice.service.tinkoff;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import ru.tinkoff.invest.openapi.MarketContext;
import ru.tinkoff.invest.openapi.OpenApi;
import ru.tinkoff.invest.openapi.model.rest.MarketInstrumentList;
import ru.tinkoff.invest.openapi.model.rest.Orderbook;

@Service
@RequiredArgsConstructor
@Async("stockThreadPoolTaskExecutor")
public class AsyncStockServiceImpl implements AsyncStockService{
    private final OpenApi openApi;

    @Override
    public CompletableFuture<MarketInstrumentList> getMarketInstrumentByTicker(String ticker) {
        MarketContext marketContext = openApi.getMarketContext();
        return marketContext.searchMarketInstrumentsByTicker(ticker);
    }

    @Override
    public CompletableFuture<Optional<Orderbook>> getOrderBookByFigi(String figi) {
        return openApi.getMarketContext().getMarketOrderbook(figi, 0);
    }
}
