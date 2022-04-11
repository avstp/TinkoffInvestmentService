package com.avstp.tinkoffinvestmentservice.service.tinkoff;

import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import ru.tinkoff.invest.openapi.MarketContext;
import ru.tinkoff.invest.openapi.OpenApi;
import ru.tinkoff.invest.openapi.model.rest.MarketInstrumentList;

@Service
@RequiredArgsConstructor
public class AsyncStockServiceImpl implements AsyncStockService{
    private final OpenApi openApi;

    @Override
    @Async
    public CompletableFuture<MarketInstrumentList> getMarketInstrumentByTicker(String ticker) {
        MarketContext marketContext = openApi.getMarketContext();
        return marketContext.searchMarketInstrumentsByTicker(ticker);
    }
}
