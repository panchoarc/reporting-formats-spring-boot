package com.reportes.reportesservice.enums;

public enum ReportFormat {
    PDF("pdf", "application/pdf"),
    XLS("xls", "application/vnd.ms-excel"),
    XLSX("xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"),
    CSV("csv", "text/csv"),
    HTML("html", "text/html");

    private final String formatName;
    private final String contentType;

    ReportFormat(String formatName, String contentType) {
        this.formatName = formatName;
        this.contentType = contentType;
    }

    public String getFormatName() {
        return formatName;
    }

    public static ReportFormat fromFormatName(String formatName) {
        for (ReportFormat reportFormat : ReportFormat.values()) {
            if (reportFormat.formatName.equalsIgnoreCase(formatName)) {
                return reportFormat;
            }
        }

        throw new IllegalArgumentException("Unsupported report format: " + formatName);
    }
}