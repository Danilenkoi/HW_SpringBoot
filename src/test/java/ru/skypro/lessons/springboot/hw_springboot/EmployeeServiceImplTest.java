package ru.skypro.lessons.springboot.hw_springboot;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import ru.skypro.lessons.springboot.hw_springboot.dto.EmployeeDTO;
import ru.skypro.lessons.springboot.hw_springboot.dto.EmployeeOutDTO;
import ru.skypro.lessons.springboot.hw_springboot.mistakes.IdNotFound;
import ru.skypro.lessons.springboot.hw_springboot.employee.Employee;
import ru.skypro.lessons.springboot.hw_springboot.employee.EmployeeView;
import ru.skypro.lessons.springboot.hw_springboot.repository.EmployeeRepository;
import ru.skypro.lessons.springboot.hw_springboot.employee.PagingEmployee;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static ru.skypro.lessons.springboot.hw_springboot.tetdata.TestData.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {
    @Mock
    PagingEmployee pagingEmployeeMock;
    @Mock
    EmployeeRepository employeeRepositoryMock;
    @InjectMocks
    EmployeeServiceImpl out;
    static int minSalary;
    static int maxSalary;

    @BeforeAll
    static void collectingInfo() {
        var ageStats = EMPLOYEE_LIST.stream().mapToInt(Employee::getSalary).summaryStatistics();
        maxSalary = ageStats.getMax();
        minSalary = ageStats.getMin();
    }


    @Test
    void addEmployee() {
        out.addEmployee(new EmployeeDTO[]{new EmployeeDTO()});
        verify(employeeRepositoryMock, only()).saveAll(anyList());
    }

    @Test
    void shouldThrowExceptionWhenEmployeeNotFound() {
        when(employeeRepositoryMock.findEmployeeView(anyLong()))
                .thenReturn(Optional.empty());
        when(employeeRepositoryMock.findById(anyLong()))
                .thenReturn(Optional.empty());

        assertThrows(IdNotFound.class, () -> out.updateEmployee(anyLong(), new EmployeeDTO()));
        assertThrows(IdNotFound.class, () -> out.getEmployeeById(anyLong()));
    }

    @Test
    void getEmployeeById() {
        Optional<EmployeeView> view = Optional.of(new EmployeeView() {
            @Override
            public String getName() {
                return "testName";
            }

            @Override
            public int getSalary() {
                return 1;
            }

            @Override
            public String getPositionName() {
                return "testPos";
            }
        });
        when(employeeRepositoryMock.findEmployeeView(anyLong()))
                .thenReturn(view);
        assertEquals(view.get(), out.getEmployeeById(anyLong()));

    }

    @Test
    void deleteEmployee() {
        out.deleteEmployee(anyLong());
        verify(employeeRepositoryMock, only()).deleteById(anyLong());
    }

    @MethodSource("providePositionIdArguments")
    @ParameterizedTest
    void getEmployeesWithPosition(Long positionId) {
        List<Employee> list = filterByPositionId(positionId);

        lenient().when(employeeRepositoryMock.findEmployeeByPosition_Id(anyLong()))
                .thenReturn(list);
        lenient().when(employeeRepositoryMock.findAllEmployees())
                .thenReturn(EMPLOYEE_LIST);

        List<EmployeeOutDTO> expected = list.stream().map(EmployeeOutDTO::fromEmployee).toList();

        assertEquals(expected, out.getEmployeesWithPosition(positionId));
    }

    @MethodSource("providePagingTestArguments")
    @ParameterizedTest
    void getEmployeesWithPaging(int pageNumber, int size) {
        Page<Employee> page = getPageFromList(EMPLOYEE_LIST, pageNumber, size);
        when(pagingEmployeeMock.findAll(PageRequest.of(pageNumber, size)))
                .thenReturn(page);

        List<EmployeeOutDTO> actual = out.getEmployeesWithPaging(pageNumber, size);
        List<EmployeeOutDTO> expected = page.map(EmployeeOutDTO::fromEmployee).toList();
        assertEquals(expected, actual);
    }

    @Test
    void getEmployeesHigheerThan() {
        int higherThan = new Random().nextInt(minSalary, maxSalary + 1);
        List<Employee> list = EMPLOYEE_LIST.stream()
                .filter(x -> x.getSalary() > higherThan).toList();

        when(employeeRepositoryMock.findEmployeeBySalaryIsAfter()(higherThan))
                .thenReturn(list);

        List<EmployeeOutDTO> expected = list.stream().map(EmployeeOutDTO::fromEmployee).toList();
        assertEquals(expected, out.getEmployeesHigheerThan(higherThan));
    }

    @Test
    void getEmployeesMaxSalary() {
        List<Employee> list = EMPLOYEE_LIST.stream()
                .filter(x -> x.getSalary() == maxSalary).toList();

        when(employeeRepositoryMock.maxSalary())
                .thenReturn(list);

        List<EmployeeOutDTO> expected = list.stream().map(EmployeeOutDTO::fromEmployee).toList();
        assertEquals(expected, out.getEmployeesMaxSalary());
    }

    @Test
    void getEmployeesMinSalary() {
        List<Employee> fromRep = EMPLOYEE_LIST.stream()
                .filter(x -> x.getSalary() == minSalary).toList();
        List<EmployeeOutDTO> expected = fromRep.stream().map(EmployeeOutDTO::fromEmployee).toList();

        when(employeeRepositoryMock.lowestSalary())
                .thenReturn(fromRep);

        assertEquals(expected, out.getEmployeesMinSalary());
    }

    @Test
    void getEmployeesHigherSalary() {
        when(employeeRepositoryMock.sumOfSalary())
                .thenReturn(EMPLOYEE_LIST);
        List<EmployeeOutDTO> actual = out.getEmployeesHigherSalary();
        assertEquals(EMPLOYEE_OUT_DTO_LIST, actual);
    }

    @Test
    void sumOfSalary() {
        int expected = 50;
        when(employeeRepositoryMock.sumOfSalary())
                .thenReturn(expected);
        int actual = out.sumOfSalary();
        assertEquals(expected, actual);
    }

    @Test
    void getAll() {
        when(employeeRepositoryMock.findAllEmployees())
                .thenReturn(EMPLOYEE_LIST);
        assertEquals(EMPLOYEE_OUT_DTO_LIST, out.getAll());
    }

    private static Stream<Arguments> providePagingTestArguments() {
        return Stream.of(
                Arguments.of(1, 2),
                Arguments.of(1, 10),
                Arguments.of(0, 2)
        );
    }

    public static Stream<Arguments> providePositionIdArguments() {
        return Stream.of(
                Arguments.arguments(1L),
                Arguments.of(2L),
                null
        );
    }

    private static <T> Page<T> getPageFromList(List<T> col, int page, int size) {
        return new PageImpl<T>(col.stream().skip((long) page * size).limit(page).toList());
    }
    private static List<Employee> filterByPositionId(Long positionId) {
        return ru.skypro.lessons.springboot.hw_springboot.tetdata.TestData.EMPLOYEE_LIST.stream()
                .filter(employee -> positionId == null || employee.getPosition().getId() == positionId)
                .toList();
    }

}