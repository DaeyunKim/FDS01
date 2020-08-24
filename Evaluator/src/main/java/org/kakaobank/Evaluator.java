package org.kakaobank;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.kakaobank.evaluation.preparation.PreparationUtils;
import org.kakaobank.evalutation.domain.*;
import org.kakaobank.kafka.ConsumerCreator;
import org.kakaobank.kafka.IKafkaConfig;

import java.time.Duration;

public class Evaluator {
    private final ObjectMapper objectMapper;
    public Evaluator() {
        this.objectMapper = new ObjectMapper();
    }

    public void evaluatorLog(){
        Consumer<String, String> consumer = ConsumerCreator.createConsumer();
        int noMessageFound = 0;
        while (true) {
            // 4000 is the time in milliseconds consumer will wait if no record is found at broker.
            ConsumerRecords<String, String> consumerRecords = consumer.poll(Duration.ofSeconds(4));


            if (consumerRecords.count() == 0) {
                noMessageFound++;
                if (noMessageFound > IKafkaConfig.MAX_NO_MESSAGE_FOUND_COUNT) {
                    // If no message found count is reached to threshold exit loop.
                    System.out.println("timeout");
                    break;
                }
                else {
                    continue;
                }
            }
            //Analyze
            consumerRecords.forEach(record -> {
                System.out.println("record : "+ PreparationUtils.getMapping(record));
            });
            // commits the offset of record to broker.
            consumer.commitAsync();
        }
        System.out.println("consumer end");
        consumer.close();
    }

}
