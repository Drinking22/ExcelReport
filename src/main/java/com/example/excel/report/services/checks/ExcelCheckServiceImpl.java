package com.example.excel.report.services.checks;

import com.example.excel.report.constant.titles.ExcelJudicialSheetsNameConst;
import com.example.excel.report.model.ExecutionProcessExcelData;
import com.example.excel.report.model.JudicialExcelData;
import com.example.excel.report.model.LawsuitExcelData;
import com.example.excel.report.services.checks.filters.judicial.JudicialReportFilterImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExcelCheckServiceImpl implements ExcelCheckService {

    private final JudicialReportFilterImpl judicialReportFilterImpl;

    @Override
    public Map<String, List<JudicialExcelData>> generateJudicialMonthlyReport(List<JudicialExcelData> judicialExcelData) {
        log.info("Generate Judicial monthly report");
        LocalDateTime[] monthlyDays = getStartAndDateForMonth();
        return generateJudicialReport(judicialExcelData, monthlyDays);
    }

    @Override
    public Map<String, List<JudicialExcelData>> generateJudicialWeeklyReport(List<JudicialExcelData> judicialExcelData) {
        log.info("Generate Judicial weekly report");
        LocalDateTime[] weeklyDays = getStartAndEndDateForWeek();
        return generateJudicialReport(judicialExcelData, weeklyDays);
    }

    @Override
    public Map<String, List<LawsuitExcelData>> generateLawsuitMonthlyReport(List<LawsuitExcelData> lawsuitExcelData) {
        log.info("Generate Lawsuit monthly report");
        LocalDateTime[] monthlyDays = getStartAndDateForMonth();
        return generateLawsuitReport(lawsuitExcelData, monthlyDays);
    }

    @Override
    public Map<String, List<LawsuitExcelData>> generateLawsuitWeeklyReport(List<LawsuitExcelData> lawsuitExcelData) {
        log.info("Generate Lawsuit weekly report");
        LocalDateTime[] weeklyDays = getStartAndEndDateForWeek();
        return generateLawsuitReport(lawsuitExcelData, weeklyDays);
    }

    @Override
    public Map<String, List<ExecutionProcessExcelData>> generateExecutionProcessMonthlyReport(List<ExecutionProcessExcelData> executionProcessExcelData) {
        log.info("Generate Execution Process monthly report");
        LocalDateTime[] monthlyDays = getStartAndDateForMonth();
        return generateExecutionProcessReport(executionProcessExcelData, monthlyDays);
    }

    @Override
    public Map<String, List<ExecutionProcessExcelData>> generateExecutionProcessWeeklyReport(List<ExecutionProcessExcelData> executionProcessExcelData) {
        log.info("Generate Execution Process weekly report");
        LocalDateTime[] weeklyDays = getStartAndEndDateForWeek();
        return generateExecutionProcessReport(executionProcessExcelData, weeklyDays);
    }

    private Map<String, List<JudicialExcelData>> generateJudicialReport(List<JudicialExcelData> judicialExcelData, LocalDateTime[] days) {
        log.info("Generate all Judicial reports and puts in Map");

        Map<String, List<JudicialExcelData>> judicialReport = new HashMap<>();

        List<JudicialExcelData> sendToDebtorButNotFiledInCourt =
                judicialReportFilterImpl.generateSendToDebtorButNotFiledInCourtReport(judicialExcelData, days[0], days[1]);
        List<JudicialExcelData> courtOrderNotReceived =
                judicialReportFilterImpl.generateCourtOrderNotReceivedReport(judicialExcelData, days[0], days[1]);
        List<JudicialExcelData> copiesOfDocumentsSent =
                judicialReportFilterImpl.generateCopiesOfDocumentsSent(judicialExcelData, days[0], days[1]);
        List<JudicialExcelData> applicationsSubmittedToCourt =
                judicialReportFilterImpl.generateApplicationsSubmittedToCourt(judicialExcelData, days[0], days[1]);
        List<JudicialExcelData> cancellationOfTheCourtOrderButNoLawsuitFiled =
                judicialReportFilterImpl.generateCancellationOfTheCourtOrderButNoLawsuitFiled(judicialExcelData, days[0], days[1]);
        List<JudicialExcelData> returnOfDocumentsFromTheCourt =
                judicialReportFilterImpl.generateReturnOfDocumentsFromTheCourt(judicialExcelData, days[0], days[1]);
        List<JudicialExcelData> receivedCourtOrder =
                judicialReportFilterImpl.generateReceivedCourtOrder(judicialExcelData, days[0], days[1]);

        judicialReport.put(ExcelJudicialSheetsNameConst.SENT_TO_DEBTOR_BUT_NOT_FILED_IN_COURT.getSheetName(), sendToDebtorButNotFiledInCourt);
        judicialReport.put(ExcelJudicialSheetsNameConst.COURT_ORDER_NOT_RECEIVED.getSheetName(), courtOrderNotReceived);
        judicialReport.put(ExcelJudicialSheetsNameConst.COPIES_OF_DOCUMENTS_SENT.getSheetName(), copiesOfDocumentsSent);
        judicialReport.put(ExcelJudicialSheetsNameConst.APPLICATIONS_SUBMITTED_TO_COURT.getSheetName(), applicationsSubmittedToCourt);
        judicialReport.put(ExcelJudicialSheetsNameConst.CANCELLATION_OF_THE_COURT_ORDER_BUT_NO_LAWSUIT_FILED.getSheetName(), cancellationOfTheCourtOrderButNoLawsuitFiled);
        judicialReport.put(ExcelJudicialSheetsNameConst.RETURN_OF_DOCUMENTS_FROM_THE_COURT.getSheetName(), returnOfDocumentsFromTheCourt);
        judicialReport.put(ExcelJudicialSheetsNameConst.RECEIVED_COURT_ORDER.getSheetName(), receivedCourtOrder);

        return judicialReport;
    }

    private Map<String, List<LawsuitExcelData>> generateLawsuitReport(List<LawsuitExcelData> lawsuitExcelData, LocalDateTime[] days) {
        log.info("Generate all Lawsuit reports and puts in Map");
        return null;
    }

    private Map<String, List<ExecutionProcessExcelData>> generateExecutionProcessReport(List<ExecutionProcessExcelData> executionProcessExcelData, LocalDateTime[] days) {
        log.info("Generate all Execution Process reports and puts in Map");
        return null;
    }

    private LocalDateTime[] getStartAndEndDateForWeek() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime startDay = now.minusDays(7).withHour(0).withMinute(0).withSecond(0);
        LocalDateTime endDay = now.minusDays(1).withHour(23).withMinute(59).withSecond(59);
        return new LocalDateTime[] {startDay, endDay};
    }

    private LocalDateTime[] getStartAndDateForMonth() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime startDay = now.minusDays(30).withHour(0).withMinute(0).withSecond(0);
        LocalDateTime endDay = now.minusDays(1).withHour(23).withMinute(59).withSecond(59);
        return new LocalDateTime[] {startDay, endDay};
    }
}
