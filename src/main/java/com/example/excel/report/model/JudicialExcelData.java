package com.example.excel.report.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class JudicialExcelData {

    private int number;
    private String fullNameDebtor; //ФИО должника
    private int personalAccountNumber; //номер лицнвого счета
    private String contract; //Договор
    private String period; //Период задолженности
    private int amountOfDebt; //Сумма долга
    private int penalty; //Пени
    private int amountOfStateDuty; //Сумма ГП
    private int numberOfStateDuty; //№ ГП
    private LocalDateTime dateOfSendingCopiesOfDocuments; //Дата отправления копий документов
    private LocalDateTime dateOfFilingAnApplicationToTheCourt; //Дата подачи заявления в суд
    private String judicialDistrict; //Судебный участок
    private LocalDateTime dateOfDetermination; //Дата определения
    private LocalDateTime dateOfReceiptOfDetermination; //Дата получения определения
    private LocalDateTime dateOfFilingAnApplicationInTheClaimProcedure; //Дата подачи заявления в исковом порядке
    private String numberOfCourtOrder; //№ судебного приказа
    private LocalDateTime dateOfCourtOrder; //Дата СП
    private LocalDateTime dateOfReceiptOfCourtOrder; //Дата получения СП
    private String comment;
}
