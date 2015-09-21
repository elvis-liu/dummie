package com.exmertec.dummie.cache.impl;

import java.util.HashMap;
import java.util.Map;

public class KeyValueDataCache extends BasicDataCache {
    private final Map<Class<?>, Map<String, Object>> keyValueCachedData;

    public KeyValueDataCache() {
        keyValueCachedData = new HashMap<Class<?>, Map<String, Object>>();
    }

    @Override
    public <T> void cacheData(Class<T> dataType, final String key, final Object value) {
        Class<?> cacheDataType = normalize(dataType);
        if (!(value == null || cacheDataType.isInstance(value))) {
            throw new ClassCastException(value + " cannot be cast to " + cacheDataType);
        }
        if (!keyValueCachedData.containsKey(cacheDataType)) {
            keyValueCachedData.put(cacheDataType, new HashMap<String, Object>() {{ put(key, value); }});
        } else {
            keyValueCachedData.get(cacheDataType).put(key, value);
        }

    }

    @Override
    public <T> T getCachedData(Class<T> dataType, String key) {
        T result = getKeyValueCachedData(dataType, key);
        return result != null ? result : super.getCachedData(dataType, key);
    }

    private <T> T getKeyValueCachedData(Class<T> dataType, String key) {
        Class<?> cacheDataType = normalize(dataType);
        Map datas = keyValueCachedData.get(cacheDataType);
        if (datas != null) {
            return (T) datas.get(key);
        }
        return null;
    }
}
