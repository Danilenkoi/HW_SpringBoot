package ru.skypro.lessons.springboot.hw_springboot.employee;

import ru.skypro.lessons.springboot.hw_springboot.dto.EmployeeDTO;
import ru.skypro.lessons.springboot.hw_springboot.dto.EmployeeOutDTO;

import java.util.List;
public interface EmployeeService {

    void addEmployee(EmployeeDTO... employees);

    void updateEmployee(long id, EmployeeDTO employee);

    EmployeeView getEmployeeById(long id);

    void deleteEmployee(long id);

    List<EmployeeOutDTO> getEmployeesHigherSalary(int salary);

    List<EmployeeOutDTO> getEmployeesWithPosition(Long positionId);

    List<EmployeeOutDTO> getEmployeesWithPaging(int pageIndex, int unitsPerPage);

    List<EmployeeOutDTO> maxSalary();

    List<EmployeeOutDTO> minSalary();

    List<EmployeeOutDTO> highSalary();

    Integer sumOfSalary();

}
