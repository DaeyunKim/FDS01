package org.kakaobank.kafka;

public interface IKafkaConfig {
    String KAFKA_BROKERS = "localhost:9092";
    String CLIENT_ID = "client1";
    String TOPIC_NAME="fds.transactions";
}
