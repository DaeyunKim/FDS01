package org.kakaobank.generator;

import com.sun.org.apache.xpath.internal.operations.String;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class CreateLogTest {

    CreateLog createLog;
    @Before
    public void setup(){
        this.createLog = new CreateLog();
    }

    @Test
    public void generateAccountNumberTest(){
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
    }

    @Test
    public void generateSignAndAccountOpen(){
        System.out.println(createLog.createSignup());
        System.out.println(createLog.creatAccountOpenTransaction(createLog.getUniqueUserId()));
        System.out.println(createLog.createSignup());
        System.out.println(createLog.creatAccountOpenTransaction(createLog.getUniqueUserId()));

        System.out.println(createLog.createDeposit());
        System.out.println(createLog.createWithdraw());
        System.out.println(createLog.createTransfer());

    }

}