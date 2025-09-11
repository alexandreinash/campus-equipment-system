package edu.cit.Dinapo.AlexandreiNash.campusequipmentloan.repository;


import edu.cit.Dinapo.AlexandreiNash.campusequipmentloan.model.Loan;
import edu.cit.Dinapo.AlexandreiNash.campusequipmentloan.model.LoanStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan, Long> {
    long countByStudentIdAndStatus(Long studentId, LoanStatus status);
}