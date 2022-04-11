package com.avstp.tinkoffinvestmentservice.model;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class Stock {
    String ticker;
    String figi;
    String name;
    String type;
    Currency currency;
    String source;
}
