package ru.skypro.lessons.springboot.hw_springboot;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "position")
public class Position{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "position_name")
    String positionName;
    @OneToMany(targetEntity = Employee.class)
    public List <Employee> employeeList;
}
