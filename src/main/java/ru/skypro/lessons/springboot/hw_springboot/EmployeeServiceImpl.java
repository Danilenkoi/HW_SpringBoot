package ru.skypro.lessons.springboot.hw_springboot;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public abstract class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee maxSalary() {
        return employeeRepository.findEmployeeWithMaxSalary();
    }

    @Override
    public Employee minSalary() {
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