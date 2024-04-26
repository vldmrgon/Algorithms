package com.my.pro;

import java.util.HashMap;
import java.util.*;

public class Cache {

    public static void main(String[] args) {
        MRUCache<Integer, String> mruCache = new MRUCache<>(3);
        mruCache.put(1, "one");
        mruCache.put(2, "two");
        mruCache.put(3, "three");
        mruCache.get(1);
        mruCache.get(2);
        mruCache.put(4, "four");

        LRUCache<Integer, String> lruCache = new LRUCache<>(3);
        lruCache.put(1, "one");
        lruCache.put(2, "two");
        lruCache.put(3, "three");
        lruCache.get(1);
        lruCache.get(2);
        lruCache.put(4, "four");

        LFUCache<Integer, String> lfuCache = new LFUCache<>(3);
        lfuCache.put(1, "A");
        lfuCache.put(2, "B");
        lfuCache.put(3, "C");

        System.out.println("Value for key 1: " + lfuCache.get(1));
        lfuCache.put(4, "D");

        System.out.println("Value for key 2: " + lfuCache.get(2));
        System.out.println("Value for key 3: " + lfuCache.get(3));
        System.out.println("Value for key 4: " + lfuCache.get(4));
        System.out.println("Value for key 1: " + lfuCache.get(1));

        lfuCache.put(5, "E");

        System.out.println("Value for key 1: " + lfuCache.get(1));
        System.out.println("Value for key 3: " + lfuCache.get(3));
        System.out.println("Value for key 4: " + lfuCache.get(4));
        System.out.println("Value for key 5: " + lfuCache.get(5));


        printCache(mruCache);
        System.out.println("***");
        printCache(lruCache);
    }

    private static void printCache(Map<Integer, String> cache) {
        for (Map.Entry<Integer, String> entry : cache.entrySet()) {
            System.out.println("Key: " + entry.getKey() + " Value: " + entry.getValue());
        }
    }

    //todo LIFO (Last-In-First-Out)
    public static class LIFOCache<K, V> {
        private final Map<K, V> cache;
        private final Deque<K> stack;
        private final int capacity;

        public LIFOCache(int capacity) {
            this.capacity = capacity;
            cache = new HashMap<>();
            stack = new ArrayDeque<>();
        }

        public void put(K key, V value) {
            if (cache.size() > capacity) {
                stack.removeFirst();
            }
            cache.put(key, value);
            stack.addFirst(key);
        }

        public V get(K key) {
            return cache.get(key);
        }
    }

    // todo FIFO (First-In-First-Out) Cache
    public static class FIFOCache<K, V> extends LinkedHashMap<K, V> {

        private int capacity;

        public FIFOCache(int initialCapacity, int capacity) {
            super(initialCapacity + 1, 1.0F, false);
            this.capacity = capacity;
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
            return super.size() > capacity;
        }
    }


    //todo LFU (Least Frequently Used) Cache
    public static class LFUCache<K, V> {
        private final Map<Integer, LinkedHashSet<K>> frequencyKeysMap;
        private final Map<K, Integer> keyFrequencyMap;
        private final Map<K, V> keyValueMap;
        private int minFrequency = 1;
        private final int capacity;

        public LFUCache(int initialCapacity) {
            this.capacity = initialCapacity;
            this.keyValueMap = new HashMap<>();
            this.keyFrequencyMap = new HashMap<>();
            this.frequencyKeysMap = new HashMap<>();
            this.frequencyKeysMap.put(1, new LinkedHashSet<>());
        }

        public V get(K key) {
            if (!keyValueMap.containsKey(key)) {
                return null;
            }
            increaseFrequency(key);
            return keyValueMap.get(key);
        }

        public void put(K key, V value) {
            if (this.capacity == 0) {
                return;
            }

            if (keyValueMap.containsKey(key)) {
                keyValueMap.put(key, value);
                increaseFrequency(key);
                return;
            }

            if (keyValueMap.size() >= capacity) {
                removeLeastFrequentItem();
            }

            keyValueMap.put(key, value);
            keyFrequencyMap.put(key, 1);
            frequencyKeysMap.get(1).add(key);
            minFrequency = 1;
        }

        private void removeLeastFrequentItem() {
            K leastFrequentKey = frequencyKeysMap.get(minFrequency).iterator().next();
            frequencyKeysMap.get(minFrequency).remove(leastFrequentKey);
            keyValueMap.remove(leastFrequentKey);
            keyFrequencyMap.remove(leastFrequentKey);
        }

        private void increaseFrequency(K key) {
            int currentFreq = keyFrequencyMap.get(key);

            keyFrequencyMap.put(key, currentFreq + 1);
            frequencyKeysMap.get(currentFreq).remove(key);

            if (currentFreq == minFrequency && frequencyKeysMap.get(currentFreq).isEmpty()) {
                minFrequency++;
            }

            frequencyKeysMap.computeIfAbsent(currentFreq + 1, k -> new LinkedHashSet<>()).add(key);
        }
    }


    // todo LRU(Least Recently Used) Cache
    public static class LRUCache<K, V> extends LinkedHashMap<K, V> {

        private final int capacity;

        public LRUCache(int initialCapacity) {
            super(initialCapacity + 1, 1.0F, true);
            this.capacity = initialCapacity;
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
            return super.size() > capacity;
        }

        public V put(K key, V value) {
            return super.put(key, value);
        }

        public V get(Object key) {
            return super.get(key);
        }
    }

    // todo MRU(Most Recently Used) Cache
    public static class MRUCache<K, V> extends LinkedHashMap<K, V> {

        private final int capacity;
        private K mruKey = null;

        public MRUCache(int capacity) {
            super(capacity + 1, 1.0F, true);
            this.capacity = capacity;
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
            return super.size() > this.capacity;
        }

        public V put(K key, V value) {
            if (super.size() >= this.capacity && !containsKey(key) && this.mruKey != null) {
                super.remove(this.mruKey);
            }
            this.mruKey = key;
            return super.put(key, value);
        }

        public V get(Object key) {
            V value = super.get(key);
            if (value != null) {
                mruKey = (K) key;
            }
            return value;
        }
    }
}