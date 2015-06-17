package com.exmertec.dummie;


import com.exmertec.dummie.cache.DummyCache;
import com.exmertec.dummie.cache.impl.DefaultCache;
import com.exmertec.dummie.cache.impl.LevelCache;

public class DummyBuilderFactory {
    private DummyLogic dummyLogic;
    private Integer floor;

    public DummyBuilderFactory(DummyLogic dummyLogic, Integer floor) {
        this.dummyLogic = dummyLogic;
        this.floor = floor;
    }

    public <T> DummyBuilder<T> create(Class<T> type) {
        DummyCache cache = null;
        switch (dummyLogic) {
            case CYCLE:
                cache = new DefaultCache();
                break;
            case LEVEL:
                cache = new LevelCache(floor);
                break;
        }
        return new DummyBuilder(type, cache);
    }
}
