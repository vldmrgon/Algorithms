package com.my.pro;

public class Scope {

    public class Cache {
    }

    public class DiskCache extends Cache {
    }

    public class MemoryCache extends Cache {
    }

    public class OptimizedDiskCache extends DiskCache {
    }


    public void main(String[] args) {
        OptimizedDiskCache optimizedDiskCache = new OptimizedDiskCache();
        Cache cache1 = (Cache) optimizedDiskCache;

        MemoryCache memoryCache = new MemoryCache();
        Cache cache2 = (Cache) memoryCache;
        DiskCache diskCache = (DiskCache) cache2;

        DiskCache diskCache1 = new DiskCache();
        OptimizedDiskCache optimizediskCache = (OptimizedDiskCache) diskCache1;

        OptimizedDiskCache optimizedDiskCache2 = new OptimizedDiskCache();
        DiskCache diskCache2 = (DiskCache) optimizedDiskCache2;

        Cache cache3 = new Cache();
        MemoryCache memoryCache1 = (MemoryCache) cache3;

       OptimizedDiskCache optimizedDiskCache3 = new OptimizedDiskCache();
        Cache cache4 = (Cache) optimizedDiskCache3;
        DiskCache diskCache5 = (DiskCache) cache4;
    }


}
