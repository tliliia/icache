package com.icache.impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.Map;

public class LFUStrategy<K> extends FrequencyStrategy<K> {
    @Override
    public K candidateToEvict() {
        if (frequencyMap.isEmpty()) {
            return null;
        }
        Map.Entry<K, Integer> min = Collections.min(frequencyMap.entrySet(),
                Comparator.comparing(Map.Entry::getValue));
        return min.getKey();
    }
}
