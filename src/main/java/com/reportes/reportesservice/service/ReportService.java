package com.reportes.reportesservice.service;


import com.reportes.reportesservice.entity.Product;

import java.io.IOException;
import java.util.List;

public interface ReportService {
    String getReportFormat();
    byte[] generateReport(List<Product> products) throws IOException;
}