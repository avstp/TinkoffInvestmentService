package com.avstp.tinkoffinvestmentservice.mapper;

import com.avstp.tinkoffinvestmentservice.model.Currency;
import com.avstp.tinkoffinvestmentservice.model.Stock;
import lombok.experimental.UtilityClass;
import ru.tinkoff.invest.openapi.model.rest.MarketInstrument;

@UtilityClass
public class TinkoffStockMapper {

    public Stock toModel(MarketInstrument marketInstrument) {
        return Stock.builder()
                .ticker(marketInstrument.getTicker())
                .figi(marketInstrument.getFigi())
                .name(marketInstrument.getName())
                .type(marketInstrument.getType().name())
                .currency(Currency.valueOf(marketInstrument.getCurrency().name()))
                .source("TINKOFF")
                .build();
    }
}
