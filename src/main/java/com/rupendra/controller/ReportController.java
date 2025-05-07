package com.rupendra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rupendra.service.LoanService;
import com.rupendra.service.ReportService;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @Autowired
    private LoanService loanService;
    @GetMapping("/history/{customerId}")
    public ResponseEntity<?> getLoanHistory(@PathVariable Long customerId) {
        return ResponseEntity.ok(reportService.getLoanHistory(customerId));
    }

    @GetMapping("/pending")
    public ResponseEntity<?> getPendingLoans() {
        return ResponseEntity.ok(reportService.getPendingLoans());
    }
    @GetMapping("/status")
    public ResponseEntity<?> getLoansByStatus(@RequestParam(defaultValue = "PENDING") String status) {
        return ResponseEntity.ok(loanService.getLoansByStatus(status));
    }
}  