package com.company;

public class BST <K extends Comparable<K>, V>{
    private Node root;

    class Node {
        private K key;
        private V val;
        private Node left, right;
        public Node(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }

    private Node putter(K key, V val, Node node) {
        if (node == null) {
            return new Node(key, val);
        }
        else {
            int num = root.key.compareTo(key);

            if (num < 0) {
                putter(key, val, node.left);
            } else if (num > 0) {
                putter(key, val, node.right);
            } else {
                node.val = val;
            }

            return node;
        }
    }

    private void deleter(K key, Node node) {
        if (node == null) {
            return;
        }
        int compare = root.key.compareTo(key);
        if (compare < 0) {
            deleter(key, node.left);
        }
        else if (compare > 0) {
            deleter(key, node.right);
        } else {
            node.val = (V) "";
            node.key = (K) "";
        }
    }

    private V getter(K key, Node node) {
        if (node == null) return null;

        int num = key.compareTo(node.key);
        if (num < 0) return getter(key, node.left);

        if (num > 0) return getter(key, node.right);

        return node.val;
    }

    public void put(K key, V value) {
        root = putter(key, value, root);
    }

    public V get(K key) {
        return getter(key, root);
    }

    public void delete(K key) {
        deleter(key, root);
    }

    public Iterable<K> iterator() {
        return null;
    }
}