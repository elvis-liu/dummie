package com.exmertec.dummie;

import com.exmertec.dummie.cache.DummyCache;
import com.exmertec.dummie.cache.impl.DefaultCache;
import com.exmertec.dummie.generator.FieldValueGenerator;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.PropertyUtilsBean;

import java.lang.reflect.Field;

public class DummyBuilder<T> {
    private final Class<T> type;
    private final DummyCache cache;

    public DummyBuilder(Class<T> type) {
        this(type, new DefaultCache());
    }

    public DummyBuilder(Class<T> type, DummyCache cache) {
        this.type = type;
        this.cache = cache;
    }

    public T build() {
        try {
            T instance = type.newInstance();
            Field[] fields = type.getDeclaredFields();
            PropertyUtilsBean propertyUtils = BeanUtilsBean.getInstance().getPropertyUtils();

            for (Field field : fields) {
                if (!propertyUtils.isWriteable(instance, field.getName())) {
                    continue;
                }

                Class<?> fieldType = field.getType();
                Object value = cache.getCachedData(fieldType);
                if (value == null) {
                    FieldValueGenerator generator = cache.getCachedGenerator(fieldType);
                    if (generator != null) {
                        value = generator.generate(cache, field);
                    }
                }
                BeanUtils.setProperty(instance, field.getName(), value);
            }
            return instance;
        } catch (Exception e) {
            throw new DummieException(e);
        }
    }

    public <E> DummyBuilder<T> override(Class<E> fieldType, E value) {
        cache.cacheData(fieldType, value);
        return this;
    }
}
