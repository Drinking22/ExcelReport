package com.example.excel.report.constant.titles;

public enum ExcelExecutionProcessSheetsNameConst {
    SUBMITTED_FOR_EXECUTION("Подано на исполнение"),
    DOCUMENT_RECEIVED_BUT_NOT_SUBMITTED_FOR_EXECUTION("Получен документ, но не подан на исполнение"),
    DOCUMENT_FILED_BUT_NO_INITIATION("Документ подан, но нет возбуждения");

    private final String sheetName;

    ExcelExecutionProcessSheetsNameConst(String sheetName) {
        this.sheetName = sheetName;
    }
}
