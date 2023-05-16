package ru.skypro.lessons.springboot.hw_springboot;

import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;
import java.sql.*;

@Service
public abstract class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void addEmployee(Employee[] employees) {
        employeeRepository.saveAll(Arrays.asList(employees));
    }

    @Override
    @SneakyThrows
    public void updateEmployee(Long id, Employee employee) {
        employeeRepository.findById(id).orElseThrow(SQLException::new);
        employee.setId(id);
        employeeRepository.save(employee);
    }

    @Override
    @SneakyThrows
    public Employee getEmployeeById(long id) {
        return employeeRepository.findById(id).orElseThrow(SQLException::new);
    }

    @Override
    public void deleteEmployee(long id) {
        employeeRepository.deleteById(id);
    }


    @Override
    public List<Employee> getEmployeesHigherSalary(int salary) {
        return employeeRepository.employeeWithSalary(salary);
    }

    @Override
    public List<Employee> maxSalary() {
        return employeeRepository.findEmployeeWithMaxSalary();
    }

    @Override
    public List<Employee> minSalary() {
        return employeeRepository.findEmployeeWithMinSalary();
    }

    @Override
    public List<Employee> highSalary() {
        return employeeRepository.findEmployeeWithHighSalary();
    }

    @Override
    public Integer sumOfSalary() {
        return employeeRepository.getSumOfSalary();
    }
}