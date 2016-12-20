package com.exmertec.dummie;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNot.not;

import org.junit.Test;

public class InflateSuperClassTest {
    @Test
    public void should_success_inflate_super_class() throws Exception {
        Employee employee = Dummie.create(Employee.class);

        assertThat(employee, not(nullValue()));

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
