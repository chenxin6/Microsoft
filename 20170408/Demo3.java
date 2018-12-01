package cn.edu.ustc.nsrl.c;

import java.util.Scanner;

public class Demo3 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		String[] strList = str.split(" ");
		long N = Long.parseLong(strList[0]);
		int Q = Integer.parseInt(strList[1]);
		sc.close();
		long result = N;
        long result2 = N;
        long result3 = N;
		int min = myMethod(N, Q);
		long robot = 1;

		robot = (long) Math.pow(2, min);
		result = ((long) Math.ceil(((double) N) / ((double) robot))) + (long) (min * Q);
        min++;
		robot = (long) Math.pow(2, min);
		result2 = ((long) Math.ceil(((double) N) / ((double) robot))) + (long) (min * Q);
        if (min > 1) {
		    min -= 2;
		}
		robot = (long) Math.pow(2, min);
        result3 = ((long) Math.ceil(((double) N) / ((double) robot))) + (long) (min * Q);

        long temp = 0;
        if (result < result2) {
            if (result < result3) {
                temp = result;
            } else {
                temp = result3;
            }
        } else {
            if (result2 < result3) {
                temp = result2;
            } else {
                temp = result3;
            }
        }
        System.out.println(temp);
	}

	static int myMethod(long N, int Q) {
		int i = 0;
		long temp = Q;
		while (true) {
			if (temp >= N) {
				break;
			}
			i++;
			temp *= 2;
		}
//		如果有这句的话就不用三个判断最小了
//		if (i > 0) {
//			i--;
//		}
		return i;
	}
}
