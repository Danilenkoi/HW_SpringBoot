package ru.skypro.lessons.springboot.hw_springboot;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @PostMapping("/add")
    public void addNewEmployees(@RequestBody Employee... employees) {
        employeeService.addEmployee(employees);
    }

    @PutMapping("/{id}")
    public void editEmployee(@PathVariable long id, @RequestBody Employee employee) {
        employeeService.updateEmployee(id, employee);
    }

    @GetMapping("/{id}")
    public Employee getEmployee(@PathVariable long id) {
        return employeeService.getEmployeeById(id);
    }

    @GetMapping("/higherSalary")
    public List<Employee> getEmployeesHigherSalary(@RequestParam (name = "salary") int salary) {
        return employeeService.getEmployeesHigherSalary(salary);
    }
    @DeleteMapping("/{id}")
    public void removeEmployeeById(@PathVariable long id) {
        employeeService.deleteEmployee(id);
    }



    @GetMapping("/max")
    public List<Employee> maxSalary() {
        return employeeService.maxSalary();
    }

    @GetMapping("/min")
    public List<Employee> minSalary() {
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
