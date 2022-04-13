package com.avstp.tinkoffinvestmentservice.service.tinkoff;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import ru.tinkoff.invest.openapi.model.rest.MarketInstrumentList;
import ru.tinkoff.invest.openapi.model.rest.Orderbook;

public interface AsyncStockService {
    CompletableFuture<MarketInstrumentList> getMarketInstrumentByTicker(String ticker);
    CompletableFuture<Optional<Orderbook>> getOrderBookByFigi(String figi);
}
