package com.example.excel.report.services;

import com.example.excel.report.constant.titles.ExcelFileNameConst;
import com.example.excel.report.exceptions.ReportGenerationException;
import com.example.excel.report.model.ExecutionProcessExcelData;
import com.example.excel.report.model.JudicialExcelData;
import com.example.excel.report.model.LawsuitExcelData;
import com.example.excel.report.services.checks.ExcelCheckService;
import com.example.excel.report.services.parser.ExcelParserService;
import com.example.excel.report.services.report.ExcelWriterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class MainReportServiceImpl implements MainReportService {
    //Проверка входных данных: Рассмотрите возможность добавления валидации для входных параметров в методах,
    // чтобы избежать возможных ошибок при выполнении.

    private final ExcelParserService excelParserService;
    private final ExcelCheckService excelCheckService;
    private final ExcelWriterService excelWriterService;

    @Override
    public FileOutputStream generateMonthlyJudicialReportFile(ExcelFileNameConst fileName, FileInputStream excelFile) {
        log.info("Starting process with judicial monthly report");

        try {
            List<JudicialExcelData> judicialExcelData = parseJudicialData(excelFile);

            Map<String, List<JudicialExcelData>> allReportsForMonthly =
                    excelCheckService.generateJudicialMonthlyReport(judicialExcelData);


            return writeJudicialReportInExcelFile(fileName, allReportsForMonthly);

        } catch (IOException ex) {
            throw new ReportGenerationException("Error generating monthly judicial report: " + ex.getMessage());
        }
    }

    @Override
    public FileOutputStream generateWeeklyJudicialReportFile(ExcelFileNameConst fileName, FileInputStream excelFile) {
        log.info("Starting process with judicial weekly report");

        try {
            List<JudicialExcelData> judicialExcelData = parseJudicialData(excelFile);

            Map<String, List<JudicialExcelData>> allReportsForWeekly =
                    excelCheckService.generateJudicialWeeklyReport(judicialExcelData);

            return writeJudicialReportInExcelFile(fileName, allReportsForWeekly);

        } catch (IOException ex) {
            throw new ReportGenerationException("Error generating weekly judicial report: " + ex.getMessage());
        }
    }

    @Override
    public FileOutputStream generateMonthlyLawsuitReportFile(ExcelFileNameConst fileName, FileInputStream excelFile) {
        log.info("Starting process with lawsuit monthly report");

        try {
            List<LawsuitExcelData> lawsuitExcelData = parseLawsuitData(excelFile);

            Map<String, List<LawsuitExcelData>> allReportsForMonthly =
                    excelCheckService.generateLawsuitMonthlyReport(lawsuitExcelData);

            return writeLawsuitReportInExcelFile(fileName, allReportsForMonthly);

        } catch (IOException ex) {
            throw new ReportGenerationException("Error generating monthly lawsuit report: " + ex.getMessage());
        }
    }

    @Override
    public FileOutputStream generateWeeklyLawsuitReportFile(ExcelFileNameConst fileName, FileInputStream excelFile) {
        log.info("Starting process with lawsuit weekly report");

        try {
            List<LawsuitExcelData> lawsuitExcelData = parseLawsuitData(excelFile);

            Map<String, List<LawsuitExcelData>> allReportsForWeekly =
                    excelCheckService.generateLawsuitWeeklyReport(lawsuitExcelData);

            return writeLawsuitReportInExcelFile(fileName, allReportsForWeekly);

        } catch (IOException ex) {
            throw new RuntimeException("Error generating weekly lawsuit report: " + ex.getMessage());
        }
    }

    @Override
    public FileOutputStream generateMonthlyExecutionProcessReportFile(ExcelFileNameConst fileName, FileInputStream excelFile) {
        log.info("Starting process with execution process monthly report");

        try {
            List<ExecutionProcessExcelData> executionProcessExcelData = parseExecutionProcessData(excelFile);

            Map<String, List<ExecutionProcessExcelData>> allReportsForMonthly =
                    excelCheckService.generateExecutionProcessMonthlyReport(executionProcessExcelData);

            return writeExecutionProcessReportInExcelFile(fileName, allReportsForMonthly);
        } catch (IOException ex) {
            throw new RuntimeException("Error generating monthly execution process report: " + ex.getMessage());
        }
    }

    @Override
    public FileOutputStream generateWeeklyExecutionProcessReportFile(ExcelFileNameConst fileName, FileInputStream excelFile) {
        log.info("Starting process with execution process weekly report");

        try {
            List<ExecutionProcessExcelData> executionProcessExcelData = parseExecutionProcessData(excelFile);

            Map<String, List<ExecutionProcessExcelData>> allReportsForWeekly =
                    excelCheckService.generateExecutionProcessWeeklyReport(executionProcessExcelData);

            return writeExecutionProcessReportInExcelFile(fileName, allReportsForWeekly);

        } catch (IOException ex) {
            throw new RuntimeException("Error generating weekly execution process report: " + ex.getMessage());
        }
    }

    private List<JudicialExcelData> parseJudicialData(FileInputStream excelFile) throws IOException {
        return excelParserService.parseJudicialExcelFileWithoutRepaymentOfDebt(excelFile);
    }

    private List<LawsuitExcelData> parseLawsuitData(FileInputStream excelFile) throws IOException {
        return excelParserService.parseLawsuitExcelFileWithoutRepaymentOfDebt(excelFile);
    }

    private List<ExecutionProcessExcelData> parseExecutionProcessData(FileInputStream excelFile) throws IOException {
        return excelParserService.parseExecutionProcessExcelFileWithoutRepaymentOfDebt(excelFile);
    }

    private FileOutputStream writeJudicialReportInExcelFile(ExcelFileNameConst fileName,
                                                            Map<String, List<JudicialExcelData>> allReports) {
        return excelWriterService.writeJudicialReportExcelFile(fileName, allReports);
    }

    private FileOutputStream writeLawsuitReportInExcelFile(ExcelFileNameConst fileName,
                                                           Map<String, List<LawsuitExcelData>> allReports) {
        return excelWriterService.writeLawsuitReportExcelFile(fileName, allReports);
    }

    private FileOutputStream writeExecutionProcessReportInExcelFile(ExcelFileNameConst fileName,
                                                                    Map<String, List<ExecutionProcessExcelData>> allReports) {
        return excelWriterService.writeExecutionProcessReportExcelFile(fileName, allReports);
    }
}
