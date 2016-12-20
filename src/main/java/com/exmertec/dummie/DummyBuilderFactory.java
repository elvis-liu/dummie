package com.exmertec.dummie;


import com.exmertec.dummie.cache.DummyCache;
import com.exmertec.dummie.cache.impl.DefaultCache;
import com.exmertec.dummie.cache.impl.LevelCache;

public class DummyBuilderFactory {
    protected BuilderConfiguration configuration;

    public DummyBuilderFactory() {
        configuration = new BuilderConfiguration(CycleLogic.CYCLE);
    }

    public DummyBuilderFactory cycleLogic(CycleLogic logic) {
        configuration.setCycleLogic(logic);
        return this;
    }

    public DummyBuilderFactory withFloor(int floor) {
        configuration.setFloor(floor);
        return this;
    }

    public <T> DummyBuilder<T> prepare(Class<T> type) {
        return new DummyBuilder(type, getCache());
    }

    public <T> T create(Class<T> type) {
        return new DummyBuilder<T>(type, getCache()).build();
    }

    private DummyCache getCache() {
        switch (configuration.getCycleLogic()) {
            case CYCLE:
                return new DefaultCache();
            case LEVEL:
                return new LevelCache(configuration.getFloor());
            default:
                throw new IllegalArgumentException();
        }
    }

    private class BuilderConfiguration {
        private Integer floor;
        private CycleLogic cycleLogic;

        public BuilderConfiguration(CycleLogic cycleLogic) {
            setCycleLogic(cycleLogic);
        }

        public Integer getFloor() {
            return floor;
        }

        public void setFloor(Integer floor) {
            this.floor = floor;
        }

        public CycleLogic getCycleLogic() {
            return cycleLogic;
        }

        public void setCycleLogic(CycleLogic cycleLogic) {
            this.cycleLogic = cycleLogic;
            if (cycleLogic == CycleLogic.LEVEL) { // set default floor value
                floor = 2;
            }
        }
    }
}
