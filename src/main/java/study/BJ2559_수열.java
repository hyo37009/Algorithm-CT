package study;

import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class BJ2559_수열 {

    class Main {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            int[] days = Arrays.stream(scanner.nextLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int[] temps = Arrays.stream(scanner.nextLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int answer = 0;

            int n = days[0];
            int k = days[1];
//                IntStream.of(temps)
            for (int i = 0; i < k; i++) {
                int sum = IntStream.of(Arrays.copyOfRange(temps, i, i + k)).sum();
                answer = Math.max(sum, answer);
            }

            System.out.println(answer);

        }
    }
}
