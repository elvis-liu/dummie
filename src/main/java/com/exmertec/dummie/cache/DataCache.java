package com.exmertec.dummie.cache;

public interface DataCache {
    <T> void cacheData(Class<T> dataType, T value);
    <T> T getCachedData(Class<T> dataType);
}
