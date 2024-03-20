package com.my.pro;

public class Cycles {

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    /*
    *     t t t t t                 t
    *   1 2 3 4 5 6 | 1 2 3 4 5 6 | 1
    *       h   h     h   h   h   | h
    */

    public static boolean tortoiseAndHare(ListNode head) {
        if (head == null) return false;

        ListNode turtle = head;
        ListNode hare = head;

        while (hare != null && hare.next != null) {
            turtle = turtle.next;
            hare = hare.next.next;

            if (turtle == hare) {
                return true;
            }
        }
        return false;
    }
}