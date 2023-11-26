package com.my.pro;

public class SinglyLinkedList {

    public static void main(String[] args) {

        ListNode l1 = createList(new int[]{2, 4, 3});
        ListNode l2 = createList(new int[]{5, 6, 4});

        ListNode listNode = addTwoNumbers(l1, l2);
        System.out.println();
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode mock = new ListNode(0);
        ListNode current = mock;

        int offset = 0;

        while (l1 != null || l2 != null) {

            int x = (l1 != null) ? l1.val : 0;
            int y = (l2 != null) ? l2.val : 0;

            int sum = x + y + offset;
            offset = sum / 10;

            current.next = new ListNode(sum % 10);

            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;

            current = current.next;
        }

        if (offset > 0) {
            current.next = new ListNode(offset);
        }

        return mock.next;
    }


    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    private static ListNode createList(int[] values) {
        if (values == null || values.length == 0) {
            return null;
        }

        ListNode mock = new ListNode();
        ListNode current = mock;
        for (int value : values) {
            current.next = new ListNode(value);
            current = current.next;
        }
        return mock.next;
    }
}