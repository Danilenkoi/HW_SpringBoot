package ru.skypro.lessons.springboot.hw_springboot;

import lombok.SneakyThrows;

import java.util.List;

public interface EmployeeService {

    void addEmployee(Employee... employees);
    void updateEmployee(long id, Employee employee);

    @SneakyThrows
    void updateEmployee(Long id, Employee employee);

    Employee getEmployeeById(long id);
    void deleteEmployee(long id);
    List<Employee> getEmployeesHigherSalary (int salary);

    List<Employee> maxSalary();

    List<Employee> minSalary();

    List<Employee> highSalary();

    Integer sumOfSalary();
}
