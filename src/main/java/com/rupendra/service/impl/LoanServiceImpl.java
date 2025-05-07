package com.rupendra.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.rupendra.bean.LoanRequest;
import com.rupendra.exception.ResourceNotFoundException;
import com.rupendra.model.Customer;
import com.rupendra.model.Loan;
import com.rupendra.repository.CustomerRepository;
import com.rupendra.repository.LoanRepository;
import com.rupendra.service.EmailService;
import com.rupendra.service.LoanService;

@Service
public class LoanServiceImpl implements LoanService {
	@Autowired
	private LoanRepository loanRepo;
	@Autowired
	private CustomerRepository customerRepo;
	@Autowired
	private EmailService emailService;

	@Value("${rupendra.mail.enable}")
	private boolean emailEnable;

	public Loan applyLoan(Loan loan) {
		Customer customer = customerRepo.findById(loan.getCustomer().getId())
				.orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
		loan.setCustomer(customer);
		loan.setStatus("PENDING");
		BigDecimal amount = loan.getAmount();
		BigDecimal interestRate = BigDecimal.valueOf(loan.getInterestRate());
		BigDecimal durationMonths = BigDecimal.valueOf(loan.getDurationMonths());

		BigDecimal total = amount.add(amount.multiply(interestRate).multiply(durationMonths)
				.divide(BigDecimal.valueOf(1200), 2, RoundingMode.HALF_UP));

		loan.setTotalPayable(total);

		// Calculate monthly payable
		BigDecimal monthlyPayable = total.divide(durationMonths, 2, RoundingMode.HALF_UP);
		loan.setEmi(monthlyPayable);
		loan.setEmiDiffernceMonth(1);

		return loanRepo.save(loan);
	}

	public Loan updateStatus(Long id, String status) {
		Loan loan = loanRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Loan not found"));
		loan.setStatus(status);
		if ("APPROVED".equals(status)) {
			loan.setStartDate(LocalDate.now());
		}
		Loan loandb = loanRepo.save(loan);
		if (emailEnable) {

			String subject = "Loan Status Update";
			String text = "Dear " + loan.getCustomer().getName() + ",\n\n" + "Your loan status has been updated to: "
					+ status + ".\n\n" + "Thank you for using our service.";
			emailService.sendEmail(loan.getCustomer().getEmail(), subject, text);
		}
		return loandb;
	}

	public Loan getLoan(Long id) {
		return loanRepo.findById(id).orElseThrow();
	}

	public Loan applyLoan(LoanRequest dto) {
		Customer customer = customerRepo.findById(dto.getCustomerId()).orElseThrow();
		Loan loan = new Loan();
		loan.setAmount(dto.getAmount());
		loan.setInterestRate(dto.getInterestRate());
		loan.setDurationMonths(dto.getDurationMonths());
		loan.setPurpose(dto.getPurpose());
		loan.setCustomer(customer);
		loan.setStatus("PENDING");
		BigDecimal total = dto.getAmount().add(dto.getAmount().multiply(BigDecimal.valueOf(dto.getInterestRate()))
				.multiply(BigDecimal.valueOf(dto.getDurationMonths())).divide(BigDecimal.valueOf(1200)));
		loan.setTotalPayable(total);
		// Calculate monthly payable
		BigDecimal monthlyPayable = total.divide(BigDecimal.valueOf(dto.getDurationMonths()), 2, RoundingMode.HALF_UP);
		loan.setEmi(monthlyPayable);
		loan.setEmiDiffernceMonth(1);
		Loan loandb = loanRepo.save(loan);
		// Send email notification
		if (emailEnable) {
			String subject = "Loan Application Status";
			String text = "Dear " + customer.getName() + ",\n\n" + "Your loan application for " + loan.getAmount()
					+ " is now in 'PENDING' status. We will notify you once it is processed.\n\n"
					+ "Thank you for using our service.";
			emailService.sendEmail(customer.getEmail(), subject, text);
		}
		return loandb;
	}

	@Override
	public List<Loan> getLoansByStatus(String status) {

		return loanRepo.findByStatus(status.toUpperCase());
	}

}