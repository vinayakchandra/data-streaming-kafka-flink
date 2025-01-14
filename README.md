# Data-Streaming-kafka-flink

This project demonstrates a stream2ing pipeline for processing weather data. It integrates `Kafka`, Apache `Flink`, and
a
data deserialization mechanism to process weather information in real time.

## Components

1. Producer ([producer.py](kafka-producer/producer.py)):

    - Generates random weather data (city and temperature) using `Faker` and KafkaProducer.
    - Publishes the data to a Kafka `topic` named weather.

   Example of generated data:

    ```json
    {
     "city": "New York",
     "temperature": 10.34
    }
    ```

   Run:

    ```bash
    python producer.py
    ```

2. Weather Class ([Weather.java](flink-processor/src/main/java/dev/vinayak/Weather.java)):

    - Represents the weather data structure in the Flink application.
    - Includes methods for initialization and serialization/deserialization.
3. Deserialization
   Schema ([WeatherDeserializationSchema.java](flink-processor/src/main/java/dev/vinayak/WeatherDeserializationSchema.java)):

    - Converts JSON-formatted Kafka messages into `Weather` objects for processing in `Flink`.
    - Utilizes Jackson's `ObjectMapper` for deserialization.
4. Main Program ([Main.java](flink-processor/src/main/java/dev/vinayak/Main.java)):

    - Configures a Flink streaming environment and consumes weather data from the Kafka topic.
    - Processes and prints the data to the console.
   #### Key Features:

    - Kafka source configuration with `KafkaSource`.
    - Uses `WeatherDeserializationSchema` to deserialize Kafka messages.
    - Prints the processed data stream.

## How to Run?

### 1. Start Kafka:

- Ensure `Kafka` is running locally on `localhost:9092`.

### 2. Run the Producer:

- Start the producer script to generate weather data:

```bash
python producer.py
```

Run the Flink Job:

Execute the `Main` class in your IDE.

### 3. Observe Results:

- The Flink console should display deserialized weather data in real time.

## Requirements

#### Python:

- Faker (`pip install faker`)
- Kafka-Python (`pip install kafka-python`)

#### Java:

- Apache Flink (compatible version)
- Kafka client library
- Jackson Databind library for JSON processing

### Example Output

Producer (Kafka):

```json
{
  "city": "Los Angeles",
  "temperature": 75.23
}
```

`Flink` Console:

```arduino
dev.vinayak.Weather{city='Los Angeles', temperature=75.23}
```
