package study;

import java.io.*;
import java.util.*;

public class BOJ2075_N번째_큰_수 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            String[] ns = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                pq.offer(Integer.parseInt(ns[j]));
                if(pq.size() > n)
                    pq.poll();
            }
        }
        br.close();

        System.out.println(pq.peek());
    }

}