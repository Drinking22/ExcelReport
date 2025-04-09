package com.example.excel.report.services.checks.filters.lawsuit;

import com.example.excel.report.services.checks.filters.judicial.abstracts.DateChecker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Класс для проверки дат по судебным делам.
 * Содержит статические методы для проверки различных условий, касающихся дат отправки документов,
 * подачи заявлений в суд и получения исполнительных документов.
 */
@Slf4j
@Component
public class LawsuitDateChecker extends DateChecker {

    @Override
    public boolean checkDate(LocalDateTime date, LocalDateTime start, LocalDateTime end) {
        return isDateInRange(date, start, end);
    }

    /**
     * Проверяет, было ли подано исковое заявление в указанный период.
     *
     * @param dateOfFilingAnApplicationInTheClaimProcedure дата подачи заявления.
     * @param start начальная дата диапазона.
     * @param end конечная дата диапазона.
     * @return true, если заявление подано в указанный диапазон дат.
     */
    public static boolean dateCheckerClaimFiledReport(LocalDateTime dateOfFilingAnApplicationInTheClaimProcedure,
                                                      LocalDateTime start, LocalDateTime end) {
        log.info("Checking claim filed. Date of sending: {}, Range: [{} - {}]", dateOfFilingAnApplicationInTheClaimProcedure, start, end);
        return isDateInRange(dateOfFilingAnApplicationInTheClaimProcedure, start, end);
    }

    /**
     * Проверяет, является ли дата рассмотрения дела актуальной.
     *
     * @param dateCaseReview дата рассмотрения дела.
     * @param now текущая дата для проверки.
     * @return true, если дата рассмотрения дела не раньше текущей даты, иначе false.
     */
    public static boolean dateCheckerDateOfReviewReport(LocalDateTime dateCaseReview, LocalDateTime now) {
        log.info("Checking date of review. Date of review: {}", dateCaseReview);
        return dateCaseReview != null && (dateCaseReview.isAfter(now) || dateCaseReview.isEqual(now));
    }

    /**
     * Проверяет, получены ли исполнительные листы в указанном периоде.
     *
     * @param dateOfReceiptExecution дата получения исполнительного листа.
     * @param start начальная дата диапазона.
     * @param end конечная дата диапазона.
     * @return true, если дата получения исполнительного листа находится в указанном диапазоне дат.
     */
    public static boolean dateCheckerReceivedWritsOfExecutionReport(LocalDateTime dateOfReceiptExecution, LocalDateTime start, LocalDateTime end) {
        log.info("Checking received writs of execution. Date of sending: {}, Range: [{} - {}]", dateOfReceiptExecution, start, end);
        return isDateInRange(dateOfReceiptExecution, start, end);
    }
}
