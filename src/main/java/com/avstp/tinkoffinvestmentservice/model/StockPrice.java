package com.avstp.tinkoffinvestmentservice.model;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.math.BigDecimal;

@Value
@AllArgsConstructor
public class StockPrice {
    String figi;
    BigDecimal price;
}
