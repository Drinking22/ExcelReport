package com.example.excel.report.services;

import com.example.excel.report.constant.titles.ExcelFileNameConst;
import com.example.excel.report.model.ExecutionProcessExcelData;
import com.example.excel.report.model.JudicialExcelData;
import com.example.excel.report.model.LawsuitExcelData;
import com.example.excel.report.services.checks.JudicialFilesCheckService;
import com.example.excel.report.services.parser.ExcelParserService;
import com.example.excel.report.services.report.JudicialExcelWriterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
@Slf4j
public class MainReportServiceImpl implements MainReportService {
    //Рекомендации
    //Логирование: В ваших методах generateWeekly... и generateMonthly... отсутствуют сообщения логирования.
    // Рекомендуется добавить их для лучшего отслеживания выполнения.
    //
    //Обработка исключений: Вместо того чтобы просто выбрасывать RuntimeException, вы можете создать собственный класс
    // исключений для более точной диагностики ошибок, связанных с отчетами.
    //
    //Проверка входных данных: Рассмотрите возможность добавления валидации для входных параметров в методах,
    // чтобы избежать возможных ошибок при выполнении.
    //
    //Упрощение методов: Если методы становятся слишком длинными, подумайте о том, чтобы выделить части логики в
    // отдельные приватные методы, чтобы улучшить читаемость.

    private final ExcelParserService excelParserService;
    private final JudicialFilesCheckService judicialFilesCheckService;
    private final JudicialExcelWriterService judicialExcelWriterService;

    @Override
    public FileOutputStream generateMonthlyJudicialReportFile(ExcelFileNameConst fileName, FileInputStream excelFile) {
        log.info("Starting process with judicial monthly report");

        try {
            List<JudicialExcelData> judicialExcelData = excelParserService.parseJudicialExcelFile(excelFile);

            ConcurrentHashMap<String, List<JudicialExcelData>> allReportsForMonthly =
                    judicialFilesCheckService.generateJudicialMonthlyReport(judicialExcelData);


            return writeJudicialReportInExcelFile(fileName, allReportsForMonthly);

        } catch (IOException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    public FileOutputStream generateWeeklyJudicialReportFile(ExcelFileNameConst fileName, FileInputStream excelFile) {
        log.info("Starting process with judicial weekly report");

        try {
            List<JudicialExcelData> judicialExcelData = excelParserService.parseJudicialExcelFile(excelFile);

            ConcurrentHashMap<String, List<JudicialExcelData>> allReportsForWeekly =
                    judicialFilesCheckService.generateJudicialWeeklyReport(judicialExcelData);

            return writeJudicialReportInExcelFile(fileName, allReportsForWeekly);

        } catch (IOException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    public FileOutputStream generateMonthlyLawsuitReportFile(ExcelFileNameConst fileName, FileInputStream excelFile) {
        log.info("Starting process with lawsuit monthly report");

        try {


        } catch (IOException ex) {
            throw new RuntimeException(ex.getMessage());
        }

        return null;
    }

    @Override
    public FileOutputStream generateWeeklyLawsuitReportFile(ExcelFileNameConst fileName, FileInputStream excelFile) {
        log.info("Starting process with lawsuit weekly report");

        try {

        } catch (IOException ex) {
            throw new RuntimeException(ex.getMessage());
        }

        return null;
    }

    @Override
    public FileOutputStream generateMonthlyExecutionProcessReportFile(ExcelFileNameConst fileName, FileInputStream excelFile) {
        log.info("Starting process with execution process monthly report");

        try {

        } catch (IOException ex) {
            throw new RuntimeException(ex.getMessage());
        }

        return null;
    }

    @Override
    public FileOutputStream generateWeeklyExecutionProcessReportFile(ExcelFileNameConst fileName, FileInputStream excelFile) {
        log.info("Starting process with execution process weekly report");

        try {

        } catch (IOException ex) {
            throw new RuntimeException(ex.getMessage());
        }

        return null;
    }

    private FileOutputStream writeJudicialReportInExcelFile(ExcelFileNameConst fileName, ConcurrentHashMap<String,
            List<JudicialExcelData>> allReports) {
        return judicialExcelWriterService.writeJudicialReportExcelFile(fileName, allReports);
    }

    private FileOutputStream writeLawsuitReportInExcelFile(ExcelFileNameConst fileName, ConcurrentHashMap<String,
            List<LawsuitExcelData>> allReports) {
        return judicialExcelWriterService.writeLawsuitReportExcelFile(fileName, allReports);
    }

    private FileOutputStream writeExecutionProcessReportInExcelFile(ExcelFileNameConst fileName, ConcurrentHashMap<String,
            List<ExecutionProcessExcelData>> allReports) {
        return judicialExcelWriterService.writeExecutionProcessReportExcelFile(fileName, allReports);
    }
}
