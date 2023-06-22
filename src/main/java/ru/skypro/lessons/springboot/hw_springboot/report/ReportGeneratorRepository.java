package ru.skypro.lessons.springboot.hw_springboot.report;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.skypro.lessons.springboot.hw_springboot.employee.Employee;

import java.util.List;

public interface ReportGeneratorRepository extends CrudRepository<Employee, Long> {
    @Query("SELECT new ru.skypro.lessons.springboot.hw_springboot.ReportView(coalesce(p.positionName, 'no position')," +
            " count(p.positionName), max(e.salary), min(e.salary), avg(e.salary)) " +
            "from Employee e left join Position p on e.position = p group by coalesce(p.positionName, 'no position')")
    List<ReportView> getReportsList();
}
