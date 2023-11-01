package com.icache.impl;

import com.icache.EvictStrategy;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class LRUStrategy<K> implements EvictStrategy<K> {
    protected final Map<K, Timestamp> frequencyMap = new HashMap<>();

    @Override
    public K candidateToEvict() {
        Map.Entry<K, Timestamp> min = Collections.min(frequencyMap.entrySet(),
                Comparator.comparing(Map.Entry::getValue));
        return min.getKey();
    }

    @Override
    public void clearEntry(K key) {
        frequencyMap.remove(key);
    }

    @Override
    public void countEntry(K key) {
        //Перезаписать актуальное время
        frequencyMap.put(key, new Timestamp(System.currentTimeMillis()));
    }
}
