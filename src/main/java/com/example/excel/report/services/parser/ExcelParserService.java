package com.example.excel.report.services.parser;

import com.example.excel.report.model.ExecutionProcessExcelData;
import com.example.excel.report.model.JudicialExcelData;
import com.example.excel.report.model.LawsuitExcelData;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public interface ExcelParserService {
    List<JudicialExcelData> parseJudicialExcelFileWithoutRepaymentOfDebt(FileInputStream excelFile) throws IOException;
    List<LawsuitExcelData> parseLawsuitExcelFileWithoutRepaymentOfDebt(FileInputStream excelFile) throws IOException;
    List<ExecutionProcessExcelData> parseExecutionProcessExcelFileWithoutRepaymentOfDebt(FileInputStream excelFile) throws IOException;
}
