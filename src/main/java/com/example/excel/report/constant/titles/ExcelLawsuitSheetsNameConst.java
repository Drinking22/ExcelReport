package com.example.excel.report.constant.titles;

import lombok.Getter;

@Getter
public enum ExcelLawsuitSheetsNameConst {
    CLAIMS_FILED("Подано исковых заявлений"),
    DATE_OF_REVIEW("Дата рассмотрения"),
    RECEIVED_WRITS_OF_EXECUTION("Получено исполнительных документов");

    private final String sheetName;

    ExcelLawsuitSheetsNameConst(String sheetName) {
        this.sheetName = sheetName;
    }
}
