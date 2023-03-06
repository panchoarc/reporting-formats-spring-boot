package com.reportes.reportesservice.controller;


import com.reportes.reportesservice.entity.Product;
import com.reportes.reportesservice.service.ProductService;
import com.reportes.reportesservice.service.ReportService;
import com.reportes.reportesservice.service.ReportServiceFactory;
import com.reportes.reportesservice.utils.HeadersUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/reports")
public class ReportController {

    private final ReportServiceFactory reportServiceFactory;

    private final ProductService productService;

    public ReportController(ReportServiceFactory reportServiceFactory, ProductService productService) {
        this.reportServiceFactory = reportServiceFactory;
        this.productService = productService;
    }

    @GetMapping(value = "/{format}")
    public ResponseEntity<?> generateReport(@PathVariable("format") String format) throws IOException {

        try {
            List<Product> products = productService.getAllProducts();
            ReportService reportService = reportServiceFactory.getReportService(format);
            byte[] reportBytes = reportService.generateReport(products);

            HeadersUtils headersUtils = new HeadersUtils();
            HttpHeaders headers = headersUtils.configureHeaderByFormat(reportService.getReportFormat());

            return ResponseEntity.ok().headers(headers).body(reportBytes);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }


    }
}
