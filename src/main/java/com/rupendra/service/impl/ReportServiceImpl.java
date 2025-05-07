package com.rupendra.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rupendra.model.Loan;
import com.rupendra.repository.LoanRepository;
import com.rupendra.service.ReportService;

@Service
public class ReportServiceImpl implements ReportService {
	@Autowired
	private LoanRepository loanRepo;

	public List<Loan> getLoanHistory(Long customerId) {
		return loanRepo.findByCustomerId(customerId);
	}

	public List<Loan> getPendingLoans() {
		return loanRepo.findByStatus("PENDING");
	}
	
	public List<Loan> getLoanByStatus(String status) {
		return loanRepo.findByStatus(status.toUpperCase());
	}
}