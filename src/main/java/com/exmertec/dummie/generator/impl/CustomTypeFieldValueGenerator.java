package com.exmertec.dummie.generator.impl;

import com.exmertec.dummie.DummieException;
import com.exmertec.dummie.cache.DummyCache;
import com.exmertec.dummie.generator.FieldValueGenerator;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.PropertyUtilsBean;

import java.lang.reflect.Field;

public class CustomTypeFieldValueGenerator<T> implements FieldValueGenerator {
    private final Class<T> type;

    public CustomTypeFieldValueGenerator(Class<T> type) {
        this.type = type;
    }

    @Override
    public Object generate(DummyCache cache, Field field) {
        return generate(cache, field.getType(), field.getName());
    }

    @Override
    public Object generate(DummyCache cache, Class<?> fieldType, String fieldName) {
        try {
            T instance = type.newInstance();

            cache.cacheData(fieldType, fieldName, instance);

            Field[] fields = type.getDeclaredFields();
            PropertyUtilsBean propertyUtils = BeanUtilsBean.getInstance().getPropertyUtils();

            for (Field field : fields) {
                if (!propertyUtils.isWriteable(instance, field.getName())) {
                    continue;
                }

                Object value = cache.getCachedData(field);
                BeanUtils.setProperty(instance, field.getName(), value);
            }
            return instance;
        } catch (Exception e) {
            throw new DummieException(e);
        }
    }
}
