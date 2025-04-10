package com.example.excel.report.services.checks.filters.abstracts;

import java.time.LocalDateTime;

public abstract class DateChecker {

    public static boolean isDateInRange(LocalDateTime date, LocalDateTime start, LocalDateTime end) {
        return date != null &&
                (date.isEqual(start) ||
                        date.isEqual(end) ||
                        (date.isAfter(start) && date.isBefore(end)));
    }

    public abstract boolean checkDate(LocalDateTime date, LocalDateTime start, LocalDateTime end);
}
