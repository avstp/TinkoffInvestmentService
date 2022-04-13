package com.avstp.tinkoffinvestmentservice.service;

import com.avstp.tinkoffinvestmentservice.dto.FigiesDto;
import com.avstp.tinkoffinvestmentservice.dto.StockPricesDto;
import com.avstp.tinkoffinvestmentservice.dto.StocksDto;
import com.avstp.tinkoffinvestmentservice.dto.TickersDto;
import com.avstp.tinkoffinvestmentservice.model.Stock;
import com.avstp.tinkoffinvestmentservice.model.StockPrice;

public interface StockService {

    Stock getStockByTicker(String ticker);

    StocksDto getStocksByTickers(TickersDto tickers);

    StockPricesDto getPrices(FigiesDto figiesDto);
}
