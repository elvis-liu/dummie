package com.exmertec.dummie.cache;

import com.exmertec.dummie.generator.FieldValueGenerator;

public interface GeneratorCache {
    FieldValueGenerator getCachedGenerator(Class<?> dataType);
    void cacheGenerator(FieldValueGenerator generator);
}
