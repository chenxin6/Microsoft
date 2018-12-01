package cn.edu.ustc.nsrl.b;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Demo5 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		sc.close();
		HashMap<String, Integer> part1 = getNeedAndMethods(str);
        int need1 = part1.get("need"), methods1 = part1.get("methods");
        
        StringBuffer str2 = new StringBuffer(str);
        str2.reverse();
        for (int i = 0; i < str2.length(); i++) {
            if (str2.charAt(i) == '(')
                str2.setCharAt(i, ')');
            else
                str2.setCharAt(i, '(');
        }
        HashMap<String, Integer> part2 = getNeedAndMethods(str2.toString());
        int need2 = part2.get("need"), methods2 = part2.get("methods");
        System.out.println((need1 + need2) + " " + (methods1 * methods2) % 1000000007);
	}

	static HashMap<String, Integer> getNeedAndMethods(String string) {
        HashMap<String, Integer> result = new HashMap<>();
        int left = 0, right = 0, need = 0;
        char[] strs = string.toCharArray();
        ArrayList<Integer> least = new ArrayList<>();
        ArrayList<Integer> tmpLeast = new ArrayList<>();
        for (char ch : strs) {
            if (ch == '(')
                left++;
            else {
                right++;
                if (right - left > need) {
                    need = right - left;
                    tmpLeast.add(need);
                    least.addAll(tmpLeast);
                    tmpLeast.clear();
                } else if (right - left > 0) {
                	tmpLeast.add(right - left);                	
                } else {                	
                	tmpLeast.add(0);
                } 
            }
        }
        result.put("need", need);    
        if (least.size() == 0) {
        	result.put("methods", 1);
            return result;
        }
        int [][] chenxin = new int[least.size()][need + 1];
        for (int i = 1; i < least.size(); i++) {
        	for (int j = 0; j <= need; j++) {
        		chenxin[i][j] = -1;
        	}
        }
        for (int j = 0; j <= need; j++) {
        	chenxin[0][j] = 1;
        }
        result.put("methods", myMethod(least, least.size() - 1, need, chenxin));        
        return result;
    }
	
	static int myMethod(ArrayList<Integer> least, int weizhi, int charu, int[][] chenxin) {
		int result = 0;
		if (weizhi == 0) {
			return 1;
		}
		if (chenxin[weizhi][charu] != -1) {
			return chenxin[weizhi][charu];
		}
//		i表示前面一个插入点要插入的个数
		for (int i = 0; i <= charu; i++) {
			if (i >= least.get(weizhi - 1)) {
				result += (myMethod(least, weizhi - 1, i, chenxin)) % 1000000007;
				result %= 1000000007;
			}
		}
		chenxin[weizhi][charu] = result;
		return result;
	}
}
