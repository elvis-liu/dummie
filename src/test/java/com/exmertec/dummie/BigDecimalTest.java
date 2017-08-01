package com.exmertec.dummie;

import com.exmertec.dummie.configuration.GenerationStrategy;
import org.junit.Test;

import java.math.BigDecimal;

import static com.exmertec.dummie.Dummie.withStrategy;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class BigDecimalTest {
    @Test
    public void should_success_create_big_decimal_type_field() throws Exception {
        BigDecimalData data = Dummie.create(BigDecimalData.class);

        assertThat(data, not(nullValue()));
        assertThat(data.getValue(), not(nullValue()));
    }

    @Test
    public void should_create_object_with_random_strategy() throws Exception {
        BigDecimalData data = withStrategy(GenerationStrategy.RANDOM).create(BigDecimalData.class);

        assertThat(data, not(nullValue()));
        assertThat(data.getValue(), not(nullValue()));
    }

    public static class BigDecimalData {
        private BigDecimal value;

        public BigDecimal getValue() {
            return value;
        }

        public void setValue(BigDecimal value) {
            this.value = value;
        }
    }
}
