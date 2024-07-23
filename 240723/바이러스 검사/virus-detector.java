import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] cust = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            cust[i] = num;
        }

        st = new StringTokenizer(br.readLine());
        int tj = Integer.parseInt(st.nextToken());
        int tw = Integer.parseInt(st.nextToken());

        //logic
        long twNum = 0;

        for (int i = 0; i < N; i++) {
            int rest = cust[i] - tj;
            twNum += 1;

            if (rest > 0) {
                int twCnt = rest / tw;
                if (rest % tw != 0) {
                    twCnt += 1;
                }
                twNum += twCnt;
            }
        }


        System.out.println(twNum);
    }
}