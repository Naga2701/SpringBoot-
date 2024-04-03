package project.stockzz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.stockzz.model.Stock;
@Repository
public interface StockRepo extends JpaRepository<Stock, Integer> {    
}