package org.kakaobank.domain;

import java.time.LocalDateTime;

/*
 * 이체로그 : Transfer
 * 고객번호 : userid
 * 송금계좌번호 : remittanceAccountNumber
 * 수취은행 : receiptBankName
 * 수취 계좌번호 : receiptAccountNumber
 * 수취계좌주 : receiptUserName
 * 이체금액 : amount
 * 거래시각 : transcationTime
 */
public class Transfer {
    Long userid;
    String remittanceAccountNumber;
    String receiptBankName;
    String receiptAccountNumber;
    String receiptUserName;
    Double amount;
    LocalDateTime transactionTime;
}
