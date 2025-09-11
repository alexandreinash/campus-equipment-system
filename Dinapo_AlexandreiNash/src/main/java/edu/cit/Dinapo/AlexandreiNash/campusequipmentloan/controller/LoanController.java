package edu.cit.Dinapo.AlexandreiNash.campusequipmentloan.controller;

import edu.cit.Dinapo.AlexandreiNash.campusequipmentloan.model.Equipment;
import edu.cit.Dinapo.AlexandreiNash.campusequipmentloan.model.Loan;
import edu.cit.Dinapo.AlexandreiNash.campusequipmentloan.service.LoanService;
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
        Loan loan = loanService.createLoan(studentId, equipmentId);
        return ResponseEntity.ok(loan);
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