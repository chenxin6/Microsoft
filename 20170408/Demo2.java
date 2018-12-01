package cn.edu.ustc.nsrl.c;

import java.util.Scanner;

public class Demo2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		String[] strList = str.split(" ");
		long N = Long.parseLong(strList[0]);
		int Q = Integer.parseInt(strList[1]);
		sc.close();
		long result = N;
		int tempQ = 0;
		long robot = 1;
		long temp = (long) Math.ceil(((double) N) / ((double) robot)) + tempQ;
		while (temp <= result) {
			tempQ += Q;
			result = temp;
			robot *= 2;
			temp = (long) Math.ceil(((double) N) / ((double) robot)) + tempQ;
		}		
		System.out.println(result);
	}
}
