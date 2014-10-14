package com.exmertec.dummie;

import com.exmertec.dummie.generator.FieldValueGenerator;
import com.exmertec.dummie.impl.BasicDummyCache;
import com.exmertec.dummie.impl.BasicGeneratorFactory;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.PropertyUtilsBean;

import java.lang.reflect.Field;

public class DummyBuilder<T> {
    private final Class<T> type;
    private final DummyCache cache;
    private final GeneratorFactory generatorFactory;

    DummyBuilder(Class<T> type) {
        this.type = type;
        this.generatorFactory = new BasicGeneratorFactory();
        this.cache = new BasicDummyCache();
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
                Object value = cache.get(fieldType);
                if (value == null) {
                    FieldValueGenerator generator = generatorFactory.getGenerator(fieldType);
                    if (generator != null) {
                        value = generator.generate(this, field);
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
        cache.put(fieldType, value);
        return this;
    }
}
