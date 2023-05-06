package ru.skypro.lessons.springboot.hw_springboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/max")
    public Employee maxSalary() {
        return employeeService.maxSalary();
    }

    @GetMapping("/min")
    public Employee minSalary() {
        return employeeService.minSalary();
    }

    @GetMapping("/high")
    public List<Employee> highSalary() {
        return employeeService.highSalary();
    }

    @GetMapping("/sum")
    public Integer sumOfSalary() {
        return employeeService.sumOfSalary();
    }
}
