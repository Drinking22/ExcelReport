package com.example.excel.report.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;

@Component
@Slf4j
public class GenerateExcelFile {

    public Workbook parseExcelFile(FileInputStream excelFile) throws IOException {
        log.info("Create XSSFWorkbook");
        return new XSSFWorkbook(excelFile);
    }
}
