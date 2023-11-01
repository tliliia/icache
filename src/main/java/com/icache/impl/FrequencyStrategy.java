package com.icache.impl;

import com.icache.EvictStrategy;

import java.util.HashMap;
import java.util.Map;

public abstract class FrequencyStrategy<K> implements EvictStrategy<K> {
    protected final Map<K, Integer> frequencyMap = new HashMap<>();

    @Override
    public abstract K candidateToEvict();

    @Override
    public void clearEntry(K key) {
        frequencyMap.remove(key);
    }

    @Override
    public void countEntry(K key) {
        if (key != null) {
            int freq = frequencyMap.containsKey(key) ? frequencyMap.get(key) + 1 : 1;
            frequencyMap.put(key, freq);
        }
    }
}
