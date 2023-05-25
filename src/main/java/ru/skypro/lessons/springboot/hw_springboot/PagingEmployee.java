package ru.skypro.lessons.springboot.hw_springboot;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.skypro.lessons.springboot.hw_springboot.Employee;

public interface PagingEmployee extends PagingAndSortingRepository<Employee, Long> {
}
