package com.rupendra.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rupendra.model.Repayment;

public interface RepaymentRepository extends JpaRepository<Repayment, Long> {
    List<Repayment> findByLoanId(Long loanId);
}

