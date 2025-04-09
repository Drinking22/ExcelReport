package com.example.excel.report.services.checks.filters.judicial;

import com.example.excel.report.services.checks.filters.judicial.abstracts.DateChecker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * Класс для проверки дат, связанных с судебными отчетами.
 * Содержит статические методы для проверки различных условий, касающихся дат отправки документов,
 * подачи заявлений в суд и получения судебных приказов.
 */
@Slf4j
@Component
public class JudicialDateChecker extends DateChecker {

    @Override
    public boolean checkDate(LocalDateTime date, LocalDateTime start, LocalDateTime end) {
        return isDateInRange(date, start, end);
    }

    /**
     * Проверяет, были ли копии документов отправлены должнику, но не поданы в суд.
     *
     * @param dateOfSendingCopiesOfDocuments дата отправки копий документов.
     * @param dateOfFilingAnApplicationToTheCourt дата подачи заявления в суд.
     * @return true, если копии документов были отправлены более 7 дней назад и заявление не подано.
     */
    public static boolean dateCheckerSendToDebtorButNotFiledInCourtReport(LocalDateTime dateOfSendingCopiesOfDocuments,
                                                                          LocalDateTime dateOfFilingAnApplicationToTheCourt) {
        log.info("Checking send to debtor but not filed in court. Date sending: {}, date of filing: {}", dateOfSendingCopiesOfDocuments, dateOfFilingAnApplicationToTheCourt);
        return dateOfSendingCopiesOfDocuments != null &&
                dateOfFilingAnApplicationToTheCourt == null &&
                ChronoUnit.DAYS.between(dateOfSendingCopiesOfDocuments, LocalDateTime.now()) >= 7;
    }

    /**
     * Проверяет, был ли судебный приказ получен более 3 месяцев назад.
     *
     * @param dateOfFilingAnApplicationToTheCourt дата подачи заявления в суд.
     * @param dateOfDetermination дата определения.
     * @param dateOfReceiptOfCourtOrder дата получения судебного приказа.
     * @return true, если заявление подано, но судебный приказ не получен более 3 месяцев.
     */
    public static boolean dateCheckerCourtOrderNotReceivedReport(LocalDateTime dateOfFilingAnApplicationToTheCourt,
                                                                 LocalDateTime dateOfDetermination, LocalDateTime dateOfReceiptOfCourtOrder) {
        log.info("Checking if court order not received. Date of filing: {}, Date of determination: {}, Date of receipt: {}",
                dateOfFilingAnApplicationToTheCourt, dateOfDetermination, dateOfReceiptOfCourtOrder);

        return dateOfFilingAnApplicationToTheCourt != null &&
                dateOfDetermination == null && dateOfReceiptOfCourtOrder == null
                && ChronoUnit.MONTHS.between(dateOfFilingAnApplicationToTheCourt, LocalDateTime.now()) > 3;
    }

    /**
     * Проверяет, были ли копии документов отправлены в указанный диапазон дат.
     *
     * @param dateOfSendingCopiesOfDocuments дата отправки копий документов.
     * @param start начальная дата диапазона.
     * @param end конечная дата диапазона.
     * @return true, если документы были отправлены в указанный диапазон дат.
     */
    public static boolean dateCheckerCopiesOfDocumentsSentReport(LocalDateTime dateOfSendingCopiesOfDocuments, LocalDateTime start, LocalDateTime end) {
        log.info("Checking if copies of documents were sent. Date of sending: {}, Range: [{} - {}]", dateOfSendingCopiesOfDocuments, start, end);
        return isDateInRange(dateOfSendingCopiesOfDocuments, start, end);
    }

    /**
     * Проверяет, были ли заявления поданы в суд в указанный диапазон дат.
     *
     * @param dateOfFilingAnApplicationToTheCourt дата подачи заявления в суд.
     * @param start начальная дата диапазона.
     * @param end конечная дата диапазона.
     * @return true, если заявление подано в указанный диапазон дат.
     */
    public static boolean dateCheckerApplicationsSubmittedToCourtReport(LocalDateTime dateOfFilingAnApplicationToTheCourt, LocalDateTime start, LocalDateTime end) {
        log.info("Checking if applications were submitted to court. Date of filing: {}, Range: [{} - {}]", dateOfFilingAnApplicationToTheCourt, start, end);
        return isDateInRange(dateOfFilingAnApplicationToTheCourt, start, end);
    }

    /**
     * Проверяет, был ли судебный приказ отменен без подачи иска.
     *
     * @param determinationToCancelTheJointVenture информация о решении об отмене.
     * @param dateOfFilingAnApplicationInTheClaimProcedure дата подачи заявления в судебном процессе.
     * @return true, если решение об отмене существует, но иск не подан.
     */
    public static boolean dateCheckerCancellationOfTheCourtOrderButNoLawsuitFiledReport(String determinationToCancelTheJointVenture, LocalDateTime dateOfFilingAnApplicationInTheClaimProcedure) {
        log.info("Checking cancellation of court order with no lawsuit filed. Determination: '{}', Date of filing: {}",
                determinationToCancelTheJointVenture, dateOfFilingAnApplicationInTheClaimProcedure);

        return determinationToCancelTheJointVenture != null && dateOfFilingAnApplicationInTheClaimProcedure == null;
    }

    /**
     * Проверяет, были ли документы возвращены из суда в указанный диапазон дат.
     *
     * @param dateOfDetermination дата определения.
     * @param determinationOnTheReturnOfTheApplication информация о решении о возврате заявления.
     * @param start начальная дата диапазона.
     * @param end конечная дата диапазона.
     * @return true, если документы были возвращены в указанный диапазон дат.
     */
    public static boolean dateCheckerReturnOfDocumentsFromTheCourtReport(LocalDateTime dateOfDetermination, String determinationOnTheReturnOfTheApplication, LocalDateTime start, LocalDateTime end) {
        log.info("Checking return of documents from court. Date of determination: {}, Determination: '{}', Range: [{} - {}]",
                dateOfDetermination, determinationOnTheReturnOfTheApplication, start, end);

        return dateOfDetermination != null && determinationOnTheReturnOfTheApplication != null &&
        (dateOfDetermination.isEqual(start) ||
                dateOfDetermination.isEqual(end) ||
                (dateOfDetermination.isAfter(start) && dateOfDetermination.isBefore(end)));
    }

    /**
     * Проверяет, был ли судебный приказ получен в указанный диапазон дат.
     *
     * @param dateOfReceiptOfCourtOrder дата получения судебного приказа.
     * @param start начальная дата диапазона.
     * @param end конечная дата диапазона.
     * @return true, если судебный приказ был получен в указанный диапазон дат.
     */
    public static boolean dateCheckerReceivedCourtOrderReport(LocalDateTime dateOfReceiptOfCourtOrder, LocalDateTime start, LocalDateTime end) {
        log.info("Checking receipt of court order. Date of receipt: {}, Range: [{} - {}]",
                dateOfReceiptOfCourtOrder, start, end);
        return isDateInRange(dateOfReceiptOfCourtOrder, start, end);
    }
}
