package com.mrle.alg.array;

public class FirstNonRep {
    public static int ger1thNonRep(int[]arr){
        for (int i = 0; i < arr.length ; i++) {
            int j;
            for (j = 0; j < arr.length; j++) {
                if (i != j && arr[i] == arr[j]) {
                    break;
                }
            }
            if (j == arr.length) {
                return arr[i];
            }
        }
        return -1;
    }
}
