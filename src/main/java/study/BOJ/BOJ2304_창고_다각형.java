package study.BOJ;

import java.io.*;
import java.util.*;

/**
 * [백준] 2304 - 창고 다각형
 * <p>
 * [문제 정보]
 * - 난이도: 실버 1
 * - 분류: 투포인터
 * - 링크: https://www.acmicpc.net/problem/2304
 * <p>
 * [문제 요약]
 * <p>
 * <p>
 * [접근 방법]
 * 왼쪽 포인터의 최고 높이가 오른쪽 포인터의 최고 높이보다 작다면
 * 왼쪽 포인터의 최고 높이가 오른쪽 포인터의 최고 높이보다 커질 때까지 계산
 * 오른쪽 포인터가 왼쪽 포인터의 최고 높이보다 커질 때까지 계산
 * 두 포인터가 같은 기둥을 가리킨다면 종료
 * 근데 오른쪽을 오른쪽 기준으로 세지 않게 조심
 * <p>
 * [시간복잡도]
 * <p>
 * <p>
 * [특이사항]
 *
 */
public class BOJ2304_창고_다각형 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int count = Integer.parseInt(br.readLine());
        Map<Integer, Integer> pillars = new HashMap<>();
        int l = 1000;
        int r = 0;

        for (int i = 0; i < count; i++) {
            String[] input = br.readLine().split(" ");
            Integer n = Integer.valueOf(input[0]);
            pillars.put(n, Integer.valueOf(input[1]));
            if (n < l) {
                l = n;
            }
            if (n > r) {
                r = n;
            }
        }

        int answer = 0;
        int lMaxHeight = pillars.get(l);
        int rMaxHeight = pillars.get(r);
        // 두 기둥 만나는 지점은 마지막에 더함
        while (l < r) {
            if (lMaxHeight < rMaxHeight) {
                if (!pillars.containsKey(l) || lMaxHeight >= pillars.get(l)) {
//                    System.out.println("*".repeat(lMaxHeight));
                    answer += lMaxHeight;
                    l++;
                    continue;
                }
                lMaxHeight = pillars.get(l);
            } else {
                if (!pillars.containsKey(r) || rMaxHeight >= pillars.get(r)) {
//                    System.out.println("&".repeat(lMaxHeight));
                    answer += rMaxHeight;
                    r--;
                    continue;
                }
                rMaxHeight = pillars.get(r);
            }
        }
        answer += pillars.get(l);
        br.close();
        System.out.println(answer);
    }
}