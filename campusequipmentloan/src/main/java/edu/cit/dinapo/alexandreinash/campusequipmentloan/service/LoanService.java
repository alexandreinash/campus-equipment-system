package edu.cit.dinapo.alexandreinash.campusequipmentloan.service;

import edu.cit.dinapo.alexandreinash.campusequipmentloan.dto.CreateLoanRequest;
import edu.cit.dinapo.alexandreinash.campusequipmentloan.dto.EquipmentDTO;
import edu.cit.dinapo.alexandreinash.campusequipmentloan.dto.LoanResponseDTO;
import edu.cit.dinapo.alexandreinash.campusequipmentloan.dto.ReturnLoanResponseDTO;
import edu.cit.dinapo.alexandreinash.campusequipmentloan.entity.Equipment;
import edu.cit.dinapo.alexandreinash.campusequipmentloan.entity.Loan;
import edu.cit.dinapo.alexandreinash.campusequipmentloan.entity.LoanStatus;
import edu.cit.dinapo.alexandreinash.campusequipmentloan.entity.Student;
import edu.cit.dinapo.alexandreinash.campusequipmentloan.exception.BusinessException;
import edu.cit.dinapo.alexandreinash.campusequipmentloan.exception.ResourceNotFoundException;
import edu.cit.dinapo.alexandreinash.campusequipmentloan.repository.EquipmentRepository;
import edu.cit.dinapo.alexandreinash.campusequipmentloan.repository.LoanRepository;
import edu.cit.dinapo.alexandreinash.campusequipmentloan.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoanService {

    private final LoanRepository loanRepo;
    private final EquipmentRepository equipmentRepo;
    private final StudentRepository studentRepo;
    private final PenaltyCalculator penaltyCalculator;

    public LoanService(LoanRepository loanRepo,
                       EquipmentRepository equipmentRepo,
                       StudentRepository studentRepo,
                       PenaltyCalculator penaltyCalculator) {
        this.loanRepo = loanRepo;
        this.equipmentRepo = equipmentRepo;
        this.studentRepo = studentRepo;
        this.penaltyCalculator = penaltyCalculator;
    }

    @Transactional
    public LoanResponseDTO createLoan(CreateLoanRequest request) {
        Equipment equipment = equipmentRepo.findById(request.getEquipmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Equipment not found"));

        if (!equipment.isAvailable()) {
            throw new BusinessException("Equipment is not available");
        }

        Student student = studentRepo.findById(request.getStudentId())
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));

        List<Loan> activeLoans = loanRepo.findByStudentIdAndStatus(student.getId(), LoanStatus.ACTIVE);
        if (activeLoans.size() >= 2) {
            throw new BusinessException("Max 2 active loans allowed per student");
        }

        Loan loan = new Loan();
        loan.setEquipment(equipment);
        loan.setStudent(student);
        loan.setStartDate(LocalDate.now());
        loan.setDueDate(LocalDate.now().plusDays(7));
        loan.setStatus(LoanStatus.ACTIVE);
        loan.setPenalty(0.0);

        equipment.setAvailable(false);
        equipmentRepo.save(equipment);

        Loan saved = loanRepo.save(loan);
        return toDto(saved);
    }

    /**
     * Return a loan and calculate penalty if overdue.
     */
    @Transactional
    public ReturnLoanResponseDTO returnLoan(Long loanId) {
        Loan loan = loanRepo.findById(loanId)
                .orElseThrow(() -> new ResourceNotFoundException("Loan not found"));

        if (loan.getStatus() != LoanStatus.ACTIVE) {
            throw new BusinessException("Loan already returned or closed");
        }

        loan.setReturnDate(LocalDate.now());

        if (loan.getReturnDate().isAfter(loan.getDueDate())) {
            double penalty = penaltyCalculator.calculate(loan.getDueDate(), loan.getReturnDate());
            loan.setPenalty(penalty);
            loan.setStatus(LoanStatus.OVERDUE);
        } else {
            loan.setPenalty(0.0);
            loan.setStatus(LoanStatus.RETURNED);
        }

        loan.getEquipment().setAvailable(true);
        equipmentRepo.save(loan.getEquipment());
        loanRepo.save(loan);

        ReturnLoanResponseDTO response = new ReturnLoanResponseDTO();
        response.setLoanId(loan.getId());
        response.setPenalty(loan.getPenalty());
        response.setStatus(loan.getStatus().name());
        return response;
    }

    /**
     * List all available equipment.
     */
    public List<EquipmentDTO> listAvailableEquipment() {
        return equipmentRepo.findByAvailableTrue().stream()
                .map(eq -> {
                    EquipmentDTO dto = new EquipmentDTO();
                    dto.setId(eq.getId());
                    dto.setName(eq.getName());
                    dto.setType(eq.getType());
                    dto.setSerialNumber(eq.getSerialNumber());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    /**
     * Helper: map Loan -> LoanResponseDTO
     */
    private LoanResponseDTO toDto(Loan loan) {
        LoanResponseDTO dto = new LoanResponseDTO();
        dto.setId(loan.getId());
        dto.setEquipmentName(loan.getEquipment().getName());
        dto.setStudentName(loan.getStudent().getName());
        dto.setStartDate(loan.getStartDate());
        dto.setDueDate(loan.getDueDate());
        dto.setReturnDate(loan.getReturnDate());
        dto.setStatus(loan.getStatus().name());
        dto.setPenalty(loan.getPenalty());
        return dto;
    }
}
