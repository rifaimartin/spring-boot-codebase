package com.cashlez.demo.repo;

import com.cashlez.demo.dto.MerchantStatus;
import com.cashlez.demo.model.Category;
import com.cashlez.demo.model.Merchant;
import com.cashlez.demo.model.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, Long> {
    Optional<Category> findById(long id);

}
