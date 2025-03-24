package com.example.excel.report.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class ExecutionProcessExcelData {

    private int number;
    private String fullNameDebtor; //ФИО должника
    private String numberOfWritExecution; //№ ИД
    private LocalDateTime dateOfExecutionWrit; //Дата получения ИД
    private int amountOfDebt; //Сумма долга
    private int amountOfRemainder; //Остаток долга
    private boolean isRepeatSubmission; //Повторное предьявление ИД после Акта ДА/НЕТ
    private LocalDateTime dateOfFilingApplicationFtx; //Дата подачи заявления ФНС
    private LocalDateTime dateOfReceiptFtxResponse; //Дата получения ответа ФНС
    private LocalDateTime dateOfWritExecutionSubmissionToBank; //Дата подачи ИД в Банк
    private String bank; //
    private LocalDateTime dateOfWithdrawalFromBank; //Дата заявления на отзыв из банка
    private LocalDateTime dateOfSubmissionWritExecutionBailiffs; //Дата подачи ИД в ФССП
    private LocalDateTime dateOfReceiptOfTheDecisionToRefuseCollection; //Дата получения пост об отказе взыск
    private LocalDateTime dateOFilingAnApplicationForNonInitiationOfEnforcementProceedings; //Дата подачи заявления о не возбуждении ИП
    private LocalDateTime dateOfInitiationOfEnforcementProceedings; //Дата возбуждения ИП
    private String numberOfEnforcementProceedings; //№ ИП
    private LocalDateTime dateOfFilingAnApplicationProgressOfEnforcementProceedings; //Дата подачи заявления о ходе ИП
    private LocalDateTime dateOfFilingTheComplaint; //Дата подачи жалобы
    private String subjectOfAppeal; //Предмет обжалования
    private String resultOfTheComplaintReview; //Результат рассмотрения жалобы
    private LocalDateTime dateOfFilingAnApplicationToTheCourtCas; // Дата подачи заявления в суд КАС
    private String subjectOfAppealCourt; //Предмет обжалования в суде
    private String courtDecision; //Судебное решение
    private boolean availabilityOfPlaceOfWork; //Наличие места работы ДА/НЕТ
    private LocalDateTime dateOfSendingApplicationPlaceOfReceiptOfIncome; //Дата отправления заявления по месту получения дохода
    private boolean presenceVehicle; //Наличие ТС ДА/НЕТ
    private LocalDateTime measuresTakenVehicle; //Меры принятые по ТС
    private boolean presenceOfRealEstate; //Наличие недвижимости ДА/НЕТ
    private String measuresTakenOnRealEstate; //Меры принятые по недвижимости
    private LocalDateTime dateOfResolutionRestrictionOnExit; //Дата пост ограничения на выезд
    private LocalDateTime dateOfExitToAddress; //Дата выхода в адрес
    private String resultOfExit; //Результат выхода
    private LocalDateTime dateOfActualTerminationOfEnforcementProceedings; //Дата фактического окончания ИП
    private LocalDateTime dateOfIssuanceOfResolutionOfReturnOfExecutionWrit; //Дата вынесения пост о возвращении ИД
    private String article; //Статья
    private String part; //Часть
    private LocalDateTime dateOfTerminationOfEnforcementProceedings;//Дата прекращения ИП
    private String reasonForTerminationOfEnforcementProceedings;//Основание прекращения ИП
    private String comment;
}
