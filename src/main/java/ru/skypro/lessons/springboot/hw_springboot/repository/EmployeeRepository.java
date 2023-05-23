package ru.skypro.lessons.springboot.hw_springboot.repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.skypro.lessons.springboot.hw_springboot.Employee;
import ru.skypro.lessons.springboot.hw_springboot.EmployeeFullInfo;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    @Query("SELECT new ru.skypro.lessons.springboot.hw_springboot." +
            "EmployeeFullInfo(e.name , e.salary , p.name) " +
            "FROM Employee e join fetch Position p " +
            "WHERE e.position = p")
    List<EmployeeFullInfo> findAllEmployeeFullInfo();

}
