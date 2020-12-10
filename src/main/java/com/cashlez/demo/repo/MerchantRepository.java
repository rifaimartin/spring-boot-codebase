package com.cashlez.demo.repo;

import com.cashlez.demo.dto.MerchantStatus;
import com.cashlez.demo.model.Merchant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface MerchantRepository extends PagingAndSortingRepository<Merchant, Long> {
    Optional<Merchant> findByIdAndStatus(long id, MerchantStatus merchantStatus);
    Optional<Merchant> findByUserName(String userName);

    @Query("SELECT p FROM Merchant p WHERE p.userName LIKE %?1%")
    Page<Merchant> findAll(Pageable pageable, String keyword);  
}
