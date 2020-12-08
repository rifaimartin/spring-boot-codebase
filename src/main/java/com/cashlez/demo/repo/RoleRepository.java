package com.cashlez.demo.repo;

import com.cashlez.demo.model.Merchant;
import com.cashlez.demo.model.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role, Long> {
    Optional<Role> findById(long id);

    Page<Role> findAll(Pageable pageable);


}
