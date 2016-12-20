package com.exmertec.dummie;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;

import com.exmertec.dummie.configuration.CycleLogic;
import org.junit.Test;

import java.util.List;
import java.util.Set;

public class LevelCacheTest {
    @Test
    public void should_create_object_with_matched_floor() throws Exception {
        NestingData3 data = new DummyBuilderFactory().cycleLogic(CycleLogic.LEVEL).create(NestingData3.class);

        assertThat(data, not(nullValue()));
        assertThat(data.getData(), not(nullValue()));
        assertThat(data.getData().getData(), notNullValue());
        assertThat(data.getData().getData().getName(), is(nullValue()));
        assertThat(data.getData().getData().getNestingData2s(), is(nullValue()));
        assertThat(data.getData1s(), not(nullValue()));
        assertThat(data.getData1s().toArray()[0], notNullValue());
    }

    @Test
    public void should_create_object_with_floor_and_cycle_itself() throws Exception {
        NestingData1 data = new DummyBuilderFactory().cycleLogic(CycleLogic.LEVEL).create(NestingData1.class);

        assertThat(data, not(nullValue()));
        assertThat(data.getName(), not(nullValue()));
        assertThat(data.getNestingData2s(), not(nullValue()));
        assertThat(data.getNestingData2s().size(), is(1));
        assertThat(data.getNestingData2s().get(0), not(nullValue()));
        assertThat(data.getNestingData2s().get(0).getDesc(), not(nullValue()));
        assertThat(data.getNestingData2s().get(0).getData(), not(nullValue()));
        assertThat(data.getNestingData2s().get(0).getData().getName(), is(nullValue()));
        assertThat(data.getNestingData2s().get(0).getData().getNestingData2s(), is(nullValue()));
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
        private Integer size;
        private Long length;
        private Character character;
        private NestingData2 data;
        private Set<NestingData1> data1s;

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public Integer getSize() {
            return size;
        }

        public void setSize(Integer size) {
            this.size = size;
        }

        public Long getLength() {
            return length;
        }

        public void setLength(Long length) {
            this.length = length;
        }

        public Character getCharacter() {
            return character;
        }

        public void setCharacter(Character character) {
            this.character = character;
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
