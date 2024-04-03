package project.stockzz.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.stockzz.model.Stock;
import project.stockzz.repository.StockRepo;


@Service
public class StockService {
    @Autowired
    private StockRepo stockRepo;

    public Stock createStock(Stock stock){
        return stockRepo.save(stock);
    }

    public List<Stock> getAllStocks(){
        return stockRepo.findAll();
    }

    public Optional<Stock> getStockById(Integer stockId){
        return stockRepo.findById(stockId);
    }

    public Stock updateStock(Stock stock){
        return stockRepo.save(stock);
    }

    public void deleteStockById(Integer stockId) {
        stockRepo.deleteById(stockId);
    }
    
}
