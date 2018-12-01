package cn.edu.ustc.nsrl.f;

import java.util.Scanner;

public class Demo3 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		String[] strList = str.split(" ");
		int N = Integer.parseInt(strList[0]);
		int K = Integer.parseInt(strList[1]);
		int[][] survey = new int[N][K + 1];
		for (int i = 0; i < N; i++) {
			String str2 = sc.nextLine();
			String[] str2List = str2.split(" ");
			for (int j = 0; j <= K; j++) {
				survey[i][j] = Integer.parseInt(str2List[j]);
			}			
		}
		sc.close();
		int result = 0;
		for (int i = 0; i < K;i ++) {
			result = myMethod(survey, result, K - i);
		}
		if (result == 0) {			
			System.out.println("otaku");			
		} else {
			System.out.println(result);			
		}
	}
	
	static int myMethod(int[][] arr, int shoulei, int gonglei) {
		int result = 0;
		int shouleivote = 0;
		int gongleivote = 0;
		for (int i = 0; i < arr.length; i++) {
			int temp1 = getIndex(arr[i], shoulei);
			int temp2 = getIndex(arr[i], gonglei);
			if (temp1 < temp2) {
				shouleivote++;
			} else {
				gongleivote++;
			}
		}
		if (gongleivote > shouleivote) {
			result = gonglei;
		} else {			
			result = shoulei;
		}
		return result;
	}
	
	static int getIndex(int[] arr, int a) {
		int result = -1;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == a) {
				result = i;
				break;
			}
		}
		return result;
	}
}
