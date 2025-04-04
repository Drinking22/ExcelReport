package com.example.excel.report.services.checks.filters.judicial;

import com.example.excel.report.model.JudicialExcelData;

import java.time.LocalDateTime;
import java.util.List;

public interface JudicialReportFilter {
    List<JudicialExcelData> generateSendToDebtorButNotFiledInCourtReport(List<JudicialExcelData> judicialExcelData, LocalDateTime start, LocalDateTime end);
    List<JudicialExcelData> generateCourtOrderNotReceivedReport(List<JudicialExcelData> judicialExcelData, LocalDateTime start, LocalDateTime end);
    List<JudicialExcelData> generateCopiesOfDocumentsSent(List<JudicialExcelData> judicialExcelData, LocalDateTime start, LocalDateTime end);
    List<JudicialExcelData> generateApplicationsSubmittedToCourt(List<JudicialExcelData> judicialExcelData, LocalDateTime start, LocalDateTime end);
    List<JudicialExcelData> generateCancellationOfTheCourtOrderButNoLawsuitFiled(List<JudicialExcelData> judicialExcelData, LocalDateTime start, LocalDateTime end);
    List<JudicialExcelData> generateReturnOfDocumentsFromTheCourt(List<JudicialExcelData> judicialExcelData, LocalDateTime start, LocalDateTime end);
    List<JudicialExcelData> generateReceivedCourtOrder(List<JudicialExcelData> judicialExcelData, LocalDateTime start, LocalDateTime end);
}

