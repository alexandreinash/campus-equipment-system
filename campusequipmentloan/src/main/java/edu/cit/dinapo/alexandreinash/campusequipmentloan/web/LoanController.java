package edu.cit.dinapo.alexandreinash.campusequipmentloan.web;
import edu.cit.dinapo.alexandreinash.campusequipmentloan.service.LoanService;
import edu.cit.dinapo.alexandreinash.campusequipmentloan.dto.CreateLoanRequest;
import edu.cit.dinapo.alexandreinash.campusequipmentloan.dto.LoanResponseDTO;
import edu.cit.dinapo.alexandreinash.campusequipmentloan.dto.ReturnLoanResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/loans")
public class LoanController {
    private final LoanService loanService;
    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }
    @PostMapping
    public ResponseEntity<LoanResponseDTO> createLoan(@Valid @RequestBody CreateLoanRequest request) {
        return ResponseEntity.ok(loanService.createLoan(request));
    }
    @PostMapping("/{id}/return")
    public ResponseEntity<ReturnLoanResponseDTO> returnLoan(@PathVariable Long id) {
        return ResponseEntity.ok(loanService.returnLoan(id));
    }
}