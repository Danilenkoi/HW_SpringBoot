package ru.skypro.lessons.springboot.hw_springboot.employee;

import org.springframework.beans.factory.annotation.Value;

public interface EmployeeView {

    int getSalary();
    @Value("#{target.position.positionName}")
    String getPositionName();


}
