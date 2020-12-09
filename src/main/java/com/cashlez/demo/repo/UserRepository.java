package com.cashlez.demo.repo;

import com.cashlez.demo.dto.UserStatus;
import com.cashlez.demo.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findById(long id);

    Page<User> findAll(Pageable pageable);
    Optional<User> findByIdAndStatus(long id, UserStatus userStatus);
}
