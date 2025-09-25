package edu.cit.dinapo.alexandreinash.campusequipmentloan.repository;
import edu.cit.dinapo.alexandreinash.campusequipmentloan.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
public interface StudentRepository extends JpaRepository<Student, Long> {
}