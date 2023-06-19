package ru.skypro.lessons.springboot.hw_springboot.employee;
import jakarta.persistence.*;
import ru.skypro.lessons.springboot.hw_springboot.position.Position;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer salary;

    @OneToMany
    @JoinColumn(name = "position_id")
    private Position position;


    public Employee() {
    }

    public Employee(Long id, String name, int salary, Position position) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.position = position;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Position getPosition(){
        return position;
    }
    public void setPosition(Position position){
        this.position = position;
    }

    @Override
    public String toString() {
        return "Employee { " +
                "Name='" + name + '\'' +
                ", salary='" + salary +
                '}';
    }
}