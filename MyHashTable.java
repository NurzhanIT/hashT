package com.company;

import java.util.ArrayList;

public class MyHashTable<K, V> {

    private static class HashNode<K, V> {
        private K key;
        private V value;
        private HashNode<K, V> next;

        public HashNode(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public String toString() {
            return "{ " + key + " " + value + " }";
        }
    }

    private ArrayList<ArrayList<HashNode<K, V>>> chainArray;
    private int size = 11;

    public MyHashTable() {
        this(11);
    }

    public MyHashTable(int size) {
        chainArray = new ArrayList<>(size);

        for (int i = 0; i < size; i++)
            chainArray.add(i, new ArrayList<HashNode<K, V>>());

    }

    private int hash(K key) {
        return key.hashCode() % size;
    }

    public void put(K key, V value) {
        int I = hash(key); // key index

        ArrayList<HashNode<K, V>> chainList = chainArray.get(I);
        HashNode<K, V> newChain = new HashNode<K, V>(key, value);

        boolean found = false;
        for (HashNode<K, V> chain : chainList) {
            if (chain.key.equals(newChain.key)) {
                chain.value = newChain.value;

                found = true;
            }
        }
        if (!found) {
            chainList.add(newChain);
        }

    }

    public V get(K key) {
        int I = hash(key);

        ArrayList<HashNode<K, V>> chainList = chainArray.get(I);

        for (HashNode<K, V> entry : chainList) {
            if (entry.key.equals(key)) {
                return entry.value;
            }
        }
        return null;
    }

    public V remove(K key) {
        int I = hash(key);
        ArrayList<HashNode<K, V>> chainList = chainArray.get(I);

        for (HashNode<K, V> chain : chainList) {
            if (chain.key.equals(key)) {
                chainArray.remove(I);
                return chain.value;
            }
        }
        return null;
    }

    public boolean contains(V value) {
        for (ArrayList<HashNode<K, V>> hashNodes : chainArray) {
            if (hashNodes.size() != 0)
                if (hashNodes.get(0).value == value) {
                    return true;
                }
        }
        return false;
    }

    public K getKey(V value) {
        if (contains(value))
            for (ArrayList<HashNode<K, V>> hashNodes : chainArray) {
                if (hashNodes.size() != 0)
                    if (hashNodes.get(0).value == value) {
                        return hashNodes.get(0).key;
                    }
            }
        return null;
    }
}