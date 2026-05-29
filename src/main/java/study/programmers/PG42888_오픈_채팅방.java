package study.programmers;

/**
 * [프로그래머스] 42888 - 오픈 채팅방
 *
 * [문제 정보]
 * - 난이도: 레벨 2
 * - 분류: 해시, 문자열, 구현
 * - 링크: https://school.programmers.co.kr/learn/courses/30/lessons/42888
 *
 * [문제 요약]
 * - Enter는 입장 메시지를 남기고 닉네임을 등록/갱신한다.
 * - Leave는 퇴장 메시지를 남기지만 닉네임 정보는 따로 주어지지 않는다.
 * - Change는 닉네임만 변경하고 출력 메시지를 남기지 않는다.
 * - 모든 메시지에는 해당 유저의 최종 닉네임이 반영되어야 한다.
 *
 * [접근 방법]
 * - 첫 번째 순회에서 Enter, Change 기록을 보고 uid별 최종 닉네임을 HashMap에 저장한다.
 * - 두 번째 순회에서 Enter, Leave 기록만 메시지로 변환한다.
 * - Leave 기록에는 닉네임이 없으므로 uid를 기준으로 첫 번째 순회에서 만든 최종 닉네임을 조회한다.
 * - Change는 출력 대상이 아니므로 두 번째 순회에서는 무시한다.
 *
 * [시간복잡도]
 * - record를 두 번 순회하므로 O(record.length)
 * - 각 record의 split 비용은 문자열 길이가 제한되어 있어 상수처럼 볼 수 있다.
 * - 공간복잡도는 uid별 닉네임 Map과 출력 메시지 List 때문에 O(record.length)
 *
 * [특이사항]
 * - result.toArray(new String[0])은 List<String>을 String[]로 변환하기 위한 타입 정보를 넘기는 코드다.
 * - result.toArray()만 호출하면 Object[]가 반환되므로 String[]로 바로 반환할 수 없다.
 */

import java.io.*;
import java.util.*;

public class PG42888_오픈_채팅방 {
    public static void main(String[] args) throws IOException {
        Solution solution = new Solution();

        String[] record = new String[]{"Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan"};

        String[] result = solution.solution(record);
        System.out.println(Arrays.toString(result));
    }

    static class Solution {
        public String[] solution(String[] record) {
            List<String> result = new ArrayList<>();
            Map<String, String> nickname = new HashMap<>();
            for (int i = 0; i < record.length; i++) {
                String[] split = record[i].split(" ");
                if (split[0].equals("Enter") || split[0].equals("Change"))
                    nickname.put(split[1], split[2]);
            }

            for (int i = 0; i < record.length; i++) {
                String[] split = record[i].split(" ");
                if (split[0].equals("Enter"))
                    result.add(nickname.get(split[1]) + "님이 들어왔습니다.");
                else if (split[0].equals("Leave"))
                    result.add(nickname.get(split[1]) + "님이 나갔습니다.");
            }

            return result.toArray(new String[]{});
        }
    }
}
