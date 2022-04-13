package com.avstp.tinkoffinvestmentservice.dto;

import com.avstp.tinkoffinvestmentservice.model.StockPrice;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockPricesDto {
    private List<StockPrice> stockPriceList;
}
