package dev.vinayak;

import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.connector.kafka.source.KafkaSource;
import org.apache.flink.connector.kafka.source.enumerator.initializer.OffsetsInitializer;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class Main {
    static final String BROKERS = "localhost:9092";

    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment(); //be ready for streaming

        System.out.println("Environment created");
        KafkaSource<Weather> source = KafkaSource.<Weather>builder()
                .setBootstrapServers(BROKERS)
                .setProperty("partition.discovery.interval.ms", "1000")
                .setTopics("weather")
                .setStartingOffsets(OffsetsInitializer.earliest())
                .setValueOnlyDeserializer(new WeatherDeserializationSchema())
                .build();

        DataStreamSource<Weather> kafka = env.fromSource(source, WatermarkStrategy.noWatermarks(), "kafka");

        System.out.println("Kafka source created");
        kafka.print();

        env.execute("Kafka-flink-postgres");
    }
}
