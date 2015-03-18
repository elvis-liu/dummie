package com.exmertec.dummie;

import org.junit.Test;

import java.util.Map;

import static com.exmertec.dummie.Dummie.create;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class MapTest {

    @Test
    public void should_create_object_with_map_field() {
        MapData data = create(MapData.class);

        assertThat(data, not(nullValue()));
        assertThat(data.getNotParameterized().size(), is(0));
        assertThat(data.getParameterized().size(), is(1));
    }

    public static class MapData {
        private Map notParameterized;

        private Map<String, Integer> parameterized;

        public Map getNotParameterized() {
            return notParameterized;
        }

        public void setNotParameterized(Map notParameterized) {
            this.notParameterized = notParameterized;
        }

        public Map<String, Integer> getParameterized() {
            return parameterized;
        }

        public void setParameterized(Map<String, Integer> parameterized) {
            this.parameterized = parameterized;
        }
    }
}
