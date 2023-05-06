package ru.skypro.lessons.springboot.hw_springboot;

import java.util.List;

public interface EmployeeRepository {
    Employee findEmployeeWithMaxSalary();

    Employee findEmployeeWithMinSalary();

    Integer getSumOfSalary();

    List<Employee> findEmployeeWithHighSalary();
}
