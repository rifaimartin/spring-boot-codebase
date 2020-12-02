package com.cashlez.demo.service;

import com.cashlez.demo.model.Category;
import com.cashlez.demo.model.Product;
import com.cashlez.demo.repo.CategoryRepository;
import com.cashlez.demo.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<Product> getAllProductByCategory (long id){

        Optional<Category> categoryOptional = categoryRepository.findById(id);

        List<Product> products = new ArrayList<>();
        if (categoryOptional.isPresent()){
            Category category = categoryOptional.get();
            products = productRepository.findAllByCategory(category);
        }
        return products;
    }
}
