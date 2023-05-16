package ru.skypro.lessons.springboot.hw_springboot;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "employee")
@Data
@RequiredArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "salary")
    private Integer salary;

    @Override
    public String toString() {
        return "Employee { " +
                "Name='" + name + '\'' +
                ", salary='" + salary +
                '}';
    }
}