package cn.edu.ustc.nsrl.e;

import java.util.Scanner;

public class Demo1 {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		String[] strList = str.split(" ");
		double x = Double.parseDouble(strList[0]);
		double y = Double.parseDouble(strList[1]);
		double r = Double.parseDouble(strList[2]);
		sc.close();
		String result = myMethod(x, y, r);
		System.out.println(result);
	}
	
	static String myMethod(double x, double y, double r) {
		int rxInt = (int) Math.floor(x + r);
		int lxInt = (int) Math.ceil(x - r);
		int resultx = 0;
		int resulty = 0;
		double dismax = 0;
		double rr = r * r;
		for (int i = rxInt; i >= lxInt; i--) {
			double d = Math.sqrt(rr - (i - x) * (i - x));
			int shang = (int) Math.floor(y + d);
			int xia = (int) Math.ceil(y - d);
			double dis1 = dis((double) i, shang, x, y);
			double dis2 = dis((double) i, xia, x, y);
			if (dis1 > dismax) {
				dismax = dis1;
				resultx = i;
				resulty = shang;
			}
			if (dis2 > dismax) {
				dismax = dis2;
				resultx = i;
				resulty = xia;
			}
		}
		return resultx + " " + resulty;
	}
	
	static double dis(double xdian, double ydian, double x, double y) {
		double result = 0;
		result = Math.sqrt((xdian - x) * (xdian - x) + (ydian - y) * (ydian - y));
		return result;
	}
	
}
