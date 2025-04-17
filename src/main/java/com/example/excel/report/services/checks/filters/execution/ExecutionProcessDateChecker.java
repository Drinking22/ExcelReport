package com.example.excel.report.services.checks.filters.execution;

import com.example.excel.report.services.checks.filters.abstracts.DateChecker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * Класс для проверки дат, связанных с исполнительным производством.
 * Содержит статические методы для проверки различных условий, касающихся дат отправки документов,
 * подачи заявлений, жалоб. Также информацию о проделанной работе с имуществом должника.
 */

@Slf4j
@Component
public class ExecutionProcessDateChecker extends DateChecker {

    private static final int MAXIMUM_DAYS_FOR_TRANSFER_OF_THE_WRIT_EXECUTION = 3;
    private static final int MAXIMUM_DAYS_FOR_WAITING_INITIATION = 10;

    @Override
    public boolean checkDate(LocalDateTime date, LocalDateTime start, LocalDateTime end) {
        return isDateInRange(date, start, end);
    }

    /**
     * Проверяет, находится ли дата подачи исполнительного листа в заданном диапазоне.
     *
     * @param dateOfSubmissionWritExecutionBailiffs дата подачи исполнительного листа.
     * @param start начальная дата диапазона.
     * @param end конечная дата диапазона.
     * @return true, если дата подачи исполнительного листа в заданном диапазоне.
     */
    public static boolean dateCheckerSubmittedForExecutionReport(LocalDateTime dateOfSubmissionWritExecutionBailiffs, LocalDateTime start, LocalDateTime end) {
        log.info("Checking date of submission writ execution. Date of submission: {}, Range: [{} - {}]", dateOfSubmissionWritExecutionBailiffs, start, end);
        return isDateInRange(dateOfSubmissionWritExecutionBailiffs, start, end);
    }

    /**
     * Проверяет, находится ли дата подачи заявления о ходе исполнительного производства в заданном диапазоне.
     *
     * @param dateOfFilingAnApplicationProgressOfEnforcementProceedings дата подачи заявления.
     * @param start начальная дата диапазона.
     * @param end конечная дата диапазона.
     * @return true, если дата подачи заявления в заданном диапазоне.
     */
    public static boolean dateCheckerNumberOfProgressReport(LocalDateTime dateOfFilingAnApplicationProgressOfEnforcementProceedings, LocalDateTime start, LocalDateTime end) {
        log.info("Checking date of submission number of progress. Date of submission: {}, Range: [{} - {}]", dateOfFilingAnApplicationProgressOfEnforcementProceedings, start, end);
        return isDateInRange(dateOfFilingAnApplicationProgressOfEnforcementProceedings, start, end);
    }

    /**
     * Проверяет, находится ли дата подачи жалобы в заданном диапазоне.
     *
     * @param dateOfFilingTheComplaint дата подачи жалобы.
     * @param start начальная дата диапазона.
     * @param end конечная дата диапазона.
     * @return true, если дата подачи жалобы в заданном диапазоне.
     */
    public static boolean dateCheckerNumberOfComplaintsFiledReport(LocalDateTime dateOfFilingTheComplaint, LocalDateTime start, LocalDateTime end) {
        log.info("Checking date of submission complaints. Date of submission: {}, Range: [{} - {}]", dateOfFilingTheComplaint, start, end);
        return isDateInRange(dateOfFilingTheComplaint, start, end);
    }

    /**
     * Проверяет, находится ли дата подачи жалобы в суд в заданном диапазоне.
     *
     * @param dateOfFilingAnApplicationToTheCourtCas дата подачи жалобы в суд.
     * @param start начальная дата диапазона.
     * @param end конечная дата диапазона.
     * @return true, если дата подачи жалобы в суд в заданном диапазоне.
     */
    public static boolean dateCheckerNumberOfComplaintsFiledInCourtReport(LocalDateTime dateOfFilingAnApplicationToTheCourtCas, LocalDateTime start, LocalDateTime end) {
        log.info("Checking date of submission complaints in court. Date of submission: {}, Range: [{} - {}]", dateOfFilingAnApplicationToTheCourtCas, start, end);
        return isDateInRange(dateOfFilingAnApplicationToTheCourtCas, start, end);
    }

