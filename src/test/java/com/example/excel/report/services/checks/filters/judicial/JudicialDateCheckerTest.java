package com.example.excel.report.services.checks.filters.judicial;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class JudicialDateCheckerTest {
    private static final int WEEK_DAYS_BACK = 7;
    private static final int DAYS_BACK_ONE = 1;
    private static final int DAYS_FILING_EXISTS = 10;
    private static final int DAYS_NOT_ENOUGH = 6;
    private static final int DAYS_AFTER_SENDING_SUCCESS = 7;
    private static final int MONTHS_BEFORE_COURT_ORDER_RECEIPT = 4;
    private static final int MONTHS_BEFORE_DETERMINATION_RECEIVED = 3;
    private static final int MONTHS_SINCE_DETERMINATION_RECEIVED = 2;
    private static final int MONTHS_BEFORE_COURT_ORDER_RECEIVED = 3;
    private static final int MONTHS_SINCE_COURT_ORDER_RECEIVED = 2;
    private static final int MONTHS_LESS_THAN = 2;

    private LocalDateTime start;
    private LocalDateTime end;

    @BeforeEach
    void setUp() {
        start = LocalDateTime.now().minusDays(WEEK_DAYS_BACK);
        end = LocalDateTime.now().minusDays(DAYS_BACK_ONE);
    }

    @Test
    @DisplayName("Application submitted after 7 days")
    void testDateCheckerSendToDebtorButNotFiledInCourtReport_FilingExists() {
        LocalDateTime dateOfSending = LocalDateTime.now().minusDays(DAYS_FILING_EXISTS);
        LocalDateTime dateOfFilingInCourt = LocalDateTime.now();
        assertFalse(JudicialDateChecker.dateCheckerSendToDebtorButNotFiledInCourtReport(dateOfSending, dateOfFilingInCourt));
    }

    @Test
    @DisplayName("Application not submitted after 7 days")
    void testDateCheckerSendToDebtorButNotFiledInCourtReport_Success() {
        LocalDateTime dateOfSending = LocalDateTime.now().minusDays(DAYS_AFTER_SENDING_SUCCESS);
        LocalDateTime dateOfFilingInCourt = null;
        assertTrue(JudicialDateChecker.dateCheckerSendToDebtorButNotFiledInCourtReport(dateOfSending, dateOfFilingInCourt));
    }

    @Test
    @DisplayName("The application has been submitted, but the deadline for submission has not passed")
    void testDateCheckerSendToDebtorButNotFiledInCourtReport_NotEnoughDays() {
        LocalDateTime dateOfSending = LocalDateTime.now().minusDays(DAYS_NOT_ENOUGH);
        LocalDateTime dateOfFilingInCourt = LocalDateTime.now();
        assertFalse(JudicialDateChecker.dateCheckerSendToDebtorButNotFiledInCourtReport(dateOfSending, dateOfFilingInCourt));
    }

    @Test
    @DisplayName("Court order not received after three months")
    void testDateCheckerCourtOrderNotReceivedReport_Success() {
        LocalDateTime dateOfFiling = LocalDateTime.now().minusMonths(MONTHS_BEFORE_COURT_ORDER_RECEIPT);
        LocalDateTime dateOfDetermination = null;
        LocalDateTime dateOfReceiptOfCourtOrder = null;
        assertTrue(JudicialDateChecker.dateCheckerCourtOrderNotReceivedReport(dateOfFiling, dateOfDetermination, dateOfReceiptOfCourtOrder));
    }

    @Test
    @DisplayName("Sent to court and received a determination")
    void testDateCheckerCourtOrderNotReceivedReport_DeterminationReceived() {
        LocalDateTime dateOfFiling = LocalDateTime.now().minusMonths(MONTHS_BEFORE_DETERMINATION_RECEIVED);
        LocalDateTime dateOfDetermination = LocalDateTime.now().minusMonths(MONTHS_SINCE_DETERMINATION_RECEIVED);
        LocalDateTime dateOfReceiptOfCourtOrder = null;
        assertFalse(JudicialDateChecker.dateCheckerCourtOrderNotReceivedReport(dateOfFiling, dateOfDetermination, dateOfReceiptOfCourtOrder));
    }

    @Test
    @DisplayName("Court order was received")
    void testDateCheckerCourtOrderNotReceivedReport_CourtOrderReceived() {
        LocalDateTime dateOfFiling = LocalDateTime.now().minusMonths(MONTHS_BEFORE_COURT_ORDER_RECEIVED);
        LocalDateTime dateOfDetermination = null;
        LocalDateTime dateOfReceiptOfCourtOrder = LocalDateTime.now().minusMonths(MONTHS_SINCE_COURT_ORDER_RECEIVED);
        assertFalse(JudicialDateChecker.dateCheckerCourtOrderNotReceivedReport(dateOfFiling, dateOfDetermination, dateOfReceiptOfCourtOrder));
    }

    @Test
    @DisplayName("Less than three months have passed")
    void testDateCheckerCourtOrderNotReceivedReport_LessThanThreeMonths() {
        LocalDateTime dateOfFiling = LocalDateTime.now().minusMonths(MONTHS_LESS_THAN);
        LocalDateTime dateOfDetermination = null;
        LocalDateTime dateOfReceiptOfCourtOrder = null;
        assertFalse(JudicialDateChecker.dateCheckerCourtOrderNotReceivedReport(dateOfFiling, dateOfDetermination, dateOfReceiptOfCourtOrder));
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
    @DisplayName("No information about the return of documents from the court")
    void testDateCheckerReturnOfDocumentsFromTheCourtReport_determinationOfReturnIsNull() {
        String determinationOfReturn = null;
        LocalDateTime dateOfDetermination = start;
        assertFalse(JudicialDateChecker.dateCheckerReturnOfDocumentsFromTheCourtReport(
                dateOfDetermination, determinationOfReturn, start, end));
    }

    @Test
    @DisplayName("Documents returned from court within the specified date range")
    void testDateCheckerReturnOfDocumentsFromTheCourtReport_Success() {
        String determinationOfReturn = "Determination return test";
        LocalDateTime dateOfDetermination = end;
        assertTrue(JudicialDateChecker.dateCheckerReturnOfDocumentsFromTheCourtReport(
                dateOfDetermination, determinationOfReturn, start, end));
    }
}