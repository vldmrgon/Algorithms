package com.my.pro;

import java.util.EmptyStackException;
import java.util.Stack;

public class StacksAndQueues {

    public static void main(String[] args) {

        String val1 = "(()(())())";
        String val2 = "(U)";
        String val3 = "())";
        String val4 = "UW";
        int valid1 = validateBrackets(val1);
        int valid2 = validateBrackets(val2);
        int valid3 = validateBrackets(val3);
        int valid4 = validateBrackets(val4);

        int[] A = {4, 3, 2, 1, 5};
        int[] B = {0, 1, 0, 0, 0};
        int fish = fishDirection(A, B);

        String str = "{[()()]}";
        int properlyBrackets = brackets(str);
    }

    public static int validateBrackets(String str) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(') stack.push('(');

            if (str.charAt(i) == ')') {
                if (stack.empty()) return 0;
                stack.pop();
            }
        }
        return stack.empty() ? 1 : 0;
    }

    public static int fishDirection(int[] A, int[] B) {
        int N = A.length;
        int aliveFish = 0;
        Stack<Integer> downstreamFish = new Stack<>();

        for (int i = 0; i < N; i++) {
            if (B[i] == 0) { // upstream fish
                while (!downstreamFish.isEmpty()) {
                    int downstreamSize = downstreamFish.pop();
                    if (downstreamSize > A[i]) {
                        downstreamFish.push(downstreamSize); // upstream fish eats downstream fish
                        break; // downstream fish is eaten
                    }
                }
                if (downstreamFish.isEmpty()) {
                    aliveFish++; // upstream fish survives
                }
            } else { // downstream fish
                downstreamFish.push(A[i]);
            }
        }

        return aliveFish + downstreamFish.size();
    }

    private static int brackets(String str) {
        Stack<Character> stack = new Stack<>();

        for (char ch : str.toCharArray()) {
            if (ch == '{' || ch == '[' || ch == '(') {
                stack.push(ch);
            } else {
                Character lastElement = stack.peek();
                if ((ch == '}' && lastElement == '{')
                        || (ch == ')' && lastElement == '(')
                        || (ch == ']' && lastElement == '[')) {
                    stack.pop();
                } else {
                    return 0;
                }
            }
        }
        return stack.empty() ? 1 : 0;
    }
}