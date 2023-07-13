package ru.skypro.lessons.springboot.hw_springboot.report;


import lombok.Data;

import java.io.Serializable;
@Data
public class ReportView implements Serializable {
    private String position;
    private Long amount;
    private Integer maxSalary;
    private Integer minSalary;
    private Double avgSalary;

    public ReportView(String position, Long amount, Integer maxSalary, Integer minSalary, Double avgSalary) {
        this.position = position;
        this.amount = amount;
        this.maxSalary = maxSalary;
        this.minSalary = minSalary;
        this.avgSalary = avgSalary;
    }
}
