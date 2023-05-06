package ru.skypro.lessons.springboot.hw_springboot;
import jakarta.persistence.*;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "salary")
    private Integer salary;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private Employee employee;

    public Employee (String name, int salary){
        this.name = name;
        this.salary = salary;
    }

    public Employee(){
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(){
        this.name = name;
    }

    public Integer getSalary() {
        return salary;
    }
    public void setSalary(){
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee { " +
                "Name='" + name + '\'' +
                ", salary='" + salary +
                '}';
    }
}