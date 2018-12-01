package cn.edu.ustc.nsrl.f;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeSet;

class MyCompare<T> implements Comparator<T> {
	@Override
	public int compare(Object o1, Object o2) {
		String str1 = (String) o1;
		String str2 = (String) o2;
		boolean flag = true;
		int min = 0;
		if (str1.length() < str2.length()) {
			flag = false;
			min = str1.length();
		} else {
			flag = true;
			min = str2.length();
		}
		for (int i = 0; i < min; i++) {
			if (str1.charAt(i) != str2.charAt(i)) {
				return str1.charAt(i) - str2.charAt(i);
			}
		}
		if (str1.length() == str2.length()) {
			return 0;
		} else if (flag) {
			return 1;					
		} else {			
			return -1;					
		}
	}
}

public class Demo1 {

	static int[] F = {1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89};
	
	public static void main(String[] args) {
		ArrayList<Integer> FList = new ArrayList<Integer>();
		for (int i = 0; i < F.length; i++) {
			FList.add(F[i]);
		}
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		sc.close();
		TreeSet<String> result = new TreeSet<String>(new MyCompare<String>());
		for (int i = 0; i < str.length(); i++) {
			HashSet<Character> set = new HashSet<Character>();
			for (int j = i; j < str.length(); j++) {
				set.add(str.charAt(j));
				if (FList.contains(set.size())) {
					result.add(str.substring(i, j + 1));
				}
			}
		}
		Iterator<String> it = result.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());			
		}
	}
	
}
