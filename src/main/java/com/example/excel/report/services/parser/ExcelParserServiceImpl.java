package com.example.excel.report.services.parser;

import com.example.excel.report.model.ExecutionProcessExcelData;
import com.example.excel.report.model.JudicialExcelData;
import com.example.excel.report.model.LawsuitExcelData;
import com.example.excel.report.util.GenerateExcelFile;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExcelParserServiceImpl implements ExcelParserService {

    private final GenerateExcelFile generateExcelFile;

    @Override
    public List<JudicialExcelData> parseJudicialExcelFile(FileInputStream excelFile) throws IOException {
        log.info("Parse sheet judicial work from excel file");

        List<JudicialExcelData> judicialExcelData = new ArrayList<>();

        try (Workbook workbook = createWorkbook(excelFile)) {
            log.info("Create sheet judicial work");
            Sheet judicialSheet = workbook.getSheetAt(0);

            for (int i = 1; i <= judicialSheet.getLastRowNum(); i++) {
                log.info("Getting data from judicial row {}", i);
                Row row = judicialSheet.getRow(i);
                if (row != null) {
                    JudicialExcelData judicialExcelModel = createJudicialModel(row, i);
                    judicialExcelData.add(judicialExcelModel);
                    log.info("Judicial model {} added to list", judicialExcelModel.getPersonalAccountNumber());
                }
            }
        }

        log.info("Full list with data from judicial sheet of excel file + {}", judicialExcelData);
        return judicialExcelData;
    }

    @Override
    public List<LawsuitExcelData> parseLawsuitExcelFile(FileInputStream excelFile) throws IOException {
        log.info("Parse sheet lawsuit work from excel file");

        List<LawsuitExcelData> lawsuitExcelData = new ArrayList<>();

        try (Workbook workbook = createWorkbook(excelFile)) {
            log.info("Create sheet lawsuit work");
            Sheet lawsuitSheet = workbook.getSheetAt(1);

            for (int i = 1; i <= lawsuitSheet.getLastRowNum(); i++) {
                log.info("Getting data from lawsuit row {}", i);
                Row row = lawsuitSheet.getRow(i);
                if (row != null) {
                    LawsuitExcelData lawsuitExcelModel = createLawsuitModel(row, i);
                    lawsuitExcelData.add(lawsuitExcelModel);
                    log.info("Lawsuit model {} added to list", lawsuitExcelModel.getPersonalAccountNumber());
                }
            }
        }

        log.info("Full list with data from lawsuit sheet of excel file + {}", lawsuitExcelData);
        return lawsuitExcelData;
    }

    @Override
    public List<ExecutionProcessExcelData> parseExecutionProcessExcelFile(FileInputStream excelFile) throws IOException {
        log.info("Parse sheet execution process work from excel file");

        List<ExecutionProcessExcelData> executionProcessExcelData = new ArrayList<>();

        try (Workbook workbook = createWorkbook(excelFile)) {
            log.info("Create sheet execution process work");
            Sheet executionProcessSheet = workbook.getSheetAt(2);

            for (int i = 1; i <= executionProcessSheet.getLastRowNum(); i++) {
                log.info("Getting data from execution process row {}", i);
                Row row = executionProcessSheet.getRow(i);
                if (row != null) {
                    ExecutionProcessExcelData executionProcessExcelModel = createExecutionProcessModel(row, i);
                    executionProcessExcelData.add(executionProcessExcelModel);
                    log.info("Execution process model {} added to list", executionProcessExcelModel.getPersonalAccountNumber());
                }
            }
        }

        log.info("Full list with data from execution process sheet of excel file + {}", executionProcessExcelData);
        return executionProcessExcelData;
    }

    public Workbook createWorkbook(FileInputStream excelFile) throws IOException {
        log.info("Create excel workbook");
        return generateExcelFile.parseExcelFile(excelFile);
    }

    public JudicialExcelData createJudicialModel(Row row, int rowIndex) {
        log.info("Create judicial object");

        int number = getCellValueAsInt(row.getCell(0), rowIndex, 0);
        String fullNameDebtor = getCellValueAsString(row.getCell(1), rowIndex, 1);
        int personalAccountNumber = getCellValueAsInt(row.getCell(2), rowIndex, 2);
        String contract = getCellValueAsString(row.getCell(3), rowIndex, 3);
        String period = getCellValueAsString(row.getCell(4), rowIndex, 4);
        int amountOfDebt = getCellValueAsInt(row.getCell(5), rowIndex, 5);
        int penalty = getCellValueAsInt(row.getCell(6), rowIndex, 6);
        int amountOfStateDuty = getCellValueAsInt(row.getCell(7), rowIndex, 7);
        int numberOfStateDuty = getCellValueAsInt(row.getCell(8), rowIndex, 8);
        LocalDateTime dateOfSendingCopiesOfDocuments = getCellValueAsLocalDateTime(row.getCell(9), rowIndex, 9);
        LocalDateTime dateOfFilingAnApplicationToTheCourt = getCellValueAsLocalDateTime(row.getCell(10), rowIndex, 10);
        String judicialDistrict = getCellValueAsString(row.getCell(11), rowIndex, 11);
        LocalDateTime dateOfDetermination = getCellValueAsLocalDateTime(row.getCell(12), rowIndex, 12);
        LocalDateTime dateOfReceiptOfDetermination = getCellValueAsLocalDateTime(row.getCell(13), rowIndex, 13);
        LocalDateTime dateOfFilingAnApplicationInTheClaimProcedure = getCellValueAsLocalDateTime(row.getCell(14), rowIndex, 14);
        String numberOfCourtOrder = getCellValueAsString(row.getCell(15), rowIndex, 15);
        LocalDateTime dateOfCourtOrder = getCellValueAsLocalDateTime(row.getCell(16), rowIndex, 16);
        LocalDateTime dateOfReceiptOfCourtOrder = getCellValueAsLocalDateTime(row.getCell(17), rowIndex, 17);
        String comment = getCellValueAsString(row.getCell(18), rowIndex, 18);

        return JudicialExcelData.builder()
                .number(number)
                .fullNameDebtor(fullNameDebtor)
                .personalAccountNumber(personalAccountNumber)
                .contract(contract)
                .period(period)
                .amountOfDebt(amountOfDebt)
                .penalty(penalty)
                .amountOfStateDuty(amountOfStateDuty)
                .numberOfStateDuty(numberOfStateDuty)
                .dateOfSendingCopiesOfDocuments(dateOfSendingCopiesOfDocuments)
                .dateOfFilingAnApplicationToTheCourt(dateOfFilingAnApplicationToTheCourt)
                .judicialDistrict(judicialDistrict)
                .dateOfDetermination(dateOfDetermination)
                .dateOfReceiptOfDetermination(dateOfReceiptOfDetermination)
                .dateOfFilingAnApplicationInTheClaimProcedure(dateOfFilingAnApplicationInTheClaimProcedure)
                .numberOfCourtOrder(numberOfCourtOrder)
                .dateOfCourtOrder(dateOfCourtOrder)
                .dateOfReceiptOfCourtOrder(dateOfReceiptOfCourtOrder)
                .comment(comment)
                .build();
    }

    public LawsuitExcelData createLawsuitModel(Row row, int rowIndex) {
        log.info("Create lawsuit object");

        int number = getCellValueAsInt(row.getCell(0), rowIndex, 0);
        String fullNameDebtor = getCellValueAsString(row.getCell(1), rowIndex, 1);
        int personalAccountNumber = getCellValueAsInt(row.getCell(2), rowIndex, 2);
        String contract = getCellValueAsString(row.getCell(3), rowIndex, 3);
        String period = getCellValueAsString(row.getCell(4), rowIndex, 4);
        int amountOfDebt = getCellValueAsInt(row.getCell(5), rowIndex, 5);
        int penalty = getCellValueAsInt(row.getCell(6), rowIndex, 6);
        int amountOfStateDuty = getCellValueAsInt(row.getCell(7), rowIndex, 7);
        int numberOfStateDuty = getCellValueAsInt(row.getCell(8), rowIndex, 8);
        String judicialDistrict = getCellValueAsString(row.getCell(9), rowIndex, 9);
        LocalDateTime dateOfFilingAnApplicationInTheClaimProcedure = getCellValueAsLocalDateTime(row.getCell(10), rowIndex, 10);
        LocalDateTime dateCaseReview = getCellValueAsLocalDateTime(row.getCell(11), rowIndex, 11);
        String courtDecisionAndCaseNumber = getCellValueAsString(row.getCell(12), rowIndex, 12);
        LocalDateTime dateOfTheDecision = getCellValueAsLocalDateTime(row.getCell(13), rowIndex, 13);
        LocalDateTime effectiveDate = getCellValueAsLocalDateTime(row.getCell(14), rowIndex, 14);
        String numberOfWritExecution = getCellValueAsString(row.getCell(15), rowIndex, 15);
        LocalDateTime dateOfExecutionWrit = getCellValueAsLocalDateTime(row.getCell(16), rowIndex, 16);
        LocalDateTime dateOfReceiptExecution = getCellValueAsLocalDateTime(row.getCell(17), rowIndex, 17);
        String comment = getCellValueAsString(row.getCell(18), rowIndex, 18);

        return LawsuitExcelData.builder()
                .number(number)
                .fullNameDebtor(fullNameDebtor)
                .personalAccountNumber(personalAccountNumber)
                .contract(contract)
                .period(period)
                .amountOfDebt(amountOfDebt)
                .penalty(penalty)
                .amountOfStateDuty(amountOfStateDuty)
                .numberOfStateDuty(numberOfStateDuty)
                .judicialDistrict(judicialDistrict)
                .dateOfFilingAnApplicationInTheClaimProcedure(dateOfFilingAnApplicationInTheClaimProcedure)
                .dateCaseReview(dateCaseReview)
                .courtDecisionAndCaseNumber(courtDecisionAndCaseNumber)
                .dateOfTheDecision(dateOfTheDecision)
                .effectiveDate(effectiveDate)
                .numberOfWritExecution(numberOfWritExecution)
                .dateOfExecutionWrit(dateOfExecutionWrit)
                .dateOfReceiptExecution(dateOfReceiptExecution)
                .comment(comment)
                .build();
    }

    public ExecutionProcessExcelData createExecutionProcessModel(Row row, int rowIndex) {
        log.info("Create execution process object");

        int number = getCellValueAsInt(row.getCell(0), rowIndex, 0);
        String fullNameDebtor = getCellValueAsString(row.getCell(1), rowIndex, 1);
        int personalAccountNumber = getCellValueAsInt(row.getCell(2), rowIndex, 2);
        String numberOfWritExecution = getCellValueAsString(row.getCell(3), rowIndex, 3);
        LocalDateTime dateOfExecutionWrit = getCellValueAsLocalDateTime(row.getCell(4), rowIndex, 4);
        int amountOfDebt = getCellValueAsInt(row.getCell(5), rowIndex, 5);
        int amountOfRemainder = getCellValueAsInt(row.getCell(6), rowIndex, 6);
        String isRepeatSubmission = getCellValueAsString(row.getCell(7), rowIndex, 7);
        LocalDateTime dateOfFilingApplicationFtx = getCellValueAsLocalDateTime(row.getCell(8), rowIndex, 8);
        LocalDateTime dateOfReceiptFtxResponse = getCellValueAsLocalDateTime(row.getCell(9), rowIndex, 9);
        LocalDateTime dateOfWritExecutionSubmissionToBank = getCellValueAsLocalDateTime(row.getCell(10), rowIndex, 10);
        String bank = getCellValueAsString(row.getCell(11), rowIndex, 11);
        LocalDateTime dateOfWithdrawalFromBank = getCellValueAsLocalDateTime(row.getCell(12), rowIndex, 12);
        LocalDateTime dateOfSubmissionWritExecutionBailiffs = getCellValueAsLocalDateTime(row.getCell(13), rowIndex, 13);
        LocalDateTime dateOfReceiptOfTheDecisionToRefuseCollection = getCellValueAsLocalDateTime(row.getCell(14), rowIndex, 14);
        LocalDateTime dateOFilingAnApplicationForNonInitiationOfEnforcementProceedings = getCellValueAsLocalDateTime(row.getCell(15), rowIndex, 15);
        LocalDateTime dateOfInitiationOfEnforcementProceedings = getCellValueAsLocalDateTime(row.getCell(16), rowIndex, 16);
        String numberOfEnforcementProceedings = getCellValueAsString(row.getCell(17), rowIndex, 17);
        LocalDateTime dateOfFilingAnApplicationProgressOfEnforcementProceedings = getCellValueAsLocalDateTime(row.getCell(18), rowIndex, 18);
        LocalDateTime dateOfFilingTheComplaint = getCellValueAsLocalDateTime(row.getCell(19), rowIndex, 19);
        String subjectOfAppeal = getCellValueAsString(row.getCell(20), rowIndex, 20);
        String resultOfTheComplaintReview = getCellValueAsString(row.getCell(21), rowIndex, 21);
        LocalDateTime dateOfFilingAnApplicationToTheCourtCas = getCellValueAsLocalDateTime(row.getCell(22), rowIndex, 22);
        String subjectOfAppealCourt = getCellValueAsString(row.getCell(23), rowIndex, 23);
        String courtDecision = getCellValueAsString(row.getCell(24), rowIndex, 24);
        String availabilityOfPlaceOfWork = getCellValueAsString(row.getCell(25), rowIndex, 25);
        LocalDateTime dateOfSendingApplicationPlaceOfReceiptOfIncome = getCellValueAsLocalDateTime(row.getCell(26), rowIndex, 26);
        String presenceVehicle = getCellValueAsString(row.getCell(27), rowIndex, 27);
        LocalDateTime measuresTakenVehicle = getCellValueAsLocalDateTime(row.getCell(28), rowIndex, 28);
        String presenceOfRealEstate = getCellValueAsString(row.getCell(29), rowIndex, 29);
        String measuresTakenOnRealEstate = getCellValueAsString(row.getCell(30), rowIndex, 30);
        LocalDateTime dateOfResolutionRestrictionOnExit = getCellValueAsLocalDateTime(row.getCell(31), rowIndex, 31);
        LocalDateTime dateOfExitToAddress = getCellValueAsLocalDateTime(row.getCell(32), rowIndex, 32);
        String resultOfExit = getCellValueAsString(row.getCell(33), rowIndex, 33);
        LocalDateTime dateOfActualTerminationOfEnforcementProceedings = getCellValueAsLocalDateTime(row.getCell(34), rowIndex, 34);
        LocalDateTime dateOfIssuanceOfResolutionOfReturnOfExecutionWrit = getCellValueAsLocalDateTime(row.getCell(35), rowIndex, 35);
        String article = getCellValueAsString(row.getCell(36), rowIndex, 36);
        String part = getCellValueAsString(row.getCell(37), rowIndex, 37);
        LocalDateTime dateOfTerminationOfEnforcementProceedings = getCellValueAsLocalDateTime(row.getCell(38), rowIndex, 38);
        String reasonForTerminationOfEnforcementProceedings = getCellValueAsString(row.getCell(39), rowIndex, 39);
        String comment = getCellValueAsString(row.getCell(40), rowIndex, 40);

        return ExecutionProcessExcelData.builder()
                .number(number)
                .fullNameDebtor(fullNameDebtor)
                .personalAccountNumber(personalAccountNumber)
                .numberOfWritExecution(numberOfWritExecution)
                .dateOfExecutionWrit(dateOfExecutionWrit)
                .amountOfDebt(amountOfDebt)
                .amountOfRemainder(amountOfRemainder)
                .isRepeatSubmission(isRepeatSubmission)
                .dateOfFilingApplicationFtx(dateOfFilingApplicationFtx)
                .dateOfReceiptFtxResponse(dateOfReceiptFtxResponse)
                .dateOfWritExecutionSubmissionToBank(dateOfWritExecutionSubmissionToBank)
                .bank(bank)
                .dateOfWithdrawalFromBank(dateOfWithdrawalFromBank)
                .dateOfSubmissionWritExecutionBailiffs(dateOfSubmissionWritExecutionBailiffs)
                .dateOfReceiptOfTheDecisionToRefuseCollection(dateOfReceiptOfTheDecisionToRefuseCollection)
                .dateOFilingAnApplicationForNonInitiationOfEnforcementProceedings(dateOFilingAnApplicationForNonInitiationOfEnforcementProceedings)
                .dateOfInitiationOfEnforcementProceedings(dateOfInitiationOfEnforcementProceedings)
                .numberOfEnforcementProceedings(numberOfEnforcementProceedings)
                .dateOfFilingAnApplicationProgressOfEnforcementProceedings(dateOfFilingAnApplicationProgressOfEnforcementProceedings)
                .dateOfFilingTheComplaint(dateOfFilingTheComplaint)
                .subjectOfAppeal(subjectOfAppeal)
                .resultOfTheComplaintReview(resultOfTheComplaintReview)
                .dateOfFilingAnApplicationToTheCourtCas(dateOfFilingAnApplicationToTheCourtCas)
                .subjectOfAppealCourt(subjectOfAppealCourt)
                .courtDecision(courtDecision)
                .availabilityOfPlaceOfWork(availabilityOfPlaceOfWork)
                .dateOfSendingApplicationPlaceOfReceiptOfIncome(dateOfSendingApplicationPlaceOfReceiptOfIncome)
                .presenceVehicle(presenceVehicle)
                .measuresTakenVehicle(measuresTakenVehicle)
                .presenceOfRealEstate(presenceOfRealEstate)
                .measuresTakenOnRealEstate(measuresTakenOnRealEstate)
                .dateOfResolutionRestrictionOnExit(dateOfResolutionRestrictionOnExit)
                .dateOfExitToAddress(dateOfExitToAddress)
                .resultOfExit(resultOfExit)
                .dateOfActualTerminationOfEnforcementProceedings(dateOfActualTerminationOfEnforcementProceedings)
                .dateOfIssuanceOfResolutionOfReturnOfExecutionWrit(dateOfIssuanceOfResolutionOfReturnOfExecutionWrit)
                .article(article)
                .part(part)
                .dateOfTerminationOfEnforcementProceedings(dateOfTerminationOfEnforcementProceedings)
                .reasonForTerminationOfEnforcementProceedings(reasonForTerminationOfEnforcementProceedings)
                .comment(comment)
                .build();
    }

    private int getCellValueAsInt(Cell cell, int rowIndex, int columnIndex) {
        if (cell == null) {
            log.warn("Row {}: Cell at column {} is null, returning default value 0.", rowIndex, columnIndex);
            return 0;
        }
        if (cell.getCellType() != CellType.NUMERIC) {
            log.warn("Row {}: Cell at column {} is not numeric (type: {}), returning default value 0.", rowIndex, columnIndex, cell.getCellType());
            return 0;
        }
        return (int) cell.getNumericCellValue();
    }

    private String getCellValueAsString(Cell cell, int rowIndex, int columnIndex) {
        if (cell == null) {
            log.warn("Row {}: Cell at column {} is null, returning empty string.", rowIndex, columnIndex);
            return "";
        }
        if (cell.getCellType() != CellType.STRING) {
            log.warn("Row {}: Cell at column {} is not a string (type: {}), returning empty string.", rowIndex, columnIndex, cell.getCellType());
            return "";
        }
        return cell.getStringCellValue();
    }

    private LocalDateTime getCellValueAsLocalDateTime(Cell cell, int rowIndex, int columnIndex) {
        if (cell == null) {
            log.warn("Row {}: Cell at column {} is null, returning null.", rowIndex, columnIndex);
            return null;
        }

        if (cell.getCellType() == CellType.NUMERIC && DateUtil.isCellDateFormatted(cell)) {
            return cell.getLocalDateTimeCellValue();
        }

        if (cell.getCellType() == CellType.STRING) {
            String dateString = cell.getStringCellValue();
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                LocalDate date = LocalDate.parse(dateString, formatter);
                return date.atStartOfDay();
            } catch (DateTimeParseException ex) {
                log.warn("Row {}: Cell at column {} contains invalid date string '{}', returning null.", rowIndex, columnIndex, dateString);
                return null;
            }
        }
        log.warn("Row {}: Cell at column {} is not a date, returning null.", rowIndex, columnIndex);

        return null;
    }
}
