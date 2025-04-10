package com.example.excel.report.services.checks;

import com.example.excel.report.model.ExecutionProcessExcelData;
import com.example.excel.report.model.JudicialExcelData;
import com.example.excel.report.model.LawsuitExcelData;

import java.util.List;
import java.util.Map;

public interface ExcelCheckService {
    Map<String, List<JudicialExcelData>> generateJudicialMonthlyReport(List<JudicialExcelData> judicialExcelData);
    Map<String, List<JudicialExcelData>> generateJudicialWeeklyReport(List<JudicialExcelData> judicialExcelData);
    Map<String, List<LawsuitExcelData>> generateLawsuitMonthlyReport(List<LawsuitExcelData> lawsuitExcelData);
    Map<String, List<LawsuitExcelData>> generateLawsuitWeeklyReport(List<LawsuitExcelData> lawsuitExcelData);
    Map<String, List<ExecutionProcessExcelData>> generateExecutionProcessMonthlyReport(List<ExecutionProcessExcelData> executionProcessExcelData);
    Map<String, List<ExecutionProcessExcelData>> generateExecutionProcessWeeklyReport(List<ExecutionProcessExcelData> executionProcessExcelData);
}
