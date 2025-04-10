package com.example.excel.report.services.checks.filters.abstracts;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class DateCheckerTest {
    private static final int WEEK_DAYS_BACK = 7;
    private static final int DAYS_BACK_ONE = 1;
    private static final int MONTHS_BACK_ONE = 1;

    private static final int DAYS_BACK_FOR_START_DATE_RANGE_MONTHLY = 40;
    private static final int DAYS_IN_RANGE_MONTHLY = 15;
    private static final int DAYS_BACK_FOR_START_DATE_RANGE_WEEKLY = 9;
    private static final int DAYS_IN_RANGE_WEEKLY = 3;
    private static final int DAYS_AHEAD_FOR_END_DATE_RANGE = 7;

    private LocalDateTime startWeekly;
    private LocalDateTime endWeekly;
    private LocalDateTime startMonthly;
    private LocalDateTime endMonthly;

    @BeforeEach
    void setUp() {
        startWeekly = LocalDateTime.now().minusDays(WEEK_DAYS_BACK);
        endWeekly = LocalDateTime.now().minusDays(DAYS_BACK_ONE);
        startMonthly = LocalDateTime.now().minusMonths(MONTHS_BACK_ONE);
        endMonthly = LocalDateTime.now().minusDays(DAYS_BACK_ONE);
    }

    @Test
    @DisplayName("Date to check is equal to start date for weekly period")
    void testIsDateInRange_EqualToStartWeekly() {
        LocalDateTime dateCheck = startWeekly;
        assertTrue(DateChecker.isDateInRange(dateCheck, startWeekly, endWeekly));
    }

    @Test
    @DisplayName("Date to check is equal to start date for monthly period")
    void testIsDateInRange_Report_EqualToStartMonthly() {
        LocalDateTime dateCheck = startMonthly;
        assertTrue(DateChecker.isDateInRange(dateCheck, startMonthly, endMonthly));
    }

    @Test
    @DisplayName("Date to check is equal to end date for weekly period")
    void testDateCheckerCopiesOfDocumentsSent_Report_EqualToEndWeekly() {
        LocalDateTime dateCheck = endWeekly;
        assertTrue(DateChecker.isDateInRange(dateCheck, startWeekly, endWeekly));
    }

    @Test
    @DisplayName("Date to check is equal to end date for monthly period")
    void testIsDateInRange_EqualToEndMonthly() {
        LocalDateTime dateCheck = endMonthly;
        assertTrue(DateChecker.isDateInRange(dateCheck, startMonthly, endMonthly));
    }

    @Test
    @DisplayName("Date to check is in range for weekly period")
    void testIsDateInRange_InRangeWeekly() {
        LocalDateTime dateCheck = LocalDateTime.now().minusDays(DAYS_IN_RANGE_WEEKLY);
        assertTrue(DateChecker.isDateInRange(dateCheck, startWeekly, endWeekly));
    }

    @Test
    @DisplayName("Date to check is in range for monthly period")
    void testIsDateInRange_InRangeMonthly() {
        LocalDateTime dateCheck = LocalDateTime.now().minusDays(DAYS_IN_RANGE_MONTHLY);
        assertTrue(DateChecker.isDateInRange(dateCheck, startMonthly, endMonthly));
    }

    @Test
    @DisplayName("Date to check is before the period under consideration for weekly period")
    void testIsDateInRange_BeforeRangeWeekly() {
        LocalDateTime dateCheck = LocalDateTime.now().minusDays(DAYS_BACK_FOR_START_DATE_RANGE_WEEKLY);
        assertFalse(DateChecker.isDateInRange(dateCheck, startWeekly, endWeekly));
    }

    @Test
    @DisplayName("Date to check is before the period under consideration for monthly period")
    void testIsDateInRange_BeforeRangeMonthly() {
        LocalDateTime dateCheck = LocalDateTime.now().minusDays(DAYS_BACK_FOR_START_DATE_RANGE_MONTHLY);
        assertFalse(DateChecker.isDateInRange(dateCheck, startMonthly, endMonthly));
    }

    @Test
    @DisplayName("Date to check is after the period under consideration for weekly period")
    void testIsDateInRange_AfterRangeWeekly() {
        LocalDateTime dateCheck = LocalDateTime.now().plusDays(DAYS_AHEAD_FOR_END_DATE_RANGE);
        assertFalse(DateChecker.isDateInRange(dateCheck, startWeekly, endWeekly));
    }

    @Test
    @DisplayName("Date to check is after the period under consideration for monthly period")
    void testIsDateInRange_AfterRangeMonthly() {
        LocalDateTime dateCheck = LocalDateTime.now().plusDays(DAYS_AHEAD_FOR_END_DATE_RANGE);
        assertFalse(DateChecker.isDateInRange(dateCheck, startMonthly, endMonthly));
    }

    @Test
    @DisplayName("Date to check is null for monthly and weekly period")
    void testIsDateInRange_IsNull() {
        LocalDateTime dateCheck = null;
        assertFalse(DateChecker.isDateInRange(dateCheck, startMonthly, endMonthly));
        assertFalse(DateChecker.isDateInRange(dateCheck, startWeekly, endWeekly));
    }
}