package org.kakaobank.domain;

import java.time.LocalDateTime;

/*
 * 계좌개설로그 : Accountopen
 * 고객번호 : userid
 * 계좌번호 : accountNumber
 * 거래시각 : transactionTime
 */
public class AccountOpen {
    Long userid;
    String accountNumber;
    LocalDateTime transactionTime;
}
