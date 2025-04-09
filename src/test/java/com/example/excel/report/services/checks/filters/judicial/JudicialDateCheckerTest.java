package com.example.excel.report.services.checks.filters.judicial;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class JudicialDateCheckerTest {

    private LocalDateTime startWeekly;
    private LocalDateTime endWeekly;
    private LocalDateTime startMonthly;
    private LocalDateTime endMonthly;

    @BeforeEach
    void setUp() {
        startWeekly = LocalDateTime.now().minusDays(7);
        endWeekly = LocalDateTime.now().minusDays(1);
        startMonthly = LocalDateTime.now().minusMonths(1);
        endMonthly = LocalDateTime.now().minusDays(1);
    }

    @Test
    @DisplayName("Application submitted after 7 days")
    void testDateCheckerSendToDebtorButNotFiledInCourtReport_FilingExists() {
        LocalDateTime dateOfSending = LocalDateTime.now().minusDays(10);
        LocalDateTime dateOfFilingInCourt = LocalDateTime.now();

        assertFalse(JudicialDateChecker.dateCheckerSendToDebtorButNotFiledInCourtReport(dateOfSending, dateOfFilingInCourt));
    }

    @Test
    @DisplayName("Application not submitted after 7 days")
    void testDateCheckerSendToDebtorButNotFiledInCourtReport_Success() {
        LocalDateTime dateOfSending = LocalDateTime.now().minusDays(7);
        LocalDateTime dateOfFilingInCourt = null;

        assertTrue(JudicialDateChecker.dateCheckerSendToDebtorButNotFiledInCourtReport(dateOfSending, dateOfFilingInCourt));
    }

    @Test
    @DisplayName("The application has been submitted, but the deadline for submission has not passed")
    void testDateCheckerSendToDebtorButNotFiledInCourtReport_NotEnoughDays() {
        LocalDateTime dateOfSending = LocalDateTime.now().minusDays(6);
        LocalDateTime dateOfFilingInCourt = LocalDateTime.now();

        assertFalse(JudicialDateChecker.dateCheckerSendToDebtorButNotFiledInCourtReport(dateOfSending, dateOfFilingInCourt));
    }

    @Test
    @DisplayName("Court order not received after three months")
    void testDateCheckerCourtOrderNotReceivedReport_Success() {
        LocalDateTime dateOfFiling = LocalDateTime.now().minusMonths(4);
        LocalDateTime dateOfDetermination = null;
        LocalDateTime dateOfReceiptOfCourtOrder = null;

        assertTrue(JudicialDateChecker.dateCheckerCourtOrderNotReceivedReport(dateOfFiling, dateOfDetermination, dateOfReceiptOfCourtOrder));
    }

    @Test
    @DisplayName("Sent to court and received a determination")
    void testDateCheckerCourtOrderNotReceivedReport_DeterminationReceived() {
        LocalDateTime dateOfFiling = LocalDateTime.now().minusMonths(3);
        LocalDateTime dateOfDetermination = LocalDateTime.now().minusMonths(2);
        LocalDateTime dateOfReceiptOfCourtOrder = null;

        assertFalse(JudicialDateChecker.dateCheckerCourtOrderNotReceivedReport(dateOfFiling, dateOfDetermination, dateOfReceiptOfCourtOrder));
    }

    @Test
    @DisplayName("Court order was received")
    void testDateCheckerCourtOrderNotReceivedReport_CourtOrderReceived() {
        LocalDateTime dateOfFiling = LocalDateTime.now().minusMonths(3);
        LocalDateTime dateOfDetermination = null;
        LocalDateTime dateOfReceiptOfCourtOrder = LocalDateTime.now().minusMonths(2);

        assertFalse(JudicialDateChecker.dateCheckerCourtOrderNotReceivedReport(dateOfFiling, dateOfDetermination, dateOfReceiptOfCourtOrder));
    }

    @Test
    @DisplayName("Less than three months have passed")
    void testDateCheckerCourtOrderNotReceivedReport_LessThanThreeMonths() {
        LocalDateTime dateOfFiling = LocalDateTime.now().minusMonths(2);
        LocalDateTime dateOfDetermination = null;
        LocalDateTime dateOfReceiptOfCourtOrder = null;

        assertFalse(JudicialDateChecker.dateCheckerCourtOrderNotReceivedReport(dateOfFiling, dateOfDetermination, dateOfReceiptOfCourtOrder));
    }

    @Test
    @DisplayName("Date to check is equal to start date for copies of documents for weekly period")
    void testDateCheckerCopiesOfDocumentsSent_Report_EqualToStartWeekly() {
        LocalDateTime dateCheck = startWeekly;
        assertTrue(JudicialDateChecker.dateCheckerCopiesOfDocumentsSentReport(dateCheck, startWeekly, endWeekly));
    }

    @Test
    @DisplayName("Date to check is equal to start date for copies of documents for monthly period")
    void testDateCheckerCopiesOfDocumentsSent_Report_EqualToStartMonthly() {
        LocalDateTime dateCheck = startMonthly;
        assertTrue(JudicialDateChecker.dateCheckerCopiesOfDocumentsSentReport(dateCheck, startMonthly, endMonthly));
    }

    @Test
    @DisplayName("Date to check is equal to end date for copies of documents for weekly period")
    void testDateCheckerCopiesOfDocumentsSent_Report_EqualToEndWeekly() {
        LocalDateTime dateCheck = endWeekly;
        assertTrue(JudicialDateChecker.dateCheckerCopiesOfDocumentsSentReport(dateCheck, startWeekly, endWeekly));
    }

    @Test
    @DisplayName("Date to check is equal to end date for copies of documents for monthly period")
    void testDateCheckerCopiesOfDocumentsSent_Report_EqualToEndMonthly() {
        LocalDateTime dateCheck = endMonthly;
        assertTrue(JudicialDateChecker.dateCheckerCopiesOfDocumentsSentReport(dateCheck, startMonthly, endMonthly));
    }

    @Test
    @DisplayName("Date to check is in range for copies of documents for weekly period")
    void testDateCheckerCopiesOfDocumentsSent_Report_InRangeWeekly() {
        LocalDateTime dateCheck = LocalDateTime.now().minusDays(3);
        assertTrue(JudicialDateChecker.dateCheckerCopiesOfDocumentsSentReport(dateCheck, startWeekly, endWeekly));
    }

    @Test
    @DisplayName("Date to check is in range for copies of documents for monthly period")
    void testDateCheckerCopiesOfDocumentsSent_Report_InRangeMonthly() {
        LocalDateTime dateCheck = LocalDateTime.now().minusDays(15);
        assertTrue(JudicialDateChecker.dateCheckerCopiesOfDocumentsSentReport(dateCheck, startMonthly, endMonthly));
    }

    @Test
    @DisplayName("Documents were sent before the period under consideration for copies of documents for weekly period")
    void testDateCheckerCopiesOfDocumentsSent_Report_BeforeRangeWeekly() {
        LocalDateTime dateCheck = LocalDateTime.now().minusDays(9);
        assertFalse(JudicialDateChecker.dateCheckerCopiesOfDocumentsSentReport(dateCheck, startWeekly, endWeekly));
    }

    @Test
    @DisplayName("Documents were sent before the period under consideration for copies of documents for monthly period")
    void testDateCheckerCopiesOfDocumentsSent_Report_BeforeRangeMonthly() {
        LocalDateTime dateCheck = LocalDateTime.now().minusDays(40);
        assertFalse(JudicialDateChecker.dateCheckerCopiesOfDocumentsSentReport(dateCheck, startMonthly, endMonthly));
    }

    @Test
    @DisplayName("Documents sent after the period under consideration for weekly period")
    void testDateCheckerCopiesOfDocumentsSent_Report_AfterRangeWeekly() {
        LocalDateTime dateCheck = LocalDateTime.now().plusDays(7);
        assertFalse(JudicialDateChecker.dateCheckerCopiesOfDocumentsSentReport(dateCheck, startWeekly, endWeekly));
    }

    @Test
    @DisplayName("Documents sent after the period under consideration for monthly period")
    void testDateCheckerCopiesOfDocumentsSent_Report_AfterRangeMonthly() {
        LocalDateTime dateCheck = LocalDateTime.now().plusDays(7);

        assertFalse(JudicialDateChecker.dateCheckerCopiesOfDocumentsSentReport(dateCheck, startMonthly, endMonthly));
    }

    @Test
    @DisplayName("Documents not sent for monthly and weekly period")
    void testDateCheckerCopiesOfDocumentsSent_Report_IsNull() {
        LocalDateTime dateCheck = null;

        assertFalse(JudicialDateChecker.dateCheckerCopiesOfDocumentsSentReport(dateCheck, startMonthly, endMonthly));
        assertFalse(JudicialDateChecker.dateCheckerCopiesOfDocumentsSentReport(dateCheck, startWeekly, endWeekly));
    }

    @Test
    @DisplayName("Date to check is equal to start date for applications submitted to court for weekly period")
    void testDateCheckerApplicationsSubmittedToCourt_EqualToStartWeeklyReport() {
        LocalDateTime dateCheck = startWeekly;
        assertTrue(JudicialDateChecker.dateCheckerApplicationsSubmittedToCourtReport(dateCheck, startWeekly, endWeekly));
    }

    @Test
    @DisplayName("Date to check is equal to start date for applications submitted to court for monthly period")
    void testDateCheckerApplicationsSubmittedToCourt_EqualToStartMonthlyReport() {
        LocalDateTime dateCheck = startMonthly;
        assertTrue(JudicialDateChecker.dateCheckerApplicationsSubmittedToCourtReport(dateCheck, startMonthly, endMonthly));
    }

    @Test
    @DisplayName("Date to check is equal to end date for applications submitted for weekly period")
    void testDateCheckerApplicationsSubmittedToCourt_EqualToEndWeeklyReport() {
        LocalDateTime dateCheck = endWeekly;
        assertTrue(JudicialDateChecker.dateCheckerApplicationsSubmittedToCourtReport(dateCheck, startWeekly, endWeekly));
    }

    @Test
    @DisplayName("Date to check is equal to end date for applications submitted for monthly period")
    void testDateCheckerApplicationsSubmittedToCourt_EqualToEndMonthlyReport() {
        LocalDateTime dateCheck = endMonthly;
        assertTrue(JudicialDateChecker.dateCheckerApplicationsSubmittedToCourtReport(dateCheck, startMonthly, endMonthly));
    }

    @Test
    @DisplayName("Date to check is in range for applications submitted for weekly period")
    void testDateCheckerApplicationsSubmittedToCourt_Report_InRangeWeekly() {
        LocalDateTime dateCheck = LocalDateTime.now().minusDays(3);
        assertTrue(JudicialDateChecker.dateCheckerApplicationsSubmittedToCourtReport(dateCheck, startWeekly, endWeekly));
    }

    @Test
    @DisplayName("Date to check is in range for applications submitted for monthly period")
    void testDateCheckerApplicationsSubmittedToCourt_Report_InRangeMonthly() {
        LocalDateTime dateCheck = LocalDateTime.now().minusDays(15);
        assertTrue(JudicialDateChecker.dateCheckerApplicationsSubmittedToCourtReport(dateCheck, startMonthly, endMonthly));
    }

    @Test
    @DisplayName("Date to check is before range for applications submitted for weekly period")
    void testDateCheckerApplicationsSubmittedToCourt_Report_BeforeRangeWeekly() {
        LocalDateTime dateCheck = LocalDateTime.now().minusDays(9);
        assertFalse(JudicialDateChecker.dateCheckerApplicationsSubmittedToCourtReport(dateCheck, startWeekly, endWeekly));
    }

    @Test
    @DisplayName("Date to check is before range for applications submitted for monthly period")
    void testDateCheckerApplicationsSubmittedToCourt_Report_BeforeRangeMonthly() {
        LocalDateTime dateCheck = LocalDateTime.now().minusDays(40);
        assertFalse(JudicialDateChecker.dateCheckerApplicationsSubmittedToCourtReport(dateCheck, startMonthly, endMonthly));
    }

    @Test
    @DisplayName("Date to check is after range for applications submitted for weekly period")
    void testDateCheckerApplicationsSubmittedToCourt_Report_AfterRangeWeekly() {
        LocalDateTime dateCheck = LocalDateTime.now().plusDays(7);
        assertFalse(JudicialDateChecker.dateCheckerApplicationsSubmittedToCourtReport(dateCheck, startWeekly, endWeekly));
    }

    @Test
    @DisplayName("Date to check is after range for applications submitted for monthly period")
    void testDateCheckerApplicationsSubmittedToCourt_Report_AfterRangeMonthly() {
        LocalDateTime dateCheck = LocalDateTime.now().plusDays(40);
        assertFalse(JudicialDateChecker.dateCheckerApplicationsSubmittedToCourtReport(dateCheck, startMonthly, endMonthly));
    }

    @Test
    @DisplayName("Documents not sent for applications submitted to court for monthly and weekly period")
    void testDateCheckerApplicationsSubmittedToCourt_Report_IsNull() {
        LocalDateTime dateCheck = null;
        assertFalse(JudicialDateChecker.dateCheckerApplicationsSubmittedToCourtReport(dateCheck, startMonthly, endMonthly));
        assertFalse(JudicialDateChecker.dateCheckerApplicationsSubmittedToCourtReport(dateCheck, startWeekly, endWeekly));
    }

    @Test
    @DisplayName("Cancellation of the court order but no lawsuit filed")
    void testDateCheckerCancellationOfTheCourtOrderButNoLawsuitFiled_Report_Success() {
        String determination = "Test determination info";
        LocalDateTime dateOfFiling = null;
        assertTrue(JudicialDateChecker.dateCheckerCancellationOfTheCourtOrderButNoLawsuitFiledReport(determination, dateOfFiling));
    }

    @Test
    @DisplayName("There is no information about the cancellation of the court order")
    void testDateCheckerCancellationOfTheCourtOrderButNoLawsuitFiled_NoCancellationInfoReport() {
        String determination = null;
        LocalDateTime dateOfFiling = LocalDateTime.now();
        assertFalse(JudicialDateChecker.dateCheckerCancellationOfTheCourtOrderButNoLawsuitFiledReport(determination, dateOfFiling));
    }

    @Test
    @DisplayName("Date to check is equal to start date for received court order for weekly period")
    void testDateCheckerReceivedCourtOrder_Report_EqualToStartWeekly() {
        LocalDateTime dateCheck = startWeekly;
        assertTrue(JudicialDateChecker.dateCheckerCopiesOfDocumentsSentReport(dateCheck, startWeekly, endWeekly));
    }

    @Test
    @DisplayName("Date to check is equal to start date for received court order for monthly period")
    void testDateCheckerReceivedCourtOrder_Report_EqualToStartMonthly() {
        LocalDateTime dateCheck = startMonthly;
        assertTrue(JudicialDateChecker.dateCheckerCopiesOfDocumentsSentReport(dateCheck, startMonthly, endMonthly));
    }

    @Test
    @DisplayName("Date to check is equal to end date for received court order for weekly period")
    void testDateCheckerReceivedCourtOrder_Report_EqualToEndWeekly() {
        LocalDateTime dateCheck = endWeekly;
        assertTrue(JudicialDateChecker.dateCheckerReceivedCourtOrderReport(dateCheck, startWeekly, endWeekly));
    }

    @Test
    @DisplayName("Date to check is equal to end date for received court order for monthly period")
    void testDateCheckerReceivedCourtOrder_Report_EqualToEndMonthly() {
        LocalDateTime dateCheck = endMonthly;
        assertTrue(JudicialDateChecker.dateCheckerReceivedCourtOrderReport(dateCheck, startMonthly, endMonthly));
    }

    @Test
    @DisplayName("Date to check is in range for received court order for weekly period")
    void testDateCheckerReceivedCourtOrder_Report_InRangeWeekly() {
        LocalDateTime dateCheck = LocalDateTime.now().minusDays(3);
        assertTrue(JudicialDateChecker.dateCheckerReceivedCourtOrderReport(dateCheck, startWeekly, endWeekly));
    }

    @Test
    @DisplayName("Date to check is in range for received court order for monthly period")
    void testDateCheckerReceivedCourtOrder_Report_InRangeMonthly() {
        LocalDateTime dateCheck = LocalDateTime.now().minusDays(15);
        assertTrue(JudicialDateChecker.dateCheckerReceivedCourtOrderReport(dateCheck, startMonthly, endMonthly));
    }

    @Test
    @DisplayName("The court order was received before the period under consideration for received court order for weekly period")
    void testDateCheckerReceivedCourtOrder_Report_BeforeRangeWeekly() {
        LocalDateTime dateCheck = LocalDateTime.now().minusDays(9);
        assertFalse(JudicialDateChecker.dateCheckerReceivedCourtOrderReport(dateCheck, startWeekly, endWeekly));
    }

    @Test
    @DisplayName("The court order was received before the period under consideration for received court order for monthly period")
    void testDateCheckerReceivedCourtOrder_Report_BeforeRangeMonthly() {
        LocalDateTime dateCheck = LocalDateTime.now().minusDays(40);
        assertFalse(JudicialDateChecker.dateCheckerReceivedCourtOrderReport(dateCheck, startMonthly, endMonthly));
    }

    @Test
    @DisplayName("The court order was received after the period under consideration for weekly period")
    void testDateCheckerReceivedCourtOrder_Report_AfterRangeWeekly() {
        LocalDateTime dateCheck = LocalDateTime.now().plusDays(7);
        assertFalse(JudicialDateChecker.dateCheckerReceivedCourtOrderReport(dateCheck, startWeekly, endWeekly));
    }

    @Test
    @DisplayName("The court order was received after the period under consideration for monthly period")
    void testDateCheckerReceivedCourtOrder_Report_AfterRangeMonthly() {
        LocalDateTime dateCheck = LocalDateTime.now().plusDays(7);

        assertFalse(JudicialDateChecker.dateCheckerReceivedCourtOrderReport(dateCheck, startMonthly, endMonthly));
    }

    @Test
    @DisplayName("Court order not received for monthly and weekly period")
    void testDateCheckerReceivedCourtOrder_Report_IsNull() {
        LocalDateTime dateCheck = null;

        assertFalse(JudicialDateChecker.dateCheckerReceivedCourtOrderReport(dateCheck, startMonthly, endMonthly));
        assertFalse(JudicialDateChecker.dateCheckerReceivedCourtOrderReport(dateCheck, startWeekly, endWeekly));
    }
}