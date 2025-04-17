package com.example.excel.report.services;

import java.io.FileInputStream;
import java.io.FileOutputStream;


public interface MainReportService {
    String generateMonthlyJudicialReportFile(String fileName, FileInputStream excelFile);
    String generateWeeklyJudicialReportFile(String fileName, FileInputStream excelFile);
    String generateMonthlyLawsuitReportFile(String fileName, FileInputStream excelFile);
    String generateWeeklyLawsuitReportFile(String fileName, FileInputStream excelFile);
    String generateMonthlyExecutionProcessReportFile(String fileName, FileInputStream excelFile);
    String generateWeeklyExecutionProcessReportFile(String fileName, FileInputStream excelFile);
}
