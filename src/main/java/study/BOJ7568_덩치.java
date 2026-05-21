package study;

import java.io.*;
import java.util.*;

/**
 * <h2>[백준] 7568 - 덩치</h2>
 *
 * <h3>문제 정보</h3>
 * <ul>
 *   <li>난이도: 실버 5</li>
 *   <li>분류: 브루트포스</li>
 *   <li>링크: <a href="https://www.acmicpc.net/problem/7568">BOJ 7568</a></li>
 * </ul>
 *
 * <h3>문제 요약</h3>
 * <p>N명의 (몸무게, 키)를 비교해 각자의 덩치 등수를 구한다.
 * 자신보다 몸무게 <b>AND</b> 키가 모두 큰 사람의 수 + 1이 등수다.</p>
 *
 * <h3>접근 방법</h3>
 * <ul>
 *   <li>모든 쌍을 O(N²)으로 전수 비교</li>
 *   <li>처음엔 정렬 후 <code>break</code>로 최적화 시도 → 틀림 (아래 특이사항 참고)</li>
 * </ul>
 *
 * <h3>시간복잡도</h3>
 * <p><b>O(N²)</b> - 모든 쌍 비교</p>
 *
 * <h3>특이사항</h3>
 * <p>덩치 비교는 두 값이 <b>동시에</b> 커야 성립한다. 키 기준으로 정렬해도
 * 몸무게가 뒤섞여 있으므로 중간에 <code>break</code>할 수 없다.
 * 어떤 정렬 기준도 "나머지 항목은 볼 필요 없다"를 보장하지 않는다.</p>
 */
public class BOJ7568_덩치 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        int[][] peoples = new int[n][];

        for (int i = 0; i < n; i++) {
            peoples[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        br.close();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int c = 1;
            int[] now = peoples[i];
            for (int j = 0; j < n; j++) {
                if (peoples[j][0] > now[0] && peoples[j][1] > now[1])
                    c++;
            }
            sb.append(c).append(" ");
        }

        System.out.println(sb.substring(0, sb.length() - 1));

    }
}