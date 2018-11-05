package com.mrle.alg.array;

public class MergeArray {

    public static int[] mergeArray(int[]a, int[]b){
        if (a == null || b == null) {
            System.out.println("Invalid Input!");
            return null;
        }

        int i = 0;
        int j = 0;
        int k = 0;
        int lengthA = a.length;
        int lengthB = b.length;

        int[] c = new int[lengthA + lengthB];

        while (i < lengthA && j < lengthB){
            if(a[i] < b[j]){
                c[k++] = a[i++];
            } else {
                c[k++] = b[j++];
            }
        }

        while (i < lengthA) {
            c[k++] = a[i++];
        }

        while (j < lengthB) {
            c[k++] = b[j++];
        }

        return c;
    }
}
