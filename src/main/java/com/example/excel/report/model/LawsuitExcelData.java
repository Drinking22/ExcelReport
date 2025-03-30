package com.example.excel.report.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class LawsuitExcelData {

    private int number;
    private String fullNameDebtor; //ФИО должника
    private int personalAccountNumber; //номер лицнвого счета
    private String contract; //Договор
    private String period; //Период задолженности
    private int amountOfDebt; //Сумма долга
    private int penalty; //Пени
    private int amountOfStateDuty; //Сумма ГП
    private int numberOfStateDuty; //№ ГП
    private String judicialDistrict; //Суд
    private LocalDateTime dateOfFilingAnApplicationInTheClaimProcedure; //Дата подачи заявления в исковом порядке
    private LocalDateTime dateCaseReview; //Дата расммотрения дела
    private String courtDecisionAndCaseNumber; //Решение суда, № дела
    private LocalDateTime dateOfTheDecision; //Дата вынесения решения
    private LocalDateTime effectiveDate; //Дата вступления в законную силу
    private String numberOfWritExecution; //№ исполнительного листа
    private LocalDateTime dateOfExecutionWrit; //Дата ИД
    private LocalDateTime dateOfReceiptExecution; //Дата получения ИД
    private String comment;
}
