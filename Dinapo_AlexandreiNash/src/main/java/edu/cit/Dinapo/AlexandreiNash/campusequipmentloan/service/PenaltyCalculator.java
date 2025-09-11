package edu.cit.Dinapo.AlexandreiNash.campusequipmentloan.service;

import edu.cit.Azcona.Jeffer.campusequipmentloan.model.Loan;
import java.math.BigDecimal;

public interface PenaltyCalculator {
    BigDecimal calculatePenalty(Loan loan);
}