package com.avstp.tinkoffinvestmentservice.dto;

import com.avstp.tinkoffinvestmentservice.model.Stock;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StocksDto {
    private List<Stock> stocks;
}
