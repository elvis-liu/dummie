package com.exmertec.dummy_easy;

import org.junit.Test;

import static com.exmertec.dummy_easy.DummyFactory.create;
import static com.exmertec.dummy_easy.DummyFactory.prepare;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class StringTest {
    @Test
    public void should_create_object_with_string_field() throws Exception {
        StringData data = create(StringData.class);

        assertThat(data, not(nullValue()));
        assertThat(data.getStringValue(), is("stringValue"));
    }

    @Test
    public void should_allow_customize_string_type_fields() throws Exception {
        StringData data = prepare(StringData.class).override(String.class, "test").build();

        assertThat(data.getStringValue(), is("test"));
    }

    public static class StringData {
        private String stringValue;

        public String getStringValue() {
            return stringValue;
        }

        public void setStringValue(String stringValue) {
            this.stringValue = stringValue;
        }
    }
}
