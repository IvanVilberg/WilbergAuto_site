package com.site.autosite.report;

import org.springframework.stereotype.Service;

@Service
public class ReportService {
    private final ReportRepository reportRepository;

    public ReportService(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    public Report save(Report report){
        System.out.println(report);
        return reportRepository.save(report);
    }
}   
