package com.exmertec.dummie;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;

import java.util.List;
import java.util.Set;
import org.junit.Test;

public class LevelCacheTest {
    @Test
    public void should_create_object_with_matched_floor() throws Exception {
        DummyBuilderFactory factory = new DummyBuilderFactory(DummyLogic.LEVEL, 2);

        NestingData3 data = factory.create(NestingData3.class).build();

        assertThat(data, not(nullValue()));
        assertThat(data.getData(), not(nullValue()));
        assertThat(data.getData().getData(), notNullValue());
        assertThat(data.getData().getData().getName(), is(nullValue()));
        assertThat(data.getData().getData().getNestingData2s(), is(nullValue()));
        assertThat(data.getData1s(), not(nullValue()));
        assertThat(data.getData1s().toArray()[0], notNullValue());
    }

    public static class NestingData1 {
        private String name;
        private List<NestingData2> nestingData2s;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<NestingData2> getNestingData2s() {
            return nestingData2s;
        }

        public void setNestingData2s(List<NestingData2> nestingData2s) {
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
        private String desc;
        private NestingData2 data;
        private Set<NestingData1> data1s;

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public NestingData2 getData() {
            return data;
        }

        public void setData(NestingData2 data) {
            this.data = data;
        }

        public Set<NestingData1> getData1s() {
            return data1s;
        }

        public void setData1s(Set<NestingData1> data1s) {
            this.data1s = data1s;
        }
    }
}
