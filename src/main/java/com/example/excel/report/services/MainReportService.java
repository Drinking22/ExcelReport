package com.example.excel.report.services;

import com.example.excel.report.constant.titles.ExcelFileNameConst;

import java.io.FileInputStream;
import java.io.FileOutputStream;


public interface MainReportService {
    FileOutputStream generateMonthlyJudicialReportFile(ExcelFileNameConst fileName, FileInputStream excelFile);
    FileOutputStream generateWeeklyJudicialReportFile(ExcelFileNameConst fileName,FileInputStream excelFile);
    FileOutputStream generateMonthlyLawsuitReportFile(ExcelFileNameConst fileName,FileInputStream excelFile);
    FileOutputStream generateWeeklyLawsuitReportFile(ExcelFileNameConst fileName,FileInputStream excelFile);
    FileOutputStream generateMonthlyExecutionProcessReportFile(ExcelFileNameConst fileName,FileInputStream excelFile);
    FileOutputStream generateWeeklyExecutionProcessReportFile(ExcelFileNameConst fileName,FileInputStream excelFile);
}
