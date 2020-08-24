package org.kakaobank.evalutation.domain;

import com.fasterxml.jackson.annotation.JsonRootName;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/*
 * 입금 로그 : Deposit
 * 고객번호 : userid
 * 입금 계좌번호 : accountNumber
 * 입금 금액 : amount
 * 거래시각 : trasactionTime
 */
@JsonRootName("deposit")
public class Deposit {
    long userid;
    String accountNumber;
    BigDecimal amount;
    LocalDateTime transactionTime;

    public Deposit(Long userid, String accountNumber, BigDecimal amount, LocalDateTime transactionTime) {
        this.userid = userid;
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.transactionTime = transactionTime;
    }

    public long getUserid() {
        return userid;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDateTime getTransactionTime() {
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

    public void setTransactionTime(LocalDateTime transactionTime) {
        this.transactionTime = transactionTime;
    }
}
