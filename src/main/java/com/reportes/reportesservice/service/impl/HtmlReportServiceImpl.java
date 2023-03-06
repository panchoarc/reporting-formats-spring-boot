package com.reportes.reportesservice.service.impl;

import com.reportes.reportesservice.entity.Product;
import com.reportes.reportesservice.enums.ReportFormat;
import com.reportes.reportesservice.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class HtmlReportServiceImpl implements ReportService {

    @Autowired
    private TemplateEngine templateEngine;

    public HtmlReportServiceImpl(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    @Override
    public String getReportFormat() {
        return ReportFormat.HTML.getFormatName();
    }

    @Override
    public byte[] generateReport(List<Product> products) {
        Context context = new Context();
        context.setVariable("products", products);
        String html = templateEngine.process("productReport", context);
        return html.getBytes(StandardCharsets.UTF_8);
    }
}
