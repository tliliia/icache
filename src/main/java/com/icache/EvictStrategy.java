package com.icache;

public interface EvictStrategy<K> {

    K candidateToEvict();
    void countEntry(K key);
    void clearEntry(K key);
}
