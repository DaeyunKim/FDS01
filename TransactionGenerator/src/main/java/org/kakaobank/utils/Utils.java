package org.kakaobank.utils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public final class Utils {

    //금액 출금체크 : 계좌 보유금액내 금액
    public static BigDecimal makeWithdrawMoney(BigDecimal accountMoney, long minAmount, long maxAmount ){
        Random random = new Random();
        boolean isAvaliableMoney=true;
        BigDecimal withdrawAmount = null;
        while(isAvaliableMoney){
            long randomMoney = random.longs(minAmount, maxAmount).findFirst().getAsLong();
            withdrawAmount = new BigDecimal(randomMoney);
            int i = accountMoney.compareTo(withdrawAmount);
            isAvaliableMoney = i < 0;
        }
        return withdrawAmount;
    }

    public static BigDecimal makeDepositMoney(Long minAmount, Long maxAmount){
        Random random = new Random();
        long randomMoney = random.longs(minAmount, maxAmount).findFirst().getAsLong();
        return new BigDecimal(randomMoney);
    }

    //생년월일
    public static LocalDate createBirthDay(){
        long minDay = LocalDate.of(1950, 1, 1).toEpochDay();
        long maxDay = LocalDate.of(2020, 12, 31).toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        LocalDate birthDay = LocalDate.ofEpochDay(randomDay);
        return birthDay;
    }

    //계좌생성
    public static String creatAccountNumber(){
        int first = (int)(Math.random()*1000);
        int second = (int)(Math.random()*1000);
        int thrid = (int)(Math.random()*100000);
        StringBuffer accountNumber = new StringBuffer();
        accountNumber.append(first);
        accountNumber.append("-");
        accountNumber.append(second);
        accountNumber.append("-");
        accountNumber.append(thrid);
//        System.out.println( accountNumber.toString());
        return accountNumber.toString();
    }
}
