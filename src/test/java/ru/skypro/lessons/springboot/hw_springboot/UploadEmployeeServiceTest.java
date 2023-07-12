package ru.skypro.lessons.springboot.hw_springboot;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.lessons.springboot.hw_springboot.employee.EmployeeService;
import ru.skypro.lessons.springboot.hw_springboot.dto.EmployeeDTO;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UploadEmployeeServiceTest {
    @Mock
    EmployeeService employeeService;
    @InjectMocks
    UploadEmployeeService out;
    ObjectMapper mapper = new ObjectMapper();

    @Test
    void upload() throws JsonProcessingException {
        MultipartFile file = new MockMultipartFile("file",
                mapper.writeValueAsBytes(new EmployeeDTO[]{new EmployeeDTO()}));
        out.upload(file);
        verify(employeeService, only()).addEmployee(any());
    }
}
