package com.exmertec.dummie.cache;

public interface DataCache {
    <T> void cacheData(Class<T> clazz, Object value);
    <T> void cacheData(Class<T> dataType, String key, Object value);
    <T> T getCachedData(Class<T> dataType, String key);
}
