package edu.cit.Azcona.Jeffer.campusequipmentloan.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Student {
    @Id @GeneratedValue
    private Long id;

    @NotBlank
    private String studentNo;

    @NotBlank
    private String name;

    @Email
    private String email;

    public void setId(Long id) {
        this.id = id;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getStudentNo() {
        return studentNo;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
