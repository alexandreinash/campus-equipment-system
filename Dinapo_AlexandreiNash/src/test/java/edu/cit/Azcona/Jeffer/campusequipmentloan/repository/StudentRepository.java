package edu.cit.Azcona.Jeffer.campusequipmentloan.repository;


import edu.cit.Azcona.Jeffer.campusequipmentloan.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {}