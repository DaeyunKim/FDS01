package org.kakaobank.generator;

import com.sun.org.apache.xpath.internal.operations.String;
import org.junit.Before;
import org.junit.Test;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.TemporalUnit;
import java.util.Date;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

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

    @Test
    public void diffTimestamp(){

        Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());
        Timestamp registerTime = Timestamp.valueOf(LocalDateTime.of(2020,8,25,05,50));
        System.out.println(timestamp.toLocalDateTime()+","+ registerTime.toLocalDateTime());
        Duration duration = Duration.ofHours(2);
        Duration diff = Duration.between( registerTime.toLocalDateTime(),timestamp.toLocalDateTime());
        System.out.println("소요시간 : "+diff.toHours());
        System.out.println("소요시간 : "+diff.toMinutes());
    }

    @Test
    public void 함수테스트(){
        Map<String,String> map = new ConcurrentHashMap<>();
        if(map.containsKey("1")){
             Optional.of(map.get("1"));
        }else{
             Optional.empty();
        }
        System.out.println(Optional.empty().isPresent());
        Optional<Map.Entry<String, String>> first = map.entrySet().stream().filter(element -> element.getKey().equals("1")).findFirst();
        System.out.println(first);
    }
}