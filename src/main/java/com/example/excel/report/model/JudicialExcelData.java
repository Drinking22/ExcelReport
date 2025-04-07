package com.example.excel.report.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class JudicialExcelData {

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
    private final LocalDateTime dateOfSendingCopiesOfDocuments; //Дата отправления копий документов
    private final LocalDateTime dateOfFilingAnApplicationToTheCourt; //Дата подачи заявления в суд
    private final String judicialDistrict; //Судебный участок
    private final LocalDateTime dateOfDetermination; //Дата определения
    private final String determinationOnTheReturnOfTheApplication; //Определение о возращении заявления
    private final String determinationToCancelTheJointVenture; //Определение об отмене СП
    private final LocalDateTime dateOfFilingAnApplicationInTheClaimProcedure; //Дата подачи заявления в исковом порядке
    private final String numberOfCourtOrder; //№ судебного приказа
    private final LocalDateTime dateOfCourtOrder; //Дата СП
    private final LocalDateTime dateOfReceiptOfCourtOrder; //Дата получения СП
    private final String comment;
}