    /**
     * Проверяет, прошло ли максимальное количество дней с момента получения исполнительного листа до его подачи.
     *
     * @param dateOfExecutionWrit дата получения исполнительного листа.
     * @param dateOfSubmissionWritExecutionBailiffs дата подачи исполнительного листа.
     * @return true, если прошло больше 3 дней для передачи исполнительного листа.
     */
    public static boolean dateCheckerReceivedButNotSubmittedForExecutionReport(LocalDateTime dateOfExecutionWrit, LocalDateTime dateOfSubmissionWritExecutionBailiffs) {
        log.info("Checking date of received but not submitted for execution. Date of received: {}, date of submitted: {}", dateOfExecutionWrit, dateOfSubmissionWritExecutionBailiffs);

        if (dateOfExecutionWrit == null) {
            return false;
        }

        if (dateOfSubmissionWritExecutionBailiffs == null) {
            return ChronoUnit.DAYS.between(dateOfExecutionWrit, LocalDateTime.now()) > MAXIMUM_DAYS_FOR_TRANSFER_OF_THE_WRIT_EXECUTION;
        }

        return ChronoUnit.DAYS.between(dateOfExecutionWrit, dateOfSubmissionWritExecutionBailiffs) > MAXIMUM_DAYS_FOR_TRANSFER_OF_THE_WRIT_EXECUTION;
    }

    /**
     * Проверяет, прошло ли максимальное количество дней с момента подачи исполнительного листа
     * до возбуждения исполнительного производства.
     *
     * @param dateOfSubmissionWritExecutionBailiffs дата подачи исполнительного листа.
     * @param dateOfInitiationOfEnforcementProceedings дата возбуждения исполнительного производства.
     * @return true, если прошло больше 10 дней для возбуждения исполнительного производства.
     */
    public static boolean dateCheckerDocumentFiledButNotInitiationReport(LocalDateTime dateOfSubmissionWritExecutionBailiffs, LocalDateTime dateOfInitiationOfEnforcementProceedings) {
        log.info("Checking date of filed but not initiation. Date of filing: {}, date of initiation: {}", dateOfSubmissionWritExecutionBailiffs, dateOfInitiationOfEnforcementProceedings);

        if (dateOfSubmissionWritExecutionBailiffs == null) {
            return false;
        }

        if (dateOfInitiationOfEnforcementProceedings == null) {
            return ChronoUnit.DAYS.between(dateOfSubmissionWritExecutionBailiffs, LocalDateTime.now()) > MAXIMUM_DAYS_FOR_WAITING_INITIATION;
        }

        return ChronoUnit.DAYS.between(dateOfSubmissionWritExecutionBailiffs, dateOfInitiationOfEnforcementProceedings) > MAXIMUM_DAYS_FOR_WAITING_INITIATION;
    }

    /**
     * Проверяет, находится ли дата выхода по адресу в заданном диапазоне.
     *
     * @param dateOfExitToAddress дата выхода по адресу
     * @param start начальная дата диапазона.
     * @param end конечная дата диапазона.
     * @return true, если дата выхода в адрес в заданном диапазоне.
     */
    public static boolean dateCheckerNumberOfOutputsReport(LocalDateTime dateOfExitToAddress, LocalDateTime start, LocalDateTime end) {
        log.info("Checking date of exit to address. Date of exit: {}, Range: [{} - {}]", dateOfExitToAddress, start, end);
        return isDateInRange(dateOfExitToAddress, start, end);
    }

    /**
     * Проверяет наличие информации по заданным данным.
     *
     * @param data данные для проверки.
     * @return true, если информация имеется.
     */
    public static boolean isAvailability(String data) {
        log.info("Check if information is available, for: {}", data);
        return data != null;
    }
}
