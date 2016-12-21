package com.exmertec.dummie.generator;

import com.exmertec.dummie.generator.data.DataGenerator;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.PropertyUtilsBean;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class Inflater {
    private static void inflateFields(Object instance, DataGenerator dataGenerator, Class<?> classType) throws
        IllegalAccessException,
        InvocationTargetException {
        Field[] fields = classType.getDeclaredFields();
        PropertyUtilsBean propertyUtils = BeanUtilsBean.getInstance().getPropertyUtils();

        for (Field field : fields) {
            if (!propertyUtils.isWriteable(instance, field.getName())) {
                continue;
            }

            Object value = dataGenerator.getData(field);
            BeanUtils.setProperty(instance, field.getName(), value);
        }
    }

    public static void inflateInstance(Object instance, DataGenerator cache, Class<?> type) throws
        InvocationTargetException,
        IllegalAccessException {
        if (type != null) {
            inflateFields(instance, cache, type);
            inflateInstance(instance, cache, type.getSuperclass());
        }
    }
}
