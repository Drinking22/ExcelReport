package com.example.excel.report.services.checks.filters.lawsuit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class LawsuitDateCheckerTest {

    private LocalDateTime startWeekly;
    private LocalDateTime endWeekly;
    private LocalDateTime startMonthly;
    private LocalDateTime endMonthly;
    private LocalDateTime now;

    @BeforeEach
    void setUp() {
        startWeekly = LocalDateTime.now().minusDays(7);
        endWeekly = LocalDateTime.now().minusDays(1);
        startMonthly = LocalDateTime.now().minusMonths(1);
        endMonthly = LocalDateTime.now().minusDays(1);
        now = LocalDateTime.now();
    }

    @Test
    @DisplayName("Date to check is equal to start date for claim filed report for weekly period")
    void testDateCheckerClaimFiledReport_EqualToStartWeekly() {
        LocalDateTime dateCheck = startWeekly;
        assertTrue(LawsuitDateChecker.dateCheckerClaimFiledReport(dateCheck, startWeekly, endWeekly));
    }

    @Test
    @DisplayName("Date to check is equal to start date for claim filed report for monthly period")
    void testDateCheckerClaimFiledReport_EqualToStartMonthly() {
        LocalDateTime dateCheck = startMonthly;
        assertTrue(LawsuitDateChecker.dateCheckerClaimFiledReport(dateCheck, startMonthly, endMonthly));
    }

    @Test
    @DisplayName("Date to check is equal to end date for claim filed report for weekly period")
    void testDateCheckerClaimFiledReport_EqualToEndWeekly() {
        LocalDateTime dateCheck = endWeekly;
        assertTrue(LawsuitDateChecker.dateCheckerClaimFiledReport(dateCheck, startWeekly, endWeekly));
    }

    @Test
    @DisplayName("Date to check is equal to end date for claim filed report for monthly period")
    void testDateCheckerClaimFiledReport_EqualToEndMonthly() {
        LocalDateTime dateCheck = endMonthly;
        assertTrue(LawsuitDateChecker.dateCheckerClaimFiledReport(dateCheck, startMonthly, endMonthly));
    }

    @Test
    @DisplayName("Date to check is in range for claim filed report for weekly period")
    void testDateCheckerClaimFiledReport_InRangeWeekly() {
        LocalDateTime dateCheck = LocalDateTime.now().minusDays(3);
        assertTrue(LawsuitDateChecker.dateCheckerClaimFiledReport(dateCheck, startWeekly, endWeekly));
    }

    @Test
    @DisplayName("Date to check is in range for claim filed report for monthly period")
    void testDateCheckerClaimFiledReport_InRangeMonthly() {
        LocalDateTime dateCheck = LocalDateTime.now().minusDays(15);
        assertTrue(LawsuitDateChecker.dateCheckerClaimFiledReport(dateCheck, startMonthly, endMonthly));
    }

    @Test
    @DisplayName("Documents were sent before the period under consideration for claim filed report for weekly period")
    void testDateCheckerClaimFiledReport_BeforeRangeWeekly() {
        LocalDateTime dateCheck = LocalDateTime.now().minusDays(9);
        assertFalse(LawsuitDateChecker.dateCheckerClaimFiledReport(dateCheck, startWeekly, endWeekly));
    }

    @Test
    @DisplayName("Documents were sent before the period under consideration for claim filed report for monthly period")
    void testDateCheckerClaimFiledReport_BeforeRangeMonthly() {
        LocalDateTime dateCheck = LocalDateTime.now().minusDays(40);
        assertFalse(LawsuitDateChecker.dateCheckerClaimFiledReport(dateCheck, startMonthly, endMonthly));
    }

    @Test
    @DisplayName("Documents sent after the period under consideration for claim filed report for weekly period")
    void testDateCheckerClaimFiledReport_AfterRangeWeekly() {
        LocalDateTime dateCheck = LocalDateTime.now().plusDays(7);
        assertFalse(LawsuitDateChecker.dateCheckerClaimFiledReport(dateCheck, startWeekly, endWeekly));
    }

    @Test
    @DisplayName("Documents sent after the period under consideration for claim filed report for monthly period")
    void testDateCheckerClaimFiledReport_AfterRangeMonthly() {
        LocalDateTime dateCheck = LocalDateTime.now().plusDays(7);

        assertFalse(LawsuitDateChecker.dateCheckerClaimFiledReport(dateCheck, startMonthly, endMonthly));
    }

    @Test
    @DisplayName("Documents not sent for monthly and weekly period")
    void testDateCheckerClaimFiledReport_IsNull() {
        LocalDateTime dateCheck = null;

        assertFalse(LawsuitDateChecker.dateCheckerClaimFiledReport(dateCheck, startMonthly, endMonthly));
        assertFalse(LawsuitDateChecker.dateCheckerClaimFiledReport(dateCheck, startWeekly, endWeekly));
    }

    @Test
    @DisplayName("The date of the case hearing was earlier")
    void dateCheckerDateOfReviewReport_isBefore() {
        LocalDateTime dateReview = now.minusDays(1);
        assertFalse(LawsuitDateChecker.dateCheckerDateOfReviewReport(dateReview, now));
    }

    @Test
    @DisplayName("The date of the case hearing is after today")
    void dateCheckerDateOfReviewReport_isAfter() {
        LocalDateTime dateReview = now.plusDays(1);
        assertTrue(LawsuitDateChecker.dateCheckerDateOfReviewReport(dateReview, now));
    }

    @Test
    @DisplayName("The date of the case hearing is today")
    void dateCheckerDateOfReviewReport_EqualNow() {
        LocalDateTime dateReview = now;
        assertTrue(LawsuitDateChecker.dateCheckerDateOfReviewReport(dateReview, now));
    }

    @Test
    @DisplayName("Date of review is null")
    void dateCheckerDateOfReviewReport_isNull() {
        LocalDateTime dateReview = null;
        assertFalse(LawsuitDateChecker.dateCheckerDateOfReviewReport(dateReview, now));
    }

    @Test
    @DisplayName("Date to check is equal to start date for received writs of execution for weekly period")
    void testDateCheckerReceivedWritsOfExecution_EqualToStartWeekly() {
        LocalDateTime dateCheck = startWeekly;
        assertTrue(LawsuitDateChecker.dateCheckerReceivedWritsOfExecutionReport(dateCheck, startWeekly, endWeekly));
    }

    @Test
    @DisplayName("Date to check is equal to start date for received writs of execution for monthly period")
    void testDateCheckerReceivedWritsOfExecution_EqualToStartMonthly() {
        LocalDateTime dateCheck = startMonthly;
        assertTrue(LawsuitDateChecker.dateCheckerReceivedWritsOfExecutionReport(dateCheck, startMonthly, endMonthly));
    }

    @Test
    @DisplayName("Date to check is equal to end date for received writs of execution for weekly period")
    void testDateCheckerReceivedWritsOfExecution_EqualToEndWeekly() {
        LocalDateTime dateCheck = endWeekly;
        assertTrue(LawsuitDateChecker.dateCheckerReceivedWritsOfExecutionReport(dateCheck, startWeekly, endWeekly));
    }

    @Test
    @DisplayName("Date to check is equal to end date for received writs of execution for monthly period")
    void testDateCheckerReceivedWritsOfExecution_EqualToEndMonthly() {
        LocalDateTime dateCheck = endMonthly;
        assertTrue(LawsuitDateChecker.dateCheckerReceivedWritsOfExecutionReport(dateCheck, startMonthly, endMonthly));
    }

    @Test
    @DisplayName("Date to check is in range for received writs of execution for weekly period")
    void testDateCheckerReceivedWritsOfExecution_InRangeWeekly() {
        LocalDateTime dateCheck = LocalDateTime.now().minusDays(3);
        assertTrue(LawsuitDateChecker.dateCheckerReceivedWritsOfExecutionReport(dateCheck, startWeekly, endWeekly));
    }

    @Test
    @DisplayName("Date to check is in range for received writs of execution for monthly period")
    void testDateCheckerReceivedWritsOfExecution_InRangeMonthly() {
        LocalDateTime dateCheck = LocalDateTime.now().minusDays(15);
        assertTrue(LawsuitDateChecker.dateCheckerReceivedWritsOfExecutionReport(dateCheck, startMonthly, endMonthly));
    }

    @Test
    @DisplayName("Documents were received before the period under consideration for received writs of execution for weekly period")
    void testDateCheckerReceivedWritsOfExecution_BeforeRangeWeekly() {
        LocalDateTime dateCheck = LocalDateTime.now().minusDays(9);
        assertFalse(LawsuitDateChecker.dateCheckerReceivedWritsOfExecutionReport(dateCheck, startWeekly, endWeekly));
    }

    @Test
    @DisplayName("Documents were received before the period under consideration for received writs of execution for monthly period")
    void testDateCheckerReceivedWritsOfExecution_BeforeRangeMonthly() {
        LocalDateTime dateCheck = LocalDateTime.now().minusDays(40);
        assertFalse(LawsuitDateChecker.dateCheckerReceivedWritsOfExecutionReport(dateCheck, startMonthly, endMonthly));
    }

    @Test
    @DisplayName("Documents received after the period under consideration for received writs of execution for weekly period")
    void testDateCheckerReceivedWritsOfExecution_AfterRangeWeekly() {
        LocalDateTime dateCheck = LocalDateTime.now().plusDays(7);
        assertFalse(LawsuitDateChecker.dateCheckerReceivedWritsOfExecutionReport(dateCheck, startWeekly, endWeekly));
    }

    @Test
    @DisplayName("Documents received after the period under consideration for received writs of execution for monthly period")
    void testDateCheckerReceivedWritsOfExecution_AfterRangeMonthly() {
        LocalDateTime dateCheck = LocalDateTime.now().plusDays(7);

        assertFalse(LawsuitDateChecker.dateCheckerReceivedWritsOfExecutionReport(dateCheck, startMonthly, endMonthly));
    }

    @Test
    @DisplayName("Documents not received for monthly and weekly period")
    void testDateCheckerReceivedWritsOfExecution_IsNull() {
        LocalDateTime dateCheck = null;

        assertFalse(LawsuitDateChecker.dateCheckerReceivedWritsOfExecutionReport(dateCheck, startMonthly, endMonthly));
        assertFalse(LawsuitDateChecker.dateCheckerReceivedWritsOfExecutionReport(dateCheck, startWeekly, endWeekly));
    }
}