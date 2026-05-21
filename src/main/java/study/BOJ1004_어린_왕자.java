package study;

import java.io.*;
import java.util.*;

/**
 * <h2>[백준] 1004 - 어린 왕자</h2>
 *
 * <h3>문제 정보</h3>
 * <ul>
 *   <li>난이도: 실버 3</li>
 *   <li>분류: 기하학</li>
 *   <li>링크: <a href="https://www.acmicpc.net/problem/1004">BOJ 1004</a></li>
 * </ul>
 *
 * <h3>문제 요약</h3>
 * <p>출발점에서 도착점까지 이동할 때, 행성계(원) 경계를 진입/이탈하는 최소 횟수를 구한다.
 * 행성계는 서로 교차하지 않는다.</p>
 *
 * <h3>접근 방법</h3>
 * <ul>
 *   <li>둘 다 포함하는 행성계는 경계를 넘을 필요 없으므로 무시</li>
 *   <li>출발점만 포함하는 행성계 → 탈출 1회</li>
 *   <li>도착점만 포함하는 행성계 → 진입 1회</li>
 *   <li>각 행성계에 대해 점이 원 안에 있는지 판별 (거리² < 반지름²)</li>
 * </ul>
 *
 * <h3>시간복잡도</h3>
 * <p><b>O(T × n)</b> - 각 테스트케이스마다 행성계 n개를 순회</p>
 *
 * <h3>특이사항</h3>
 * <p>행성계가 교차하지 않으므로, 각 원을 독립적으로 판별하면 된다.</p>
 */
public class BOJ1004_어린_왕자 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int t = Integer.parseInt(br.readLine());
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < t; i++) {
            StringTokenizer input = new StringTokenizer(br.readLine());
            Node prince = new Node(Integer.parseInt(input.nextToken()), Integer.parseInt(input.nextToken()), 0);
            Node rose = new Node(Integer.parseInt(input.nextToken()), Integer.parseInt(input.nextToken()), 0);

            int n = Integer.parseInt(br.readLine());

            Node planet;
            int count = 0;
            for (int j = 0; j < n; j++) {
                input = new StringTokenizer(br.readLine());
                planet = new Node(Integer.parseInt(input.nextToken()), Integer.parseInt(input.nextToken()), Integer.parseInt(input.nextToken()));
                if(planet.isHave(prince)){
                    if(planet.isHave(rose))
                        continue;
                    count++;
                }
                if (planet.isHave(rose))
                    count++;
            }

            answer.append(count);
            if(i < t - 1) {
                answer.append("\n");
            }
        }
        br.close();

        System.out.println(answer);
    }

    static class Node{
            int x;
            int y;
            int r;

        public Node(int x, int y, int r) {
            this.x = x;
            this.y = y;
            this.r = r;
        }

        public boolean isHave(Node other) {
            return (Math.pow(other.x - x, 2) + Math.pow(other.y - y, 2)) < Math.pow(r, 2);
        }
    }
}