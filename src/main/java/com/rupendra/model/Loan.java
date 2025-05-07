package com.rupendra.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Loan {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private BigDecimal amount;
	private double interestRate;
	private int durationMonths;
	private String purpose;
	private String status;
    private BigDecimal totalPayable; // principal + interest
    private LocalDate startDate;        
	private BigDecimal emi;
    private LocalDate lastPayementDate;        
    private int emiDiffernceMonth;  
    private BigDecimal paid;
	private BigDecimal remainingPayable;// totalPayable - paid

	
	@ManyToOne
	@JsonBackReference("customer-loan")

	private Customer customer;

	@OneToMany(mappedBy = "loan")
	@JsonManagedReference("loan-repayment")

	private List<Repayment> repayments;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

	public int getDurationMonths() {
		return durationMonths;
	}

	public void setDurationMonths(int durationMonths) {
		this.durationMonths = durationMonths;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getTotalPayable() {
		return totalPayable;
	}

	public void setTotalPayable(BigDecimal totalPayable) {
		this.totalPayable = totalPayable;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<Repayment> getRepayments() {
		return repayments;
	}

	public void setRepayments(List<Repayment> repayments) {
		this.repayments = repayments;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public BigDecimal getEmi() {
		return emi;
	}

	public void setEmi(BigDecimal emi) {
		this.emi = emi;
	}

	public LocalDate getLastPayementDate() {
		return lastPayementDate;
	}

	public void setLastPayementDate(LocalDate lastPayementDate) {
		this.lastPayementDate = lastPayementDate;
	}

	public int getEmiDiffernceMonth() {
		return emiDiffernceMonth;
	}

	public void setEmiDiffernceMonth(int emiDiffernceMonth) {
		this.emiDiffernceMonth = emiDiffernceMonth;
	}

	public BigDecimal getPaid() {
		return paid;
	}

	public void setPaid(BigDecimal paid) {
		this.paid = paid;
	}

	public BigDecimal getRemainingPayable() {
		return remainingPayable;
	}

	public void setRemainingPayable(BigDecimal remainingPayable) {
		this.remainingPayable = remainingPayable;
	}

	

}