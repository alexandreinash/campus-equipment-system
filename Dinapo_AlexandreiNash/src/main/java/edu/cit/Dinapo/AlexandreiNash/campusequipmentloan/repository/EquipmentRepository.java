package edu.cit.Dinapo.AlexandreiNash.campusequipmentloan.repository;

import edu.cit.Dinapo.AlexandreiNash.campusequipmentloan.model.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
    List<Equipment> findByAvailabilityTrue();
}