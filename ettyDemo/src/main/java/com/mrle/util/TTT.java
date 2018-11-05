package com.mrle.util;

public class TTT {
    public static void main(String[] args) {

        int a = 550;
        int b = 210;
        int s=1;
        int i;
        for(i=2;i<=a&&i<=b;i++)
        {
            if(a%i==0&&b%i==0)
            {
                s=i;
            }
        }
        System.out.println(a+"与"+b+"的最大公约数是"+s);
    }

}
