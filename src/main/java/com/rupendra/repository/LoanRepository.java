package com.rupendra.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rupendra.model.Loan;

public interface LoanRepository extends JpaRepository<Loan, Long> {
    List<Loan> findByCustomerId(Long customerId);
    List<Loan> findByStatus(String status);
}