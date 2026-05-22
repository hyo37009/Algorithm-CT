package study.programmers;

import java.io.*;
import java.util.*;

/**
 * [프로그래머스] 76502 - 괄호 회전하기
 * <p>
 * [문제 정보]
 * - 난이도: 레벨 2
 * - 분류: 구현
 * - 링크: https://school.programmers.co.kr/learn/courses/30/lessons/76502
 * <p>
 * [문제 요약]
 * <p>
 * <p>
 * [접근 방법]
 * <p>
 * <p>
 * [시간복잡도]
 * <p>
 * <p>
 * [특이사항]
 * <p>
 * [테스트케이스]
 * - "[](){}" -> 3
 * - "}]()[{" -> 2
 * - "[)(]" -> 0
 * - "}}}" -> 0
 * - "([)]" -> 0
 * - "({)}" -> 0
 * - "()[{}]" -> 2
 * - "{[()]}" -> 1
 * - ")(" -> 1
 * - "(((((" -> 0
 *
 */
public class PG76502_괄호_회전하기 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        Solution solution = new Solution();
        System.out.println(solution.solution("[]({})"));

        bw.flush();
        bw.close();
    }

    static class Solution {
        public int solution(String s) {
            int answer = 0;
            if (s.length() % 2 == 1)
                return answer;
            // 돌려서 넣어줌
            for (int i = 0; i < s.length(); i++) {
                String concat = s.substring(i).concat(s.substring(0,i));
                System.out.println("concat = " + concat);
                if (!isOpen(String.valueOf(s.charAt(i)))) {
                    continue;
                }
                if (getGeualho(concat)) {
                    answer += 1;
                }

            }

            return answer;
        }


        // 올바른 괄호열 판단
        public boolean getGeualho(String s) {

            Deque<String> dq1 = new ArrayDeque<>();
            Deque<String> dq2 = new ArrayDeque<>();
            Deque<String> dq3 = new ArrayDeque<>();

            for (String c : s.split("")) {
                Deque<String> now;
                boolean open = false;
                if (c.equals("(") || c.equals(")"))
                    now = dq1;
                else if (c.equals("{") || c.equals("}"))
                    now = dq2;
                else if (c.equals("[") || c.equals("]"))
                    now = dq3;
                else return false;
                open = isOpen(c);

                if (open)
                    now.push(c);
                else {
                    // 닫는 괄호일 때
                    if (now.isEmpty())
                        return false; // 닫을게 없음
                    String pop = now.pop(); // 앞에 있는거 뭐지
                    if (isOpen(pop)){ // 여는 괄호
                        if (now.equals(dq1))
                            return dq2.isEmpty() && dq3.isEmpty();
                        if (now.equals(dq2))
                            return dq3.isEmpty() && dq1.isEmpty();
                        if (now.equals(dq3))
                            return dq1.isEmpty() && dq2.isEmpty();
                    }
                    return false; // 닫는괄호 - ㄲㅈ
                }

            }
            return dq1.isEmpty() && dq2.isEmpty() && dq3.isEmpty();
        }

        private boolean isOpen(String c) {
            if (c.equals("(") || c.equals("[") || c.equals("{"))
                return true;
            return false;
        }
    }

}
