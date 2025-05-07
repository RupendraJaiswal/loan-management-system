package com.rupendra.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rupendra.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {}
