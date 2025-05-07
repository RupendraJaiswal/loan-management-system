package com.rupendra.service;

import java.util.List;

import com.rupendra.model.Loan;

public interface ReportService {
    List<Loan> getLoanHistory(Long customerId);
    List<Loan> getPendingLoans();
}