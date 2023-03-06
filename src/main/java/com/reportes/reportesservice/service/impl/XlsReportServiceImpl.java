package com.reportes.reportesservice.service.impl;

import com.reportes.reportesservice.entity.Product;
import com.reportes.reportesservice.enums.ReportFormat;
import com.reportes.reportesservice.service.ReportService;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class XlsReportServiceImpl implements ReportService {

    @Override
    public String getReportFormat() {
        return ReportFormat.XLSX.getFormatName();
    }

    @Override
    public byte[] generateReport(List<Product> products) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Products");

        // Create headers
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Product ID");
        headerRow.createCell(1).setCellValue("Product Name");
        headerRow.createCell(2).setCellValue("Price");

        // Populate data rows
        int rowIdx = 1;
        for (Product product : products) {
            Row row = sheet.createRow(rowIdx++);
            row.createCell(0).setCellValue(product.getId());
            row.createCell(1).setCellValue(product.getName());
            row.createCell(2).setCellValue(product.getPrice());
        }
        workbook.write(outputStream);
        byte[] bytes = outputStream.toByteArray();
        outputStream.close();

        return bytes;
    }
}