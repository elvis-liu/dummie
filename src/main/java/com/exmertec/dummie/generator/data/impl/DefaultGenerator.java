package com.exmertec.dummie.generator.data.impl;

import com.exmertec.dummie.generator.data.DataGenerator;
import com.exmertec.dummie.configuration.GenerationStrategy;
import com.exmertec.dummie.generator.field.FieldValueGenerator;
import com.exmertec.dummie.generator.field.impl.CustomTypeFieldValueGenerator;

import java.lang.reflect.Field;

public class DefaultGenerator extends DataGenerator {

    public DefaultGenerator(GenerationStrategy generationStrategy) {
        super(generationStrategy);
    }

    @Override
    public Object getData(Field field) {
        Object value = super.getData(field);
        dynamicCacheData(field.getType(), field.getName(), value);

        return value;
    }

    @Override
    public Object getData(Class<?> dataType, String key) {
        Object value = super.getData(dataType, key);

        try {
            dynamicCacheData(dataType, key, Class.forName(dataType.getName()).cast(value));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return value;
    }

    @Override
    protected FieldValueGenerator getDefaultFieldValueGenerator(Class<?> dataType) {
        return new CustomTypeFieldValueGenerator(dataType);
    }
}
