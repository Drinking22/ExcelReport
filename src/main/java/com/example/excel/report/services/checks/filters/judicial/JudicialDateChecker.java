package com.example.excel.report.services.checks.filters.judicial;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * Класс для проверки дат, связанных с судебными отчетами.
 * Содержит статические методы для проверки различных условий, касающихся дат отправки документов,
 * подачи заявлений в суд и получения судебных приказов.
 */
@Component
public class JudicialDateChecker {

    /**
     * Проверяет, были ли копии документов отправлены должнику, но не поданы в суд.
     *
     * @param dateOfSendingCopiesOfDocuments дата отправки копий документов.
     * @param dateOfFilingAnApplicationToTheCourt дата подачи заявления в суд.
     * @return true, если копии документов были отправлены более 7 дней назад и заявление не подано.
     */
    public static boolean dateCheckerSendToDebtorButNotFiledInCourtReport(LocalDateTime dateOfSendingCopiesOfDocuments,
                                                                          LocalDateTime dateOfFilingAnApplicationToTheCourt) {
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
    public static boolean dateCheckerGenerateCourtOrderNotReceivedReport(LocalDateTime dateOfFilingAnApplicationToTheCourt,
                                                                         LocalDateTime dateOfDetermination, LocalDateTime dateOfReceiptOfCourtOrder) {
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
    public static boolean dateCheckerCopiesOfDocumentsSent(LocalDateTime dateOfSendingCopiesOfDocuments, LocalDateTime start, LocalDateTime end) {
        return dateOfSendingCopiesOfDocuments != null &&
                (dateOfSendingCopiesOfDocuments.isEqual(start) ||
                        dateOfSendingCopiesOfDocuments.isEqual(end) ||
                        (dateOfSendingCopiesOfDocuments.isAfter(start) && dateOfSendingCopiesOfDocuments.isBefore(end)));
    }

    /**
     * Проверяет, были ли заявления поданы в суд в указанный диапазон дат.
     *
     * @param dateOfFilingAnApplicationToTheCourt дата подачи заявления в суд.
     * @param start начальная дата диапазона.
     * @param end конечная дата диапазона.
     * @return true, если заявление подано в указанный диапазон дат.
     */
    public static boolean dateCheckerApplicationsSubmittedToCourt(LocalDateTime dateOfFilingAnApplicationToTheCourt, LocalDateTime start, LocalDateTime end) {
        return dateOfFilingAnApplicationToTheCourt != null &&
                (dateOfFilingAnApplicationToTheCourt.isEqual(start) ||
                        dateOfFilingAnApplicationToTheCourt.isEqual(end) ||
                        (dateOfFilingAnApplicationToTheCourt.isAfter(start) && dateOfFilingAnApplicationToTheCourt.isBefore(end)));
    }

    /**
     * Проверяет, был ли судебный приказ отменен без подачи иска.
     *
     * @param determinationToCancelTheJointVenture информация о решении об отмене.
     * @param dateOfFilingAnApplicationInTheClaimProcedure дата подачи заявления в судебном процессе.
     * @return true, если решение об отмене существует, но иск не подан.
     */
    public static boolean dateCheckerCancellationOfTheCourtOrderButNoLawsuitFiled(String determinationToCancelTheJointVenture, LocalDateTime dateOfFilingAnApplicationInTheClaimProcedure) {
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
    public static boolean dateCheckerReturnOfDocumentsFromTheCourt(LocalDateTime dateOfDetermination, String determinationOnTheReturnOfTheApplication, LocalDateTime start, LocalDateTime end) {
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
    public static boolean dateCheckerReceivedCourtOrder(LocalDateTime dateOfReceiptOfCourtOrder, LocalDateTime start, LocalDateTime end) {
        return dateOfReceiptOfCourtOrder != null &&
                (dateOfReceiptOfCourtOrder.isEqual(start) ||
                        dateOfReceiptOfCourtOrder.isEqual(end) ||
                        (dateOfReceiptOfCourtOrder.isAfter(start) && dateOfReceiptOfCourtOrder.isBefore(end)));
    }
}
