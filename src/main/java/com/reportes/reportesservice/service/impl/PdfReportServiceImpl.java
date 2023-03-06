package com.reportes.reportesservice.service.impl;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Table;
import com.reportes.reportesservice.entity.Product;
import com.reportes.reportesservice.enums.ReportFormat;
import com.reportes.reportesservice.service.ReportService;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class PdfReportServiceImpl implements ReportService {

    @Override
    public String getReportFormat() {
        return ReportFormat.PDF.getFormatName();
    }

    @Override
    public byte[] generateReport(List<Product> products) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(baos));
        Document doc = new Document(pdfDoc);

        Table table = new Table(3);
        table.addCell("ID");
        table.addCell("Name");
        table.addCell("Price");

        for (Product product : products) {
            table.addCell(product.getId().toString());
            table.addCell(product.getName());
            table.addCell(product.getPrice().toString());
        }

        doc.add(table);
        doc.close();

        return baos.toByteArray();
    }
}