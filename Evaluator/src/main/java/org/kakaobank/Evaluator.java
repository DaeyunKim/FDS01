package org.kakaobank;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.kakaobank.evaluation.preparation.PreparationUtils;
import org.kakaobank.evalutation.domain.AccountOpen;
import org.kakaobank.evalutation.domain.Log;
import org.kakaobank.evalutation.domain.Signup;
import org.kakaobank.kafka.ConsumerCreator;
import org.kakaobank.kafka.IKafkaConfig;
import org.kakaobank.module.FDSdetection;
import org.kakaobank.module.UserProfileService;

import java.time.Duration;

public class Evaluator {
    private PreparationUtils preparationUtils;
    private UserProfileService userProfileService ;
    private FDSdetection fdsDetection;
    public Evaluator() {
        this.preparationUtils = new PreparationUtils();
        this.userProfileService = new UserProfileService();
        this.fdsDetection = new FDSdetection();
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
                System.out.println("record : "+ preparationUtils.getLog(record));
                Log log = preparationUtils.getLog(record);
                try{
                    fdsDetection.detectFDS(log);
                    userProfileService.getTransactionLog(log);
                }catch(Exception e){
                    System.out.println("Consumer Detection Error");
                    e.printStackTrace();
//                    consumer.close();
                }

            });
            // commits the offset of record to broker.
            consumer.commitAsync();
        }
        System.out.println("consumer end");
        consumer.close();
    }

}
