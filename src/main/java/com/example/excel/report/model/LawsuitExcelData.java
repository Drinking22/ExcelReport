package com.example.excel.report.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class LawsuitExcelData {

    private final int number;
    private final String fullNameDebtor; //ФИО должника
    private final int personalAccountNumber; //номер лицевого счета
    private final String contract; //Договор
    private final String period; //Период задолженности
    private final BigDecimal amountOfDebt; //Сумма долга
    private final LocalDateTime debtRepaymentDate; //дата погашения задолженности
    private final BigDecimal penalty; //Пени
    private final BigDecimal amountOfStateDuty; //Сумма ГП
    private final String numberOfStateDuty; //№ ГП
    private final String judicialDistrict; //Суд
    private final LocalDateTime dateOfFilingAnApplicationInTheClaimProcedure; //Дата подачи заявления в исковом порядке
    private final LocalDateTime dateCaseReview; //Дата рассмотрения дела
    private final String courtDecisionAndCaseNumber; //Решение суда, № дела
    private final LocalDateTime dateOfTheDecision; //Дата вынесения решения
    private final LocalDateTime effectiveDate; //Дата вступления в законную силу
    private final String numberOfWritExecution; //№ исполнительного листа
    private final LocalDateTime dateOfExecutionWrit; //Дата ИД
    private final LocalDateTime dateOfReceiptExecution; //Дата получения ИД
    private final String comment;
}
