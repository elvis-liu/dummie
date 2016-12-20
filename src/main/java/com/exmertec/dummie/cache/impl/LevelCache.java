package com.exmertec.dummie.cache.impl;

import com.exmertec.dummie.cache.DummyCache;

import java.lang.reflect.Field;
import java.util.Stack;

public class LevelCache extends DummyCache {

    private Integer floor;

    private Stack<Class<?>> parents;

    public LevelCache(Integer floor) {
        super();

        this.floor = floor;
        this.parents = new Stack<Class<?>>();
    }

    // TODO: still has bug if a class has field with same type as itself.
     private boolean isOverFloor(Field field) {
        if (parents.empty()) {
            parents.push(field.getDeclaringClass());
        } else if (parents.contains(field.getDeclaringClass())) {
            while (!parents.peek().equals(field.getDeclaringClass())) {
                parents.pop();
            }
        } else {
            if (parents.size() >= floor) {
                return true;
            }
            parents.push(field.getDeclaringClass());
        }
        return false;
    }

    public Object getCachedData(Field field) {
        return isOverFloor(field) ? null : super.getCachedData(field);
    }
}
