package edu.cit.dinapo.alexandreinash.campusequipmentloan;
import edu.cit.dinapo.alexandreinash.campusequipmentloan.entity.Equipment;
import edu.cit.dinapo.alexandreinash.campusequipmentloan.entity.Student;
import edu.cit.dinapo.alexandreinash.campusequipmentloan.repository.EquipmentRepository;
import edu.cit.dinapo.alexandreinash.campusequipmentloan.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
@Component
public class DataLoader implements CommandLineRunner {
    private final EquipmentRepository equipmentRepo;
    private final StudentRepository studentRepo;
    public DataLoader(EquipmentRepository equipmentRepo, StudentRepository studentRepo) {
        this.equipmentRepo = equipmentRepo;
        this.studentRepo = studentRepo;
    }
    @Override
    public void run(String... args) {
        // Preload some equipment
        equipmentRepo.save(new Equipment(null, "Laptop Dell", "Laptop", "SN12345", true));
        equipmentRepo.save(new Equipment(null, "Projector Epson", "Projector", "SN54321", true));
        equipmentRepo.save(new Equipment(null, "Microscope", "Lab", "SN77777", true));
        // Preload some students
        studentRepo.save(new Student(null, "2025-001", "Alice Santos", "alice@example.com"));
        studentRepo.save(new Student(null, "2025-002", "Bob Cruz", "bob@example.com"));
    }
}
