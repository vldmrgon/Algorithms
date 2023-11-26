package com.my.pro;

import java.util.LinkedList;
import java.util.List;

public class HashMap<K, V> {

    private static final float LOAD_FACTOR_THRESHOLD = 0.75F;
    private static final int DEFAULT_CAPACITY = 16;
    private List<Pair<K, V>>[] buckets;
    private int size = 0;

    /*
LinkedList[] index
              [0] = new List<Pair<K,V>>
              [1] = new List<Pair<K,V>>
              [2] = new List<Pair<K,V>>
              [3] = new List<Pair<K,V>>
  BUCKETS     [4] = new List<Pair<K,V>>
              [5] = new List<Pair<K,V>>
              [6] = new List<Pair<K,V>>
              [7] = new List<Pair<K,V>>
              [8] = new List<Pair<K,V>>

     */

    public HashMap() {
        this(DEFAULT_CAPACITY);
    }

    public HashMap(int capacity) {
        this.buckets = new LinkedList[capacity];
        for (int i = 0; i < capacity; i++) {
            this.buckets[i] = new LinkedList<>();
        }
    }

    public void put(K key, V value) {
        List<Pair<K, V>> bucket = getPairs(key);
        for (Pair<K, V> pair : bucket) {
            if (pair.first.equals(key)) {
                pair.second = value;
                return;
            }
        }
        bucket.add(new Pair<>(key, value));
        size++;
    }

    public V get(K key) {
        List<Pair<K, V>> bucket = getPairs(key);
        for (Pair<K, V> pair : bucket) {
            if (pair.first.equals(key)) {
                return pair.second;
            }
        }
        return null;
    }

    public void remove(K key) {
        List<Pair<K, V>> bucket = getPairs(key);
        for (Pair<K, V> pair : bucket) {
            if (pair.first.equals(key)) {
                bucket.remove(pair);
                size--;
            }
        }
    }

    public int size() {
        int countHashMap = 0;
        for (List<Pair<K, V>> bucket : this.buckets) {
            if (bucket != null) {
                countHashMap += bucket.size();
            }
        }
        return countHashMap;
    }

    private List<Pair<K, V>> getPairs(K key) {
        int index = getIndex(key);
        return this.buckets[index];
    }

    private int getIndex(K key) {
        return Math.abs(key.hashCode() % this.buckets.length);
    }

    private int getIndex(K key, int capacity) {
        return Math.abs(key.hashCode() % capacity);
    }

    private boolean checkLoadHashMap() {
        return (float) size / buckets.length > LOAD_FACTOR_THRESHOLD;
    }

    private void resize() {
        int newCapacity = buckets.length * 2;
        List<Pair<K, V>>[] newBuckets = new LinkedList[newCapacity];

        for (int i = 0; i < newCapacity; i++) {
            newBuckets[i] = new LinkedList<>();
        }

        for (List<Pair<K, V>> bucket : this.buckets) {
            for (Pair<K, V> pair : bucket) {

                int index = getIndex(pair.first, newCapacity);
                newBuckets[index].add(pair);
            }
        }
        this.buckets = newBuckets;
    }

    public static class Pair<F, S> {
        private F first;
        private S second;

        public Pair(F first, S second) {
            this.first = first;
            this.second = second;
        }
    }

    public static void main(String[] args) {
        HashMap<String, Integer> map = new HashMap<>(20);
        map.put("Hello", 777);
        Integer hello = map.get("Hello");
        int size1 = map.size();
        map.remove("Hello");
        int size2 = map.size();
        System.out.println();
    }
}
