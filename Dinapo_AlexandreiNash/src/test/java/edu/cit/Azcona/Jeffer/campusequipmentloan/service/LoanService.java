package edu.cit.Azcona.Jeffer.campusequipmentloan.service;

import edu.cit.Azcona.Jeffer.campusequipmentloan.model.Equipment;
import edu.cit.Azcona.Jeffer.campusequipmentloan.model.Loan;
import edu.cit.Azcona.Jeffer.campusequipmentloan.model.LoanStatus;
import edu.cit.Azcona.Jeffer.campusequipmentloan.model.Student;
import edu.cit.Azcona.Jeffer.campusequipmentloan.repository.EquipmentRepository;
import edu.cit.Azcona.Jeffer.campusequipmentloan.repository.LoanRepository;
import edu.cit.Azcona.Jeffer.campusequipmentloan.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class LoanService {

    private final LoanRepository loanRepo;
    private final StudentRepository studentRepo;
    private final EquipmentRepository equipmentRepo;
    private final PenaltyCalculator penaltyCalculator;

    public LoanService(LoanRepository loanRepo, StudentRepository studentRepo,
                       EquipmentRepository equipmentRepo, PenaltyCalculator penaltyCalculator) {
        this.loanRepo = loanRepo;
        this.studentRepo = studentRepo;
        this.equipmentRepo = equipmentRepo;
        this.penaltyCalculator = penaltyCalculator;
    }

    public Loan createLoan(Long studentId, Long equipmentId) {
        Student student = studentRepo.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        Equipment equipment = equipmentRepo.findById(equipmentId)
                .orElseThrow(() -> new RuntimeException("Equipment not found"));

        if (!equipment.isAvailability()) {
            throw new RuntimeException("Equipment not available");
        }

        long activeLoans = loanRepo.countByStudentIdAndStatus(studentId, LoanStatus.ACTIVE);
        if (activeLoans >= 2) {
            throw new RuntimeException("Max 2 active loans allowed");
        }

        equipment.setAvailability(false);
        equipmentRepo.save(equipment);

        Loan loan = new Loan();
        loan.setStudent(student);
        loan.setEquipment(equipment);
        loan.setStartDate(LocalDate.now());
        loan.setDueDate(LocalDate.now().plusDays(7));
        loan.setStatus(LoanStatus.ACTIVE);

        return loanRepo.save(loan);
    }

    public BigDecimal returnLoan(Long loanId) {
        Loan loan = loanRepo.findById(loanId)
                .orElseThrow(() -> new RuntimeException("Loan not found"));

        loan.setReturnDate(LocalDate.now());
        loan.setStatus(LoanStatus.RETURNED);

        Equipment equipment = loan.getEquipment();
        equipment.setAvailability(true);
        equipmentRepo.save(equipment);

        loanRepo.save(loan);
        return penaltyCalculator.calculatePenalty(loan);
    }

    public List<Equipment> getAvailableEquipment() {
        return equipmentRepo.findByAvailabilityTrue();
    }
}