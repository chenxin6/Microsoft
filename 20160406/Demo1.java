package cn.edu.ustc.nsrl.d;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Demo1 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		HashMap<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>();
		int caseNumber = Integer.parseInt(str);
		for (int i = 0; i < caseNumber; i++) {
			String str2 = sc.nextLine();
			String[] str2List = str2.split(" ");
			String str3 = sc.nextLine();
			String[] str3List = str3.split(" ");
			int N = Integer.parseInt(str2List[0]);
			int P = Integer.parseInt(str2List[1]);
			int W = Integer.parseInt(str2List[2]);
			int H = Integer.parseInt(str2List[3]);
			map.put(i, new ArrayList<Integer>());
			map.get(i).add(N);
			map.get(i).add(P);
			map.get(i).add(W);
			map.get(i).add(H);
			for (int j = 0; j < N; j++) {
				map.get(i).add(Integer.parseInt(str3List[j]));
			}
		}
		sc.close();
		int[] arr = new int[caseNumber];
		for (int i = 0; i < caseNumber; i++) {
			arr[i] = myMethod(map.get(i));
			System.out.println(arr[i]);
		}
	}

	static int myMethod(ArrayList<Integer> list) {
		int result = 0;
		int N = list.get(0);
		int P = list.get(1);
		int W = list.get(2);
		int H = list.get(3); 
		int[] arr = new int[N];
		for (int i = 4; i < list.size(); i++) {
			arr[i - 4] = list.get(i);
		}
		while (true) {
			result++;
			int meihangduoshaoge = W / result;
			int duoshaohang = H / result;
			int needHang = 0;
			for (int i = 0; i < arr.length; i++) {
				needHang += Math.ceil((double) arr[i] / meihangduoshaoge);
			}
			if (P * duoshaohang < needHang) {
				break;
			}
		}
		result--;
		return result;
	}
}
