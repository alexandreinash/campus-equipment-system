package edu.cit.Dinapo.AlexandreiNash.campusequipmentloan.controller;

import edu.cit.Dinapo.AlexandreiNash.campusequipmentloan.DTO.EquipmentDTO;
import edu.cit.Dinapo.AlexandreiNash.campusequipmentloan.model.Equipment;
import edu.cit.Dinapo.AlexandreiNash.campusequipmentloan.service.EquipmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/equipment")
public class EquipmentController {

    private final EquipmentService equipmentService;

    public EquipmentController(EquipmentService equipmentService) {
        this.equipmentService = equipmentService;
    }

    @PostMapping(value = "/equipment", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Equipment> createEquipment(@RequestBody EquipmentDTO equipmentDto) {
        Equipment equipment = equipmentService.createEquipment(equipmentDto.getName());
        return ResponseEntity.status(HttpStatus.CREATED).body(equipment);
    }
}