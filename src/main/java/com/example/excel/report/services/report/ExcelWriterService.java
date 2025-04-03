package com.example.excel.report.services.report;

import com.example.excel.report.constant.titles.ExcelFileNameConst;
import com.example.excel.report.model.ExecutionProcessExcelData;
import com.example.excel.report.model.JudicialExcelData;
import com.example.excel.report.model.LawsuitExcelData;

import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;

public interface ExcelWriterService {
    FileOutputStream writeJudicialReportExcelFile(ExcelFileNameConst fileName, Map<String, List<JudicialExcelData>> allReports);
    FileOutputStream writeLawsuitReportExcelFile(ExcelFileNameConst fileName, Map<String, List<LawsuitExcelData>> allReports);
    FileOutputStream writeExecutionProcessReportExcelFile(ExcelFileNameConst fileName, Map<String, List<ExecutionProcessExcelData>> allReports);
}
