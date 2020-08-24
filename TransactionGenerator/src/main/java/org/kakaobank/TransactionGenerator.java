package org.kakaobank;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.kakaobank.evalutation.domain.LogType;
import org.kakaobank.generator.CreateLog;
import org.kakaobank.kafka.IKafkaConfig;
import org.kakaobank.kafka.TransactionProducer;

public class TransactionGenerator {

    private final CreateLog createLog;
    private final Producer<String, String> producer;

    public TransactionGenerator() {
        this.createLog = new CreateLog();
        this.producer = TransactionProducer.createProducer();
    }

    private void createTransaction(String logType , String result) {
//        Producer<Long, String> producer = TransactionProducer.createProducer();
//        for (int index = 0; index < IKafkaConfig.MESSAGE_COUNT; index++) {
            //Create Log Type
            ProducerRecord<String, String> record = new ProducerRecord<>(IKafkaConfig.TOPIC_NAME, logType,
                    result);
//            System.out.println( "sendMessage : "+result);
            try{
                producer.send(record);
            }catch( Exception e){
                System.out.println("Producer 에러 발생 ");
                e.printStackTrace();
                throw new RuntimeException("Producer Error 발생 ");
            }

    }

    public void createLog() {
//        String result;
        int i=0;
        createTransaction(LogType.SIGNUP.name(), createLog.createSignup());
        createTransaction(LogType.ACCOUNTOPEN.name(), createLog.creatAccountOpenTransaction( createLog.getUniqueUserId()));
        while (true) {
//            System.out.println("send index "+i);
            int logType = (int) ((Math.random() * 10) % 4);
            switch (logType) {
                case 0:
                    String signupLog = createLog.createSignup();
                    String accountLog = createLog.creatAccountOpenTransaction( createLog.getUniqueUserId());
                    if( !signupLog.equals("null")&&
                        !accountLog.equals("null")){
                        createTransaction(LogType.SIGNUP.name(), signupLog);
                        createTransaction(LogType.ACCOUNTOPEN.name(), accountLog);
                    }
                    break;
                case 1:
                    String depositLog = createLog.createDeposit();
                    if(!depositLog.equals("null")){
                        createTransaction(LogType.DEPOSIT.name(), depositLog);
                    }
                    break;
                case 2:
                    String withdrawLog = createLog.createWithdraw();
                    if(!withdrawLog.equals("null")){
                        createTransaction(LogType.WITHDRAW.name(), withdrawLog);
                    }
                    break;
                case 3:
                    String transferLog = createLog.createTransfer();
                    if(!transferLog.equals("null")){
                        createTransaction(LogType.TRANSFER.name(), transferLog);
                    }
                    break;
                default:
                    System.out.println(logType);
                    break;
            }
            i++;
//            System.out.println("End of Producer");
        }

    }
}
