package com.example.excel.report.services.checks;

import com.example.excel.report.model.ExecutionProcessExcelData;
import com.example.excel.report.model.JudicialExcelData;
import com.example.excel.report.model.LawsuitExcelData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExcelCheckServiceImpl implements ExcelCheckService {

    private Map<String, JudicialExcelData> allReports;


    @Override
    public Map<String, List<JudicialExcelData>> generateJudicialMonthlyReport(List<JudicialExcelData> judicialExcelData) {
        return null;
    }

    @Override
    public Map<String, List<JudicialExcelData>> generateJudicialWeeklyReport(List<JudicialExcelData> judicialExcelData) {
        return null;
    }

    @Override
    public Map<String, List<LawsuitExcelData>> generateLawsuitMonthlyReport(List<LawsuitExcelData> lawsuitExcelData) {
        return null;
    }

    @Override
    public Map<String, List<LawsuitExcelData>> generateLawsuitWeeklyReport(List<LawsuitExcelData> lawsuitExcelData) {
        return null;
    }

    @Override
    public Map<String, List<ExecutionProcessExcelData>> generateExecutionProcessMonthlyReport(List<ExecutionProcessExcelData> executionProcessExcelData) {
        return null;
    }

    @Override
    public Map<String, List<ExecutionProcessExcelData>> generateExecutionProcessWeeklyReport(List<ExecutionProcessExcelData> executionProcessExcelData) {
        return null;
    }
}
