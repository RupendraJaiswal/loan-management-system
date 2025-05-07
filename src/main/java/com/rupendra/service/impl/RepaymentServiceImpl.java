package com.rupendra.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.rupendra.exception.ResourceNotFoundException;
import com.rupendra.model.Loan;
import com.rupendra.model.Repayment;
import com.rupendra.repository.LoanRepository;
import com.rupendra.repository.RepaymentRepository;
import com.rupendra.service.EmailService;
import com.rupendra.service.RepaymentService;

@Service
public class RepaymentServiceImpl implements RepaymentService {

	@Autowired
	private RepaymentRepository repaymentRepo;
	@Autowired
	private LoanRepository loanRepo;
	@Autowired
	private EmailService emailService;

	@Value("${rupendra.mail.enable}")
	private boolean emailEnable;

	@Override
	public Repayment makeRepayment(Repayment repayment) {
		Long loanId = repayment.getLoan().getId();
		Loan loan = loanRepo.findById(loanId).orElseThrow(() -> new ResourceNotFoundException("Loan not found"));

		repayment.setLoan(loan);
		repayment.setPaymentDate(LocalDate.now());
		repayment.setPayableAmount(loan.getEmi());
		if (loan.getLastPayementDate() != null
				&& loan.getLastPayementDate().plusMonths(loan.getEmiDiffernceMonth()).isBefore(LocalDate.now())
				&& loan.getRemainingPayable().compareTo(BigDecimal.ZERO) > 0) {

			repayment.setStatus("OVERDUE");

		} else {
			repayment.setStatus("ON_TIME");
		}

		repayment.setPaymentStatus("SUCCESS");
		repayment = repaymentRepo.save(repayment);

		BigDecimal totalPaid = repaymentRepo.findByLoanId(loanId).stream().map(Repayment::getAmountPaid)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
		loan.setPaid(totalPaid);
		loan.setLastPayementDate(repayment.getPaymentDate());
		loan.setRemainingPayable(loan.getTotalPayable().subtract(totalPaid));
		if (totalPaid.compareTo(loan.getTotalPayable()) >= 0) {
			loan.setStatus("REPAID");
			if (emailEnable) {
				// Send email notification for full repayment
				String subject = "Loan Fully Repaid";
				String text = "Dear " + loan.getCustomer().getName() + ",\n\n" + "Congratulations! Your loan of "
						+ loan.getAmount() + " has been fully repaid.\n\n" + "Thank you for using our service.";
				emailService.sendEmail(loan.getCustomer().getEmail(), subject, text);
			}
		}
		loanRepo.save(loan);
		if (emailEnable) {
			// Send email notification for full repayment
			String subject = "Loan Partially paid";
			String text = "Dear " + loan.getCustomer().getName() + ",\n\n" + "Congratulations! Your loan of "
					+ loan.getAmount() + " has been Partially paid.Your paid amount is " + repayment.getAmountPaid()
					+ "\n\n" + "Thank you for using our service.";
			emailService.sendEmail(loan.getCustomer().getEmail(), subject, text);
		}

		return repayment;
	}
}
