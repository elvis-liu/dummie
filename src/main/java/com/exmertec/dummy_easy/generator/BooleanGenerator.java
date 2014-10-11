package com.exmertec.dummy_easy.generator;

import com.exmertec.dummy_easy.DummyDataGenerator;

public class BooleanGenerator implements DummyDataGenerator {
    private final Boolean defaultValue;

    public BooleanGenerator(Boolean defaultValue) {
        this.defaultValue = defaultValue;
    }

    @Override
    public <T> T generate(Class<T> type) {
        return (T) defaultValue;
    }
}
