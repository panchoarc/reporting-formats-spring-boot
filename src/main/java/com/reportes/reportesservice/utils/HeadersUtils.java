package com.reportes.reportesservice.utils;

import com.reportes.reportesservice.enums.ReportFormat;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class HeadersUtils {

    public HttpHeaders configureHeaderByFormat(String format) {

        HttpHeaders headers = new HttpHeaders();

        if (format.equalsIgnoreCase(ReportFormat.HTML.getFormatName())) {
            headers.setContentType(MediaType.TEXT_HTML);
            headers.setContentDisposition(ContentDisposition.builder("inline").filename("report.html").build());
        } else if (format.equalsIgnoreCase(ReportFormat.CSV.getFormatName())) {
            headers.setContentType(MediaType.TEXT_PLAIN);
            headers.setContentDisposition(ContentDisposition.builder("attachment").filename("report.csv").build());
        } else if (format.equalsIgnoreCase(ReportFormat.PDF.getFormatName())) {
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDisposition(ContentDisposition.builder("attachment").filename("report.pdf").build());
        } else if (format.equalsIgnoreCase(ReportFormat.XLS.getFormatName())) {
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDisposition(ContentDisposition.builder("attachment").filename("report.xls").build());
        } else if (format.equalsIgnoreCase(ReportFormat.XLSX.getFormatName())) {
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDisposition(ContentDisposition.builder("attachment").filename("report.xlsx").build());
        } else {
            throw new IllegalArgumentException("Unsupported report format: " + format);
        }
        return headers;
    }
}
