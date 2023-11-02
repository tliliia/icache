package com.icache;

import com.icache.impl.Cache;
import com.icache.impl.LFUStrategy;
import com.icache.impl.LRUStrategy;
import com.icache.impl.MFUStrategy;

public class CacheBuilder<K, V> {
    public static final int DEFAULT_MAX_SIZE = 100;
    public static final EvictStrategyType DEFAULT_EVICT_STRATEGY = EvictStrategyType.LRU;

    private Integer maxSize;
    private EvictStrategyType type;

    public static CacheBuilder create() {
        return new CacheBuilder();
    }

    public CacheBuilder<K, V> maxSize(int maxSize) {
        if (maxSize <= 0) {
            throw new IllegalArgumentException("Необходимо задать корректное значение ");
        }
        this.maxSize = maxSize;
        return this;
    }

    public CacheBuilder<K, V> evictStrategy(EvictStrategyType type) {
        this.type = type;
        return this;
    }

    public Cache<K, V> build() {
        if (maxSize == null) {
            maxSize = DEFAULT_MAX_SIZE;
        }
        if (type == null) {
            type = DEFAULT_EVICT_STRATEGY;
        }
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
