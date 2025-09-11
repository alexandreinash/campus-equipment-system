package edu.cit.Azcona.Jeffer.campusequipmentloan.repository;

import edu.cit.Azcona.Jeffer.campusequipmentloan.model.Loan;
import edu.cit.Azcona.Jeffer.campusequipmentloan.model.LoanStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan, Long> {
    long countByStudentIdAndStatus(Long studentId, LoanStatus status);
}