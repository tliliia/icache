package com.icache;

import com.icache.impl.Cache;

public class Demo {
    public static void main(String[] args) {
        Cache<String, String> cache = DemoCacheBuilder.create()
                .maxSize(3)
                .evictStrategy(EvictStrategyType.LFU)
                .build();
        cache.put("1", "one");
        cache.put("2", "two");
        cache.get("2");
        cache.get("1");
        cache.get("2");
        int i=0;
    }
}
