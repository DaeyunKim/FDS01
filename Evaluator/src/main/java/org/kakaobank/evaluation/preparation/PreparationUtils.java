package org.kakaobank.evaluation.preparation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.kakaobank.evalutation.domain.*;

public class PreparationUtils {
    private ObjectMapper objectMapper;

    public PreparationUtils(){
        this.objectMapper = new ObjectMapper();
    }

    public Log getLog(ConsumerRecord<String, String> record){
        String key = record.key();
        Log log = null;
        LogType logType = LogType.valueOf(key);
        switch(logType){
            case SIGNUP:
                try {
                    log = objectMapper.readValue(record.value(), Signup.class);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
                break;
            case ACCOUNTOPEN:
                try {
                    log = objectMapper.readValue(record.value(), AccountOpen.class);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
                break;
            case DEPOSIT:
                try {
                    log = objectMapper.readValue(record.value(), Deposit.class);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
                break;
            case WITHDRAW:
                try {
                    log = objectMapper.readValue(record.value(), Withdraw.class);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
                break;
            case TRANSFER:
                try {
                    log = objectMapper.readValue(record.value(), Transfer.class);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
                break;
            default :
                System.out.println("null");
                break;
        }
        return log;

    }


}
