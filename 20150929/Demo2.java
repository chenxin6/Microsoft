package cn.edu.ustc.nsrl.e;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Demo2 {
	static int N = 0;
	static int M = 0;
	static HashMap<Integer, ArrayList<Integer>> way = new HashMap<Integer, ArrayList<Integer>>();
	static HashMap<Integer, ArrayList<Integer>> distance = new HashMap<Integer, ArrayList<Integer>>();
	static int[] level = new int[N + 1];
	static int[] sonNumber = new int[N + 1];
	static int[] fatherDis = new int[N + 1];
	static long THD = 0;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		String[] strList = str.split(" ");
		N = Integer.parseInt(strList[0]);
		M = Integer.parseInt(strList[1]);
		level = new int[N + 1];
		sonNumber = new int[N + 1];
		fatherDis = new int[N + 1];
		for (int i = 0; i < N - 1; i++) {
			String str2 = sc.nextLine();
			String[] str2List = str2.split(" ");
			int x = Integer.parseInt(str2List[0]);
			int y = Integer.parseInt(str2List[1]);
			int dis = Integer.parseInt(str2List[2]);
			if (way.get(x) == null) {
				way.put(x, new ArrayList<Integer>());
			}
			way.get(x).add(y);
			if (way.get(y) == null) {
				way.put(y, new ArrayList<Integer>());
			}
			way.get(y).add(x);
			
			if (distance.get(x) == null) {
				distance.put(x, new ArrayList<Integer>());
			}
			distance.get(x).add(dis);
			if (distance.get(y) == null) {
				distance.put(y, new ArrayList<Integer>());
			}
			distance.get(y).add(dis);
		}
		level[1] = 1;
		myMethod(1);
		ArrayList<Long> result = new ArrayList<Long>();
		for (int i = 0; i < M; i++) {
			String str3 = sc.nextLine();
			if (str3.equals("QUERY")) {
				result.add(THD);
			} else {
				String[] tempArr = str3.split(" ");
				int tempx = Integer.parseInt(tempArr[1]);
				int tempy = Integer.parseInt(tempArr[2]);
				int tempdis = Integer.parseInt(tempArr[3]);
				if (level[tempx] < level[tempy]) {
					int temp = tempx;
					tempx = tempy;
					tempy = temp;
				}
				THD += (long) (tempdis - fatherDis[tempx]) * (N - sonNumber[tempx]) * sonNumber[tempx];
				fatherDis[tempx] = tempdis;
			}
		}
		sc.close();
		for (int i = 0; i < result.size(); i++) {
			System.out.println(result.get(i));
		}
	}
	
	static void myMethod(int index) {
		sonNumber[index] = 1;
//		得到与index节点相连的节点的列表，所以我们要筛选出来子节点
		ArrayList<Integer> list = way.get(index);
		for (int xianglian : list) {
//			说明是它的子节点
			if (level[xianglian] == 0) {
				level[xianglian] = level[index] + 1;
				fatherDis[xianglian] = distance.get(index).get(list.indexOf(xianglian));
				myMethod(xianglian);
				sonNumber[index] += sonNumber[xianglian];
				THD += (long) (N - sonNumber[xianglian]) * sonNumber[xianglian] * fatherDis[xianglian];
			}
		}
	}
	
}
