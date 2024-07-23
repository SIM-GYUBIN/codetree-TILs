import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, RESULT;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        RESULT = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //logic
        //마지막에 +1

        dfs(new boolean[N], -1,0);

        System.out.println(RESULT);

    }

    private static void dfs(boolean[] daySwitch, int numIdx, int depth) {
        if (depth == N/2) {
            int[] morning = new int[N/2];
            int[] night = new int[N/2];
            int mIdx = 0;
            int nIdx = 0;
            for (int i = 0; i < N; i++) {
                if(daySwitch[i]) {
                    morning[mIdx++] = i;
                } else {
                    night[nIdx++] = i;
                }
            }

            int abs = Math.abs(calcWork(morning) - calcWork(night));
            RESULT = Math.min(RESULT, abs);
            return;
        }

        for (int i = numIdx+1; i < N; i++) {
            daySwitch[i] = true;
            dfs(daySwitch, i, depth+1);
            daySwitch[i] = false;
        }
    }

    private static int calcWork(int[] idxArr) {
        int sum = 0;
        for (int i = 0; i < N/2; i++) {
            for (int j = i+1; j < N/2; j++) {
                sum += arr[idxArr[i]][idxArr[j]];
                sum += arr[idxArr[j]][idxArr[i]];
            }
        }

        return sum;
    }
}