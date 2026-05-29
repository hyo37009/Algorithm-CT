package study.programmers;

/**
 * [프로그래머스] 42579 - 베스트 앨범
 * <p>
 * [문제 정보]
 * - 난이도: 레벨 3
 * - 분류: 해시, 정렬
 * - 링크: https://school.programmers.co.kr/learn/courses/30/lessons/42579
 * <p>
 * [문제 요약]
 * - 장르별 총 재생 횟수가 높은 장르부터 앨범에 수록한다.
 * - 같은 장르 안에서는 재생 횟수가 높은 노래를 먼저 수록한다.
 * - 같은 장르 안에서 재생 횟수가 같으면 고유 번호가 낮은 노래를 먼저 수록한다.
 * - 장르별 최대 2곡만 선택하고, 장르에 곡이 1개뿐이면 1곡만 선택한다.
 * <p>
 * [접근 방법]
 * 1. 장르별 총 재생 횟수가 많은 대로 정렬
 * 2. 각 장르당 재생 횟수가 높은 2개의 인덱스를 가져오기
 * <p>
 * [시간복잡도]
 * - 장르별 노래를 모으고 총 재생 횟수를 계산: O(N)
 * - 장르 정렬: O(G log G), G는 장르 수
 * - 각 장르별 노래 정렬: 전체적으로 O(N log N) 이하
 * - 전체 시간복잡도: O(N log N)
 * <p>
 * [특이사항]
 * - 모든 장르는 총 재생 횟수가 다르므로 장르 총합이 같은 경우는 고려하지 않아도 된다.
 * - 장르 내부에서는 재생 횟수 내림차순, 재생 횟수가 같으면 고유 번호 오름차순으로 정렬해야 한다.
 * <p>
 * [검증된 예시 케이스]
 * - 아래 정답은 현재 풀이 코드가 아니라 별도 기준 구현으로 검증했다.
 * <p>
 * 1) 기본 예시
 * genres = ["classic", "pop", "classic", "classic", "pop"]
 * plays  = [500, 600, 150, 800, 2500]
 * answer = [4, 1, 3, 0]
 * <p>
 * 2) 장르에 곡이 1개뿐인 경우
 * genres = ["classic"]
 * plays  = [100]
 * answer = [0]
 * <p>
 * 3) 장르 내부 재생 횟수가 같은 경우
 * genres = ["classic", "classic", "pop", "pop"]
 * plays  = [500, 500, 600, 100]
 * answer = [0, 1, 2, 3]
 * <p>
 * 4) 여러 장르 정렬과 장르별 2곡 선택
 * genres = ["jazz", "classic", "jazz", "pop", "classic", "pop"]
 * plays  = [300, 500, 800, 600, 150, 2500]
 * answer = [5, 3, 2, 0, 1, 4]
 * <p>
 * 5) 장르 총합 정렬 확인
 * genres = ["a", "b", "a", "b", "c"]
 * plays  = [100, 300, 100, 200, 1000]
 * answer = [4, 1, 3, 0, 2]
 */

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class PG42579_베스트_앨범 {
    public static void main(String[] args) throws IOException {
        Solution solution = new Solution();

        String[] genres = new String[]{"classic", "pop", "classic", "classic", "pop"};
        int[] plays = new int[]{500, 600, 150, 800, 2500};


        int[] result = solution.solution(genres, plays);
        System.out.println(Arrays.toString(result));
    }

    static class Solution {
        public int[] solution(String[] genres, int[] plays) {
            Map<String, Genre> genreMap = new HashMap<>();

            for (int i = 0; i < genres.length; i++) {
                Song song = new Song(i, plays[i]);
                genreMap.computeIfAbsent(genres[i], Genre::new)
                        .addSong(song);
            }
            genreMap.values().forEach(g -> g.songs.sort(Song::compareTo));

            return genreMap.entrySet().stream()
                    .sorted(Map.Entry.comparingByValue())
                    .flatMap(e -> {
                        List<Song> songs = e.getValue().songs;
                        return songs.subList(0, Math.min(2, songs.size())).stream();
                    })
                    .map(s -> s.index)
                    .mapToInt(Integer::intValue)
                    .toArray();
        }

        class Genre implements Comparable<Genre> {
            public String name;
            public int totalPlayCounts = 0;
            public List<Song> songs = new ArrayList<>();

            public Genre(String name) {
                this.name = name;
            }

            public void addSong(Song song) {
                this.totalPlayCounts += song.playCount;
                songs.add(song);
            }

            @Override
            public int compareTo(Genre o) {
                return Integer.compare(o.totalPlayCounts, this.totalPlayCounts);
            }
        }
    }

    static class Song implements Comparable<Song> {
        public int index;
        public int playCount;

        public Song(int index, int playCount) {
            this.index = index;
            this.playCount = playCount;
        }

        @Override
        public int compareTo(Song song) {
            if (song.playCount != this.playCount) return Integer.compare(song.playCount, this.playCount);
            else return Integer.compare(this.index, song.index);
        }
    }
}

