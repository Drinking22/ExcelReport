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
import java.time.LocalDateTime;
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
                log.info("Getting data from row {}", i);
                Row row = judicialSheet.getRow(i);
                if (row != null) {
                    JudicialExcelData judicialExcelModel = createJudicialModel(row, i);
                    judicialExcelData.add(judicialExcelModel);
                    log.info("Judicial model {} added to list", judicialExcelModel.getPersonalAccountNumber());
                }
            }
        }

        log.info("Full list with data from excel file + {}", judicialExcelData);
        return judicialExcelData;
    }

    @Override
    public List<LawsuitExcelData> parseLawsuitExcelFile(FileInputStream excelFile) {
        log.info("Parse sheet lawsuit work from excel file");
        return List.of();
    }

    @Override
    public List<ExecutionProcessExcelData> parseExecutionProcessExcelFile(FileInputStream excelFile) {
        log.info("Parse sheet execution process work from excel file");
        return List.of();
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

        return new JudicialExcelData(number, fullNameDebtor, personalAccountNumber, contract, period, amountOfDebt, penalty, amountOfStateDuty,
                numberOfStateDuty, dateOfSendingCopiesOfDocuments, dateOfFilingAnApplicationToTheCourt, judicialDistrict, dateOfDetermination,
                dateOfReceiptOfDetermination, dateOfFilingAnApplicationInTheClaimProcedure, numberOfCourtOrder, dateOfCourtOrder,
                dateOfReceiptOfCourtOrder, comment);
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
        log.warn("Row {}: Cell at column {} is not a date, returning null.", rowIndex, columnIndex);

        return null;
    }
}
