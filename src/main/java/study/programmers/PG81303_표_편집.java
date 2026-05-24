package study.programmers;

import java.io.*;
import java.util.*;

/**
 * <h2>[프로그래머스] 81303 - 표 편집</h2>
 *
 * <h3>문제 정보</h3>
 * <ul>
 *   <li>난이도: 레벨3</li>
 *   <li>분류: 구현, 연결 리스트</li>
 *   <li>링크: <a href="https://school.programmers.co.kr/learn/courses/30/lessons/81303">프로그래머스 81303</a></li>
 * </ul>
 *
 * <h3>문제 요약</h3>
 * <p>
 * 표의 행을 선택, 삭제, 복구하는 시스템을 구현한다.
 * "U X", "D X", "C", "Z" 명령어를 처리한 뒤 최종 상태를 반환한다.
 * </p>
 *
 * <h3>내 접근 방법</h3>
 * <ul>
 *   <li>N이 최대 1,000,000이고 명령어 수가 200,000이므로 효율적인 이동과 삭제가 필요하다.</li>
 *   <li>배열을 이용한 양방향 연결 리스트(prev, next 배열)를 구축하여 삭제와 복구를 O(1)에 처리한다.</li>
 *   <li>삭제된 노드 정보는 Stack에 저장하여 "Z" 명령어 시 가장 최근 삭제된 노드부터 복구한다.</li>
 * </ul>
 * <p>
 * [특이사항]
 *
 */
public class PG81303_표_편집 {

    public static void main(String[] args) throws IOException {
        Solution solution = new Solution();
        String[] cmd = new String[]{"D 2","C","U 3","C","D 4","C","U 2","Z","Z","U 1","C"};
        System.out.println(solution.solution(8, 2, cmd));
    }

    static class Solution {
        public String solution(int n, int k, String[] cmd) {
            Deque<Integer> deleted = new ArrayDeque<>();
            Deque<Integer> left = new ArrayDeque<>();
            Deque<Integer> right = new ArrayDeque<>();

            boolean[] board = new boolean[n];

            for (int i = 0; i <= k; i++) {
                left.push(i);
            }
            for (int i = n - 1; i > k; i--) {
                right.push(i);
            }


            int row = 0;
            for (String c : cmd) {
                String action = c.substring(0, 1);
                if (action.equals("U") || action.equals("D"))
                    row = Integer.parseInt(c.substring(2));

                switch (action.toUpperCase()) {
                    case "U" -> {
                        for (int i = 0; i < row; i++) {
                            right.push(left.pop());
                        }
                    }
                    case "D" -> {
                        for (int i = 0; i < row; i++) {
                            left.push(right.pop());
                        }
                    }
                    case "C" -> {
                        int idx = left.pop();
                        deleted.push(idx);
                        board[idx] = true;
                        if (!right.isEmpty())
                            left.push(right.pop());
                    }
                    case "Z" -> {
                        int idx = deleted.pop();
                        board[idx] = false;
                        int t = 0;
                        while (true) {
                            if (!right.isEmpty() && idx > left.peek() && idx > right.peek() ) {
                                left.push(right.pop());
                                t++;
                            } else if (!left.isEmpty() && idx < left.peek() && idx < right.peek()) {
                                right.push(left.pop());
                                t--;
                            } else
                                break;
                        }

                        left.push(idx);

                        for (int i = 0; i < Math.abs(t); i++) {
                            if (t > 0)
                                right.push(left.pop());
                            else
                                left.push(right.pop());
                        }
                        if (t > 0)
                            right.push(left.pop());
                    }
                }

            }

            StringBuilder sb = new StringBuilder();
            for (boolean f : board)
                sb.append(f? "X" : "O");
            return sb.toString();
        }
    }

}