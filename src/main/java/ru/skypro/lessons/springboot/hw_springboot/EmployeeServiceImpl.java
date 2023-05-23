package ru.skypro.lessons.springboot.hw_springboot;

import org.springframework.stereotype.Service;
import ru.skypro.lessons.springboot.hw_springboot.dto.EmployeeDTO;
import ru.skypro.lessons.springboot.hw_springboot.repository.EmployeeRepository;
import java.util.List;
import java.util.stream.Collectors;

@Service
public abstract class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;


    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    @Override
    public List<EmployeeDTO> getAllEmployees() {
        return employeeRepository.findAllEmployees().stream()
                .map(EmployeeDTO::fromEmployee)
                .collect(Collectors.toList());
    }
}