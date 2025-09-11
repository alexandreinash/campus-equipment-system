package edu.cit.Azcona.Jeffer.campusequipmentloan.service;


import edu.cit.Azcona.Jeffer.campusequipmentloan.model.Loan;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class FixedPenaltyCalculator implements PenaltyCalculator {
    private static final BigDecimal DAILY_PENALTY = new BigDecimal("50");

    @Override
    public BigDecimal calculatePenalty(Loan loan) {
        return DAILY_PENALTY.multiply(BigDecimal.valueOf(loan.getLateDays()));
    }
}