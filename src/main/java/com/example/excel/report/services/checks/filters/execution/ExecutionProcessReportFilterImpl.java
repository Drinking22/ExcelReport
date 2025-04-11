package com.example.excel.report.services.checks.filters.execution;

import com.example.excel.report.model.ExecutionProcessExcelData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class ExecutionProcessReportFilterImpl implements ExecutionProcessReportFilter {

    @Override
    public List<ExecutionProcessExcelData> generateSubmittedForExecutionReport(List<ExecutionProcessExcelData> executionProcessExcelData, LocalDateTime start, LocalDateTime end) {
        log.info("Generate submitted for execution process report");
        return executionProcessExcelData.stream()
                .filter(data -> ExecutionProcessDateChecker.dateCheckerSubmittedForExecutionReport(
                        data.getDateOfSubmissionWritExecutionBailiffs(), start, end))
                .toList();
    }

    @Override
    public List<ExecutionProcessExcelData> generateDocumentReceivedButNotSubmittedForExecutionReport(List<ExecutionProcessExcelData> executionProcessExcelData) {
        log.info("Generate document received but not submitted for execution process report");
        return executionProcessExcelData.stream()
                .filter(data -> ExecutionProcessDateChecker.dateCheckerReceivedButNotSubmittedForExecutionReport(
                        data.getDateOfExecutionWrit(), data.getDateOfSubmissionWritExecutionBailiffs()))
                .toList();
    }

    @Override
    public List<ExecutionProcessExcelData> generateDocumentFiledButNotInitiationReport(List<ExecutionProcessExcelData> executionProcessExcelData) {
        log.info("Generate document filed but not initiation for execution process report");
        return executionProcessExcelData.stream()
                .filter(data -> ExecutionProcessDateChecker.dateCheckerDocumentFiledButNotInitiationReport(
                        data.getDateOfSubmissionWritExecutionBailiffs(), data.getDateOfInitiationOfEnforcementProceedings()))
                .toList();
    }

    @Override
    public List<ExecutionProcessExcelData> generateNumberOfProgressReport(List<ExecutionProcessExcelData> executionProcessExcelData, LocalDateTime start, LocalDateTime end) {
        log.info("Generate number of progress for execution process report");
        return executionProcessExcelData.stream()
                .filter(data -> ExecutionProcessDateChecker.dateCheckerNumberOfProgressReport(
                        data.getDateOfFilingAnApplicationProgressOfEnforcementProceedings(), start, end))
                .toList();
    }

    @Override
    public List<ExecutionProcessExcelData> generateNumberOfComplaintsFiledReport(List<ExecutionProcessExcelData> executionProcessExcelData, LocalDateTime start, LocalDateTime end) {
        log.info("Generate number of complaints filed for execution process report");
        return executionProcessExcelData.stream()
                .filter(data -> ExecutionProcessDateChecker.dateCheckerNumberOfComplaintsFiledReport(
                        data.getDateOfFilingTheComplaint(), start, end))
                .toList();
    }

    @Override
    public List<ExecutionProcessExcelData> generateNumberOfComplaintsFiledInCourtReport(List<ExecutionProcessExcelData> executionProcessExcelData, LocalDateTime start, LocalDateTime end) {
        log.info("Generate number of complaints filed in court for execution process report");
        return executionProcessExcelData.stream()
                .filter(data -> ExecutionProcessDateChecker.dateCheckerNumberOfComplaintsFiledInCourtReport(
                        data.getDateOfFilingAnApplicationToTheCourtCas(), start, end))
                .toList();
    }

    @Override
    public List<ExecutionProcessExcelData> generateControlOfTheWorkplaceReport(List<ExecutionProcessExcelData> executionProcessExcelData) {
        log.info("Generate control of the workplace for execution process report");
        return executionProcessExcelData.stream()
                .filter(data -> ExecutionProcessDateChecker.isAvailability(data.getAvailabilityOfPlaceOfWork()))
                .toList();
    }

    @Override
    public List<ExecutionProcessExcelData> generateVehicleControlReport(List<ExecutionProcessExcelData> executionProcessExcelData) {
        log.info("Generate vehicle control for execution process report");
        return executionProcessExcelData.stream()
                .filter(data -> ExecutionProcessDateChecker.isAvailability(data.getPresenceVehicle()))
                .toList();
    }

    @Override
    public List<ExecutionProcessExcelData> generatePropertyControlReport(List<ExecutionProcessExcelData> executionProcessExcelData) {
        log.info("Generate property control for execution process report");
        return executionProcessExcelData.stream()
                .filter(data -> ExecutionProcessDateChecker.isAvailability(data.getPresenceOfRealEstate()))
                .toList();
    }

    @Override
    public List<ExecutionProcessExcelData> generateNumberOfOutputsReport(List<ExecutionProcessExcelData> executionProcessExcelData, LocalDateTime start, LocalDateTime end) {
        log.info("Generate number of outputs for execution process report");
        return executionProcessExcelData.stream()
                .filter(data -> ExecutionProcessDateChecker.dateCheckerNumberOfOutputsReport(
                        data.getDateOfExitToAddress(), start, end))
                .toList();
    }
}
