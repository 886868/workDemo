package com.mrle.alg.array;

public class SecondSmallest {

    public static void print2Data(int arr[]) {

        int first = Integer.MAX_VALUE;
        int second = Integer.MAX_VALUE;
        if(arr == null || arr.length < 2) {
            System.out.println("Invalid Input!");
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            if(arr[i] < first) {
                second = first;
                first = arr[i];
            } else if(arr[i] < second && arr[i] != first){
                second = arr[i];
            }
        }

        if(second == Integer.MAX_VALUE){
            System.out.println("No second smallest element.");
            return;
        }

        System.out.println("The smallest is:" + first + ", and second smallest is:" + second);
    }
}
