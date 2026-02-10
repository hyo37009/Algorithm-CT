package study;

import java.io.*;
import java.util.*;

public class BOJ21921_블로그 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int[] input = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int x = input[1];
        input = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        br.close();

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < input.length - x + 1; i++) {
            int now = 0;
            for(int j = 0; j < x; j++)
                now += input[i + j];
            max = Math.max(max, now);
        }

        int r = 0;
        for (int i = 0; i < input.length - x + 1; i++) {
            int now = 0;
            for(int j = 0; j < x; j++)
                now += input[i + j];
            if (now == max) r++;
        }
        if (max == 0) {
            System.out.println("SAD");
            return;
        }
        System.out.println(max);
        System.out.println(r);
    }
}