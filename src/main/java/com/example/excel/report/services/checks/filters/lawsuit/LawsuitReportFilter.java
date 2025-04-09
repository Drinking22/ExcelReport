package com.example.excel.report.services.checks.filters.lawsuit;

import com.example.excel.report.model.LawsuitExcelData;

import java.time.LocalDateTime;
import java.util.List;

public interface LawsuitReportFilter {
    List<LawsuitExcelData> generateClaimFiledReport(List<LawsuitExcelData> lawsuitExcelData, LocalDateTime start, LocalDateTime end);
    List<LawsuitExcelData> generateDateOfReviewReport(List<LawsuitExcelData> lawsuitExcelData, LocalDateTime now);
    List<LawsuitExcelData> generateReceivedWritsOfExecutionReport(List<LawsuitExcelData> lawsuitExcelData, LocalDateTime start, LocalDateTime end);
}
