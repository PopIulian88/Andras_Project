package com.beautySalon.service;

import com.beautySalon.entity.Stock;
import com.beautySalon.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockService {
    @Autowired
    private StockRepository stockRepository;


    public Stock saveStock(Stock stock) {
        return stockRepository.save(stock);
    }



    public List<Stock> getStocks() {
        return stockRepository.findAll();
    }


    public Stock getStockById(Long id) {
        return stockRepository.findById(id).orElse(null);
    }


    public void deleteStock(Long id) {

//        Stock myStock = getStockById(id);
//        if (myStock.getId() != null) {
//            List<Product> productList = productService.findAllProductsByStock(myStock);
//
//            for (Product product : productList) {
//                productService.deleteProduct(product.getId());
//            }
//        }

        stockRepository.deleteById(id);
    }


    public Stock updateStock(Stock stock) {
        Stock existingStock = stockRepository.findById(stock.getId()).orElse(null);
        existingStock.setName(stock.getName());
        existingStock.setQuantity(stock.getQuantity());
        existingStock.setPrice(stock.getPrice());

        return stockRepository.save(existingStock);
    }
}
