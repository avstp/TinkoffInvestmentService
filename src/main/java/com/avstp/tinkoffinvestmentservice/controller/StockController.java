package com.avstp.tinkoffinvestmentservice.controller;

import com.avstp.tinkoffinvestmentservice.dto.FigiesDto;
import com.avstp.tinkoffinvestmentservice.dto.StockPricesDto;
import com.avstp.tinkoffinvestmentservice.dto.StocksDto;
import com.avstp.tinkoffinvestmentservice.dto.TickersDto;
import com.avstp.tinkoffinvestmentservice.model.Stock;
import com.avstp.tinkoffinvestmentservice.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stock")
@RequiredArgsConstructor
public class StockController {
    private final StockService stockService;

    @GetMapping("/{ticker}")
    public Stock getStock(@PathVariable String ticker) {
        return stockService.getStockByTicker(ticker);
    }

    @GetMapping
    public StocksDto getStockList(TickersDto tickersDto) {
        return stockService.getStocksByTickers(tickersDto);
    }

    @GetMapping("/price")
    public StockPricesDto getPriceList(FigiesDto figiesDto) {
        return stockService.getPrices(figiesDto);
    }
}
