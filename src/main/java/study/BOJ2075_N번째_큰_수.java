package study;

import java.io.*;
import java.util.*;

public class BOJ2075_N번째_큰_수 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        int[][] nums = new int[n][n];

        for (int i = 0; i < n; i++) {
            String[] ns = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                nums[i][j] = Integer.parseInt(ns[j]);
            }
        }
        br.close();

        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            pq.offer(new Node(nums[i][n - 1], i, n - 1));
        }

        int count = 0;
        int answer = 0;
        while (count < n) {
            Node node = pq.poll();
            answer = node.value;
            pq.offer(new Node(nums[node.col][node.row - 1], node.col, node.row - 1));
            count++;
        }

        System.out.println(answer);
    }

    static class Node implements Comparable<Node> {
        int value;
        int col;
        int row;

        public Node(int value, int col, int row) {
            this.value = value;
            this.col = col;
            this.row = row;
        }


        @Override
        public int compareTo(Node o) {
            // 내림차순 정렬
            return o.value - value;
        }
    }

}