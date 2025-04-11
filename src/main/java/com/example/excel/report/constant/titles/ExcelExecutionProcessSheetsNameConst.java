package com.example.excel.report.constant.titles;

import lombok.Getter;

@Getter
public enum ExcelExecutionProcessSheetsNameConst {
    SUBMITTED_FOR_EXECUTION("Подано на исполнение"),
    DOCUMENT_RECEIVED_BUT_NOT_SUBMITTED_FOR_EXECUTION("Получен документ, но не подан на исполнение"),
    DOCUMENT_FILED_BUT_NO_INITIATION("Документ подан, но нет возбуждения"),
    NUMBER_OF_PROGRESS_REPORTS("Количество заявлений о ходе"),
    NUMBER_OF_COMPLAINTS_FILED("Количество поданных жалоб"),
    NUMBER_OF_COMPLAINTS_FILED_IN_COURT("Количество поданных жалоб в суд"),
    CONTROL_OF_THE_WORKPLACE("Контроль места работы"),
    VEHICLE_CONTROL("Контроль транспортного средства"),
    PROPERTY_CONTROL("Контроль недвижимости"),
    NUMBER_OF_OUTPUTS("Количество выходов");

    private final String sheetName;

    ExcelExecutionProcessSheetsNameConst(String sheetName) {
        this.sheetName = sheetName;
    }
}
