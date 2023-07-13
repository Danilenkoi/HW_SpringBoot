package ru.skypro.lessons.springboot.hw_springboot.employee;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.skypro.lessons.springboot.hw_springboot.employee.Employee;

public interface PagingEmployee extends PagingAndSortingRepository<Employee, Long> {
}
