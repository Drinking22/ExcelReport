package com.example.excel.report.services.checks.filters.execution;

import com.example.excel.report.model.ExecutionProcessExcelData;

import java.time.LocalDateTime;
import java.util.List;

public interface ExecutionProcessReportFilter {
    List<ExecutionProcessExcelData> generateSubmittedForExecutionReport(List<ExecutionProcessExcelData> executionProcessExcelData, LocalDateTime start, LocalDateTime end);
    List<ExecutionProcessExcelData> generateDocumentReceivedButNotSubmittedForExecutionReport(List<ExecutionProcessExcelData> executionProcessExcelData);
    List<ExecutionProcessExcelData> generateDocumentFiledButNotInitiationReport(List<ExecutionProcessExcelData> executionProcessExcelData);
    List<ExecutionProcessExcelData> generateNumberOfProgressReport(List<ExecutionProcessExcelData> executionProcessExcelData, LocalDateTime start, LocalDateTime end);
    List<ExecutionProcessExcelData> generateNumberOfComplaintsFiledReport(List<ExecutionProcessExcelData> executionProcessExcelData, LocalDateTime start, LocalDateTime end);
    List<ExecutionProcessExcelData> generateNumberOfComplaintsFiledInCourtReport(List<ExecutionProcessExcelData> executionProcessExcelData, LocalDateTime start, LocalDateTime end);
    List<ExecutionProcessExcelData> generateControlOfTheWorkplaceReport(List<ExecutionProcessExcelData> executionProcessExcelData);
    List<ExecutionProcessExcelData> generateVehicleControlReport(List<ExecutionProcessExcelData> executionProcessExcelData);
    List<ExecutionProcessExcelData> generatePropertyControlReport(List<ExecutionProcessExcelData> executionProcessExcelData);
    List<ExecutionProcessExcelData> generateNumberOfOutputsReport(List<ExecutionProcessExcelData> executionProcessExcelData, LocalDateTime start, LocalDateTime end);
}

