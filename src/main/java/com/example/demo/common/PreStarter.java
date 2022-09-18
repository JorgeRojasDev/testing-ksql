package com.example.demo.common;

import com.example.demo.common.utils.CommonConstants;
import com.example.demo.ksqlapi.utils.KsqlClientUtils;
import io.confluent.ksql.api.client.Client;
import io.confluent.ksql.api.client.KsqlObject;
import io.confluent.ksql.api.client.Row;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.stream.IntStream;

@Component
@ConditionalOnProperty(name = "starter", havingValue = "prestarter")
@Slf4j
public class PreStarter {

    @EventListener(ApplicationReadyEvent.class)
    void start() {
        final int maxFechaRevRem = 5;
        final int maxElementsByFechaRevRem = 50;

        try (Client client = KsqlClientUtils.createClient()) {
            IntStream.range(0, maxFechaRevRem).forEach(index -> {
                IntStream.range(0, maxElementsByFechaRevRem).forEach(subindex -> {
                    final int elementIncremental = maxFechaRevRem * maxElementsByFechaRevRem + subindex;

                    KsqlObject ksqlObject = new KsqlObject();
                    ksqlObject.put("ROWKEY", String.format("example-%s", elementIncremental));
                    ksqlObject.put("FECHA_REVREM", String.valueOf(index * 1000));
                    ksqlObject.put("DATOS_ADICIONALES", "test data");

                    client.insertInto(CommonConstants.TEST_STREAM, ksqlObject);
                });
            });
        }
    }
}
