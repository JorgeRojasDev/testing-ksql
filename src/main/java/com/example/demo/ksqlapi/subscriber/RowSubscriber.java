package com.example.demo.ksqlapi.subscriber;

import io.confluent.ksql.api.client.Row;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

@Slf4j
public class RowSubscriber implements Subscriber<Row> {
    private Subscription subscription;

    @Getter
    private int counter = 0;

    @Override
    public synchronized void onSubscribe(Subscription subscription) {
        log.info("Subscriber is subscribed.");
        this.subscription = subscription;

        // Request the first row
        subscription.request(1);
    }

    @Override
    public synchronized void onNext(Row row) {
        counter++;
        log.info(String.format("Received a row!: %s", counter));
        log.info("Row: " + row.values());

        //Request
        subscription.request(1);
    }

    @Override
    public synchronized void onError(Throwable throwable) {
        log.error("SubscriberError", throwable);
    }

    @Override
    public synchronized void onComplete() {
        log.info("Query has ended.");
    }
}
