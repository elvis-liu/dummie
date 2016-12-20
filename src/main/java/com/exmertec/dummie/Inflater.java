package com.exmertec.dummie;

import com.exmertec.dummie.cache.DummyCache;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.PropertyUtilsBean;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class Inflater {
    private static void inflateFields(Object instance, DummyCache cache, Class<?> classType) throws
        IllegalAccessException,
        InvocationTargetException {
        Field[] fields = classType.getDeclaredFields();
        PropertyUtilsBean propertyUtils = BeanUtilsBean.getInstance().getPropertyUtils();

        for (Field field : fields) {
            if (!propertyUtils.isWriteable(instance, field.getName())) {
                continue;
            }

            Object value = cache.getCachedData(field);
            BeanUtils.setProperty(instance, field.getName(), value);
        }
    }

    public static void inflateInstance(Object instance, DummyCache cache, Class<?> type) throws
        InvocationTargetException,
        IllegalAccessException {
        if (type != null) {
            inflateFields(instance, cache, type);
            inflateInstance(instance, cache, type.getSuperclass());
        }
    }
}
