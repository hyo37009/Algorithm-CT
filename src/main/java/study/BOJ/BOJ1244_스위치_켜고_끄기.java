package study.BOJ;


import java.io.*;

/**
 * <h1>[백준] 1244 - 스위치 켜고 끄기</h1>
 * <p>
 *     남학생과 여학생이 각자의 규칙에 따라 스위치 상태를 변경할 때, 모든 작업 후의 최종 스위치 상태를 출력하는 문제
 * </p>
 *
 * <h2>문제 정보</h2>
 * <ul>
 *     <li><b>난이도:</b> 실버 4</li>
 *     <li><b>분류:</b> 구현, 시뮬레이션</li>
 *     <li><b>링크:</b> <a href="https://www.acmicpc.net/problem/1244" target="_blank">https://www.acmicpc.net/problem/1244</a></li>
 * </ul>
 *
 * <h2>규칙 요약</h2>
 * <ul>
 *     <li><b>남학생:</b> 자신이 받은 수의 배수인 모든 스위치의 상태를 반전</li>
 *     <li><b>여학생:</b> 자신이 받은 번호의 스위치를 중심으로, 좌우가 대칭인 가장 긴 구간을 찾아 그 구간의 모든 스위치 상태를 반전</li>
 * </ul>
 *
 * <h2>접근 방법</h2>
 * <p>
 *     요구사항을 그대로 코드로 옮기는 구현 문제
 * </p>
 * <ul>
 *     <li><b>남학생:</b> <code>for</code> 반복문의 증감식(step)을 학생이 받은 수로 설정하여 배수 위치의 스위치를 조작</li>
 *     <li><b>여학생:</b> <code>while</code> 반복문과 두 개의 포인터(left, right)를 사용하여 중앙에서부터 양쪽으로 확장하며 대칭 여부를 확인</li>
 * </ul>
 *
 * <h2>시간 복잡도</h2>
 * <p>
 *     학생 수를 N, 스위치 개수를 S라고 할 때 <code>O(N * S)</code>
 *     각 학생마다 최악의 경우 스위치 배열 전체를 순회할 수 있기 때문
 * </p>
 *
 * <h2>특이사항</h2>
 * <ul>
 *     <li>스위치 번호는 1부터 시작(1-based indexing)하므로, 0부터 시작하는 배열 인덱스와의 차이를 주의해야 함</li>
 *     <li>입력: 스위치 상태는 개수와 상관없이 한 줄에 모두 주어짐</li>
 *     <li>출력: 최종 스위치 상태는 한 줄에 20개씩 끊어서 출력해야 함</li>
 * </ul>
 *
 * @author hyo37
 * @since 2026-02-06
 */
public class BOJ1244_스위치_켜고_끄기 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int switchNum = Integer.parseInt(br.readLine());
        String[] switches = br.readLine().split(" ");
        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            String[] input = br.readLine().split(" ");
            int sex = Integer.parseInt(input[0]);
            int k = Integer.parseInt(input[1]);
            if (sex == 1) { // 남학생일 때
                for (int j = k - 1; j < switchNum; j += k) {
                    switches[j] = (switches[j].equals("0")) ? "1" : "0";
                }
            } else { // 여학생일 때
                k--;
                switches[k] = (switches[k].equals("0")) ? "1" : "0";
                int l = k - 1;
                int r = k + 1;
                while (l >= 0 && r < (switchNum)) {
                    if(!switches[l].equals(switches[r]))
                        break;
                    switches[l] = (switches[l].equals("0")) ? "1" : "0";
                    switches[r] = (switches[r].equals("0")) ? "1" : "0";
                    l--;
                    r++;
                }
            }

        }
        for (int i = 0; i < switchNum; i++) {
            System.out.printf("%s ", switches[i]);
            if((i + 1) % 20 == 0)
                System.out.println();
        }
        br.close();
    }
}