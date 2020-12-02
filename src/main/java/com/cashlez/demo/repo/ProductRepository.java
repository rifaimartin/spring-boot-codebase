package com.cashlez.demo.repo;

import com.cashlez.demo.model.Category;
import com.cashlez.demo.model.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends CrudRepository<Product, Long> {

    List<Product> findAllByCategory(Category category);
}
