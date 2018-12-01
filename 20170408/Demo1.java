package cn.edu.ustc.nsrl.c;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

public class Demo1 {

	public static void main(String[] args) {
		int result = 0;
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		sc.nextLine();
		HashMap<Integer, Integer> heng = new HashMap<Integer, Integer>();
		HashMap<Integer, Integer> shu = new HashMap<Integer, Integer>();
		HashMap<Integer, Integer> zhengxie = new HashMap<Integer, Integer>();
		HashMap<Integer, Integer> fanxie = new HashMap<Integer, Integer>();
		for (int i = 0; i < N; i++) {
			String str1 = sc.nextLine();
			String[] str1List = str1.split(" ");
			int x = Integer.parseInt(str1List[0]);
			int y = Integer.parseInt(str1List[1]);
				
			int k = y - x;
			int k2 = x + y;
			
			if (heng.get(y) == null) {
				heng.put(y, 1);
			} else {
				int temp = heng.get(y) + 1;
				heng.put(y, temp);
			}
			
			if (shu.get(x) == null) {
				shu.put(x, 1);
			} else {
				int temp = shu.get(x) + 1;
				shu.put(x, temp);
			}
			
			if (zhengxie.get(k) == null) {
				zhengxie.put(k, 1);
			} else {
				int temp = zhengxie.get(k) + 1;
				zhengxie.put(k, temp);
			}
			
			if (fanxie.get(k2) == null) {
				fanxie.put(k2, 1);
			} else {
				int temp = fanxie.get(k2) + 1;
				fanxie.put(k2, temp);
			}
		}
		sc.close();
		
		result += myMethod2(heng);
		result += myMethod2(shu);
		result += myMethod2(zhengxie);
		result += myMethod2(fanxie);
		
		System.out.println(result);
	}
	
	static int myMethod2(HashMap<Integer, Integer> map) {
		int result = 0;
		Collection<Integer> c = map.values();
		Iterator<Integer> it = c.iterator();
		while (it.hasNext()) {
			int temp = it.next();
			if (temp > 1) {
				result += temp*(temp - 1) / 2;				
			}
		}
		return result;
	}
	
	static boolean myMethod(String str1, String str2) {
		boolean result = false;
		int x1 = 0;
		int y1 = 0;
		int x2 = 0;
		int y2 = 0;
		String[] str1List = str1.split(" ");
		String[] str2List = str2.split(" ");
		x1 = Integer.parseInt(str1List[0]);
		y1 = Integer.parseInt(str1List[1]);
		x2 = Integer.parseInt(str2List[0]);
		y2 = Integer.parseInt(str2List[1]);
		
		if (x1 == x2 || y1 == y2) {
			return true;
		}
		
		if (Math.abs(y2 - y1) == Math.abs(x2 - x1)) {
			result = true;
		}
		
		return result;
	}
}
