package com.exmertec.dummie.cache;

import com.exmertec.dummie.generator.FieldValueGenerator;

import java.lang.reflect.Field;

public abstract class DummyCache implements DataCache, GeneratorCache {

    public Object getCachedData(Field field) {
        Class<?> fieldType = field.getType();
        Object value =  getCachedData(fieldType);
        if (value == null) {
            FieldValueGenerator generator = getCachedGenerator(fieldType);
            if (generator != null) {
                value = generator.generate(this, field);
            }
        }
        return value;
    }


}
