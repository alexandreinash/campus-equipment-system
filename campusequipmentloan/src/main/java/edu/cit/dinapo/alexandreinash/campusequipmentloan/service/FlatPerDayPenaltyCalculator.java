package edu.cit.dinapo.alexandreinash.campusequipmentloan.service;
import org.springframework.stereotype.Component;
import java.time.temporal.ChronoUnit;
import java.time.LocalDate;
@Component
public class FlatPerDayPenaltyCalculator implements PenaltyCalculator {
    private static final double PENALTY_PER_DAY = 50.0;
    @Override
    public double calculate(LocalDate dueDate, LocalDate returnDate) {
        if (returnDate == null || !returnDate.isAfter(dueDate)) {
            return 0;
        }
        long daysLate = ChronoUnit.DAYS.between(dueDate, returnDate);
        return daysLate * PENALTY_PER_DAY;
    }
}