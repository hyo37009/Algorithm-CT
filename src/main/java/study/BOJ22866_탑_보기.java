package study;

import java.io.*;
import java.util.*;

/**
 * [백준] 22866 - 탑 보기
 *
 * [문제 정보]
 * - 난이도: 골드 3
 * - 분류:
 * - 링크: https://www.acmicpc.net/problem/22866
 *
 * [문제 요약]
 *
 *
 * [접근 방법]
 *
 *
 * [시간복잡도]
 *
 *
 * [특이사항]
 *
 */
public class BOJ22866_탑_보기 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        int[] buildings = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            int canSee = 0;
            int l = i - 1; // 지금 보는 건물의 인덱스
            int lTopHeight = buildings[i]; // 지금까지 가장 큰 높이
            int lp = i; // 가장 크고 가까운 건물의 인덱스

            int r = i + 1;
            int rTopHeight = buildings[i];
            int rp = i;

            while (l >= 0){
                if(lTopHeight < buildings[l]){ // 가장 큰 건물보다 더 큰 건물이 나오면
                    lTopHeight = buildings[l]; // 교체해줌
                    if (lp == i)
                        lp = l;
                    canSee++;
                }
                l--;
            }

            while (r < n){
                if(rTopHeight < buildings[r]){
                    rTopHeight = buildings[r];
                    if (rp == i)
                        rp = r;
                    canSee++;
                }
                r++;
            }
            sb.append(canSee);

            if (canSee > 0) {
                int ll = lp == i? Integer.MAX_VALUE: i - lp;
                int rr = rp == i? Integer.MAX_VALUE: rp - i;
                sb.append(" ").append(ll <= rr? lp + 1 : rp + 1);
            }
            if(i < n - 1)
                sb.append("\n");
        }
        System.out.println(sb);

    }
}
