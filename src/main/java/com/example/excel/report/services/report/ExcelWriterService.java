package com.example.excel.report.services.report;

import com.example.excel.report.model.ExecutionProcessExcelData;
import com.example.excel.report.model.JudicialExcelData;
import com.example.excel.report.model.LawsuitExcelData;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface ExcelWriterService {
    String writeJudicialReportExcelFile(String fileName, Map<String, List<JudicialExcelData>> allReports, String filePath) throws IOException;
    String writeLawsuitReportExcelFile(String fileName, Map<String, List<LawsuitExcelData>> allReports, String filePath);
    String writeExecutionProcessReportExcelFile(String fileName, Map<String, List<ExecutionProcessExcelData>> allReports, String filePath);
}
