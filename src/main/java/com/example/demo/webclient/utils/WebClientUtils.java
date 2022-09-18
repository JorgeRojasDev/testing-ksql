package com.example.demo.webclient.utils;

import com.example.demo.common.utils.CommonConstants;
import com.example.demo.webclient.model.KsqlRequest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class WebClientUtils {

    public static WebClient webClient() {
        return WebClient.create("http://localhost:8088");
    }

    public static KsqlRequest ksqlRequest() {
        return KsqlRequest.builder().sql(CommonConstants.EXAMPLE_QUERY).properties(CommonConstants.EARLIEST_PROPERTIES).build();
    }
}
