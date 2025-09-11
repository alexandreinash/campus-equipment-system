package edu.cit.Dinapo.AlexandreiNash.campusequipmentloan.controller;

import edu.cit.Dinapo.AlexandreiNash.campusequipmentloan.model.Student;
import edu.cit.Dinapo.AlexandreiNash.campusequipmentloan.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @PostMapping
    public Student addStudent(@RequestBody Student student) {
        return studentRepository.save(student);
    }
}