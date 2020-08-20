package org.kakaobank.domain;

import java.time.LocalDateTime;

/*
 * 출금로그 : Withdraw
 * 고객번호 : userid
 * 출금계좌번호 : accountNumber
 * 출금 금액 : amount
 * 거래시각 : trasactionTime
 */
public class Withdraw {
    Long userid;
    String accountNumber;
    Double amount;
    LocalDateTime transactionTime;
}
