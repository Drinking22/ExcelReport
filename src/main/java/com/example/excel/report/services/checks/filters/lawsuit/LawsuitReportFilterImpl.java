package com.example.excel.report.services.checks.filters.lawsuit;

import com.example.excel.report.model.JudicialExcelData;
import com.example.excel.report.model.LawsuitExcelData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Реализация интерфейса {@link LawsuitReportFilter} для генерации отчетов по судебным делам.
 * Этот сервис фильтрует и обрабатывает списки {@link LawsuitExcelData} на основе определенных критериев.
 */
@Service
@Slf4j
public class LawsuitReportFilterImpl implements LawsuitReportFilter {

    /**
     * Генерирует отчет о поданных исках в указанный период.
     *
     * @param lawsuitExcelData список объектов {@link LawsuitExcelData} для фильтрации.
     * @param start начальная дата диапазона.
     * @param end конечная дата диапазона.
     * @return Список данных судебных дел, соответствующих критериям фильтрации.
     */
    @Override
    public List<LawsuitExcelData> generateClaimFiledReport(List<LawsuitExcelData> lawsuitExcelData, LocalDateTime start, LocalDateTime end) {
        log.info("Generating a report claim filed");
        return lawsuitExcelData.stream()
                .filter(data -> LawsuitDateChecker.dateCheckerClaimFiledReport(
                        data.getDateOfFilingAnApplicationInTheClaimProcedure(), start, end))
                .toList();
    }

    /**
     * Генерирует отчет о дате рассмотрения дел.
     *
     * @param lawsuitExcelData список объектов {@link LawsuitExcelData} для фильтрации.
     * @param now              Текущая дата для фильтрации.
     * @return Список данных судебных дел, соответствующих критериям фильтрации.
     */
    @Override
    public List<LawsuitExcelData> generateDateOfReviewReport(List<LawsuitExcelData> lawsuitExcelData, LocalDateTime now) {
        log.info("Generating a report date of review");
        return lawsuitExcelData.stream()
                .filter(data -> LawsuitDateChecker.dateCheckerDateOfReviewReport(
                        data.getDateCaseReview(), now))
                .toList();
    }

    /**
     * Генерирует отчет о полученных исполнительных листах в указанный период.
     *
     * @param lawsuitExcelData список объектов {@link LawsuitExcelData} для фильтрации.
     * @param start начальная дата диапазона.
     * @param end конечная дата диапазона.
     * @return Список данных судебных дел, соответствующих критериям фильтрации.
     */
    @Override
    public List<LawsuitExcelData> generateReceivedWritsOfExecutionReport(List<LawsuitExcelData> lawsuitExcelData, LocalDateTime start, LocalDateTime end) {
        log.info("Generating a report received writs of execution");
        return lawsuitExcelData.stream()
                .filter(data -> LawsuitDateChecker.dateCheckerReceivedWritsOfExecutionReport(
                        data.getDateOfReceiptExecution(), start, end))
                .toList();
    }
}
