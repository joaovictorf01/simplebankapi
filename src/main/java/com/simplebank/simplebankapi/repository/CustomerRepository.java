package com.simplebank.simplebankapi.repository;

import com.simplebank.simplebankapi.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
