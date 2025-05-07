package com.rupendra.service;

import java.util.List;

import com.rupendra.bean.LoanRequest;
import com.rupendra.model.Loan;

public interface LoanService {
    Loan applyLoan(Loan loanRequest);
    Loan updateStatus(Long loanId, String status);
    Loan getLoan(Long loanId);
    Loan applyLoan(LoanRequest loanRequest);
    List<Loan> getLoansByStatus(String status);

}
