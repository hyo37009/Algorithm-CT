package study;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

/**
 * <h2>[백준] 13305 - 주유소</h2>
 *
 * <h3>문제 정보</h3>
 * <ul>
 *   <li>난이도: 실버 3</li>
 *   <li>분류: 그리디</li>
 *   <li>링크: <a href="https://www.acmicpc.net/problem/13305">BOJ 13305</a></li>
 * </ul>
 *
 * <h3>문제 요약</h3>
 * <p>일직선 도로 위 N개 도시를 왼쪽→오른쪽으로 이동할 때, 각 도시의 주유 가격이 다를 경우 최소 비용을 구한다.</p>
 *
 * <h3>접근 방법</h3>
 * <ul>
 *   <li>왼쪽부터 순회하며 <b>현재까지의 최소 리터 가격</b>을 유지</li>
 *   <li>각 구간을 최소 가격으로 이동: <code>answer += minPrice * dist[i]</code></li>
 *   <li>더 싼 주유소를 만나면 최소 가격 갱신</li>
 * </ul>
 *
 * <h3>시간복잡도</h3>
 * <p><b>O(N)</b> - 도시를 한 번 순회</p>
 *
 * <h3>특이사항</h3>
 * <ul>
 *   <li>처음에 DFS를 떠올렸으나, 과거 선택이 미래에 영향을 주지 않으므로 그리디가 성립</li>
 *   <li>거리 × 가격이 최대 10^18 → <b><code>int</code> 오버플로우</b>, 반드시 <code>long</code> 사용</li>
 * </ul>
 */
public class BOJ13305_주유소 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        long[] distance = Arrays.stream(br.readLine().split(" "))
                .mapToLong(Long::parseLong)
                .toArray();
        long[] price = Arrays.stream(br.readLine().split(" "))
                .mapToLong(Long::parseLong)
                .toArray();

        long answer = 0;
        long minPrice = price[0];
        for (int i = 0; i < n - 1; i++) {
            minPrice = Math.min(minPrice, price[i]);
            answer += distance[i] * minPrice;
        }

        System.out.println(answer);
    }
}
