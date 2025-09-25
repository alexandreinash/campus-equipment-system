package edu.cit.dinapo.alexandreinash.campusequipmentloan.web;

import edu.cit.dinapo.alexandreinash.campusequipmentloan.dto.EquipmentDTO;
import edu.cit.dinapo.alexandreinash.campusequipmentloan.service.LoanService; // ✅ add this import
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/equipment")
public class EquipmentController {

    private final LoanService loanService;

    public EquipmentController(LoanService loanService) {
        this.loanService = loanService;
    }

    @GetMapping("/available")
    public List<EquipmentDTO> listAvailable() {
        return loanService.listAvailableEquipment();
    }
}
