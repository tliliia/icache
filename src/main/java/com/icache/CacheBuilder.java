package com.icache;

import com.icache.impl.Cache;
import com.icache.impl.LFUStrategy;
import com.icache.impl.LRUStrategy;
import com.icache.impl.MFUStrategy;

public class CacheBuilder<K, V> {
    private int maxSize;
    private EvictStrategyType type;

    public static CacheBuilder create() {
        return new CacheBuilder();
    }

    public CacheBuilder<K, V> maxSize(int maxSize) {
        this.maxSize = maxSize;
        return this;
    }

    public CacheBuilder<K, V> evictStrategy(EvictStrategyType type) {
        this.type = type;
        return this;
    }

    public Cache<K, V> build() {
        EvictStrategy strategy = null;
        switch (type) {
            case LFU:
                strategy = new LFUStrategy();
                break;
            case MFU:
                strategy = new MFUStrategy();
                break;
            default:
                strategy = new LRUStrategy();
                break;
        }
        return new Cache<K, V>(maxSize, strategy);
    }
}
