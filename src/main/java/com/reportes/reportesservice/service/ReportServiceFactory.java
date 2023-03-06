package com.reportes.reportesservice.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ReportServiceFactory {
    private Map<String, ReportService> reportServiceMap;

    @Autowired
    public ReportServiceFactory(List<ReportService> reportServices) {
        this.reportServiceMap = reportServices.stream()
                .collect(Collectors.toMap(ReportService::getReportFormat, Function.identity()));
    }

    public ReportService getReportService(String format) {
        ReportService reportService = reportServiceMap.get(format);
        if (reportService == null) {
            throw new IllegalArgumentException("Unsupported report format: " + format);
        }
        return reportService;
    }
}
