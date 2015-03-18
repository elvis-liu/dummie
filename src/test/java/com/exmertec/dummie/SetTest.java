package com.exmertec.dummie;


import org.junit.Test;

import java.util.Set;

import static com.exmertec.dummie.Dummie.create;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class SetTest {

    @Test
    public void should_create_object_with_map_field() {
        SetData data = create(SetData.class);

        assertThat(data, not(nullValue()));
        assertThat(data.getNotParameterized().size(), is(0));
        assertThat(data.getParameterized().size(), is(1));
    }

    public static class SetData {
        private Set notParameterized;

        private Set<String> parameterized;

        public Set getNotParameterized() {
            return notParameterized;
        }

        public void setNotParameterized(Set notParameterized) {
            this.notParameterized = notParameterized;
        }

        public Set<String> getParameterized() {
            return parameterized;
        }

        public void setParameterized(Set<String> parameterized) {
            this.parameterized = parameterized;
        }
    }
}
