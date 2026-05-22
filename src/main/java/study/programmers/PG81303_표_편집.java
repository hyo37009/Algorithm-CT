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
 *
 * [특이사항]
 *
 */
public class PG81303_표_편집 {

    public static void main(String[] args) throws IOException {
        Solution solution = new Solution();
        String[] cmd = new String[] {"D 2","C","U 3","C","D 4","C","U 2","Z","Z"};
        System.out.println(solution.solution(8, 2, cmd));
    }

    static class Solution {
        public String solution(int n, int k, String[] cmd) {
            boolean[] board = new boolean[n];
            Deque<Integer> deleted = new ArrayDeque<>();
            String answer = "";

            int now = 0;
            int row = 0;
            for (String c : cmd) {
                String action = c.substring(0, 1);
                if (action.equals("U") || action.equals("D"))
                    row = Integer.parseInt(c.substring(2));

                switch (action.toUpperCase()) {
                    case "U" -> now -= row;
                    case "D" -> now += row;
                    case "C" -> {
                        deleted.push(now);
                        board[now] = true;
                        if(now >= n - deleted.size())
                            now--;
                        else
                            now++;
                    }
                    case "Z" -> {
                        int returned = deleted.pop();
                        board[returned] = false;
                        if (returned < now)
                            now++;
                        else
                            now--;
                    }
                }

            }
            StringBuilder sb = new StringBuilder();
            for (boolean b : board) {
                if (b)
                    sb.append("X");
                else
                    sb.append("O");
            }
            answer = sb.toString();

            return answer;
        }


    }
}