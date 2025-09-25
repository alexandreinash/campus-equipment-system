package edu.cit.dinapo.alexandreinash.campusequipmentloan.dto;
import jakarta.validation.constraints.NotNull;
public class CreateLoanRequest {
    @NotNull
    private Long equipmentId;
    @NotNull
    private Long studentId;

    public Long getEquipmentId() { return equipmentId; }
    public void setEquipmentId(Long equipmentId) { this.equipmentId = equipmentId; }

    public Long getStudentId() { return studentId; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }
}