package com.exmertec.dummie;

import static com.exmertec.dummie.Dummie.withStrategy;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNot.not;

import com.exmertec.dummie.configuration.GenerationStrategy;
import org.junit.Test;

public class InflateSuperClassTest {
    @Test
    public void should_success_inflate_super_class() throws Exception {
        Employee employee = Dummie.create(Employee.class);

        validate(employee);
    }

    @Test
    public void should_create_object_with_random_strategy() throws Exception {
        Employee employee = withStrategy(GenerationStrategy.RANDOM).create(Employee.class);

        validate(employee);
    }

    private void validate(Employee employee) {
        assertThat(employee, not(nullValue()));
        assertThat(employee.getName(), not(nullValue()));
        assertThat(employee.getAvatar(), not(nullValue()));
        assertThat(employee.getAge(), not(nullValue()));
        assertThat(employee.isOnHoliday(), not(nullValue()));
    }

    public static class People {
        private String name;
        private Integer age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }
    }

    public static class Employee extends People {
        private String avatar;
        private boolean onHoliday;

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public boolean isOnHoliday() {
            return onHoliday;
        }

        public void setOnHoliday(boolean onHoliday) {
            this.onHoliday = onHoliday;
        }
    }
}
