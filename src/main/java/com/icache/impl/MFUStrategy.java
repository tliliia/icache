package com.icache.impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.Map;

public class MFUStrategy<K> extends FrequencyStrategy<K> {
    @Override
    public K candidateToEvict() {
        if (frequencyMap.isEmpty()) {
            return null;
        }
        Map.Entry<K, Integer> max = Collections.max(frequencyMap.entrySet(),
                Comparator.comparing(Map.Entry::getValue));
        return max.getKey();
    }
}
