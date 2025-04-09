package com.example.excel.report.services.checks.filters.judicial;

import com.example.excel.report.model.JudicialExcelData;

import java.time.LocalDateTime;
import java.util.List;

public interface JudicialReportFilter {
    List<JudicialExcelData> generateSendToDebtorButNotFiledInCourtReport(List<JudicialExcelData> judicialExcelData);
    List<JudicialExcelData> generateCourtOrderNotReceivedReport(List<JudicialExcelData> judicialExcelData);
    List<JudicialExcelData> generateCopiesOfDocumentsSentReport(List<JudicialExcelData> judicialExcelData, LocalDateTime start, LocalDateTime end);
    List<JudicialExcelData> generateApplicationsSubmittedToCourtReport(List<JudicialExcelData> judicialExcelData, LocalDateTime start, LocalDateTime end);
    List<JudicialExcelData> generateCancellationOfTheCourtOrderButNoLawsuitFiledReport(List<JudicialExcelData> judicialExcelData);
    List<JudicialExcelData> generateReturnOfDocumentsFromTheCourtReport(List<JudicialExcelData> judicialExcelData, LocalDateTime start, LocalDateTime end);
    List<JudicialExcelData> generateReceivedCourtOrderReport(List<JudicialExcelData> judicialExcelData, LocalDateTime start, LocalDateTime end);
}

