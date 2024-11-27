package com.pratik.accounts.repository;

import com.pratik.accounts.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    //we are able to interect with my database by spring data jpa
    Optional<Customer> findByMobileNumber(String mobileNumber);

}
