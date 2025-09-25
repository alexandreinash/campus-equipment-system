package edu.cit.dinapo.alexandreinash.campusequipmentloan.repository;
import edu.cit.dinapo.alexandreinash.campusequipmentloan.entity.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
    List<Equipment> findByAvailableTrue();
}