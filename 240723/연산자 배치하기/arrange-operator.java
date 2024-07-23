import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, maxResult, minResult;
    static int[] nums;
    static int[] operatorList = new int[3];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        nums = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        st= new StringTokenizer(br.readLine());
        //연산자: 0 더하기, 1 빼기, 2 곱하기
        for (int i = 0; i < 3; i++) {
            operatorList[i] = Integer.parseInt(st.nextToken());
        }

        int[] operators = new int[N-1];
        maxResult = Integer.MIN_VALUE;
        minResult = Integer.MAX_VALUE;
        dfs(operators, 0);

        System.out.println(minResult+" "+maxResult);

    }

    private static void dfs(int[] operators, int depth) {
        if (depth == N-1) {
            int calculate = calculate(operators);

            maxResult = Math.max(calculate, maxResult);
            minResult = Math.min(calculate, minResult);
            return;
        }

        for (int i = 0; i < 3; i++) {
            if (operatorList[i] != 0) {
                operators[depth] = i;
                operatorList[i]--;
                dfs(operators, depth + 1);
                operatorList[i]++;
            }
        }
    }

    private static int calculate(int[] operators) {
        int sum = nums[0];
        for (int i = 1; i < N; i++) {
            int operator = operators[i-1];
            if (operator == 0) {
                sum += nums[i];
            } else if (operator==1) {
                sum -= nums[i];
            } else if (operator==2) {
                sum *= nums[i];
            }
        }

        return sum;
    }
}