package com.exmertec.dummie;


import com.exmertec.dummie.cache.DummyCache;
import com.exmertec.dummie.cache.impl.DefaultCache;
import com.exmertec.dummie.cache.impl.LevelCache;

public abstract class DummyBuilderFactory {
    protected BuilderConfiguration configuration;

    protected DummyBuilderFactory() {
        configuration = new BuilderConfiguration();
    }

    public static DummyBuilderFactory use(CycleLogic logic) {
        switch (logic) {
            case CYCLE:
                return new DefaultBuilderFactory();
            case LEVEL:
                return new LevelBuilderFactory();
            default:
                throw new IllegalArgumentException();
        }
    }

    public void withFloor(int floor) {
        configuration.setFloor(floor);
    }

    public <T> DummyBuilder<T> prepare(Class<T> type) {
        return new DummyBuilder(type, getCache());
    }

    public <T> T create(Class<T> type) {
        return new DummyBuilder<T>(type, getCache()).build();
    }

    protected abstract DummyCache getCache();

    private static class LevelBuilderFactory extends DummyBuilderFactory {
        @Override
        protected DummyCache getCache() {
            return new LevelCache(configuration.getFloor());
        }
    }

    private static class DefaultBuilderFactory extends DummyBuilderFactory {
        @Override
        protected DummyCache getCache() {
            return new DefaultCache();
        }
    }

    private class BuilderConfiguration {
        private Integer floor = 2;

        public Integer getFloor() {
            return floor;
        }

        public void setFloor(Integer floor) {
            this.floor = floor;
        }
    }
}
