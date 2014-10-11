package com.exmertec.dummy_easy;

public class DummyFactory {
    public static <T> T create(Class<T> type) {
        return prepare(type).build();
    }

    public static <T> DummyObjectBuilder<T> prepare(Class<T> type) {
        return new DummyObjectBuilder(type);
    }
}
