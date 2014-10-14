package com.exmertec.dummie;

public interface DummyCache {
    <T> void put(Class<T> dataType, T value);
    <T> T get(Class<T> dataType);
}
