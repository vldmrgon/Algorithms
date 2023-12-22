package com.my.pro;

import java.util.ArrayDeque;
import java.util.Queue;

public class BinarySearchTree<T extends Comparable<T>> {

    private TreeNode<T> root = null;

    public TreeNode<T> search(T value) {
        return search(root, value);
    }

    public void insert(T value) {
        root = insertRec(root, value);
    }

    public void depthFirstTraversal(TreeNode<T> node) {
        if (node == null) {
            return;
        }
        System.out.println(node.value);
        depthFirstTraversal(node.left);
        depthFirstTraversal(node.right);
    }

    public void breadthFirstTraversal(TreeNode<T> node) {
        if (node == null) {
            return;
        }

        Queue<TreeNode<T>> queue = new ArrayDeque<>();
        queue.add(node);

        while (!queue.isEmpty()) {

            TreeNode<T> currentNode = queue.poll();
            System.out.println(currentNode.value);

            if (currentNode.left != null) {
                queue.add(currentNode.left);
            }

            if (currentNode.right != null) {
                queue.add(currentNode.right);
            }
        }
    }

    public void delete(T value) {
        root = deleteRec(root, value);
    }

    private TreeNode<T> deleteRec(TreeNode<T> root, T value) {
        if (root == null) {
            return null;
        }

        if (value.compareTo(root.value) < 0) {
            root.left = deleteRec(root.left, value);
        } else if (value.compareTo(root.value) > 0) {
            root.right = deleteRec(root.right, value);
        } else {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }
        }

        root.value = minValue(root.right);
        return root;
    }

    private T minValue(TreeNode<T> root) {
        T minValue = root.value;
        while (root.left != null) {
            minValue = root.left.value;
            root = root.left;
        }
        return minValue;
    }

    public boolean findSubtree(TreeNode<Integer> tree, TreeNode<Integer> subTree) {
        if (tree == null || subTree == null) {
            return false;
        }

        if (isIdentical(tree, subTree)) {
            return true;
        }
        return findSubtree(tree.left, subTree) || findSubtree(tree.right, subTree);
    }

    private boolean isIdentical(TreeNode<Integer> tree, TreeNode<Integer> subTree) {
        if (tree == null && subTree == null) {
            return true;
        }

        if (tree == null || subTree == null) {
            return false;
        }
        return (tree.value.equals(subTree.value)) && isIdentical(tree.left, subTree.left) && isIdentical(tree.right, subTree.right);
    }


    private TreeNode<T> search(TreeNode<T> root, T value) {
        if (root == null || root.value == value) {
            return root;
        }

        if (value.compareTo(root.value) < 0) {
            return search(root.left, value);
        } else {
            return search(root.right, value);
        }
    }

    private TreeNode<T> insertRec(TreeNode<T> root, T value) {
        if (root == null) {
            root = new TreeNode<T>(value);
            return root;
        }

        if (value.compareTo(root.value) < 0) {
            root.left = insertRec(root.left, value);
        } else if (value.compareTo(root.value) > 0) {
            root.right = insertRec(root.right, value);
        }

        return root;
    }

    public TreeNode<T> getRoot() {
        return root;
    }

    public static class TreeNode<T> {
        TreeNode<T> left, right = null;
        T value;

        public TreeNode(T value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {

        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.insert(4);
        tree.insert(3);
        tree.insert(5);
        tree.insert(1);

        TreeNode<Integer> search = tree.search(1);

        tree.depthFirstTraversal(tree.getRoot());
        tree.breadthFirstTraversal(tree.getRoot());

        BinarySearchTree<Integer> mainNode = new BinarySearchTree<>();
        mainNode.insert(10);
        mainNode.insert(5);
        mainNode.insert(15);
        mainNode.insert(8);
        mainNode.insert(12);
        mainNode.insert(18);

        BinarySearchTree<Integer> subtree = new BinarySearchTree<>();
        subtree.insert(15);
        subtree.insert(12);
        subtree.insert(18);

        boolean isSubtree = new BinarySearchTree<>().findSubtree(mainNode.root, subtree.root);
        mainNode.delete(15);
    }
 }
