package com.rupendra.scheduling;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.rupendra.model.Loan;
import com.rupendra.repository.LoanRepository;
import com.rupendra.service.EmailService;

@Component
public class OverdueLoanNotificationService {

	@Autowired
	private LoanRepository loanRepo;
	@Autowired
	private EmailService emailService;
	@Value("${rupendra.mail.enable}")
	private boolean emailEnable;

	@Scheduled(cron = "0 0 0 * * ?") // Runs daily at midnight
	public void checkOverdueLoansAndUpdate() {
		List<Loan> loans = loanRepo.findByStatus("APPROVED");
		for (Loan loan : loans) {
			if (loan.getLastPayementDate().plusMonths(loan.getEmiDiffernceMonth()).isBefore(LocalDate.now())
					&& loan.getRemainingPayable().compareTo(BigDecimal.ZERO) > 0) {
				loan.setStatus("OVERDUE");
				loanRepo.save(loan);
				if (emailEnable) {
					String subject = "Overdue Loan Reminder";

					String text = "Dear " + loan.getCustomer().getName() + ",\n\n"
							+ "This is a reminder that your loan of " + loan.getAmount()
							+ " for the current month is overdue. The overdue EMI amount is " + loan.getEmi()
							+ ". Please make your repayment as soon as possible.\n\n" + "Thank you.";

					emailService.sendEmail(loan.getCustomer().getEmail(), subject, text);
				}

			}
		}
	}
}
