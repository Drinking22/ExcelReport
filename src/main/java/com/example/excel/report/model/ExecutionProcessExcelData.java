package com.example.excel.report.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ExecutionProcessExcelData {

    private final int number;
    private final String fullNameDebtor; //ФИО должника
    private final int personalAccountNumber; //номер лицевого счета
    private final String numberOfWritExecution; //№ ИД
    private final LocalDateTime dateOfExecutionWrit; //Дата получения ИД
    private final int amountOfDebt; //Сумма долга
    private final int amountOfRemainder; //Остаток долга
    private final boolean isRepeatSubmission; //Повторное предъявление ИД после Акта ДА/НЕТ
    private final LocalDateTime dateOfFilingApplicationFtx; //Дата подачи заявления ФНС
    private final LocalDateTime dateOfReceiptFtxResponse; //Дата получения ответа ФНС
    private final LocalDateTime dateOfWritExecutionSubmissionToBank; //Дата подачи ИД в Банк
    private final String bank; //
    private final LocalDateTime dateOfWithdrawalFromBank; //Дата заявления на отзыв из банка
    private final LocalDateTime dateOfSubmissionWritExecutionBailiffs; //Дата подачи ИД в ФССП
    private final LocalDateTime dateOfReceiptOfTheDecisionToRefuseCollection; //Дата получения пост об отказе взыск
    private final LocalDateTime dateOFilingAnApplicationForNonInitiationOfEnforcementProceedings; //Дата подачи заявления о не возбуждении ИП
    private final LocalDateTime dateOfInitiationOfEnforcementProceedings; //Дата возбуждения ИП
    private final String numberOfEnforcementProceedings; //№ ИП
    private final LocalDateTime dateOfFilingAnApplicationProgressOfEnforcementProceedings; //Дата подачи заявления о ходе ИП
    private final LocalDateTime dateOfFilingTheComplaint; //Дата подачи жалобы
    private final String subjectOfAppeal; //Предмет обжалования
    private final String resultOfTheComplaintReview; //Результат рассмотрения жалобы
    private final LocalDateTime dateOfFilingAnApplicationToTheCourtCas; // Дата подачи заявления в суд КАС
    private final String subjectOfAppealCourt; //Предмет обжалования в суде
    private final String courtDecision; //Судебное решение
    private final boolean availabilityOfPlaceOfWork; //Наличие места работы ДА/НЕТ
    private final LocalDateTime dateOfSendingApplicationPlaceOfReceiptOfIncome; //Дата отправления заявления по месту получения дохода
    private final boolean presenceVehicle; //Наличие ТС ДА/НЕТ
    private final LocalDateTime measuresTakenVehicle; //Меры принятые по ТС
    private final boolean presenceOfRealEstate; //Наличие недвижимости ДА/НЕТ
    private final String measuresTakenOnRealEstate; //Меры принятые по недвижимости
    private final LocalDateTime dateOfResolutionRestrictionOnExit; //Дата пост ограничения на выезд
    private final LocalDateTime dateOfExitToAddress; //Дата выхода в адрес
    private final String resultOfExit; //Результат выхода
    private final LocalDateTime dateOfActualTerminationOfEnforcementProceedings; //Дата фактического окончания ИП
    private final LocalDateTime dateOfIssuanceOfResolutionOfReturnOfExecutionWrit; //Дата вынесения пост о возвращении ИД
    private final String article; //Статья
    private final String part; //Часть
    private final LocalDateTime dateOfTerminationOfEnforcementProceedings;//Дата прекращения ИП
    private final String reasonForTerminationOfEnforcementProceedings;//Основание прекращения ИП
    private final String comment;
}
