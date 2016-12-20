package com.exmertec.dummie;

public class Dummie {
    public static <T> T create(Class<T> type) {
        return prepare(type).build();
    }

    public static <T> DummyBuilder<T> prepare(Class<T> type) {
        return new DummyBuilderFactory().prepare(type);
    }
}
