package cn.edu.ustc.nsrl.c;

import java.util.Scanner;

public class Demo4 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		String[] strList = str.split(" ");
		double N = Double.parseDouble(strList[0]);
		int Q = Integer.parseInt(strList[1]);
		sc.close();
		double temp = N / Q;
		int min = (int) (Math.log(temp) / Math.log(2));
		if (min < 0) min = 0;
		double robot = Math.pow(2, min);
        long result = ((long) Math.ceil(N / robot)) + (long) min * Q;
        System.out.println(result);
	}
}
