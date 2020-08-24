package org.kakaobank.evalutation.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;

/*
 * 입금 로그 : Deposit
 * 고객번호 : userid
 * 입금 계좌번호 : accountNumber
 * 입금 금액 : amount
 * 거래시각 : trasactionTime
 */
public class Deposit implements Log {
    long userid;
    String accountNumber;
    BigDecimal amount;
    Timestamp transactionTime;

    public Deposit() {
    }

    public Deposit(Long userid, String accountNumber, BigDecimal amount, Timestamp transactionTime) {
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

    @Override
    public String toString() {
        return "Deposit{" +
                "userid=" + userid +
                ", accountNumber='" + accountNumber + '\'' +
                ", amount=" + amount +
                ", transactionTime=" + transactionTime +
                '}';
    }
}
