package org.kakaobank.kafka;


public interface IKafkaConfig {
    String KAFKA_BROKERS = "localhost:9092";
    String CLIENT_ID = "client1";
    String TOPIC_NAME="fds.transactions";
    String GROUP_ID_CONFIG="consumerGroup1";
    Integer MAX_NO_MESSAGE_FOUND_COUNT=1000;
    String OFFSET_RESET_LATEST="latest";
    String OFFSET_RESET_EALIER="earliest";
    Integer MAX_POLL_RECORDS=1;

}
