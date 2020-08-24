package org.kakaobank.transaction.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;

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
    BigDecimal amount;
    Timestamp transactionTime;

    public Withdraw(long userid, String account, BigDecimal amount, Timestamp time) {
        this.userid = userid;
        this.accountNumber = account;
        this.amount = amount;
        this.transactionTime = time;
    }

    public Long getUserid() {
        return userid;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public BigDecimal getAmount() {
        return amount;
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

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setTransactionTime(Timestamp transactionTime) {
        this.transactionTime = transactionTime;
    }
}
