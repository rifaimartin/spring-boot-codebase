package com.cashlez.demo.repo;

import com.cashlez.demo.dto.MerchantStatus;
import com.cashlez.demo.model.Merchant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface MerchantRepository extends PagingAndSortingRepository<Merchant, Long> {
    Optional<Merchant> findByIdAndStatus(long id, MerchantStatus merchantStatus);
    Optional<Merchant> findByUserName(String userName);

    Page<Merchant> findAll(Pageable pageable);

}
