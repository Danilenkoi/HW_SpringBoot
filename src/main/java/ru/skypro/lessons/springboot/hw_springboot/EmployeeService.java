package ru.skypro.lessons.springboot.hw_springboot;

import java.util.List;

public interface EmployeeService {
    Employee maxSalary();

    Employee minSalary();

    List<Employee> highSalary();

    Integer sumOfSalary();
}
