package com.example.excel.report.constant.titles;

import lombok.Getter;

@Getter
public enum ExcelJudicialSheetsNameConst {

    SENT_TO_DEBTOR_BUT_NOT_FILED_IN_COURT("Отправлено должнику, но не подано в суд"),
    COURT_ORDER_NOT_RECEIVED("Не получен судебный приказ более 3 месяцев"),
    COPIES_OF_DOCUMENTS_SENT("Отправлено копий документов должнику"),
    APPLICATIONS_SUBMITTED_TO_COURT("Отправлено заявлений в суд"),
    CANCELLATION_OF_THE_COURT_ORDER_BUT_NO_LAWSUIT_FILED("Отмена СП, но не подан иск"),
    RETURN_OF_DOCUMENTS_FROM_THE_COURT("Возврат документов из суда"),
    RECEIVED_COURT_ORDER("Получено СП");

    private final String sheetName;

    ExcelJudicialSheetsNameConst(String sheetName) {
        this.sheetName = sheetName;
    }
}
