package edu.cit.dinapo.alexandreinash.campusequipmentloan.dto;
import java.time.LocalDate;
public class LoanResponseDTO {
    private Long id;
    private String equipmentName;
    private String studentName;
    private LocalDate startDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private String status;
    private double penalty;
    // getters and setters
}