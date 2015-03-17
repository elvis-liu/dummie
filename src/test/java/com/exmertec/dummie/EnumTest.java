package com.exmertec.dummie;

import org.junit.Test;

import static com.exmertec.dummie.Dummie.create;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

public class EnumTest {

    @Test
    public void should_create_object_with_enum_field() {
        EnumData enumData = create(EnumData.class);

        assertThat(enumData, not(nullValue()));
        assertEquals(DataType.STRING, enumData.getDataType());
    }

    @Test
    public void should_create_object_without_any_constant() {
        EmptyEnumData emptyEnumData = create(EmptyEnumData.class);

        assertThat(emptyEnumData, not(nullValue()));
        assertEquals(null, emptyEnumData.getDataType());
    }

    public static enum DataType {
        STRING, INTEGER
    }
    public static class EnumData {
        private DataType dataType;

        public DataType getDataType() {
            return dataType;
        }

        public void setDataType(DataType dataType) {
            this.dataType = dataType;
        }
    }

    public static enum EmptyDataType {
    }

    public static class EmptyEnumData {
        private EmptyDataType dataType;

        public EmptyDataType getDataType() {
            return dataType;
        }

        public void setDataType(EmptyDataType dataType) {
            this.dataType = dataType;
        }
    }
}
