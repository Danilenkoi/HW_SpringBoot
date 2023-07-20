package ru.skypro.lessons.springboot.hw_springboot.employee;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.lessons.springboot.hw_springboot.dto.EmployeeDTO;

@Service
public class UploadEmployeeService {
    EmployeeService employeeService;

    public UploadEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @SneakyThrows
    public void upload(MultipartFile multi) {
        EmployeeDTO[] arr = new ObjectMapper().readValue(multi.getInputStream(), EmployeeDTO[].class);
        employeeService.addEmployee(arr);
    }
}
