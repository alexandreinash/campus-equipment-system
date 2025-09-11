package edu.cit.Azcona.Jeffer.campusequipmentloan.repository;

import edu.cit.Azcona.Jeffer.campusequipmentloan.model.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
    List<Equipment> findByAvailabilityTrue();
}
