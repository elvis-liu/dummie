package com.exmertec.dummy_easy;

import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.Field;

public class DummyObjectBuilder<T> {
    private final Class<T> type;
    private final DummyDataGeneratorFactory generatorFactory;

    DummyObjectBuilder(Class<T> type) {
        this.type = type;
        this.generatorFactory = new DummyDataGeneratorFactory();
    }

    public T build() {
        try {
            T instance = type.newInstance();
            Field[] fields = type.getDeclaredFields();
            for (Field field : fields) {
                DummyDataGenerator generator = generatorFactory.getGenerator(field.getType());
                if (generator != null) {
                    Object value = generator.generate(field.getType());
                    BeanUtils.setProperty(instance, field.getName(), value);
                }
            }
            return instance;
        } catch (Exception e) {
            throw new DummyCreateException(e);
        }
    }
}
