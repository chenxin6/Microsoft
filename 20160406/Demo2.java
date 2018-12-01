package cn.edu.ustc.nsrl.d;

import java.util.Scanner;

class Node {
    int flag;//0代表普通节点，1代表允许规则，2代表禁止规则
    Node[] next = new Node[2];
    Node(int flag){
        this.flag = flag;
    }
}

public class Demo2 {
	static int[] chenxin = {0x80000000, 0x40000000, 0x20000000, 0x10000000, 0x08000000, 0x04000000, 0x02000000, 0x01000000, 
							0x00800000, 0x00400000, 0x00200000, 0x00100000, 0x00080000, 0x00040000, 0x00020000, 0x00010000,
							0x00008000, 0x00004000, 0x00002000, 0x00001000, 0x00000800, 0x00000400, 0x00000200, 0x00000100,
							0x00000080, 0x00000040, 0x00000020, 0x00000010, 0x00000008, 0x00000004, 0x00000002, 0x00000001, 0x00000000};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str1 = sc.nextLine();
		String[] str1List = str1.split(" ");
		int N = Integer.parseInt(str1List[0]);
		int M = Integer.parseInt(str1List[1]);
		Node root = new Node(0);
		for (int i = 0; i < N; i++) {
			String str2 = sc.nextLine();
			String[] str2List = str2.split(" ");
			String[] tempStr2List = str2List[1].split("/");
			int mask = 32;
			if (tempStr2List.length > 1) {
				mask = Integer.parseInt(tempStr2List[1]);
			}
			insert(root, tempStr2List[0], mask, str2List[0].equals("allow"));
		}
		String[] result = new String[M];
		for (int i = 0; i < M; i++) {
			String str3 = sc.nextLine();
			if (isAllow(root, str3)) {
				result[i] = "YES";				
			} else {				
				result[i] = "NO";
			}
		}
		sc.close();
		for (int i = 0; i < M; i++) {
			System.out.println(result[i]);
		}
	}
	
	static boolean isAllow(Node root, String ip) {
		boolean result = true;
		int ipInt = changeStringToInt(ip);
		int temp = 0x80000000;
		for (int i = 0; i <= 32; i++) {
			if (root.flag == 1) {
				result = true;
			} else if (root.flag == 2) {
				result = false;				
			}
//			���߻�ﵽһ����Ч��
			int nextNumber = temp & ipInt;
//			int nextNumber = chenxin[i] & ipInt;
			if (nextNumber == 0) {
				if (root.next[0] == null) {
					break;
				} else {
					root = root.next[0];
				}
			} else {
				if (root.next[1] == null) {
					break;
				} else {
					root = root.next[1];
				}
			}
			temp >>>= 1;
		}
		return result;
	}
	
	static void insert(Node root, String ip, int mask, boolean allow) {
		int ipInt = changeStringToInt(ip);
		int temp = 0x80000000;
		for (int i = 0; i <= mask; i++) {
			if (root.flag != 0) {
				break;
			}
			if (i == mask) {
				if (allow) {
					root.flag = 1;
				} else {
					root.flag = 2;					
				}
				break;
			}
//			���߻�ﵽһ����Ч��
			int nextNumber = temp & ipInt;
//			int nextNumber = chenxin[i] & ipInt;
			if (nextNumber == 0) {
				if (root.next[0] == null) {
					root.next[0] = new Node(0);					
				}
				root = root.next[0];
			} else {
				if (root.next[1] == null) {
					root.next[1] = new Node(0);				
				}
				root = root.next[1];
			}
			temp >>>= 1;
		}
	}
	
	static int changeStringToInt(String str) {
		String[] strList = str.split("\\.");
		int temp = 0;
		temp += Integer.parseInt(strList[0]);
		temp <<= 8;
		temp += Integer.parseInt(strList[1]);
		temp <<= 8;
		temp += Integer.parseInt(strList[2]);
		temp <<= 8;
		temp += Integer.parseInt(strList[3]);
		return temp;
	}
	
}
