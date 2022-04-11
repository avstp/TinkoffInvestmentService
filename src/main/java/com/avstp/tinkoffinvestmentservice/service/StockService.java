package com.avstp.tinkoffinvestmentservice.service;

import com.avstp.tinkoffinvestmentservice.dto.StocksDto;
import com.avstp.tinkoffinvestmentservice.dto.TickersDto;
import com.avstp.tinkoffinvestmentservice.model.Stock;

public interface StockService {

    Stock getStockByTicker(String ticker);

    StocksDto getStocksByTickers(TickersDto tickers);
}
