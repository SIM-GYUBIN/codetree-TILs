import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    static int N, K;
    static int[] safeArr;
    static boolean[] manLocArr;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        safeArr = new int[2*N+1];
        manLocArr = new boolean[2*N+1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= 2*N; i++) {
            safeArr[i] = Integer.parseInt(st.nextToken());
        }

        int turn = 0;
        int firstLoc = 1;
        int endLoc = N;
        int zeroSafe = 0;
        while (zeroSafe < K) {
            turn++;
            //1. 무빙워크 한 칸 이동
            firstLoc = before(firstLoc);
            endLoc = before(endLoc);

            if (turn==338){
                System.out.println();
            }
            //2. 무빙워크에 올라가있는 사람들 앞으로 한칸 이동
            // 앞 칸에 사람있거나 안정성 0이면 제자리
            for (int i = endLoc; i != before(firstLoc); i = before(i)) {
                if(manLocArr[i]) {
                    //N칸이면 내림 , 움직여서 N칸 돼도 내림
                    if (i == endLoc) {
                        manLocArr[i] = false;
                    } else if (!manLocArr[next(i)] && safeArr[next(i)] != 0) { //앞칸에 사람 없고, 안정성 0 아니면 가
                        manLocArr[i] = false;
                        manLocArr[next(i)] = true;
                        safeArr[next(i)]--;
                        if (safeArr[next(i)] == 0) {
                            zeroSafe++;
                        }

                        if (next(i) == endLoc) { //이동한 자리가 N칸이면 내림
                            manLocArr[next(i)] = false;
                        }
                    }
                }
            }


            //3. 사람 태우기 (0이 아니라면
            if (safeArr[firstLoc] != 0 && !manLocArr[firstLoc]) {
                manLocArr[firstLoc] = true;
                safeArr[firstLoc]--;
                if (safeArr[firstLoc] == 0) {
                    zeroSafe++;
                }
            }
        }
        System.out.println(turn);

    }

    private static int before(int idx) {
        return idx == 1 ? 2*N : idx - 1;
    }

    private static int next(int idx) {
        return idx == 2*N ? 1 : idx + 1;
    }
}