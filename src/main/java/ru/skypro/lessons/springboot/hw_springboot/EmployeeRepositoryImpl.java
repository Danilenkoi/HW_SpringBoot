package ru.skypro.lessons.springboot.hw_springboot;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public abstract class EmployeeRepositoryImpl implements EmployeeRepository{
    private final List<Employee> employeeList = List.of(
            new Employee("Gleb", 90_000),
            new Employee("Дима", 102_000),
            new Employee("Олег", 80_000),
            new Employee("Вика", 165_000));

    @Query("select e from Employee e where e.salary = (select max(salary) from Employee)")
    Employee findEmployeeWithMaxSalary();

    @Query("select e from Employee e where e.salary = (select min(salary) from Employee)")
    Employee findEmployeeWithMinSalary();

    @Query("select sum(salary) from Employee ")
    Integer getSumOfSalary();

    @Query("select e from Employee e where e.salary > (select avg(salary) from Employee)")
    List<Employee> findEmployeeWithHighSalary();
}
