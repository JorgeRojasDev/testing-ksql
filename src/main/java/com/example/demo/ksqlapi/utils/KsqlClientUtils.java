package com.example.demo.ksqlapi.utils;

import io.confluent.ksql.api.client.Client;
import io.confluent.ksql.api.client.ClientOptions;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class KsqlClientUtils {

    public static Client createClient() {
        return Client.create(clientOptions());
    }

    private static ClientOptions clientOptions() {
        return ClientOptions.create()
                .setHost("localhost")
                .setPort(8088);
    }
}
