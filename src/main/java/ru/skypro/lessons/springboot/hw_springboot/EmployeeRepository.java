package ru.skypro.lessons.springboot.hw_springboot;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    void deleteById(long id);

    List<Employee> employeeWithSalary(int salary);

    @Query("select e from Employee e where e.salary = (select max(salary) from Employee)")
    List<Employee> findEmployeeWithMaxSalary();

    @Query("select e from Employee e where e.salary = (select min(salary) from Employee)")
    List<Employee> findEmployeeWithMinSalary();

    @Query("select sum(salary) from Employee ")
    Integer getSumOfSalary();

    @Query("select e from Employee e where e.salary > (select avg(salary) from Employee)")
    List<Employee> findEmployeeWithHighSalary();
}
