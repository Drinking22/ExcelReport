package com.example.excel.report.constant.titles;

import lombok.Getter;

@Getter
public enum ExcelSheetsNameConst {

    SENT_TO_DEBTOR_BUT_NOT_FILED_IN_COURT("Отправлено должнику но не подано в суд");

    private final String sheetName;

    ExcelSheetsNameConst(String sheetName) {
        this.sheetName = sheetName;
    }
}
