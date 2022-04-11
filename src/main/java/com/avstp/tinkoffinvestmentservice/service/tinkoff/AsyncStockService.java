package com.avstp.tinkoffinvestmentservice.service.tinkoff;

import java.util.concurrent.CompletableFuture;
import ru.tinkoff.invest.openapi.model.rest.MarketInstrumentList;

public interface AsyncStockService {
    CompletableFuture<MarketInstrumentList> getMarketInstrumentByTicker(String ticker);
}
