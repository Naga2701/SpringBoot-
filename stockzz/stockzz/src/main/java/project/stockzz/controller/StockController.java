package project.stockzz.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import project.stockzz.model.Stock;
import project.stockzz.service.StockService;

@RestController
@RequestMapping("/api")
public class StockController {
    @Autowired
    private StockService stockService;
    
    @PostMapping("/stock")
    public ResponseEntity<Stock> createStock(@RequestBody Stock stock){
        Stock createdStock = stockService.createStock(stock);
        if (createdStock != null) {
            return new ResponseEntity<>(createdStock, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/stock")
    public ResponseEntity<List<Stock>> getAllStocks(){
        List<Stock> stocks = stockService.getAllStocks();
        if (!stocks.isEmpty()) {
            return new ResponseEntity<>(stocks, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/stock/{stockId}")
    public ResponseEntity<Stock> getStockById(@PathVariable int stockId){
        Optional<Stock> stock = stockService.getStockById(stockId);
        if (stock.isPresent()) {
            return new ResponseEntity<>(stock.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/stock/{stockId}") 
    public ResponseEntity<Stock> updateStock(@PathVariable int stockId, @RequestBody Stock updatedStock) {
        Optional<Stock> existingStockOptional = stockService.getStockById(stockId);
        
        if (existingStockOptional.isPresent()) {
            Stock existingStock = existingStockOptional.get();
           existingStock.setEmail(updatedStock.getEmail());
            existingStock.setName(updatedStock.getName());
             existingStock.setPrice(updatedStock.getPrice());
               
            Stock updatedStockEntity = stockService.updateStock(existingStock);
            
            return new ResponseEntity<>(updatedStockEntity, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/stock/{stockId}")
public ResponseEntity<Void> deleteStock(@PathVariable int stockId) {
    Optional<Stock> stock = stockService.getStockById(stockId);
    if (stock.isPresent()) {
        stockService.deleteStockById(stockId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

}
