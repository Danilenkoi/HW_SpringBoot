package ru.skypro.lessons.springboot.hw_springboot.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.lessons.springboot.hw_springboot.employee.EmployeeService;
import ru.skypro.lessons.springboot.hw_springboot.dto.EmployeeOutDTO;

import java.util.List;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {
    private final EmployeeService employeeService;


    @GetMapping("/all")
    public List<EmployeeOutDTO> getAllEmployees() {
        return employeeService.getAll();
    }
}
