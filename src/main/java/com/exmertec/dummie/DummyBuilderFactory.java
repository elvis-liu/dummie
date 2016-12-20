package com.exmertec.dummie;


import com.exmertec.dummie.cache.DummyCache;
import com.exmertec.dummie.cache.impl.DefaultCache;
import com.exmertec.dummie.cache.impl.LevelCache;
import com.exmertec.dummie.configuration.Configuration;
import com.exmertec.dummie.configuration.CycleLogic;
import com.exmertec.dummie.configuration.GenerationStrategy;

public class DummyBuilderFactory {
    protected Configuration configuration;

    public DummyBuilderFactory() {
        configuration = new Configuration(CycleLogic.CYCLE, GenerationStrategy.DEFAULT);
    }

    public DummyBuilderFactory cycleLogic(CycleLogic logic) {
        configuration.setCycleLogic(logic);
        return this;
    }

    public DummyBuilderFactory withFloor(int floor) {
        configuration.setFloor(floor);
        return this;
    }

    public DummyBuilderFactory withStrategy(GenerationStrategy strategy) {
        configuration.setGenerationStrategy(strategy);
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
}
