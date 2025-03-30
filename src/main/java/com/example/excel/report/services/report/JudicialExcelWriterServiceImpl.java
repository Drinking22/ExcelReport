package com.example.excel.report.services.report;

import com.example.excel.report.constant.titles.ExcelFileNameConst;
import com.example.excel.report.model.ExecutionProcessExcelData;
import com.example.excel.report.model.JudicialExcelData;
import com.example.excel.report.model.LawsuitExcelData;
import com.example.excel.report.util.ColumnsExcelReport;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
@Slf4j
public class JudicialExcelWriterServiceImpl implements JudicialExcelWriterService {

    private final ColumnsExcelReport columnsExcelReport;

    @Override
    public FileOutputStream writeJudicialReportExcelFile(ExcelFileNameConst fileName, ConcurrentHashMap<String, List<JudicialExcelData>> allReports) {
        return null;
    }

    @Override
    public FileOutputStream writeLawsuitReportExcelFile(ExcelFileNameConst fileName, ConcurrentHashMap<String, List<LawsuitExcelData>> allReports) {
        return null;
    }

    @Override
    public FileOutputStream writeExecutionProcessReportExcelFile(ExcelFileNameConst fileName, ConcurrentHashMap<String, List<ExecutionProcessExcelData>> allReports) {
        return null;
    }
}
