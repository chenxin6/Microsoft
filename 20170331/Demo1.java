package cn.edu.ustc.nsrl.b;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Demo1 {
    public static void main(String[] args) {
        int P = 0;
        int Q = 0;
        int N = 0;
        double result = 0;
        String str = "";
        Scanner sc = new Scanner(System.in);
        str = sc.nextLine();
        String[] a = str.split(" ");
        P = Integer.parseInt(a[0]);
        Q = Integer.parseInt(a[1]);
        N = Integer.parseInt(a[2]);
        sc.close();
        
        ArrayList<Integer> list = new ArrayList<Integer>();
        
        list.add(P);
        
    	for (int i = 1; i < N; i++) {
    		list.add(list.get(i - 1) / 2);
    	}
        
		Iterator<Integer> it = list.iterator();
		while (it.hasNext()) {
			result += myMethod(it.next(), Q);
		}
        
        System.out.printf("%.2f", result);
    }
    
    public static double myMethod(int P, int Q) {
        int j = 1;
        boolean flag = true;
        boolean chenxin = true;
        double sum = 0;
        double gailv = 1.0;
        double oldFailGailv = 1.0;
       
        while (flag) {
            if (chenxin) {
                gailv = P / 100.0;
                chenxin = false;
            } else {
                gailv += Q / 100.0;
            }
            
            if (gailv >= 1.0) {
                gailv = 1.0;
                flag = false;
            }
                
            sum += oldFailGailv * j * gailv;

            oldFailGailv *= 1.0 - gailv;
            j++;
        }
        return sum;
    }
    
}
