package com.exmertec.dummie;

import static com.exmertec.dummie.Dummie.create;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.junit.Test;

public class ListTest {
    @Test
    public void should_create_object_with_list_field() throws Exception {
        ListData data = create(ListData.class);

        assertThat(data, not(nullValue()));
        assertThat(data.getNotParameterized().size(), is(0));
        assertThat(data.getParameterized().size(), is(1));
    }

    @Test
    public void should_create_object_with_mix_list_field() throws Exception {
        MixListData data = create(MixListData.class);

        assertThat(data, not(nullValue()));
        assertThat(data.getNotParameterized().size(), is(0));
        assertThat(data.getParameterized().size(), is(1));
    }

    public static class ListData {
        private List notParameterized;

        private List<String> parameterized;

        public List<String> getParameterized() {
            return parameterized;
        }

        public void setParameterized(List<String> parameterized) {
            this.parameterized = parameterized;
        }

        public List getNotParameterized() {
            return notParameterized;
        }

        public void setNotParameterized(List notParameterized) {
            this.notParameterized = notParameterized;
        }
    }

    public static class MixListData {
        private ArrayList notParameterized;

        private LinkedList<String> parameterized;

        public ArrayList getNotParameterized() {
            return notParameterized;
        }

        public void setNotParameterized(ArrayList notParameterized) {
            this.notParameterized = notParameterized;
        }

        public LinkedList<String> getParameterized() {
            return parameterized;
        }

        public void setParameterized(LinkedList<String> parameterized) {
            this.parameterized = parameterized;
        }
    }
}
