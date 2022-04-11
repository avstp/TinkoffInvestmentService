package com.avstp.tinkoffinvestmentservice.model;

import lombok.Getter;

@Getter
public enum Currency {
    RUB("RUB"),
    USD("USD"),
    EUR("EUR"),
    CNY("CNY");

    private final String currency;

    Currency(String currency) {
        this.currency = currency;
    }
}
