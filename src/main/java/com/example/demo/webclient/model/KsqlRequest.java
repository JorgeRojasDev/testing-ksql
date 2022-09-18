package com.example.demo.webclient.model;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Builder
@Getter
public class KsqlRequest {

    private final String sql;
    private final Map<String, Object> properties;
}
