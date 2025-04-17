package com.example.excel.report.services.checks.filters.execution;

import com.example.excel.report.model.ExecutionProcessExcelData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Реализация интерфейса {@link ExecutionProcessReportFilter} для генерации различных судебных отчетов.
 * Этот сервис фильтрует и обрабатывает списки {@link ExecutionProcessExcelData} на основе определенных критериев.
 */

@Service
@Slf4j
public class ExecutionProcessReportFilterImpl implements ExecutionProcessReportFilter {

    /**
     * Генерирует отчет о количестве поданных на исполнение исполнительных документов в указанный диапазон дат.
     *
     * @param executionProcessExcelData список объектов {@link ExecutionProcessExcelData} для фильтрации.
     * @param start начальная дата диапазона.
     * @param end конечная дата диапазона.
     * @return список {@link ExecutionProcessExcelData} поданных на исполнение.
     */
    @Override
    public List<ExecutionProcessExcelData> generateSubmittedForExecutionReport(List<ExecutionProcessExcelData> executionProcessExcelData, LocalDateTime start, LocalDateTime end) {
        log.info("Generate submitted for execution process report");
        return executionProcessExcelData.stream()
                .filter(data -> ExecutionProcessDateChecker.dateCheckerSubmittedForExecutionReport(
                        data.getDateOfSubmissionWritExecutionBailiffs(), start, end))
                .toList();
    }

    /**
     * Генерирует отчет о полученных документах, но не поданных на исполнение.
     *
     * @param executionProcessExcelData список объектов {@link ExecutionProcessExcelData} для фильтрации.
     * @return список {@link ExecutionProcessExcelData} не поданных на исполнение.
     */
    @Override
    public List<ExecutionProcessExcelData> generateDocumentReceivedButNotSubmittedForExecutionReport(List<ExecutionProcessExcelData> executionProcessExcelData) {
        log.info("Generate document received but not submitted for execution process report");
        return executionProcessExcelData.stream()
                .filter(data -> ExecutionProcessDateChecker.dateCheckerReceivedButNotSubmittedForExecutionReport(
                        data.getDateOfExecutionWrit(), data.getDateOfSubmissionWritExecutionBailiffs()))
                .toList();
    }

    /**
     * Генерирует отчет о поданных документах на исполнение, но нет возбужденных исполнительных производств.
     *
     * @param executionProcessExcelData список объектов {@link ExecutionProcessExcelData} для фильтрации.
     * @return список {@link ExecutionProcessExcelData} нет возбужденных исполнительных производств.
     */
    @Override
    public List<ExecutionProcessExcelData> generateDocumentFiledButNotInitiationReport(List<ExecutionProcessExcelData> executionProcessExcelData) {
        log.info("Generate document filed but not initiation for execution process report");
        return executionProcessExcelData.stream()
                .filter(data -> ExecutionProcessDateChecker.dateCheckerDocumentFiledButNotInitiationReport(
                        data.getDateOfSubmissionWritExecutionBailiffs(), data.getDateOfInitiationOfEnforcementProceedings()))
                .toList();
    }

    /**
     * Генерирует отчет о количестве поданных заявлений о ходе исполнительного производства.
     *
     * @param executionProcessExcelData список объектов {@link ExecutionProcessExcelData} для фильтрации.
     * @param start начальная дата диапазона.
     * @param end конечная дата диапазона.
     * @return список {@link ExecutionProcessExcelData} заявлений о ходе исполнительного производства.
     */
    @Override
    public List<ExecutionProcessExcelData> generateNumberOfProgressReport(List<ExecutionProcessExcelData> executionProcessExcelData, LocalDateTime start, LocalDateTime end) {
        log.info("Generate number of progress for execution process report");
        return executionProcessExcelData.stream()
                .filter(data -> ExecutionProcessDateChecker.dateCheckerNumberOfProgressReport(
                        data.getDateOfFilingAnApplicationProgressOfEnforcementProceedings(), start, end))
                .toList();
    }

