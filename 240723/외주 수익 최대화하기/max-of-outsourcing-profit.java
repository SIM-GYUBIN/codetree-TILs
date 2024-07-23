import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, maxCost;
    static Work[] works;

    static class Work {
        int dur;
        int cost;

        public Work(int dur, int cost) {
            this.dur = dur;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        works = new Work[N+1];
        works[0] = new Work(0, 0);

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            works[i] = new Work(end, cost);
        }

        maxCost = 0;
        calWork(0, 0);


        System.out.println(maxCost);
    }

    private static void calWork(int end, int costSum) {
        if (end >= N) {
            maxCost = Math.max(costSum, maxCost);
            return;
        }


        for (int start = end+1; start <= N; start++) {
            int endTime = start + works[start].dur - 1;
            int earnCost = costSum + works[start].cost;
            if (start + works[start].dur - 1 > N) {
                calWork(N, costSum);
//                continue;
            } else {
                calWork(endTime, earnCost);
            }
        }
    }
}