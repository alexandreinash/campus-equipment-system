package edu.cit.Dinapo.AlexandreiNash.campusequipmentloan.service;

import edu.cit.Azcona.Jeffer.campusequipmentloan.model.Equipment;
import edu.cit.Azcona.Jeffer.campusequipmentloan.repository.EquipmentRepository;
import org.springframework.stereotype.Service;

@Service
public class EquipmentService {
    private final EquipmentRepository equipmentRepository;

    public EquipmentService(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }

    public Equipment createEquipment(String name) {
        Equipment equipment = new Equipment();
        equipment.setName(name);
        equipment.setAvailability(true);
        return equipmentRepository.save(equipment);
    }
}