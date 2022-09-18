package com.example.demo.ksqlapi;

import com.example.demo.common.utils.CommonConstants;
import com.example.demo.ksqlapi.subscriber.RowSubscriber;
import com.example.demo.ksqlapi.utils.KsqlClientUtils;
import io.confluent.ksql.api.client.Client;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "starter", havingValue = "ksql")
@Slf4j
public class KsqlStarter {

    @EventListener(ApplicationReadyEvent.class)
    void start() {
        Client client = KsqlClientUtils.createClient();
        RowSubscriber rowSubscriber = new RowSubscriber();
        client.streamQuery(CommonConstants.EXAMPLE_QUERY, CommonConstants.EARLIEST_PROPERTIES)
                .thenAccept(streamedQueryResult -> streamedQueryResult.subscribe(rowSubscriber))
                .exceptionally(ex -> {
                    log.error("Error", ex);
                    return null;
                });
    }
}
