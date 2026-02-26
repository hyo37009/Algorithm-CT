package study;

import java.io.*;
import java.util.*;

public class BOJ2075_N번째_큰_수 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        PriorityQueue<Stack<Integer>> nums = new PriorityQueue<>(
                (a, b) -> b.peek() - a.peek()
        );


        int n = Integer.parseInt(br.readLine());
        Stack<Integer>[] col = new Stack[n];
        for (int i = 0; i < n; i++) {
            col[i] = new Stack<>();
        }
        for (int i = 0; i < n; i++) {
            String[] ns = br.readLine().split( " ");
            for (int j = 0; j < n; j++) {
                col[j].add(Integer.parseInt(ns[j]));
            }
        }
        for (int i = 0; i < n; i++) {
            nums.add(col[i]);
        }
        br.close();

        int count = 0;
        int answer = 0;
        Stack<Integer> temp;
        while (count < n) {
            answer = nums.peek().pop();
            nums.offer(nums.poll());
            count++;
        }

        System.out.println(answer);
    }
}