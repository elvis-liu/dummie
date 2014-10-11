package com.exmertec.dummy_easy;

public interface DummyDataGenerator {
    <T> T generate(Class<T> type);
}
