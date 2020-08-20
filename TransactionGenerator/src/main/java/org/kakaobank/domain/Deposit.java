package org.kakaobank.domain;

import java.time.LocalDateTime;

/*
 * 입금 로그 : Deposit
 * 고객번호 : userid
 * 입금 계좌번호 : accountNumber
 * 입금 금액 : amount
 * 거래시각 : trasactionTime
 */
public class Deposit {
    Long userid;
    String accountNumber;
    Double amount;
    LocalDateTime transactionTime;
}
