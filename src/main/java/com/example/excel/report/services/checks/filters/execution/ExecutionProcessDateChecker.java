package com.example.excel.report.services.checks.filters.execution;

import com.example.excel.report.services.checks.filters.abstracts.DateChecker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Slf4j
@Component
public class ExecutionProcessDateChecker extends DateChecker {

    private static final int MAXIMUM_DAYS_FOR_TRANSFER_OF_THE_WRIT_EXECUTION = 3;
    private static final int MAXIMUM_DAYS_FOR_WAITING_INITIATION = 10;

    public static boolean dateCheckerSubmittedForExecutionReport(LocalDateTime dateOfSubmissionWritExecutionBailiffs, LocalDateTime start, LocalDateTime end) {
        log.info("Checking date of submission writ execution. Date of submission: {}, Range: [{} - {}]", dateOfSubmissionWritExecutionBailiffs, start, end);
        return isDateInRange(dateOfSubmissionWritExecutionBailiffs, start, end);
    }

    public static boolean dateCheckerNumberOfProgressReport(LocalDateTime dateOfFilingAnApplicationProgressOfEnforcementProceedings, LocalDateTime start, LocalDateTime end) {
        log.info("Checking date of submission number of progress. Date of submission: {}, Range: [{} - {}]", dateOfFilingAnApplicationProgressOfEnforcementProceedings, start, end);
        return isDateInRange(dateOfFilingAnApplicationProgressOfEnforcementProceedings, start, end);
    }

    public static boolean dateCheckerNumberOfComplaintsFiledReport(LocalDateTime dateOfFilingTheComplaint, LocalDateTime start, LocalDateTime end) {
        log.info("Checking date of submission complaints. Date of submission: {}, Range: [{} - {}]", dateOfFilingTheComplaint, start, end);
        return isDateInRange(dateOfFilingTheComplaint, start, end);
    }

    public static boolean dateCheckerNumberOfComplaintsFiledInCourtReport(LocalDateTime dateOfFilingAnApplicationToTheCourtCas, LocalDateTime start, LocalDateTime end) {
        log.info("Checking date of submission complaints in court. Date of submission: {}, Range: [{} - {}]", dateOfFilingAnApplicationToTheCourtCas, start, end);
        return isDateInRange(dateOfFilingAnApplicationToTheCourtCas, start, end);
    }

    public static boolean dateCheckerReceivedButNotSubmittedForExecutionReport(LocalDateTime dateOfExecutionWrit, LocalDateTime dateOfSubmissionWritExecutionBailiffs) {
        log.info("Checking date of received but not submitted for execution. Date of received: {}, date of submitted: {}", dateOfExecutionWrit, dateOfSubmissionWritExecutionBailiffs);

        if (dateOfExecutionWrit == null) {
            return false;
        }

        if (dateOfSubmissionWritExecutionBailiffs == null) {
            return ChronoUnit.DAYS.between(dateOfExecutionWrit, LocalDateTime.now()) > MAXIMUM_DAYS_FOR_TRANSFER_OF_THE_WRIT_EXECUTION;
        }

        return ChronoUnit.DAYS.between(dateOfExecutionWrit, dateOfSubmissionWritExecutionBailiffs) > MAXIMUM_DAYS_FOR_TRANSFER_OF_THE_WRIT_EXECUTION;
    }

    public static boolean dateCheckerDocumentFiledButNotInitiationReport(LocalDateTime dateOfSubmissionWritExecutionBailiffs, LocalDateTime dateOfInitiationOfEnforcementProceedings) {
        log.info("Checking date of filed but not initiation. Date of filing: {}, date of initiation: {}", dateOfSubmissionWritExecutionBailiffs, dateOfInitiationOfEnforcementProceedings);

        if (dateOfSubmissionWritExecutionBailiffs == null) {
            return false;
        }

        if (dateOfInitiationOfEnforcementProceedings == null) {
            return ChronoUnit.DAYS.between(dateOfSubmissionWritExecutionBailiffs, LocalDateTime.now()) > MAXIMUM_DAYS_FOR_WAITING_INITIATION;
        }

        return ChronoUnit.DAYS.between(dateOfSubmissionWritExecutionBailiffs, dateOfInitiationOfEnforcementProceedings) > MAXIMUM_DAYS_FOR_WAITING_INITIATION;
    }

    public static boolean dateCheckerNumberOfOutputsReport(LocalDateTime dateOfExitToAddress, LocalDateTime start, LocalDateTime end) {
        log.info("Checking date of exit to address. Date of exit: {}, Range: [{} - {}]", dateOfExitToAddress, start, end);
        return isDateInRange(dateOfExitToAddress, start, end);
    }

    public static boolean isAvailability(String data) {
        log.info("Check if information is available, for: {}", data);
        return data != null;
    }

    @Override
    public boolean checkDate(LocalDateTime date, LocalDateTime start, LocalDateTime end) {
        return isDateInRange(date, start, end);
    }
}
