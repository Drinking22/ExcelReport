package com.example.excel.report.services.report;

import com.example.excel.report.constant.titles.ExcelFileNameConst;
import com.example.excel.report.model.ExecutionProcessExcelData;
import com.example.excel.report.model.JudicialExcelData;
import com.example.excel.report.model.LawsuitExcelData;

import java.io.FileOutputStream;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public interface JudicialExcelWriterService {
    FileOutputStream writeJudicialReportExcelFile(ExcelFileNameConst fileName, ConcurrentHashMap<String, List<JudicialExcelData>> allReports);
    FileOutputStream writeLawsuitReportExcelFile(ExcelFileNameConst fileName, ConcurrentHashMap<String, List<LawsuitExcelData>> allReports);
    FileOutputStream writeExecutionProcessReportExcelFile(ExcelFileNameConst fileName, ConcurrentHashMap<String, List<ExecutionProcessExcelData>> allReports);
}
