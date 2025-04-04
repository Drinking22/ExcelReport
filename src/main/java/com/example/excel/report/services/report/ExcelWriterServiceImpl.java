package com.example.excel.report.services.report;

import com.example.excel.report.constant.titles.ExcelFileNameConst;
import com.example.excel.report.constant.titles.ExcelJudicialSheetsNameConst;
import com.example.excel.report.model.ExecutionProcessExcelData;
import com.example.excel.report.model.JudicialExcelData;
import com.example.excel.report.model.LawsuitExcelData;
import com.example.excel.report.constant.ColumnsExcelReport;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExcelWriterServiceImpl implements ExcelWriterService {

    private final ColumnsExcelReport columnsExcelReport;
    private final ExcelJudicialSheetsNameConst excelJudicialSheetsNameConst;

    @Override
    public FileOutputStream writeJudicialReportExcelFile(ExcelFileNameConst fileName, Map<String, List<JudicialExcelData>> allReports) {
        return null;
    }

    @Override
    public FileOutputStream writeLawsuitReportExcelFile(ExcelFileNameConst fileName, Map<String, List<LawsuitExcelData>> allReports) {
        return null;
    }

    @Override
    public FileOutputStream writeExecutionProcessReportExcelFile(ExcelFileNameConst fileName, Map<String, List<ExecutionProcessExcelData>> allReports) {
        return null;
    }
}
