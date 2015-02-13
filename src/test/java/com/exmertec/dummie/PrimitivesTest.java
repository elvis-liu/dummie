package com.exmertec.dummie;

import org.junit.Test;

import static com.exmertec.dummie.Dummie.create;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class PrimitivesTest {
    @Test
    public void should_create_object_with_primitive_fields() throws Exception {
        PrimitiveData data = create(PrimitiveData.class);

        assertThat(data, not(nullValue()));
        assertThat(data.isBooleanValue(), is(false));
    }
//
//    @Test
//    public void should_allow_customize_primitive_type_fields() throws Exception {
//        PrimitiveData data = prepare(PrimitiveData.class).override(boolean.class, true).build();
//
//        assertThat(data.isBooleanValue(), is(true));
//    }

    @Test
    public void should_create_object_with_primitive_wrapper_fields() throws Exception {
        PrimitiveWrapperData data = create(PrimitiveWrapperData.class);

        assertThat(data.getBooleanValue(), is(false));
    }
//
//    @Test
//    public void should_allow_customize_primitive_wrapper_type_fields() throws Exception {
//        PrimitiveWrapperData data = prepare(PrimitiveWrapperData.class).override(Boolean.class, true).build();
//
//        assertThat(data.getBooleanValue(), is(true));
//    }

    public static class PrimitiveData {
        private byte byteValue;
        private short shortValue;
        private int intValue;
        private long longValue;
        private float floatValue;
        private double doubleValue;
        private boolean booleanValue;
        private char charValue;

        public byte getByteValue() {
            return byteValue;
        }

        public void setByteValue(byte byteValue) {
            this.byteValue = byteValue;
        }

        public short getShortValue() {
            return shortValue;
        }

        public void setShortValue(short shortValue) {
            this.shortValue = shortValue;
        }

        public int getIntValue() {
            return intValue;
        }

        public void setIntValue(int intValue) {
            this.intValue = intValue;
        }

        public long getLongValue() {
            return longValue;
        }

        public void setLongValue(long longValue) {
            this.longValue = longValue;
        }

        public float getFloatValue() {
            return floatValue;
        }

        public void setFloatValue(float floatValue) {
            this.floatValue = floatValue;
        }

        public double getDoubleValue() {
            return doubleValue;
        }

        public void setDoubleValue(double doubleValue) {
            this.doubleValue = doubleValue;
        }

        public boolean isBooleanValue() {
            return booleanValue;
        }

        public void setBooleanValue(boolean booleanValue) {
            this.booleanValue = booleanValue;
        }

        public char getCharValue() {
            return charValue;
        }

        public void setCharValue(char charValue) {
            this.charValue = charValue;
        }
    }

    public static class PrimitiveWrapperData {
        private Byte byteValue;
        private Short shortValue;
        private Integer intValue;
        private Long longValue;
        private Float floatValue;
        private Double doubleValue;
        private Boolean booleanValue;
        private Character charValue;

        public Byte getByteValue() {
            return byteValue;
        }

        public void setByteValue(Byte byteValue) {
            this.byteValue = byteValue;
        }

        public Short getShortValue() {
            return shortValue;
        }

        public void setShortValue(Short shortValue) {
            this.shortValue = shortValue;
        }

        public Integer getIntValue() {
            return intValue;
        }

        public void setIntValue(Integer intValue) {
            this.intValue = intValue;
        }

        public Long getLongValue() {
            return longValue;
        }

        public void setLongValue(Long longValue) {
            this.longValue = longValue;
        }

        public Float getFloatValue() {
            return floatValue;
        }

        public void setFloatValue(Float floatValue) {
            this.floatValue = floatValue;
        }

        public Double getDoubleValue() {
            return doubleValue;
        }

        public void setDoubleValue(Double doubleValue) {
            this.doubleValue = doubleValue;
        }

        public Boolean getBooleanValue() {
            return booleanValue;
        }

        public void setBooleanValue(Boolean booleanValue) {
            this.booleanValue = booleanValue;
        }

        public Character getCharValue() {
            return charValue;
        }

        public void setCharValue(Character charValue) {
            this.charValue = charValue;
        }
    }
}