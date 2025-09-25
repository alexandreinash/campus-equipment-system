package edu.cit.dinapo.alexandreinash.campusequipmentloan.service;
import java.time.LocalDate;
public interface PenaltyCalculator {
    double calculate(LocalDate dueDate, LocalDate returnDate);
}