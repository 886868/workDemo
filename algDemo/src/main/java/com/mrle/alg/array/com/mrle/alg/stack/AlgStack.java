package com.mrle.alg.array.com.mrle.alg.stack;

import java.util.Stack;

public class AlgStack {

    public static Stack<Integer> sortStack(Stack<Integer> input) {
        Stack<Integer> tmpS = new Stack<Integer>();

        if (input.isEmpty()) {
            System.out.println("Invalid Input.");
            return null;
        }

        while (!input.isEmpty()) {

            Integer tmpI = input.pop();

            while (!tmpS.isEmpty() && tmpS.peek() > tmpI) {
                input.push(tmpS.pop());
            }

            tmpS.push(tmpI);
        }

        return tmpS;
    }
}
