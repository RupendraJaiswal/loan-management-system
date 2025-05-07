package com.rupendra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rupendra.bean.LoanRequest;
import com.rupendra.service.LoanService;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @PostMapping("/apply")
    public ResponseEntity<?> applyLoan(@RequestBody LoanRequest loanRequest) {
        return ResponseEntity.ok(loanService.applyLoan(loanRequest));
    }

    @PutMapping("/{id}/approve")
    public ResponseEntity<?> approveLoan(@PathVariable Long id) {
        return ResponseEntity.ok(loanService.updateStatus(id, "APPROVED"));
    }

    @PutMapping("/{id}/reject")
    public ResponseEntity<?> rejectLoan(@PathVariable Long id) {
        return ResponseEntity.ok(loanService.updateStatus(id, "REJECTED"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getLoan(@PathVariable Long id) {
        return ResponseEntity.ok(loanService.getLoan(id));
    }
}