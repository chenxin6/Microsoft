package cn.edu.ustc.nsrl.f;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Demo2 {
	
	static HashMap<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>();
	public static void main(String[] args) {
		init();
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		int caseNumber = Integer.parseInt(str);
		for (int i = 0; i < caseNumber; i++) {
			String str2 = sc.nextLine();
			String[][] dp = new String[str2.length()][10];
			String result = myMethod2(str2, 0, 1, dp, false);
			System.out.println(result);
		}
		sc.close();
	}
	
	static String myMethod2(String str, int index, int thumb, String[][] dp, boolean jiewei) {
		String result = "";
		if (index == str.length()) {
			return "";
		}
		if (thumb == 0) {
			for (int i = index; i < str.length(); i++) {
				result += '0';				
			}
			return result;
		}
		if (jiewei) {
			for (int i = index; i < str.length(); i++) {
				result += '9';				
			}
			return result;
		}
//		走到这里说明jiewei肯定是不借位
		if (str.charAt(index) == '0') {
			if (thumb != 3 && thumb != 6 && thumb != 9) {
				for (int i = index; i < str.length(); i++) {
					result += '0';				
				}
			}
			return result;
		}
		if (dp[index][thumb] != null) {
			return dp[index][thumb];
		}
		if (str.charAt(index) - '0' == thumb) {
			result = thumb + "" + myMethod2(str, index + 1, thumb, dp, false);
		} else {
			ArrayList<Integer> list = map.get(thumb);
			for (Integer integer : list) {
				if (integer <= str.charAt(index) - '0') {
					String temp = "";
					if (integer == str.charAt(index) - '0') {
						temp = integer + "" + myMethod2(str, index + 1, integer, dp, false);						
					} else {						
						temp = integer + "" + myMethod2(str, index + 1, integer, dp, true);						
					}
					if (bidaxiao(temp, result)) {
						result = temp;
					}
				}
			}
		}
		dp[index][thumb] = result;
		return result;
	}
	
	static boolean bidaxiao(String str1, String str2) {
		boolean result = true;
		if (str1.length() > str2.length()) {
			result = true;
		} else if (str1.length() < str2.length()) {
			result = false;
		} else {
			for (int i = 0; i < str1.length(); i++) {
				int temp = str1.charAt(i) - str2.charAt(i);
				if (temp > 0) {
					result = true;
					break;
				} else if (temp < 0) {
					result = false;
					break;
				}
			}
		}
		return result;
	}
	
	static void init() {
		map.put(0,  new ArrayList<Integer>());
		map.get(0).add(0);
		map.put(1,  new ArrayList<Integer>());
		map.get(1).add(0);
		map.get(1).add(1);
		map.get(1).add(2);
		map.get(1).add(3);
		map.get(1).add(4);
		map.get(1).add(5);
		map.get(1).add(6);
		map.get(1).add(7);
		map.get(1).add(8);
		map.get(1).add(9);
		map.put(2,  new ArrayList<Integer>());
		map.get(2).add(2);
		map.get(2).add(5);
		map.get(2).add(8);
		map.get(2).add(0);
		map.get(2).add(3);
		map.get(2).add(6);
		map.get(2).add(9);
		map.put(3,  new ArrayList<Integer>());
		map.get(3).add(3);
		map.get(3).add(6);
		map.get(3).add(9);
		map.put(4,  new ArrayList<Integer>());
		map.get(4).add(4);
		map.get(4).add(5);
		map.get(4).add(6);
		map.get(4).add(7);
		map.get(4).add(8);
		map.get(4).add(9);
		map.get(4).add(0);
		map.put(5,  new ArrayList<Integer>());
		map.get(5).add(5);
		map.get(5).add(6);
		map.get(5).add(8);
		map.get(5).add(9);
		map.get(5).add(0);
		map.put(6,  new ArrayList<Integer>());
		map.get(6).add(6);
		map.get(6).add(9);
		map.put(7,  new ArrayList<Integer>());
		map.get(7).add(7);
		map.get(7).add(8);
		map.get(7).add(9);
		map.get(7).add(0);
		map.put(8,  new ArrayList<Integer>());
		map.get(8).add(8);
		map.get(8).add(0);
		map.get(8).add(9);
		map.put(9,  new ArrayList<Integer>());
		map.get(9).add(9);
	}
}
