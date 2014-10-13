package com.exmertec.dummy_easy;

import com.exmertec.dummy_easy.generator.FieldValueGenerator;
import com.exmertec.dummy_easy.generator.impl.StringFieldValueGenerator;
import com.google.common.collect.Maps;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.BeanUtilsBean2;
import org.apache.commons.beanutils.PropertyUtilsBean;

import java.lang.reflect.Field;
import java.util.Map;

public class DummyObjectBuilder<T> {
    private final Class<T> type;
    private final Map<Class<?>, Object> generatedValues;
    private final Map<Class<?>, FieldValueGenerator> generators;

    DummyObjectBuilder(Class<T> type) {
        this.type = type;
        this.generatedValues = defaultGeneratedValues();
        this.generators = defaultGenerators();
    }

    private static Map<Class<?>, FieldValueGenerator> defaultGenerators() {
        Map<Class<?>, FieldValueGenerator> generatorsMap = Maps.newHashMap();
        generatorsMap.put(String.class, new StringFieldValueGenerator());
        return generatorsMap;
    }

    private static Map<Class<?>, Object> defaultGeneratedValues() {
        Map<Class<?>, Object> valuesMap = Maps.newHashMap();
        appendPrimitiveWrappers(valuesMap);
        return valuesMap;
    }

    private static void appendPrimitiveWrappers(Map<Class<?>, Object> valuesMap) {
        valuesMap.put(Boolean.class, false);
        valuesMap.put(Byte.class, (byte) 0);
        valuesMap.put(Character.class, '\u0000');
        valuesMap.put(Double.class, (double) 0);
        valuesMap.put(Float.class, (float) 0);
        valuesMap.put(Integer.class, 0);
        valuesMap.put(Long.class, 0l);
        valuesMap.put(Short.class, (short) 0);
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

                Object value = generatedValues.get(field.getType());
                if (value == null) {
                    FieldValueGenerator generator = generators.get(field.getType());
                    if (generator != null) {
                        value = generator.generate(field);
                    }
                }

                BeanUtils.setProperty(instance, field.getName(), value);
            }
            return instance;
        } catch (Exception e) {
            throw new DummyCreateException(e);
        }
    }

    public <E> DummyObjectBuilder<T> override(Class<E> fieldType, E value) {
        generatedValues.put(fieldType, value);
        return this;
    }
}
