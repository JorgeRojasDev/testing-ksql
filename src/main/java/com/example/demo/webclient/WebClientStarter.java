package com.example.demo.webclient;

import com.example.demo.webclient.utils.WebClientUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.concurrent.atomic.AtomicInteger;

@Component
@ConditionalOnProperty(name = "starter", havingValue = "webclient")
@Slf4j
public class WebClientStarter {

    @EventListener(ApplicationReadyEvent.class)
    void start() {
        AtomicInteger counter = new AtomicInteger();

        WebClient webClient = WebClientUtils.webClient();

        Flux<String> flux = webClient.post().uri("/query-stream").bodyValue(WebClientUtils.ksqlRequest()).exchangeToFlux(clientResponse ->
                clientResponse.bodyToFlux(String.class)
        ).doOnComplete(() -> {
            log.info("Total records: {}", counter.get() - 1);
        });

        flux.subscribe(response -> {
            counter.getAndIncrement();
            log.info(response);
        });
    }
}
