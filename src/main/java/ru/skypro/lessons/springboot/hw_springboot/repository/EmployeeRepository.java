package ru.skypro.lessons.springboot.hw_springboot.repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.skypro.lessons.springboot.hw_springboot.Employee;
import ru.skypro.lessons.springboot.hw_springboot.EmployeeView;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    @Query(value = "select e from Employee e where e.id = ?1")
    Optional<EmployeeView> findEmployeeView(Long id);

    @Query("select e from Employee e where e.salary = (select max(salary) from Employee)")
    List<Employee> maxSalary();

    @Query("select e from Employee e where e.salary = (select min(salary) from Employee)")
    List<Employee> lowestSalary();

    List<Employee> findEmployeeByPosition_Id(long positionId);

    @Query("from Employee")
    List<Employee> findAllEmployees();

    @Query("select sum(salary) from Employee ")
    Integer getSumOfSalary();

    @Query("select e from Employee e where e.salary > (select avg(salary) from Employee)")
    List<Employee> findEmployeeWithSalaryOverAverage();
    List<Employee> findEmployeeBySalaryIsAfter(int salary);


}
