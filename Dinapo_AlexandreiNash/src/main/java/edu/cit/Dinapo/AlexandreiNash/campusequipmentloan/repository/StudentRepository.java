package edu.cit.Dinapo.AlexandreiNash.campusequipmentloan.repository;

import edu.cit.Dinapo.AlexandreiNash.campusequipmentloan.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {}