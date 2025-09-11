package edu.cit.Azcona.Jeffer.campusequipmentloan;

import edu.cit.Azcona.Jeffer.campusequipmentloan.model.Equipment;
import edu.cit.Azcona.Jeffer.campusequipmentloan.model.Loan;
import edu.cit.Azcona.Jeffer.campusequipmentloan.service.LoanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping("/loans")
    public ResponseEntity<Loan> createLoan(@RequestParam Long studentId, @RequestParam Long equipmentId) {
        return ResponseEntity.ok(loanService.createLoan(studentId, equipmentId));
    }

    @PostMapping("/loans/{id}/return")
    public ResponseEntity<Map<String, Object>> returnLoan(@PathVariable Long id) {
        BigDecimal penalty = loanService.returnLoan(id);
        Map<String, Object> response = new HashMap<>();
        response.put("penalty", penalty);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/equipment/available")
    public List<Equipment> listAvailableEquipment() {
        return loanService.getAvailableEquipment();
    }
}