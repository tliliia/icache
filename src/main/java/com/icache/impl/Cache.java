package com.icache.impl;

import com.icache.Cacheable;
import com.icache.EvictStrategy;

import java.util.HashMap;
import java.util.Map;

public class Cache<K, V> implements Cacheable<K, V> {
    private final int maxSize;
    private final EvictStrategy evictStrategy;
    private final Map<K, V> storage;

    public Cache(int maxSize, EvictStrategy evictStrategy) {
        this.maxSize = maxSize;
        this.evictStrategy = evictStrategy;
        this.storage = new HashMap<>();
    }

    private boolean isNeedEvict() {
        return storage.size() > maxSize;
    }

    @Override
    public void put(K key, V value) {
        storage.put(key, value);
        if (isNeedEvict()) {
            remove((K)evictStrategy.candidateToEvict());
        }
    }

    @Override
    public V get(K key) {
        V value = storage.get(key);
        evictStrategy.countEntry(key);
        return value;
    }

    @Override
    public void remove(K key) {
        storage.remove(key);
        evictStrategy.clearEntry(key);
    }

    @Override
    public void clear() {
        storage.clear();
    }
}
