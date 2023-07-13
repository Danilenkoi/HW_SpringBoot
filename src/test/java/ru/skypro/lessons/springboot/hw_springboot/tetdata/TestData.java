package ru.skypro.lessons.springboot.hw_springboot.tetdata;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.skypro.lessons.springboot.hw_springboot.dto.EmployeeOutDTO;
import ru.skypro.lessons.springboot.hw_springboot.employee.Employee;
import ru.skypro.lessons.springboot.hw_springboot.position.Position;
import ru.skypro.lessons.springboot.hw_springboot.report.ReportView;

import java.util.ArrayList;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

public class TestData {
    final static public List<Position> POSITION_LIST = List.of(
            new Position(1L, "driver"),
            new Position(2L, "manager"),
            new Position(3L, "inginer"),
            new Position(3L, "CEO")
    final static public String REPORT_DATA;
}

    final static public List<Employee> EMPLOYEE_LIST = List.of(
            new Employee(1L, "Bob", 20000, POSITION_LIST.get(0)),
            new Employee(2L, "Dan", 45000,  POSITION_LIST.get(1)),
            new Employee(3L, "Pam", 30000, POSITION_LIST.get(2)),
            new Employee(4L, "Joe",70000, POSITION_LIST.get(3))
    );
    final static public List<EmployeeOutDTO> EMPLOYEE_OUT_DTO_LIST = EMPLOYEE_LIST.stream().map(EmployeeOutDTO::fromEmployee).toList();

    final static public List<ReportView> REPORT_VIEW_LIST = TestData.EMPLOYEE_LIST.stream()
            .collect(Collectors.groupingBy(Employee::getPosition))
            .entrySet().stream()
            .map(e -> {
                IntSummaryStatistics s = e.getValue().stream().mapToInt(Employee::getSalary).summaryStatistics();
                return new ReportView(e.getKey().getPositionName(), s.getCount(), s.getMax(), s.getMin(), s.getAverage());
            }).toList();
    final static public String REPORT_DATA;

    static {
        try {
            REPORT_DATA = new ObjectMapper().writeValueAsString(REPORT_VIEW_LIST);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
