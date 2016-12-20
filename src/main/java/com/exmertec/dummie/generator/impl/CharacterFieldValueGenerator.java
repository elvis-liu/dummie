package com.exmertec.dummie.generator.impl;

import com.exmertec.dummie.cache.DummyCache;
import com.exmertec.dummie.configuration.GenerationStrategy;
import com.exmertec.dummie.generator.FieldValueGenerator;

import java.util.Random;

public class CharacterFieldValueGenerator extends FieldValueGenerator {

    public CharacterFieldValueGenerator(GenerationStrategy strategy) {
        super(strategy, Character.class, char.class);
    }

    @Override
    protected Character defaultGenerator(DummyCache cache, Class<?> fieldType, String fieldName) {
        return '\u0000';
    }

    @Override
    protected Character randomGenerator(DummyCache cache, Class<?> fieldType, String fieldName) {
        return String.valueOf(new Random().nextInt(128)).charAt(0);
    }
}
