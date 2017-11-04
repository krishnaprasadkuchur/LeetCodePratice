package LeetCodeEasy;

//Problem 70: You are climbing a stair case. It takes n steps to reach to the top.
//
//        Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
//
//        Note: Given n will be a positive integer.

public class ClimbingStairs70 {

    public static void main(String[] args){
        int steps = 5;
        System.out.println("distinct ways to climb "+steps+" is: "+climbingStairsDP(steps));


    }

    public static int climbingStairs(int n){
        int[] memostore = new int[n+1];
        return climbingStairswithMemo(0, n, memostore);

    }

    // Without DP. and memoization
    public static int climbStairswithouDp(int n) {

        if (n == 1){
            return 1;
        }
        else if(n == 2){
            return 2;
        }
        else return climbStairswithouDp(n-1) + climbStairswithouDp(n-2);

    }

    public static int climbingStairswithMemo(int i, int n, int[] memo){
        System.out.println("Called method for:"+i+" and "+n);
        if(i > n){
            return 0;
        }

        if(i == n){
            return 1;
        }

        if(memo[i] > 0){
            return memo[i];
        }
        memo[i] = climbingStairswithMemo(i+1,n, memo) + climbingStairswithMemo(i+2, n , memo);
        return memo[i];

    }


    // O(n) time and space complexity
    public static  int climbingStairsDP(int n){

        if(n == 0 || n == 1 || n == 2) return n;
        int[] dp = new int[n+1];
        dp[1] = 1;
        dp[2] = 2;

        for(int i=3; i<=n; i++){
            dp[i] = dp[i - 1] + dp [i-2];
        }
        return dp[n];

    }
}
