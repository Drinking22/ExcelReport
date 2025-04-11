package com.example.excel.report.services.checks.filters.execution;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

class ExecutionProcessDateCheckerTest {
    private static final int MORE_THAN_THREE_DAYS = 4;
    private static final int LESS_THAN_THREE_DAYS = 2;
    private static final int LESS_THREE_DAYS_AFTER_SUBMISSION = 2;
    private static final int MORE_THREE_DAYS_AFTER_SUBMISSION = 5;
    private static final int SUBMITTED_MORE_THAN_TEN_DAYS = 15;
    private static final int SUBMITTED_LESS_THAN_TEN_DAYS = 5;
    private static final int DATE_OF_SUBMITTING = 15;
    private static final int DATE_OF_INITIATION_MORE_THEN_TEN_DAYS = 4;
    private static final int DATE_OF_INITIATION_LESS_THEN_TEN_DAYS = 7;

    @BeforeEach
    void setUp() {
    }

    @Test
    @DisplayName("No date received")
    void testDateCheckerReceivedButNotSubmittedForExecutionReport_DateOfExecutionNull() {
        LocalDateTime dateOfExecution = null;
        LocalDateTime dateOfSubmission = LocalDateTime.now();
        assertFalse(ExecutionProcessDateChecker.dateCheckerReceivedButNotSubmittedForExecutionReport(dateOfExecution, dateOfSubmission));
    }

    @Test
    @DisplayName("The document has been received and not submitted and more than three days have passed")
    void testDateCheckerReceivedButNotSubmittedForExecutionReport_MoreThenThreeDays() {
        LocalDateTime dateOfExecution = LocalDateTime.now().minusDays(MORE_THAN_THREE_DAYS);
        LocalDateTime dateOfSubmission = null;
        assertTrue(ExecutionProcessDateChecker.dateCheckerReceivedButNotSubmittedForExecutionReport(dateOfExecution, dateOfSubmission));
    }

    @Test
    @DisplayName("The document has been received and not submitted and less than three days have passed")
    void testDateCheckerReceivedButNotSubmittedForExecutionReport_LessThenThreeDays() {
        LocalDateTime dateOfExecution = LocalDateTime.now().minusDays(LESS_THAN_THREE_DAYS);
        LocalDateTime dateOfSubmission = null;
        assertFalse(ExecutionProcessDateChecker.dateCheckerReceivedButNotSubmittedForExecutionReport(dateOfExecution, dateOfSubmission));
    }

    @Test
    @DisplayName("Document received and submitted, less than three days have passed since submission")
    void testDateCheckerReceivedButNotSubmittedForExecutionReport_LessThenThreeDaysSinceSubmission() {
        LocalDateTime dateOfExecution = LocalDateTime.now().minusDays(LESS_THREE_DAYS_AFTER_SUBMISSION);
        LocalDateTime dateOfSubmission = LocalDateTime.now();
        assertFalse(ExecutionProcessDateChecker.dateCheckerReceivedButNotSubmittedForExecutionReport(dateOfExecution, dateOfSubmission));
    }

    @Test
    @DisplayName("Document received and submitted, more than three days have passed since submission")
    void testDateCheckerReceivedButNotSubmittedForExecutionReport_MoreThenThreeDaysSinceSubmission() {
        LocalDateTime dateOfExecution = LocalDateTime.now().minusDays(MORE_THREE_DAYS_AFTER_SUBMISSION);
        LocalDateTime dateOfSubmission = LocalDateTime.now();
        assertTrue(ExecutionProcessDateChecker.dateCheckerReceivedButNotSubmittedForExecutionReport(dateOfExecution, dateOfSubmission));
    }

    @Test
    @DisplayName("No filing date")
    void testDateCheckerDocumentFiledButNotInitiationReport_DateOfFiledNull() {
        LocalDateTime dateOfFiling = null;
        LocalDateTime dateOfInitiation = null;
        assertFalse(ExecutionProcessDateChecker.dateCheckerDocumentFiledButNotInitiationReport(dateOfFiling, dateOfInitiation));
    }

    @Test
    @DisplayName("Submitted more than ten days ago and not initiated")
    void testDateCheckerDocumentFiledButNotInitiationReport_SubmittedMoreThenTenDays() {
        LocalDateTime dateOfFiling = LocalDateTime.now().minusDays(SUBMITTED_MORE_THAN_TEN_DAYS);
        LocalDateTime dateOfInitiation = null;
        assertTrue(ExecutionProcessDateChecker.dateCheckerDocumentFiledButNotInitiationReport(dateOfFiling, dateOfInitiation));
    }

    @Test
    @DisplayName("Submitted less than ten days ago and not initiated")
    void testDateCheckerDocumentFiledButNotInitiationReport_SubmittedLessThenTenDays() {
        LocalDateTime dateOfFiling = LocalDateTime.now().minusDays(SUBMITTED_LESS_THAN_TEN_DAYS);
        LocalDateTime dateOfInitiation = null;
        assertFalse(ExecutionProcessDateChecker.dateCheckerDocumentFiledButNotInitiationReport(dateOfFiling, dateOfInitiation));
    }

    @Test
    @DisplayName("Filed and excited, more than ten days have passed")
    void testDateCheckerDocumentFiledButNotInitiationReport_MoreThenTenDaysPassed() {
        LocalDateTime dateOfFiling = LocalDateTime.now().minusDays(DATE_OF_SUBMITTING);
        LocalDateTime dateOfInitiation = LocalDateTime.now().minusDays(DATE_OF_INITIATION_MORE_THEN_TEN_DAYS);
        assertTrue(ExecutionProcessDateChecker.dateCheckerDocumentFiledButNotInitiationReport(dateOfFiling, dateOfInitiation));
    }

    @Test
    @DisplayName("Filed and excited, less than ten days have passed")
    void testDateCheckerDocumentFiledButNotInitiationReport_LessThenTenDaysPassed() {
        LocalDateTime dateOfFiling = LocalDateTime.now().minusDays(DATE_OF_SUBMITTING);
        LocalDateTime dateOfInitiation = LocalDateTime.now().minusDays(DATE_OF_INITIATION_LESS_THEN_TEN_DAYS);
        ;
        assertFalse(ExecutionProcessDateChecker.dateCheckerDocumentFiledButNotInitiationReport(dateOfFiling, dateOfInitiation));
    }

    @Test
    void testIsAvailability_NotSuccess() {
        String data = null;
        assertFalse(ExecutionProcessDateChecker.isAvailability(data));
    }

    @Test
    void testIsAvailability_Success() {
        String data = "Test data";
        assertTrue(ExecutionProcessDateChecker.isAvailability(data));
    }
}