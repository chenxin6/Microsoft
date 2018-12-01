package cn.edu.ustc.nsrl.c;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Demo6 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str1 = sc.nextLine();
		int N = Integer.parseInt(str1);
		int boss = 0;
		int[][] arr = new int[N][4];
		int maxNeedMessage = 0;
//		值是他的子节点
		HashMap<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>();
		for (int i = 0; i < N; i++) {
			String str2 = sc.nextLine();
			String[] str2List = str2.split(" ");
			arr[i][0] = Integer.parseInt(str2List[0]);
			arr[i][1] = Integer.parseInt(str2List[1]);
			arr[i][2] = Integer.parseInt(str2List[2]);
			arr[i][3] = Integer.parseInt(str2List[3]);
			if (maxNeedMessage < arr[i][1]) {
				maxNeedMessage = arr[i][1];
			}
			if (arr[i][0] == 0) {
				boss = i;
			} else {
				if (map.get(arr[i][0] - 1) == null) {
					map.put(arr[i][0] - 1, new ArrayList<Integer>());
				}
				map.get(arr[i][0] - 1).add(i);
			}
		}
		sc.close();
		int [][] dp = new int[N][maxNeedMessage + 1];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j <= maxNeedMessage; j++) {
				dp[i][j] = -1;
			}
		}
		int result = 0;
		result = myMethod(arr, map, boss, arr[boss][1], dp);
		if (result == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {			
			System.out.println(result);
		}
	}
	
	static int myMethod(int[][] arr, HashMap<Integer, ArrayList<Integer>> map, int target, int messageNeed, int[][] dp) {
		int result = Integer.MAX_VALUE;
		if (messageNeed <= 0) {
			return arr[target][3];
		}
		if (dp[target][messageNeed] != -1) {
			return dp[target][messageNeed];
		}
		int sunTotalMessage = 0;
//		浅复制子节点列表
		ArrayList<Integer> list = map.get(target);
//		深复制子节点列表
		ArrayList<Integer> realList = new ArrayList<Integer>();
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {				
				sunTotalMessage += arr[list.get(i)][2];
				realList.add(list.get(i));
			}
		}
		if (sunTotalMessage < messageNeed) {
			return Integer.MAX_VALUE;
		}
//		能运行到这里说明list一定不为null
//		messageNeed是需要的情报信息
		for (Integer integer : realList) {
			long temp = 0;
			int weizhi = list.indexOf(integer);
			list.remove(weizhi);
			temp = (long) myMethod(arr, map, integer, arr[integer][1], dp) + myMethod(arr, map, target, messageNeed - arr[integer][2], dp);
			if (temp < result) {
				result = (int) temp;
			}
			list.add(weizhi, integer);
		}
		dp[target][messageNeed] = result;
		return dp[target][messageNeed];
	}

}
