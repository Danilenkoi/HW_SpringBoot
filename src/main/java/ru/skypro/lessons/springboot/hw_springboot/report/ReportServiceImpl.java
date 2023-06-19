package ru.skypro.lessons.springboot.hw_springboot.report;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import ru.skypro.lessons.springboot.hw_springboot.mistakes.IdNotFound;

import java.util.List;

@Service
@AllArgsConstructor
public class ReportServiceImpl implements ReportService {
    private ReportGeneratorRepository generatorRepository;
    private ReportRepository reportRepository;
    private ObjectMapper mapper;

    @Override
    public long generateReport() {
        Report report = new Report(getJSON());
        reportRepository.save(report);
        return report.getId();
    }

    @Override
    public Resource getReport(long id) {
        Report report = reportRepository.findById(id).orElseThrow(IdNotFound::new);
        return new ByteArrayResource(report.getReport().getBytes());
    }



    @SneakyThrows
    private String getJSON() {
        List<ReportView> list = generatorRepository.getReportsList();
        return mapper.writeValueAsString(list);
    }
}
