import random
import time
from json import dumps

import schedule
from faker import Faker
from kafka import KafkaProducer

kafka_nodes = "localhost:9092"
myTopic = "weather"


def gen_data():
    faker = Faker()

    producer = KafkaProducer(
        bootstrap_servers=kafka_nodes,
        value_serializer=lambda x: dumps(x).encode('utf-8')
    )

    my_data = {"city": faker.city(), "temperature": random.uniform(10.0, 110.0)}
    producer.send(topic=myTopic, value=my_data)
    producer.flush()


if __name__ == '__main__':
    # schedule.every(10).seconds.do(gen_data())

    while True:
        gen_data()
        # schedule.run_pending()
        time.sleep(1)
