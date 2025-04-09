package com.example.excel.report.services.checks.filters.judicial;

import com.example.excel.report.model.JudicialExcelData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Реализация интерфейса {@link JudicialReportFilter} для генерации различных судебных отчетов.
 * Этот сервис фильтрует и обрабатывает списки {@link JudicialExcelData} на основе определенных критериев.
 */

@Slf4j
@Service
public class JudicialReportFilterImpl implements JudicialReportFilter{

    /**
     * Генерирует отчет о документах, отправленных должнику, но не поданных в суд.
     *
     * @param judicialExcelData список объектов {@link JudicialExcelData} для фильтрации.
     * @return список {@link JudicialExcelData}, которые были отправлены должнику, но не поданы в суд.
     */
    @Override
    public List<JudicialExcelData> generateSendToDebtorButNotFiledInCourtReport(List<JudicialExcelData> judicialExcelData) {
        log.info("Generating a report sent to the debtor, but not filed in court");
        return judicialExcelData.stream()
                .filter(data -> JudicialDateChecker.dateCheckerSendToDebtorButNotFiledInCourtReport(
                        data.getDateOfSendingCopiesOfDocuments(), data.getDateOfFilingAnApplicationToTheCourt()))
                .toList();
    }

    /**
     * Генерирует отчет о случаях, когда судебный приказ не был получен более 3 месяцев.
     *
     * @param judicialExcelData список объектов {@link JudicialExcelData} для фильтрации.
     * @return список {@link JudicialExcelData}, где судебный приказ не был получен более 3 месяцев.
     */
    @Override
    public List<JudicialExcelData> generateCourtOrderNotReceivedReport(List<JudicialExcelData> judicialExcelData) {
        log.info("Generating a report no court order received for more than 3 months");
        return judicialExcelData.stream()
                .filter(data -> JudicialDateChecker.dateCheckerCourtOrderNotReceivedReport(
                        data.getDateOfFilingAnApplicationToTheCourt(), data.getDateOfDetermination(),
                        data.getDateOfReceiptOfCourtOrder()
                ))
                .toList();
    }

    /**
     * Генерирует отчет о количестве заявлений, отправленных должнику в указанный диапазон дат.
     *
     * @param judicialExcelData список объектов {@link JudicialExcelData} для фильтрации.
     * @param start начальная дата диапазона.
     * @param end конечная дата диапазона.
     * @return список {@link JudicialExcelData}, где документы были отправлены должнику в указанный диапазон дат.
     */
    @Override
    public List<JudicialExcelData> generateCopiesOfDocumentsSentReport(List<JudicialExcelData> judicialExcelData, LocalDateTime start, LocalDateTime end) {
        log.info("Generating a report on the number of applications sent to the debtor");
        return judicialExcelData.stream()
                .filter(data -> JudicialDateChecker.dateCheckerCopiesOfDocumentsSentReport(
                        data.getDateOfSendingCopiesOfDocuments(), start, end))
                .toList();
    }

    /**
     * Генерирует отчет о поданных в суд заявлениях в определенный период.
     *
     * @param judicialExcelData список объектов {@link JudicialExcelData} для фильтрации.
     * @param start начальная дата диапазона.
     * @param end конечная дата диапазона.
     * @return список {@link JudicialExcelData}, где заявления были поданы в суд в указанный диапазон дат.
     */
    @Override
    public List<JudicialExcelData> generateApplicationsSubmittedToCourtReport(List<JudicialExcelData> judicialExcelData, LocalDateTime start, LocalDateTime end) {
        log.info("Generating a report applications submitted to court");
        return judicialExcelData.stream()
                .filter(data -> JudicialDateChecker.dateCheckerApplicationsSubmittedToCourtReport(
                        data.getDateOfFilingAnApplicationToTheCourt(), start, end))
                .toList();
    }

    /**
     * Генерирует отчет о случаях отмены судебного приказа без подачи иска.
     *
     * @param judicialExcelData список объектов {@link JudicialExcelData} для фильтрации.
     * @return список {@link JudicialExcelData}, где судебный приказ был отменен, но иск не был подан.
     */
    @Override
    public List<JudicialExcelData> generateCancellationOfTheCourtOrderButNoLawsuitFiledReport(List<JudicialExcelData> judicialExcelData) {
        log.info("Generating a report cancellation of the court order but no lawsuit filed");
        return judicialExcelData.stream()
                .filter(data -> JudicialDateChecker.dateCheckerCancellationOfTheCourtOrderButNoLawsuitFiledReport(
                        data.getDeterminationToCancelTheJointVenture(), data.getDateOfFilingAnApplicationInTheClaimProcedure()))
                .toList();
    }

    /**
     * Генерирует отчет о возврате документов из суда в указанный диапазон дат.
     *
     * @param judicialExcelData список объектов {@link JudicialExcelData} для фильтрации.
     * @param start начальная дата диапазона.
     * @param end конечная дата диапазона.
     * @return список {@link JudicialExcelData}, где документы были возвращены из суда в указанный диапазон дат.
     */
    @Override
    public List<JudicialExcelData> generateReturnOfDocumentsFromTheCourtReport(List<JudicialExcelData> judicialExcelData, LocalDateTime start, LocalDateTime end) {
        log.info("Generating a report return of documents from the court");
        return judicialExcelData.stream()
                .filter(data -> JudicialDateChecker.dateCheckerReturnOfDocumentsFromTheCourtReport(
                        data.getDateOfDetermination(), data.getDeterminationOnTheReturnOfTheApplication(), start, end))
                .toList();
    }

    /**
     * Генерирует отчет о полученных судебных приказах в указанный диапазон дат.
     *
     * @param judicialExcelData список объектов {@link JudicialExcelData} для фильтрации.
     * @param start начальная дата диапазона.
     * @param end конечная дата диапазона.
     * @return список {@link JudicialExcelData}, где судебные приказы были получены в указанный диапазон дат.
     */
    @Override
    public List<JudicialExcelData> generateReceivedCourtOrderReport(List<JudicialExcelData> judicialExcelData, LocalDateTime start, LocalDateTime end) {
        log.info("Generating a report received court order");
        return judicialExcelData.stream()
                .filter(data -> JudicialDateChecker.dateCheckerReceivedCourtOrderReport(
                        data.getDateOfReceiptOfCourtOrder(), start, end))
                .toList();
    }
}
