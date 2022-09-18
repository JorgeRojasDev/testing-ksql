package com.example.demo.common.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommonConstants {

    public static final String TEST_TABLE = "INST_TABLE";
    public static final String TEST_STREAM = "INST_TABLE";

    public static final String EXAMPLE_QUERY = String.format("SELECT * FROM %s;", TEST_TABLE);
    public static final Map<String, Object> EARLIEST_PROPERTIES = Map.of("auto.offset.reset", "earliest");
}
