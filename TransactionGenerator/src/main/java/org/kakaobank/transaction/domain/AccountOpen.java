package org.kakaobank.transaction.domain;

import java.sql.Timestamp;

/*
 * 계좌개설로그 : Accountopen
 * 고객번호 : userid
 * 계좌번호 : accountNumber
 * 거래시각 : transactionTime
 */
public class AccountOpen {
    Long userid;
    String accountNumber;
    Timestamp transactionTime;

    public AccountOpen(Long userid, String accountNumber, Timestamp transactionTime) {
        this.userid = userid;
        this.accountNumber = accountNumber;
        this.transactionTime = transactionTime;
    }

    public Long getUserid() {
        return userid;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public Timestamp getTransactionTime() {
        return transactionTime;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setTransactionTime(Timestamp transactionTime) {
        this.transactionTime = transactionTime;
    }


}
