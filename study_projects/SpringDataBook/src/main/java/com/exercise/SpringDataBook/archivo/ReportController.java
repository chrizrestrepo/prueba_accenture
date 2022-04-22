package com.exercise.SpringDataBook.archivo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@RestController("/report")
public class ReportController {

    public final ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping("/")
    public File getReportStudents(){
        return reportService.genereteReport();
    }
}
