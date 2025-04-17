package com.example.excel.report.services.report;

import com.example.excel.report.constant.ColumnsExcelReport;
import com.example.excel.report.model.ExecutionProcessExcelData;
import com.example.excel.report.model.JudicialExcelData;
import com.example.excel.report.model.LawsuitExcelData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExcelWriterServiceImpl implements ExcelWriterService {

    @Override
    public String writeJudicialReportExcelFile(String fileName, Map<String, List<JudicialExcelData>> allReports, String filePath) {
        log.info("Recording report of judicial work in excel file");

        String fullPath = filePath + File.separator + fileName;

        try (Workbook workbook = createWorkbook();
             FileOutputStream outputStream = createFileOutputStream(filePath)) {

            fillWorkbookWithJudicialReports(workbook, allReports);

            workbook.write(outputStream);
        } catch (IOException ex) {
            log.error("Error writing Excel file", ex);
            throw new RuntimeException("Failed to write Excel file", ex);
        }
        return fullPath;
    }

    @Override
    public String writeLawsuitReportExcelFile(String fileName, Map<String, List<LawsuitExcelData>> allReports, String filePath) {
        log.info("");
        return null;
    }

    @Override
    public String writeExecutionProcessReportExcelFile(String fileName, Map<String, List<ExecutionProcessExcelData>> allReports, String filePath) {
        log.info("");
        return null;
    }

    private void fillWorkbookWithJudicialReports(Workbook workbook, Map<String, List<JudicialExcelData>> allReports) {
        log.info("Fill workbook with judicial reports");

        for (Map.Entry<String, List<JudicialExcelData>> entry : allReports.entrySet()) {
            String sheetName = entry.getKey();
            List<JudicialExcelData> dataList = entry.getValue();
            String[] columnsName = ColumnsExcelReport.getJudicialColumns();
            createSheetWithData(workbook, sheetName, dataList, columnsName);
        }
    }

    private void fillWorkbookWithLawsuitReports(Workbook workbook, Map<String, List<LawsuitExcelData>> allReports) {
        log.info("");
    }

    private void fillWorkbookWithExecutionProcessReports(Workbook workbook, Map<String, List<ExecutionProcessExcelData>> allReports) {
        log.info("");
    }

    private void createSheetWithData(Workbook workbook, String sheetName, List<JudicialExcelData> dataList, String[] columnsName) {
        log.info("Create sheets");

        if (dataList != null && !dataList.isEmpty()) {
            Sheet sheet = workbook.createSheet(sheetName);
            createHeaderRow(sheet, columnsName);
            createRowsForJudicialData(sheet, dataList);
        }
    }

    private void createHeaderRow(Sheet sheet, String[] columnsName) {
        log.info("Create header row");

        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < columnsName.length; i++) {
            headerRow.createCell(i).setCellValue(columnsName[i]);
        }
    }

    private void createRowsForJudicialData(Sheet sheet, List<JudicialExcelData> dataList) {
        log.info("Create rows for judicial work data");

        int rowNumber = 1;
        for (JudicialExcelData data : dataList) {
            Row row = sheet.createRow(rowNumber++);
            row.createCell(0).setCellValue(data.getNumber());
            row.createCell(1).setCellValue(data.getFullNameDebtor());
            row.createCell(2).setCellValue(data.getPersonalAccountNumber());
            row.createCell(3).setCellValue(data.getContract());
            row.createCell(4).setCellValue(data.getPeriod());
            row.createCell(5).setCellValue(data.getAmountOfDebt() != null ? data.getAmountOfDebt().doubleValue() : 0);
            row.createCell(6).setCellValue(data.getDebtRepaymentDate());
            row.createCell(7).setCellValue(data.getPenalty() != null ? data.getPenalty().doubleValue() : 0);
            row.createCell(8).setCellValue(data.getAmountOfStateDuty() != null ? data.getAmountOfStateDuty().doubleValue() : 0);
            row.createCell(9).setCellValue(data.getNumberOfStateDuty());
            row.createCell(10).setCellValue(data.getDateOfSendingCopiesOfDocuments());
            row.createCell(11).setCellValue(data.getDateOfFilingAnApplicationToTheCourt());
            row.createCell(12).setCellValue(data.getJudicialDistrict());
            row.createCell(13).setCellValue(data.getDateOfDetermination());
            row.createCell(14).setCellValue(data.getDeterminationOnTheReturnOfTheApplication());
            row.createCell(15).setCellValue(data.getDeterminationToCancelTheJointVenture());
            row.createCell(16).setCellValue(data.getDateOfFilingAnApplicationInTheClaimProcedure());
            row.createCell(17).setCellValue(data.getNumberOfCourtOrder());
            row.createCell(18).setCellValue(data.getDateOfCourtOrder());
            row.createCell(19).setCellValue(data.getDateOfReceiptOfCourtOrder());
            row.createCell(20).setCellValue(data.getComment());
        }
    }

    private void createRowsForLawsuitData(Sheet sheet, List<LawsuitExcelData> dataList) {
        log.info("");
    }

    private void createRowsForExecutionProcessData(Sheet sheet, List<ExecutionProcessExcelData> dataList) {
        log.info("");
    }

    private Workbook createWorkbook() {
        return new XSSFWorkbook();
    }

    private FileOutputStream createFileOutputStream(String filePath) throws FileNotFoundException {
        return new FileOutputStream(filePath);
    }
}
