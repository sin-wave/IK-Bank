package com.pratik.accounts.repository;

import com.pratik.accounts.entity.Accounts;
import com.pratik.accounts.entity.Customer;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.Optional;

public interface AccountsRepository extends JpaRepository<Accounts, Long> {
    Optional<Accounts> findByCustomerId(long customerId);
    @Transactional
    @Modifying
    void deleteByCustomerId(long customerId);
}
