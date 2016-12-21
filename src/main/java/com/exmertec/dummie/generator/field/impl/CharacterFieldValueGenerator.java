package com.exmertec.dummie.generator.field.impl;

import com.exmertec.dummie.generator.data.DataGenerator;
import com.exmertec.dummie.generator.field.FieldValueGenerator;

import java.util.Random;

public class CharacterFieldValueGenerator extends FieldValueGenerator {

    public CharacterFieldValueGenerator() {
        super(Character.class, char.class);
    }

    @Override
    protected Character defaultGenerator(DataGenerator dataGenerator, Class<?> fieldType, String fieldName) {
        return '\u0000';
    }

    @Override
    protected Character randomGenerator(DataGenerator dataGenerator, Class<?> fieldType, String fieldName) {
        return String.valueOf(new Random().nextInt(128)).charAt(0);
    }
}
