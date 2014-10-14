package com.exmertec.dummie;

import org.junit.Test;

import java.util.List;

import static com.exmertec.dummie.Dummie.create;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ListTest {
    @Test
    public void should_create_object_with_list_field() throws Exception {
        ListData data = create(ListData.class);

        assertThat(data, not(nullValue()));
        assertThat(data.getNotParameterized().size(), is(0));
    }

    public static class ListData {
        private List notParameterized;

        public List getNotParameterized() {
            return notParameterized;
        }

        public void setNotParameterized(List notParameterized) {
            this.notParameterized = notParameterized;
        }
    }
}
