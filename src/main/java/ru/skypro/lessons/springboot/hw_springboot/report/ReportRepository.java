package ru.skypro.lessons.springboot.hw_springboot.report;

import org.springframework.data.repository.CrudRepository;

public interface ReportRepository extends CrudRepository<Report, Long> {
}