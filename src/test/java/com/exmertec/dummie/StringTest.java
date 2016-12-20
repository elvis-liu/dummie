package com.exmertec.dummie;

import static com.exmertec.dummie.Dummie.create;
import static com.exmertec.dummie.Dummie.prepare;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import com.exmertec.dummie.configuration.GenerationStrategy;
import org.junit.Test;

public class StringTest {
    @Test
    public void should_create_object_with_string_field() throws Exception {
        StringData data = create(StringData.class);

        assertThat(data, not(nullValue()));
        assertThat(data.getStringValue(), is("stringValue"));
    }

    @Test
    public void should_create_object_with_random_strategy() throws Exception {
        StringData data = new DummyBuilderFactory()
            .withStrategy(GenerationStrategy.RANDOM)
            .create(StringData.class);

        assertThat(data, not(nullValue()));
        assertThat(data.getStringValue(), not(nullValue()));
    }

    @Test
    public void should_allow_customize_string_type_fields() throws Exception {
        StringData data = prepare(StringData.class).override("stringValue", "test").build();

        assertThat(data.getStringValue(), is("test"));
    }

    @Test
    public void should_not_write_fields_without_setter() throws Exception {
        StringData data = create(StringData.class);

        assertThat(data.getNoSetter(), is(nullValue()));
    }

    public static class StringData {
        private String stringValue;

        private String noSetter;

        public String getStringValue() {
            return stringValue;
        }

        public void setStringValue(String stringValue) {
            this.stringValue = stringValue;
        }

        public String getNoSetter() {
            return noSetter;
        }
    }
}
