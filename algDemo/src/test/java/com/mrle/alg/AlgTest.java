package com.mrle.alg;

import com.mrle.alg.array.FirstNonRep;
import com.mrle.alg.array.MergeArray;
import com.mrle.alg.array.SecondSmallest;
import com.mrle.alg.array.com.mrle.alg.stack.AlgStack;
import org.junit.Test;

import java.util.Stack;

public class AlgTest {

    @Test
    public void secondSmallestTest(){
        int[]arr = {13,12,1,4,78,1};
        SecondSmallest.print2Data(arr);
    }

    @Test
    public void firstNonRepTest(){
        int arr[] = { 9, 4, 9, 6, 7, 4 };
        System.out.println(FirstNonRep.ger1thNonRep(arr));
    }

    @Test
    public void mergeArrayTest(){
        int[] a = {1, 3, 5, 7};
        int[] b = {2, 4, 6, 8};

        int[] c = MergeArray.mergeArray(a, b);
        if (c != null) {
            for (int ele : c) {
                System.out.print(ele + " ");
            }
        }
    }

    @Test
    public void sortStackTest() {
        Stack<Integer> input = new Stack<Integer>();

        input.push(34);
        input.push(3);
        input.push(31);
        input.push(98);
        input.push(92);
        input.push(23);

        Stack<Integer> res = AlgStack.sortStack(input);

        if (res == null) {
            System.out.println("Invalid Result.");
            return;
        }

        while (!res.isEmpty()) {
            System.out.print(res.pop() + " ");
        }

    }
}
