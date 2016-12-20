package com.exmertec.dummie;

import static com.exmertec.dummie.Dummie.create;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import com.exmertec.dummie.configuration.GenerationStrategy;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class MapTest {

    @Test
    public void should_create_object_with_map_field() {
        MapData data = create(MapData.class);

        validateSimpleMap(data);
    }

    @Test
    public void should_create_object_with_random_strategy() {
        MapData data = new DummyBuilderFactory().withStrategy(GenerationStrategy.RANDOM).create(MapData.class);

        validateSimpleMap(data);
    }

    private void validateSimpleMap(MapData data) {
        assertThat(data, not(nullValue()));
        assertThat(data.getNotParameterized().size(), is(0));
        assertThat(data.getParameterized().size(), is(1));
    }

    @Test
    public void should_create_object_with_mix_map_field() {
        MixMapData data = create(MixMapData.class);

        validateMixMap(data);
    }

    @Test
    public void should_create_object_with_mix_map_field_and_random_strategy() {
        MixMapData data = new DummyBuilderFactory().withStrategy(GenerationStrategy.RANDOM).create(MixMapData.class);

        validateMixMap(data);
    }

    private void validateMixMap(MixMapData data) {
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

    public static class MixMapData {
        private HashMap notParameterized;

        private HashMap<String, Integer> parameterized;

        public HashMap getNotParameterized() {
            return notParameterized;
        }

        public void setNotParameterized(HashMap notParameterized) {
            this.notParameterized = notParameterized;
        }

        public HashMap<String, Integer> getParameterized() {
            return parameterized;
        }

        public void setParameterized(HashMap<String, Integer> parameterized) {
            this.parameterized = parameterized;
        }
    }
}
