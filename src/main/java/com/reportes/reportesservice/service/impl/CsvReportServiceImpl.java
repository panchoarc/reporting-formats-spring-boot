package com.reportes.reportesservice.service.impl;

import com.opencsv.CSVWriter;
import com.reportes.reportesservice.entity.Product;
import com.reportes.reportesservice.enums.ReportFormat;
import com.reportes.reportesservice.service.ReportService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

@Service
public class CsvReportServiceImpl implements ReportService {
    @Override
    public String getReportFormat() {
        return ReportFormat.CSV.getFormatName();
    }

    @Override
    public byte[] generateReport(List<Product> products) throws IOException {
        StringWriter stringWriter = new StringWriter();
        CSVWriter csvWriter = new CSVWriter(stringWriter);
        String[] headers = {"Product ID", "Product Name", "Price"};

        // Write headers
        csvWriter.writeNext(headers);

        // Write data rows
        for (Product product : products) {
            String[] data = {String.valueOf(product.getId()), product.getName(), String.valueOf(product.getPrice())};
            csvWriter.writeNext(data);
        }

        csvWriter.close();
        return stringWriter.toString().getBytes();
    }
}