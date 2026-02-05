package study;


import java.io.*;
import java.util.*;

/**
 * [백준] 1244 - 스위치 켜고 끄기
 *
 * [문제 정보]
 * - 난이도: 실버 4
 * - 분류:
 * - 링크: https://www.acmicpc.net/problem/1244
 *
 * [문제 요약]
 * 남 : 자신이 받은 수의 배수인 스위치를 반전
 * 여 : 자신의 번호를 중앙으로 한 수열에서 가장 많은 스위치를 포함하는 구간을 찾아서 모두 반전
 *
 * [접근 방법]
 * 남 : 그냥 바꾼다
 * 여 : 투포인터
 *
 * [시간복잡도]
 *
 *
 * [특이사항]
 * 스위치 개수는 100 이하
 * 출력은 20개 단위
 * 1-base로 들어옴
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