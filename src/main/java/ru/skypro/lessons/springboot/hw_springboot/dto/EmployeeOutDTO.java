package ru.skypro.lessons.springboot.hw_springboot.dto;

import lombok.Data;
import lombok.experimental.Accessors;
import ru.skypro.lessons.springboot.hw_springboot.Employee;
import ru.skypro.lessons.springboot.hw_springboot.Position;

@Data
@Accessors(chain = true)
public class EmployeeOutDTO {
    private Long id;
    private String name;
    private Integer salary;
    private PositionDTO positionDTO;

    public static EmployeeOutDTO fromEmployee(Employee employee) {
        return new EmployeeOutDTO()
                .setId(employee.getId())
                .setName(employee.getName())
                .setSalary(employee.getSalary())
                .setPositionDTO(PositionDTO.fromPosition(employee.getPosition()));
    }

    public Employee toEmployee() {
        Employee employee = new Employee();
        employee.setId(this.getId());
        employee.setName(this.getName());
        employee.setSalary(this.salary);
        employee.setPosition(this.positionDTO.toPosition());
        return employee;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

}
