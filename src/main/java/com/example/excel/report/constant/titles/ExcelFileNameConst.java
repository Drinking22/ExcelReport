package com.example.excel.report.constant.titles;

import lombok.Getter;

@Getter
public enum ExcelFileNameConst {
    WEEKLY_REPORT_APSHERONSK("Отчет за неделю по Апшеронску"),
    MONTHLY_REPORT_APSHERONSK("Отчет за месяц по Апшеронску"),
    WEEKLY_REPORT_GORAYCHIY_CLUCH("Отчет за неделю по Горячему Ключу"),
    MONTHLY_REPORT_GORAYCHIY_CLUCH("Отчет за месяц по Горячему Ключу"),
    WEEKLY_REPORT_BELORECHENSK("Отчет за неделю по Белореченску"),
    MONTHLY_REPORT_BELORECHENSK("Отчет за месяц по Белореченску"),
    WEEKLY_REPORT_TUAPSE("Отчет за неделю по Туапсе"),
    MONTHLY_REPORT_TUAPSE("Отчет за месяц по Туапсе"),
    WEEKLY_REPORT_SOCHI("Отчет за неделю по Сочи"),
    MONTHLY_REPORT_SOCHI("Отчет за месяц по Сочи"),;

    private final String excelFileName;

    ExcelFileNameConst(String message) {
        this.excelFileName = message;
    }
}
