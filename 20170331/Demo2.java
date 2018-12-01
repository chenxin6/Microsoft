package cn.edu.ustc.nsrl.b;

import java.util.ArrayList;
import java.util.Scanner;

public class Demo2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str1 = sc.nextLine();
		String[] str1List = str1.split(" ");
		int N = Integer.parseInt(str1List[0]);
		int M = Integer.parseInt(str1List[1]);
		int[] MList = new int[M];
		int[][] tree = new int[M][];
		int K = Integer.parseInt(str1List[2]);
	
		String str2 = sc.nextLine();
		String[] str2List = str2.split(" ");
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < M; i++) {
			MList[i] = Integer.parseInt(str2List[i]);
			tree[i] = new int[MList[i]];
			String str3 = sc.nextLine();
			String[] str3List = new String[MList[i]];
			str3List = str3.split(" ");
			for (int j = 0; j < MList[i]; j++) {
				tree[i][j] = Integer.parseInt(str3List[j]);
				list.add(Integer.parseInt(str3List[j]));
			}
		}
		
		boolean[] isLeaf = new boolean[N];
		int[] leaves = new int[K]; 
		String str4 = sc.nextLine();
		String[] str4List = str4.split(" ");
		for (int i = 0; i < K; i++) {
			leaves[i] = Integer.parseInt(str4List[i]);
			isLeaf[list.indexOf(Integer.parseInt(str4List[i]))] = true;
		}
		
		int[][] distance = new int[N][N];
		for (int i = 0; i < K; i++) {
			String str5 = sc.nextLine();
			String[] str5List = str5.split(" ");
			for (int j = 0; j < K; j++) {
				distance[list.indexOf(leaves[i])][list.indexOf(leaves[j])] = Integer.parseInt(str5List[j]);
			}	
		}
		sc.close();
		
		int[] result = new int[N];
		result = myMethod(N, M, K, MList, tree, list, isLeaf, leaves, distance);
		for (int i = 0; i < result.length; i++) {
			int temp = list.indexOf(i + 1);
			if (i == result.length - 1) {
				System.out.println(result[temp]);								
			} else {
				System.out.print(result[temp] + " ");				
			}
		}
	}
	
	public static int[] myMethod(int N, int M, int K, int[] MList, int[][] tree, ArrayList<Integer> list, boolean[] isLeaf, int[] leaves, int[][] distance) {
		int[] result = new int[N];
		int index = 0;
		int parentIndex = 0;
		
		for (int curLevel = M - 1; curLevel > 0; curLevel--) {
			for (index = 0; index < MList[curLevel]; index++) {
				while (isLeaf[list.indexOf(tree[curLevel - 1][parentIndex])]) {
					parentIndex++;
				}
				result[list.indexOf(tree[curLevel][index])] = tree[curLevel - 1][parentIndex];
				for (int i = 0; i < N; i++) {
					distance[i][list.indexOf(tree[curLevel - 1][parentIndex])] = distance[i][list.indexOf(tree[curLevel][index])] - 1;
					distance[list.indexOf(tree[curLevel - 1][parentIndex])][i] = distance[list.indexOf(tree[curLevel][index])][i] - 1;
				}
				if (index != MList[curLevel] - 1) {
					if (distance[list.indexOf(tree[curLevel][index])][list.indexOf(tree[curLevel][index + 1])] > 2) {
						parentIndex++;
						while (isLeaf[list.indexOf(tree[curLevel - 1][parentIndex])]) {
							parentIndex++;
						}
					}
				}
			}
			parentIndex = 0;
		}
		return result;
	}
}
