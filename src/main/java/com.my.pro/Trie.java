package com.my.pro;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Trie {

    private TrieNode root;

    public Trie() {
        this.root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode current = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            TrieNode trieNode = current.children.get(ch);
            if (trieNode == null) {
                trieNode = new TrieNode();
                current.children.put(ch, trieNode);
            }
            current = trieNode;
        }
        current.isEndOfWord = true;
    }

    public List<String> autocomplete(String prefix) {
        List<String> results = new ArrayList<>();
        TrieNode current = root;

        for (char ch : prefix.toCharArray()) {
            TrieNode node = current.children.get(ch);
            if (node == null) {
                return results;
            }
            current = node;
        }
        findAllWorlds(current, prefix, results);
        return results;
    }

    private void findAllWorlds(TrieNode node, String prefix, List<String> results) {
        if (node.isEndOfWord) {
            results.add(prefix);
        }
        for (char ch : node.children.keySet()) {
            TrieNode currentNode = node.children.get(ch);
            if (currentNode != null) {
                findAllWorlds(currentNode, prefix + ch, results);
            }
        }
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("hello");
        trie.insert("helium");
        trie.insert("hey");
        trie.insert("he");
        trie.insert("helsinki");

        List<String> list = trie.autocomplete("heli");

    }

    public static class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        boolean isEndOfWord;

        public TrieNode() {
            isEndOfWord = false;
            for (char i = 'a'; i <= 'z'; i++) {
                children.put(i, null);
            }
        }
    }
}

