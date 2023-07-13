package ru.skypro.lessons.springboot.hw_springboot.report;

import org.springframework.core.io.Resource;

public interface ReportService {
    long generateReport();

    Resource getReport(long id);
}