    /**
     * Генерирует отчет о количестве поданных жалоб.
     *
     * @param executionProcessExcelData список объектов {@link ExecutionProcessExcelData} для фильтрации.
     * @param start начальная дата диапазона.
     * @param end конечная дата диапазона.
     * @return список {@link ExecutionProcessExcelData} поданных жалоб.
     */
    @Override
    public List<ExecutionProcessExcelData> generateNumberOfComplaintsFiledReport(List<ExecutionProcessExcelData> executionProcessExcelData, LocalDateTime start, LocalDateTime end) {
        log.info("Generate number of complaints filed for execution process report");
        return executionProcessExcelData.stream()
                .filter(data -> ExecutionProcessDateChecker.dateCheckerNumberOfComplaintsFiledReport(
                        data.getDateOfFilingTheComplaint(), start, end))
                .toList();
    }

    /**
     * Генерирует отчет о количестве поданных жалоб в суд.
     *
     * @param executionProcessExcelData список объектов {@link ExecutionProcessExcelData} для фильтрации.
     * @param start начальная дата диапазона.
     * @param end конечная дата диапазона.
     * @return список {@link ExecutionProcessExcelData} поданных жалоб в суд.
     */
    @Override
    public List<ExecutionProcessExcelData> generateNumberOfComplaintsFiledInCourtReport(List<ExecutionProcessExcelData> executionProcessExcelData, LocalDateTime start, LocalDateTime end) {
        log.info("Generate number of complaints filed in court for execution process report");
        return executionProcessExcelData.stream()
                .filter(data -> ExecutionProcessDateChecker.dateCheckerNumberOfComplaintsFiledInCourtReport(
                        data.getDateOfFilingAnApplicationToTheCourtCas(), start, end))
                .toList();
    }

    /**
     * Генерирует отчет по работе с местом дохода должника.
     *
     * @param executionProcessExcelData список объектов {@link ExecutionProcessExcelData} для фильтрации.
     * @return список {@link ExecutionProcessExcelData} с местом дохода должника.
     */
    @Override
    public List<ExecutionProcessExcelData> generateControlOfTheWorkplaceReport(List<ExecutionProcessExcelData> executionProcessExcelData) {
        log.info("Generate control of the workplace for execution process report");
        return executionProcessExcelData.stream()
                .filter(data -> ExecutionProcessDateChecker.isAvailability(data.getAvailabilityOfPlaceOfWork()))
                .toList();
    }

    /**
     * Генерирует отчет по работе с транспортными средствами должника.
     *
     * @param executionProcessExcelData список объектов {@link ExecutionProcessExcelData} для фильтрации.
     * @return список {@link ExecutionProcessExcelData} с транспортными средствами должника.
     */
    @Override
    public List<ExecutionProcessExcelData> generateVehicleControlReport(List<ExecutionProcessExcelData> executionProcessExcelData) {
        log.info("Generate vehicle control for execution process report");
        return executionProcessExcelData.stream()
                .filter(data -> ExecutionProcessDateChecker.isAvailability(data.getPresenceVehicle()))
                .toList();
    }

    /**
     * Генерирует отчет по работе с недвижимостью должника.
     *
     * @param executionProcessExcelData список объектов {@link ExecutionProcessExcelData} для фильтрации.
     * @return список {@link ExecutionProcessExcelData} с недвижимостью должника.
     */
    @Override
    public List<ExecutionProcessExcelData> generatePropertyControlReport(List<ExecutionProcessExcelData> executionProcessExcelData) {
        log.info("Generate property control for execution process report");
        return executionProcessExcelData.stream()
                .filter(data -> ExecutionProcessDateChecker.isAvailability(data.getPresenceOfRealEstate()))
                .toList();
    }

    /**
     * Генерирует отчет о количестве выходов в адрес должника.
     *
     * @param executionProcessExcelData список объектов {@link ExecutionProcessExcelData} для фильтрации.
     * @param start начальная дата диапазона.
     * @param end конечная дата диапазона.
     * @return список {@link ExecutionProcessExcelData} выходов в адрес должника.
     */
    @Override
    public List<ExecutionProcessExcelData> generateNumberOfOutputsReport(List<ExecutionProcessExcelData> executionProcessExcelData, LocalDateTime start, LocalDateTime end) {
        log.info("Generate number of outputs for execution process report");
        return executionProcessExcelData.stream()
                .filter(data -> ExecutionProcessDateChecker.dateCheckerNumberOfOutputsReport(
                        data.getDateOfExitToAddress(), start, end))
                .toList();
    }
}
