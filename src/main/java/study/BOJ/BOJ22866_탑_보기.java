package study.BOJ;

import java.io.*;
import java.util.*;

/**
 * <h2>[백준] 22866 - 탑 보기</h2>
 *
 * <h3>문제 정보</h3>
 * <ul>
 *   <li>난이도: 골드 3</li>
 *   <li>분류: 스택</li>
 *   <li>링크: <a href="https://www.acmicpc.net/problem/22866">BOJ 22866</a></li>
 * </ul>
 *
 * <h3>문제 요약</h3>
 * <p>N개의 건물에서 각 옥상에서 양쪽으로 자기보다 높은 건물만 볼 수 있을 때,
 * 보이는 건물 수와 가장 가까운 건물 번호를 구한다.</p>
 *
 * <h3>접근 방법</h3>
 * <ul>
 *   <li>각 건물에서 왼쪽/오른쪽 끝까지 순회하며 최대 높이 갱신으로 카운트 (브루트포스)</li>
 *   <li>포인터 변수가 있지만 매 반복마다 초기화되므로 투 포인터가 아님</li>
 *   <li>단조 감소 스택으로 O(n) 풀이가 가능 → 미구현</li>
 * </ul>
 *
 * <h3>시간복잡도</h3>
 * <p><b>O(n²)</b> - 각 건물마다 양쪽 끝까지 순회. 시간초과</p>
 *
 * <h3>특이사항</h3>
 * <ul>
 *   <li>포인터 변수(l, r)가 있어도 매번 초기화되면 브루트포스다</li>
 *   <li>"각 위치에서 보이는 원소 수" 유형 → 단조 스택 패턴</li>
 * </ul>
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
