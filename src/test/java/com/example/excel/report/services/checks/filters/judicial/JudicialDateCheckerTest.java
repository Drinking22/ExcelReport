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
    void testDateCheckerGenerateCourtOrderNotReceivedReport_Success() {
        LocalDateTime dateOfFiling = LocalDateTime.now().minusMonths(4);
        LocalDateTime dateOfDetermination = null;
        LocalDateTime dateOfReceiptOfCourtOrder = null;

        assertTrue(JudicialDateChecker.dateCheckerGenerateCourtOrderNotReceivedReport(dateOfFiling, dateOfDetermination, dateOfReceiptOfCourtOrder));
    }

    @Test
    @DisplayName("Sent to court and received a determination")
    void testDateCheckerGenerateCourtOrderNotReceivedReport_DeterminationReceived() {
        LocalDateTime dateOfFiling = LocalDateTime.now().minusMonths(3);
        LocalDateTime dateOfDetermination = LocalDateTime.now().minusMonths(2);
        LocalDateTime dateOfReceiptOfCourtOrder = null;

        assertFalse(JudicialDateChecker.dateCheckerGenerateCourtOrderNotReceivedReport(dateOfFiling, dateOfDetermination, dateOfReceiptOfCourtOrder));
    }

    @Test
    @DisplayName("Court order was received")
    void testDateCheckerGenerateCourtOrderNotReceivedReport_CourtOrderReceived() {
        LocalDateTime dateOfFiling = LocalDateTime.now().minusMonths(3);
        LocalDateTime dateOfDetermination = null;
        LocalDateTime dateOfReceiptOfCourtOrder = LocalDateTime.now().minusMonths(2);

        assertFalse(JudicialDateChecker.dateCheckerGenerateCourtOrderNotReceivedReport(dateOfFiling, dateOfDetermination, dateOfReceiptOfCourtOrder));
    }

    @Test
    @DisplayName("Less than three months have passed")
    void testDateCheckerGenerateCourtOrderNotReceivedReport_LessThanThreeMonths() {
        LocalDateTime dateOfFiling = LocalDateTime.now().minusMonths(2);
        LocalDateTime dateOfDetermination = null;
        LocalDateTime dateOfReceiptOfCourtOrder = null;

        assertFalse(JudicialDateChecker.dateCheckerGenerateCourtOrderNotReceivedReport(dateOfFiling, dateOfDetermination, dateOfReceiptOfCourtOrder));
    }

    @Test
    @DisplayName("Date to check is equal to start date for copies of documents for weekly period")
    void testDateCheckerCopiesOfDocumentsSent_EqualToStartWeekly() {
        LocalDateTime dateCheck = startWeekly;
        assertTrue(JudicialDateChecker.dateCheckerCopiesOfDocumentsSent(dateCheck, startWeekly, endWeekly));
    }

    @Test
    @DisplayName("Date to check is equal to start date for copies of documents for monthly period")
    void testDateCheckerCopiesOfDocumentsSent_EqualToStartMonthly() {
        LocalDateTime dateCheck = startMonthly;
        assertTrue(JudicialDateChecker.dateCheckerCopiesOfDocumentsSent(dateCheck, startMonthly, endMonthly));
    }

    @Test
    @DisplayName("Date to check is equal to end date for copies of documents for weekly period")
    void testDateCheckerCopiesOfDocumentsSent_EqualToEndWeekly() {
        LocalDateTime dateCheck = endWeekly;
        assertTrue(JudicialDateChecker.dateCheckerCopiesOfDocumentsSent(dateCheck, startWeekly, endWeekly));
    }

    @Test
    @DisplayName("Date to check is equal to end date for copies of documents for monthly period")
    void testDateCheckerCopiesOfDocumentsSent_EqualToEndMonthly() {
        LocalDateTime dateCheck = endMonthly;
        assertTrue(JudicialDateChecker.dateCheckerCopiesOfDocumentsSent(dateCheck, startMonthly, endMonthly));
    }

    @Test
    @DisplayName("Date to check is in range for copies of documents for weekly period")
    void testDateCheckerCopiesOfDocumentsSent_InRangeWeekly() {
        LocalDateTime dateCheck = LocalDateTime.now().minusDays(3);
        assertTrue(JudicialDateChecker.dateCheckerCopiesOfDocumentsSent(dateCheck, startWeekly, endWeekly));
    }

    @Test
    @DisplayName("Date to check is in range for copies of documents for monthly period")
    void testDateCheckerCopiesOfDocumentsSent_InRangeMonthly() {
        LocalDateTime dateCheck = LocalDateTime.now().minusDays(15);
        assertTrue(JudicialDateChecker.dateCheckerCopiesOfDocumentsSent(dateCheck, startMonthly, endMonthly));
    }

    @Test
    @DisplayName("Documents were sent before the period under consideration for copies of documents for weekly period")
    void testDateCheckerCopiesOfDocumentsSent_BeforeRangeWeekly() {
        LocalDateTime dateCheck = LocalDateTime.now().minusDays(9);
        assertFalse(JudicialDateChecker.dateCheckerCopiesOfDocumentsSent(dateCheck, startWeekly, endWeekly));
    }

    @Test
    @DisplayName("Documents were sent before the period under consideration for copies of documents for monthly period")
    void testDateCheckerCopiesOfDocumentsSent_BeforeRangeMonthly() {
        LocalDateTime dateCheck = LocalDateTime.now().minusDays(40);
        assertFalse(JudicialDateChecker.dateCheckerCopiesOfDocumentsSent(dateCheck, startMonthly, endMonthly));
    }

    @Test
    @DisplayName("Documents sent after the period under consideration for weekly period")
    void testDateCheckerCopiesOfDocumentsSent_AfterRangeWeekly() {
        LocalDateTime dateCheck = LocalDateTime.now().plusDays(7);
        assertFalse(JudicialDateChecker.dateCheckerCopiesOfDocumentsSent(dateCheck, startWeekly, endWeekly));
    }

    @Test
    @DisplayName("Documents sent after the period under consideration for monthly period")
    void testDateCheckerCopiesOfDocumentsSent_AfterRangeMonthly() {
        LocalDateTime dateCheck = LocalDateTime.now().plusDays(7);

        assertFalse(JudicialDateChecker.dateCheckerCopiesOfDocumentsSent(dateCheck, startMonthly, endMonthly));
    }

    @Test
    @DisplayName("Documents not sent for monthly and weekly period")
    void testDateCheckerCopiesOfDocumentsSent_IsNull() {
        LocalDateTime dateCheck = null;

        assertFalse(JudicialDateChecker.dateCheckerCopiesOfDocumentsSent(dateCheck, startMonthly, endMonthly));
        assertFalse(JudicialDateChecker.dateCheckerCopiesOfDocumentsSent(dateCheck, startWeekly, endWeekly));
    }

    @Test
    @DisplayName("Date to check is equal to start date for applications submitted to court for weekly period")
    void testDateCheckerApplicationsSubmittedToCourt_EqualToStartWeekly() {
        LocalDateTime dateCheck = startWeekly;
        assertTrue(JudicialDateChecker.dateCheckerApplicationsSubmittedToCourt(dateCheck, startWeekly, endWeekly));
    }

    @Test
    @DisplayName("Date to check is equal to start date for applications submitted to court for monthly period")
    void testDateCheckerApplicationsSubmittedToCourt_EqualToStartMonthly() {
        LocalDateTime dateCheck = startMonthly;
        assertTrue(JudicialDateChecker.dateCheckerApplicationsSubmittedToCourt(dateCheck, startMonthly, endMonthly));
    }

    @Test
    @DisplayName("Date to check is equal to end date for applications submitted for weekly period")
    void testDateCheckerApplicationsSubmittedToCourt_EqualToEndWeekly() {
        LocalDateTime dateCheck = endWeekly;
        assertTrue(JudicialDateChecker.dateCheckerApplicationsSubmittedToCourt(dateCheck, startWeekly, endWeekly));
    }

    @Test
    @DisplayName("Date to check is equal to end date for applications submitted for monthly period")
    void testDateCheckerApplicationsSubmittedToCourt_EqualToEndMonthly() {
        LocalDateTime dateCheck = endMonthly;
        assertTrue(JudicialDateChecker.dateCheckerApplicationsSubmittedToCourt(dateCheck, startMonthly, endMonthly));
    }

    @Test
    @DisplayName("Date to check is in range for applications submitted for weekly period")
    void testDateCheckerApplicationsSubmittedToCourt_InRangeWeekly() {
        LocalDateTime dateCheck = LocalDateTime.now().minusDays(3);
        assertTrue(JudicialDateChecker.dateCheckerApplicationsSubmittedToCourt(dateCheck, startWeekly, endWeekly));
    }

    @Test
    @DisplayName("Date to check is in range for applications submitted for monthly period")
    void testDateCheckerApplicationsSubmittedToCourt_InRangeMonthly() {
        LocalDateTime dateCheck = LocalDateTime.now().minusDays(15);
        assertTrue(JudicialDateChecker.dateCheckerApplicationsSubmittedToCourt(dateCheck, startMonthly, endMonthly));
    }

    @Test
    @DisplayName("Date to check is before range for applications submitted for weekly period")
    void testDateCheckerApplicationsSubmittedToCourt_BeforeRangeWeekly() {
        LocalDateTime dateCheck = LocalDateTime.now().minusDays(9);
        assertFalse(JudicialDateChecker.dateCheckerApplicationsSubmittedToCourt(dateCheck, startWeekly, endWeekly));
    }

    @Test
    @DisplayName("Date to check is before range for applications submitted for monthly period")
    void testDateCheckerApplicationsSubmittedToCourt_BeforeRangeMonthly() {
        LocalDateTime dateCheck = LocalDateTime.now().minusDays(40);
        assertFalse(JudicialDateChecker.dateCheckerApplicationsSubmittedToCourt(dateCheck, startMonthly, endMonthly));
    }

    @Test
    @DisplayName("Date to check is after range for applications submitted for weekly period")
    void testDateCheckerApplicationsSubmittedToCourt_AfterRangeWeekly() {
        LocalDateTime dateCheck = LocalDateTime.now().plusDays(7);
        assertFalse(JudicialDateChecker.dateCheckerApplicationsSubmittedToCourt(dateCheck, startWeekly, endWeekly));
    }

    @Test
    @DisplayName("Date to check is after range for applications submitted for monthly period")
    void testDateCheckerApplicationsSubmittedToCourt_AfterRangeMonthly() {
        LocalDateTime dateCheck = LocalDateTime.now().plusDays(40);
        assertFalse(JudicialDateChecker.dateCheckerApplicationsSubmittedToCourt(dateCheck, startMonthly, endMonthly));
    }

    @Test
    @DisplayName("Documents not sent for applications submitted to court for monthly and weekly period")
    void testDateCheckerApplicationsSubmittedToCourt_IsNull() {
        LocalDateTime dateCheck = null;
        assertFalse(JudicialDateChecker.dateCheckerApplicationsSubmittedToCourt(dateCheck, startMonthly, endMonthly));
        assertFalse(JudicialDateChecker.dateCheckerApplicationsSubmittedToCourt(dateCheck, startWeekly, endWeekly));
    }

    @Test
    @DisplayName("Cancellation of the court order but no lawsuit filed")
    void testDateCheckerCancellationOfTheCourtOrderButNoLawsuitFiled_Success() {
        String determination = "Test determination info";
        LocalDateTime dateOfFiling = null;
        assertTrue(JudicialDateChecker.dateCheckerCancellationOfTheCourtOrderButNoLawsuitFiled(determination, dateOfFiling));
    }

    @Test
    @DisplayName("There is no information about the cancellation of the court order")
    void testDateCheckerCancellationOfTheCourtOrderButNoLawsuitFiled_NoCancellationInfo() {
        String determination = null;
        LocalDateTime dateOfFiling = LocalDateTime.now();
        assertFalse(JudicialDateChecker.dateCheckerCancellationOfTheCourtOrderButNoLawsuitFiled(determination, dateOfFiling));
    }

    @Test
    @DisplayName("Date to check is equal to start date for received court order for weekly period")
    void testDateCheckerReceivedCourtOrder_EqualToStartWeekly() {
        LocalDateTime dateCheck = startWeekly;
        assertTrue(JudicialDateChecker.dateCheckerCopiesOfDocumentsSent(dateCheck, startWeekly, endWeekly));
    }

    @Test
    @DisplayName("Date to check is equal to start date for received court order for monthly period")
    void testDateCheckerReceivedCourtOrder_EqualToStartMonthly() {
        LocalDateTime dateCheck = startMonthly;
        assertTrue(JudicialDateChecker.dateCheckerCopiesOfDocumentsSent(dateCheck, startMonthly, endMonthly));
    }

    @Test
    @DisplayName("Date to check is equal to end date for received court order for weekly period")
    void testDateCheckerReceivedCourtOrder_EqualToEndWeekly() {
        LocalDateTime dateCheck = endWeekly;
        assertTrue(JudicialDateChecker.dateCheckerReceivedCourtOrder(dateCheck, startWeekly, endWeekly));
    }

    @Test
    @DisplayName("Date to check is equal to end date for received court order for monthly period")
    void testDateCheckerReceivedCourtOrder_EqualToEndMonthly() {
        LocalDateTime dateCheck = endMonthly;
        assertTrue(JudicialDateChecker.dateCheckerReceivedCourtOrder(dateCheck, startMonthly, endMonthly));
    }

    @Test
    @DisplayName("Date to check is in range for received court order for weekly period")
    void testDateCheckerReceivedCourtOrder_InRangeWeekly() {
        LocalDateTime dateCheck = LocalDateTime.now().minusDays(3);
        assertTrue(JudicialDateChecker.dateCheckerReceivedCourtOrder(dateCheck, startWeekly, endWeekly));
    }

    @Test
    @DisplayName("Date to check is in range for received court order for monthly period")
    void testDateCheckerReceivedCourtOrder_InRangeMonthly() {
        LocalDateTime dateCheck = LocalDateTime.now().minusDays(15);
        assertTrue(JudicialDateChecker.dateCheckerReceivedCourtOrder(dateCheck, startMonthly, endMonthly));
    }

    @Test
    @DisplayName("The court order was received before the period under consideration for received court order for weekly period")
    void testDateCheckerReceivedCourtOrder_BeforeRangeWeekly() {
        LocalDateTime dateCheck = LocalDateTime.now().minusDays(9);
        assertFalse(JudicialDateChecker.dateCheckerReceivedCourtOrder(dateCheck, startWeekly, endWeekly));
    }

    @Test
    @DisplayName("The court order was received before the period under consideration for received court order for monthly period")
    void testDateCheckerReceivedCourtOrder_BeforeRangeMonthly() {
        LocalDateTime dateCheck = LocalDateTime.now().minusDays(40);
        assertFalse(JudicialDateChecker.dateCheckerReceivedCourtOrder(dateCheck, startMonthly, endMonthly));
    }

    @Test
    @DisplayName("The court order was received after the period under consideration for weekly period")
    void testDateCheckerReceivedCourtOrder_AfterRangeWeekly() {
        LocalDateTime dateCheck = LocalDateTime.now().plusDays(7);
        assertFalse(JudicialDateChecker.dateCheckerReceivedCourtOrder(dateCheck, startWeekly, endWeekly));
    }

    @Test
    @DisplayName("The court order was received after the period under consideration for monthly period")
    void testDateCheckerReceivedCourtOrder_AfterRangeMonthly() {
        LocalDateTime dateCheck = LocalDateTime.now().plusDays(7);

        assertFalse(JudicialDateChecker.dateCheckerReceivedCourtOrder(dateCheck, startMonthly, endMonthly));
    }

    @Test
    @DisplayName("Court order not received for monthly and weekly period")
    void testDateCheckerReceivedCourtOrder_IsNull() {
        LocalDateTime dateCheck = null;

        assertFalse(JudicialDateChecker.dateCheckerReceivedCourtOrder(dateCheck, startMonthly, endMonthly));
        assertFalse(JudicialDateChecker.dateCheckerReceivedCourtOrder(dateCheck, startWeekly, endWeekly));
    }
}