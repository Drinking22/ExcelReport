package com.example.excel.report.services.checks;

import com.example.excel.report.model.ExecutionProcessExcelData;
import com.example.excel.report.model.JudicialExcelData;
import com.example.excel.report.model.LawsuitExcelData;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public interface ExcelCheckService {
    ConcurrentHashMap<String, List<JudicialExcelData>> generateJudicialMonthlyReport(List<JudicialExcelData> judicialExcelData);
    ConcurrentHashMap<String, List<JudicialExcelData>> generateJudicialWeeklyReport(List<JudicialExcelData> judicialExcelData);
    ConcurrentHashMap<String, List<LawsuitExcelData>> generateLawsuitMonthlyReport(List<LawsuitExcelData> lawsuitExcelData);
    ConcurrentHashMap<String, List<LawsuitExcelData>> generateLawsuitWeeklyReport(List<LawsuitExcelData> lawsuitExcelData);
    ConcurrentHashMap<String, List<ExecutionProcessExcelData>> generateExecutionProcessMonthlyReport(List<ExecutionProcessExcelData> executionProcessExcelData);
    ConcurrentHashMap<String, List<ExecutionProcessExcelData>> generateExecutionProcessWeeklyReport(List<ExecutionProcessExcelData> executionProcessExcelData);
}
