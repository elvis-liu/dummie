package com.exmertec.dummie;

import com.exmertec.dummie.generator.FieldValueGenerator;

public interface GeneratorFactory {
    FieldValueGenerator getGenerator(Class<?> dataType);
}
