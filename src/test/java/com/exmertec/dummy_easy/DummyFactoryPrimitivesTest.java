package com.exmertec.dummy_easy;

import org.junit.Test;

import static com.exmertec.dummy_easy.DummyFactory.create;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class DummyFactoryPrimitivesTest {
    @Test
    public void should_create_object_with_primitive_fields() throws Exception {
        PrimitiveData data = create(PrimitiveData.class);
        assertThat(data, not(nullValue()));
    }

    @Test
    public void should_create_object_with_primitive_wrapper_fields() throws Exception {
        PrimitiveWrapperData data = create(PrimitiveWrapperData.class);

        assertThat(data.getBooleanValue(), is(false));
    }
}