package edu.cit.dinapo.alexandreinash.campusequipmentloan.repository;
import edu.cit.dinapo.alexandreinash.campusequipmentloan.entity.Loan;
import edu.cit.dinapo.alexandreinash.campusequipmentloan.entity.LoanStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface LoanRepository extends JpaRepository<Loan, Long> {
    List<Loan> findByStudentIdAndStatus(Long studentId, LoanStatus status);
}