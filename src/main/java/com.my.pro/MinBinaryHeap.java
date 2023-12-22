package com.my.pro;

import java.util.Arrays;

public class MinBinaryHeap {

    private int capacity = 10;
    private int size = 0;
    private int[] items = new int[capacity];

    private int getLeftChildIndex(int parentIndex) {
        return 2 * parentIndex + 1;
    }

    private int getRightChildIndex(int parentIndex) {
        return 2 * parentIndex + 2;
    }


    private boolean hasLeftChild(int index) {
        return getLeftChildIndex(index) < size;
    }

    private boolean hasRightChild(int index) {
        return getRightChildIndex(index) < size;
    }


    private int leftChild(int index) {
        return items[getLeftChildIndex(index)];
    }

    private int rightChild(int index) {
        return items[getRightChildIndex(index)];
    }

    private void swap(int index1, int index2) {
        int temp = items[index1];
        items[index1] = items[index2];
        items[index2] = temp;
    }


    public int peek() {
        if (size == 0) {
            throw new IllegalStateException();
        }
        return items[0];
    }

    public int poll() {
        if (size == 0) {
            throw new IllegalStateException();
        }
        int item = items[0];
        items[0] = items[size - 1];
        size--;
        heapifyDown();
        return item;
    }

    public void add(int item) {
        ensureCapacity();
        items[size] = item;
        size++;
        heapifyUp();
    }

    private void ensureCapacity() {
        if (size == capacity) {
            items = Arrays.copyOf(items, capacity * 2);
            capacity *= 2;
        }
    }

    private void heapifyUp() {
        int index = size - 1;
        boolean hasPar = hasParent(index);
        int parentIndex = parent(index);
        int item = items[index];

        while (hasParent(index) && parent(index) > items[index]) {
            swap(getParentIndex(index), index);
            index = getParentIndex(index);
        }
    }

    private boolean hasParent(int index) {
        int parentIndex = getParentIndex(index);
        return parentIndex >= 0;
    }

    private int getParentIndex(int childIndex) {
        int i = (childIndex - 1) / 2;
        return i;
    }

    private int parent(int index) {
        int parentIndex = getParentIndex(index);
        int item = items[parentIndex];
        return item;
    }

    private void heapifyDown() {
        int index = 0;
        while (hasLeftChild(index)) {
            int smallerChildIndex = getLeftChildIndex(index);
            if (hasRightChild(index) && rightChild(index) < leftChild(index)) {
                smallerChildIndex = getRightChildIndex(index);
            }

            if (items[index] < items[smallerChildIndex]) {
                break;
            } else {
                swap(index, smallerChildIndex);
            }

            index = smallerChildIndex;
        }
    }

    public static void main(String[] args) {
        MinBinaryHeap minHeap = new MinBinaryHeap();
        minHeap.add(4);
        minHeap.add(9);
        minHeap.add(2);
        minHeap.add(7);
        minHeap.add(5);

        System.out.println("Min heap peek: " + minHeap.peek()); // Output: 2
        System.out.println("Min heap poll: " + minHeap.poll()); // Output: 2
        minHeap.add(1);
        System.out.println("Min heap peek after insertion: " + minHeap.peek()); // Output: 1
    }
}