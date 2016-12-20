package com.exmertec.dummie;

import static com.exmertec.dummie.Dummie.prepare;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

public class RandomTest {

    @Test
    public void should_prepare_class_and_custom_random() throws Exception {
        NestingData1 nestingData1 = prepare(NestingData1.class).random(String.class).build();

        assertThat(nestingData1, not(nullValue()));
        assertThat(nestingData1.getName(), not(nullValue()));
        assertThat(nestingData1.getName(), not(equalTo("name")));
        assertThat(nestingData1.getNestingData2s(), not(nullValue()));
        assertThat(nestingData1.getNestingData2s().getData(), not(nullValue()));
    }

    @Test
    public void should_prepare_class_and_set_random_strategy_by_filed_name() throws Exception {
        NestingData1 nestingData1 = prepare(NestingData1.class)
            .random("name")
            .build();

        assertThat(nestingData1, not(nullValue()));
        assertThat(nestingData1.getName(), not(nullValue()));
        assertThat(nestingData1.getName(), not(equalTo("name")));
        assertThat(nestingData1.getNestingData2s(), not(nullValue()));
        assertThat(nestingData1.getNestingData2s().getData(), not(nullValue()));
    }

    @Test
    public void should_prepare_class_and_not_set_random_with_wrong_field_name() throws Exception {
        NestingData1 nestingData1 = prepare(NestingData1.class)
            .override(String.class, "abc")
            .random("name1")
            .build();

        assertThat(nestingData1, not(nullValue()));
        assertThat(nestingData1.getName(), equalTo("abc"));
        assertThat(nestingData1.getNestingData2s(), not(nullValue()));
        assertThat(nestingData1.getNestingData2s().getData(), not(nullValue()));
    }

    @Test
    public void should_return_different_value_when_set_random() throws Exception {
        NestingData3 nestingData3 = prepare(NestingData3.class)
            .random(String.class)
            .build();

        assertThat(nestingData3, not(nullValue()));
        assertThat(nestingData3.getName(), not(nullValue()));
        assertThat(nestingData3.getDesc(), not(nullValue()));
        assertThat(nestingData3.getDesc(), not(equalTo(nestingData3.getName())));
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

    public static class NestingData3 {
        private String name;
        private String desc;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }
}
