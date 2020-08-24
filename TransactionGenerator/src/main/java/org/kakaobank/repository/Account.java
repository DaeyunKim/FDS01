package org.kakaobank.repository;

import java.math.BigDecimal;

public class Account {
    String accountNumber;
    BigDecimal amount;

    public Account(String accountNumber, BigDecimal amount) {
        this.accountNumber = accountNumber;
        this.amount = amount;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
