package cn.edu.ustc.nsrl.e;

import java.util.Scanner;

public class Demo3 {
//	这里加个0是为了让他的下标与斐波那契数的第几个相对应
	static int[] F = {0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 
					89, 144, 233, 377, 610, 987, 1597, 2584, 4181, 6765, 
					10946, 17711, 28657, 46368, 75025};
	static int mod = 1000000007;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
//		第一个参数表示前几个数字，第二个参数代表匹配几个斐波那契数
		int[][] dp = new int[N + 1][25 + 1];
		for(int i = 1; i <= N; i++) {
//	    	i表示第几个数字
	        int t = sc.nextInt();
//	        j代表匹配了几个斐波那契数，最多25个，因为ai<=100000
	        for(int j = 1; j <= 25; j++) {
	            if(t == F[j]) {
	            	if (j == 1) {
	            		dp[i][1] = (1 + dp[i - 1][1]) % mod;
	            	} else {	            		
	            		dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j]) % mod;	                	
	            	}
	            } else {
	            	dp[i][j] = dp[i - 1][j] % mod;	            	
	            }
	        }
	    }
		sc.close();
		int result = 0;
		for(int i = 1; i <= 25; i++) {
			result = (result + dp[N][i]) % mod;
		}
		System.out.println(result);
	}
	
	static int getF(int index) {
		int result = 0;
		if (F[index] != 0) {
			return F[index];
		}
		if (index == 0 || index == 1) {
			F[index] = 1;
			return 1;
		}
		result = getF(index - 1) + getF(index - 2);
		F[index] = result;
		return result;
	}
}

