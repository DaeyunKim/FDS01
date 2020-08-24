package org.kakaobank.transaction.domain;

import org.junit.Test;
import org.kakaobank.generator.CreateLog;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.OptionalInt;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.*;

public class SignupTest {

    @Test
    public void createBirthday(){
        long minDay = LocalDate.of(1950, 1, 1).toEpochDay();
        long maxDay = LocalDate.of(2020, 12, 31).toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        LocalDate birthDay = LocalDate.ofEpochDay(randomDay);
        System.out.println(birthDay.toString());
    }

    @Test
    public void signupJson(){
        CreateLog createLog = new CreateLog();
        System.out.println(createLog.createSignup());
        System.out.println(createLog.createSignup());

    }

    @Test
    public void RandomTest(){
        Random random = new Random();
        int i=0;
        while(i<10) {
            int first = random.ints(1, 2).findFirst().getAsInt();
            System.out.println(first);
            i++;
        }
    }

}