package study;

import java.io.*;
import java.util.*;

public class BOJ21921_블로그 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int[] input = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int n = input[0];
        int x = input[1];
        input = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        br.close();

        int max = 0;
        int count = 1;
        int now = 0;
        for(int i = 0; i < x; i++)
            now += input[i];
        for (int i = x; i < input.length; i++) {
            now = now - input[i - x] + input[i];
            if (max < now){
                max = now;
                count = 1;
            } else if (max == now) {
                count++;
            }
        }
        if (max == 0) {
            System.out.println("SAD");
            return;
        }
        System.out.println(max);
        System.out.println(count);
    }
}