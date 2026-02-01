package study;

import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class BJ2559_수열 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] days = Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int[] temps = Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int n = days[0];
        int k = days[1];

        int sum = IntStream.of(Arrays.copyOfRange(temps, 0, k)).sum();
        int answer = sum;

        for (int i = 1; i < n - k + 1; i++) {
            sum = sum - temps[i - 1] + temps[i + k - 1];
            answer = Math.max(answer, sum);
        }
        System.out.println(answer);
    }
}
