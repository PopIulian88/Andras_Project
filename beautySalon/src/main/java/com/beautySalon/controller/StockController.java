package com.beautySalon.controller;

import com.beautySalon.entity.Stock;
import com.beautySalon.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StockController {
    @Autowired
    private StockService stockService;

    @PostMapping("/addStock")
    public Stock addStock(@RequestBody Stock stock){
        return stockService.saveStock(stock);
    }

    @GetMapping("/stocks")
    public List<Stock> findAllStocks() {
        return stockService.getStocks();
    }

    @GetMapping("/stockById/{id}")
    public Stock findStockById(@PathVariable Long id) {
        return stockService.getStockById(id);
    }


    @PutMapping("/stock/update")
    public Stock updateStock(@RequestBody Stock stock){
        return stockService.updateStock(stock);
    }

    @DeleteMapping("/stock/delete/{id}")
    public void deleteStock(@PathVariable Long id) {
        stockService.deleteStock(id);
    }

}
