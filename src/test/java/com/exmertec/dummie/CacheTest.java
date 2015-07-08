package com.exmertec.dummie;

import static com.exmertec.dummie.Dummie.create;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

public class CacheTest {

    @Test
    public void should_create_object() {
        NestingData1 nestingData1 = create(NestingData1.class);

        assertThat(nestingData1, not(nullValue()));
        assertThat(nestingData1.getNestingData2s(), not(nullValue()));
        assertThat(nestingData1.getNestingData2s().getData(), not(nullValue()));
    }

    public static class NestingData1 {
        private String name;
        private NestingData2 nestingData2s;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public NestingData2 getNestingData2s() {
            return nestingData2s;
        }

        public void setNestingData2s(NestingData2 nestingData2s) {
            this.nestingData2s = nestingData2s;
        }
    }

    public static class NestingData2 {
        private String desc;
        private NestingData1 data;

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public NestingData1 getData() {
            return data;
        }

        public void setData(NestingData1 data) {
            this.data = data;
        }
    }
}
