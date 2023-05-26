package ru.skypro.lessons.springboot.hw_springboot;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.skypro.lessons.springboot.hw_springboot.dto.EmployeeDTO;
import ru.skypro.lessons.springboot.hw_springboot.dto.EmployeeOutDTO;
import ru.skypro.lessons.springboot.hw_springboot.repository.EmployeeRepository;

import java.awt.print.Pageable;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public abstract class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    private final PagingEmployee employeePaging;

    @Override
    public void addEmployee(EmployeeDTO[] employees) {
        employeeRepository.saveAll(Arrays.stream(employees).map(EmployeeDTO::toEmployee).toList());
    }
    public List<EmployeeOutDTO> getEmployeesWithPosition(Long positionId) {
        if (positionId == null) {
            return fromEmployeeToDTOList(employeeRepository.findAllEmployees());
        } else {
            return fromEmployeeToDTOList(employeeRepository.findEmployeeByPosition_Id(positionId));
        }
    }
    @Override
        public void updateEmployee ( long id, EmployeeDTO employee){
            employeeRepository.findById(id).orElseThrow(IdNotFound::new);
            employeeRepository.save(employee.toEmployee().setId(id));
        }
    @Override
        public EmployeeView getEmployeeById(long id) {
            return employeeRepository.findEmployeeView(id).orElseThrow(IdNotFound::new);
        }
    @Override
    public void deleteEmployee(long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public List<EmployeeOutDTO> getEmployeesWithPaging(int pageIndex, int unitsPerPage) {
        Pageable employeesOfPage = PageRequest.of(pageIndex, unitsPerPage);
        return employeePaging.findAll(employeesOfPage).map(EmployeeOutDTO::fromEmployee).toList();
    }

    private List<EmployeeOutDTO> fromEmployeeToDTOList(List<Employee> list) {
        return list.stream()
                .map(EmployeeOutDTO::fromEmployee)
                .collect(Collectors.toList());
    }

    @Override
    public List<EmployeeOutDTO> getEmployeesHigherSalary(int salary) {
        return fromEmployeeToDTOList(employeeRepository.findEmployeeBySalaryIsAfter(salary));
    }

    @Override
    public List<EmployeeOutDTO> maxSalary() {
        return fromEmployeeToDTOList(employeeRepository.maxSalary());
    }

    @Override
    public List<EmployeeOutDTO> minSalary() {
        return fromEmployeeToDTOList(employeeRepository.lowestSalary());
    }

    @Override
    public List<EmployeeOutDTO> highSalary() {
        return fromEmployeeToDTOList(employeeRepository.findEmployeeWithSalaryOverAverage());
    }

    @Override
    public Integer sumOfSalary() {
        return employeeRepository.getSumOfSalary();
    }
}