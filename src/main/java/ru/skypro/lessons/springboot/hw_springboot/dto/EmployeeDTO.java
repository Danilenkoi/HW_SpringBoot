package ru.skypro.lessons.springboot.hw_springboot.dto;
import ru.skypro.lessons.springboot.hw_springboot.employee.Employee;

public class EmployeeDTO {

    private Long id;
    private String name;
    private Integer salary;
    private Long positionId;

    public static EmployeeDTO fromEmployee(Employee employee){
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employee.setId(employee.getId());
        employee.setName(employee.getName());
        employee.setSalary(employee.getSalary());
        employee.setPosition(employee.getPositionId());
        return employeeDTO;
    }

    public Employee toEmployee() {
        Employee employee = new Employee();
        employee.setId(this.getId());
        employee.setName(this.getName());
        employee.setSalary(this.getSalary());
        employee.setPosition(this.positionId());
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

    public Long getPositionId(){
        return positionId;
    }

    public void setPositionId() {
        this.positionId = positionId;
    }
}
