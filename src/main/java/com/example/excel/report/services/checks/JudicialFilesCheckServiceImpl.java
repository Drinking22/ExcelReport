package com.example.excel.report.services.checks;

import com.example.excel.report.model.ExecutionProcessExcelData;
import com.example.excel.report.model.JudicialExcelData;
import com.example.excel.report.model.LawsuitExcelData;
import com.example.excel.report.services.report.JudicialExcelWriterServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
@Slf4j
public class JudicialFilesCheckServiceImpl implements JudicialFilesCheckService {

    private ConcurrentHashMap<String, JudicialExcelData> allReports;


    @Override
    public ConcurrentHashMap<String, List<JudicialExcelData>> generateJudicialMonthlyReport(List<JudicialExcelData> judicialExcelData) {
        return null;
    }

    @Override
    public ConcurrentHashMap<String, List<JudicialExcelData>> generateJudicialWeeklyReport(List<JudicialExcelData> judicialExcelData) {
        return null;
    }

    @Override
    public ConcurrentHashMap<String, List<LawsuitExcelData>> generateLawsuitMonthlyReport(List<LawsuitExcelData> lawsuitExcelData) {
        return null;
    }

    @Override
    public ConcurrentHashMap<String, List<LawsuitExcelData>> generateLawsuitWeeklyReport(List<LawsuitExcelData> lawsuitExcelData) {
        return null;
    }

    @Override
    public ConcurrentHashMap<String, List<ExecutionProcessExcelData>> generateExecutionProcessMonthlyReport(List<ExecutionProcessExcelData> executionProcessExcelData) {
        return null;
    }

    @Override
    public ConcurrentHashMap<String, List<ExecutionProcessExcelData>> generateExecutionProcessWeeklyReport(List<ExecutionProcessExcelData> executionProcessExcelData) {
        return null;
    }
}
