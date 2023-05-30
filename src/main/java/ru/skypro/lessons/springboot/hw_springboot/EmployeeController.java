package ru.skypro.lessons.springboot.hw_springboot;

import org.springframework.web.bind.annotation.*;
import ru.skypro.lessons.springboot.hw_springboot.dto.EmployeeDTO;
import ru.skypro.lessons.springboot.hw_springboot.dto.EmployeeOutDTO;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @PostMapping("/add")
    public void addNewEmployees(@RequestBody EmployeeDTO... employees) {
        employeeService.addEmployee(employees);
    }

    @PutMapping("/{id}")
    public void editEmployee(@PathVariable long id, @RequestBody EmployeeDTO employee) {
        employeeService.updateEmployee(id, employee);
    }

    @GetMapping("/{id}")
    public EmployeeView getEmployee(@PathVariable long id) {
        return employeeService.getEmployeeById(id);
    }

    @GetMapping("/higherSalary")
    public List<EmployeeOutDTO> getEmployeesHigherSalary(@RequestParam (name = "salary") int salary) {
        return employeeService.getEmployeesHigherSalary(salary);
    }
    @DeleteMapping("/{id}")
    public void removeEmployeeById(@PathVariable long id) {
        employeeService.deleteEmployee(id);
    }



    @GetMapping("/max")
    public List<EmployeeOutDTO> maxSalary() {
        return employeeService.maxSalary();
    }

    @GetMapping("/min")
    public List<EmployeeOutDTO> minSalary() {
        return employeeService.minSalary();
    }

    @GetMapping("/high")
    public List<EmployeeOutDTO> highSalary() {
        return employeeService.highSalary();
    }

    @GetMapping("/sum")
    public Integer sumOfSalary() {
        return employeeService.sumOfSalary();
    }
}
