package cn.edu.ustc.nsrl.d;

import java.util.ArrayList;
import java.util.Scanner;

public class Demo4 {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		int T = Integer.parseInt(str);
		String[][] chenxin = new String[T][];
		int[] xmax = new int[T];		
		int[] ymax = new int[T];
		int[] zmax = new int[T];
		int[] xmin = new int[T];
		int[] ymin = new int[T];
		for (int i = 0; i < T; i++) {
			xmin[i] = 100;
			ymin[i] = 100;			
		}
		for (int i = 0; i < T; i++) {
			String str1 = sc.nextLine();
			int N = Integer.parseInt(str1);
			chenxin[i] = new String[N];
			for (int j = 0; j < N; j++) {
				chenxin[i][j] = sc.nextLine();
				String[] tempList = chenxin[i][j].split(" ");
				int x = Integer.parseInt(tempList[0]);
				int y = Integer.parseInt(tempList[1]);
				int z = Integer.parseInt(tempList[2]);
				if (x > xmax[i]) {
					xmax[i] = x;
				}
				if (y > ymax[i]) {
					ymax[i] = y;
				}
				if (z > zmax[i]) {
					zmax[i] = z;
				}
				if (x < xmin[i]) {
					xmin[i] = x;
				}
				if (y < ymin[i]) {
					ymin[i] = y;
				}
			}
		}
		sc.close();
		String[] result = new String[T];
		for (int i = 0; i < T; i++) {
			int[][][] arr = new int[xmax[i] + 2][ymax[i] + 2][zmax[i] + 2];
			int[][][] visited = new int[xmax[i] + 2][ymax[i] + 2][zmax[i] + 2];
			for (int j = 0; j < chenxin[i].length; j++) {
				String[] tempList = chenxin[i][j].split(" ");
				int x = Integer.parseInt(tempList[0]);
				int y = Integer.parseInt(tempList[1]);
				int z = Integer.parseInt(tempList[2]);
				if (!myMethod(x, y, z, arr)) {
					result[i] = "No";
					break;
				}
			}
			if (result[i] != "No") {
				boolean flag = true;
				for (int j = 0; j < chenxin[i].length; j++) {
					String[] tempList = chenxin[i][chenxin[i].length - 1 - j].split(" ");
					int x = Integer.parseInt(tempList[0]);
					int y = Integer.parseInt(tempList[1]);
					int z = Integer.parseInt(tempList[2]);
//					表示连接不到，j+1代表倒数第几个
					if (!myMethod2(x, y, z, arr, visited, j + 1, xmin[i], ymin[i], xmax[i], ymax[i], zmax[i])) {
						flag = false;
						break;
					} else {
						arr[x][y][z] = 0;
					}
				}
				if (flag) {
					result[i] = "Yes";
				} else {
					result[i] = "No";
				}
			}	
		}
		for (int i = 0; i < T; i++) {
			System.out.println(result[i]);
		}
	}
	
	static boolean myMethod(int x, int y, int z, int[][][] arr) {
		boolean result = true;
		if (arr[x - 1][y][z] == 0 && arr[x][y - 1][z] == 0 && arr[x][y][z - 1] == 0 && 
			arr[x + 1][y][z] == 0 && arr[x][y + 1][z] == 0 && arr[x][y][z + 1] == 0 && z != 1) {			
			return false;
		}
		arr[x][y][z] = 1;
		return result;
	}
	
	static boolean myMethod2(int x, int y, int z, int[][][] arr, int[][][] visited, 
			int count, int minx, int miny, int maxx, int maxy, int maxz) {
		boolean result = false;
		ArrayList<String> list = new ArrayList<String>();
		String key = String.valueOf(x) + " " + String.valueOf(y) + " " + String.valueOf(z);
		list.add(key);
		while (list.size() > 0) {
			String[] tempList = list.remove(list.size() - 1).split(" ");
			int tempx = Integer.parseInt(tempList[0]);
			int tempy = Integer.parseInt(tempList[1]);
			int tempz = Integer.parseInt(tempList[2]);
			if (tempx <= minx || tempx >= maxx || tempy <= miny || tempy >= maxy || tempz >= maxz || 
				visited[tempx][tempy][tempz] != 0 && visited[tempx][tempy][tempz] != count) {
				result = true;
				break;
			}
			if (tempz == 0) {
				continue;
			}
			String zuostr = String.valueOf(tempx - 1) + " " + String.valueOf(tempy) + " " + String.valueOf(tempz);
			String youstr = String.valueOf(tempx + 1) + " " + String.valueOf(tempy) + " " + String.valueOf(tempz);
			String houstr = String.valueOf(tempx) + " " + String.valueOf(tempy - 1) + " " + String.valueOf(tempz);
			String qianstr = String.valueOf(tempx) + " " + String.valueOf(tempy + 1) + " " + String.valueOf(tempz);
			String xiastr = String.valueOf(tempx) + " " + String.valueOf(tempy) + " " + String.valueOf(tempz - 1);
			String shangstr = String.valueOf(tempx) + " " + String.valueOf(tempy) + " " + String.valueOf(tempz + 1);
			boolean zuo = arr[tempx - 1][tempy][tempz] == 0 && visited[tempx - 1][tempy][tempz] != count;
			boolean you = arr[tempx + 1][tempy][tempz] == 0 && visited[tempx + 1][tempy][tempz] != count;
			boolean hou = arr[tempx][tempy - 1][tempz] == 0 && visited[tempx][tempy - 1][tempz] != count;
			boolean qian = arr[tempx][tempy + 1][tempz] == 0 && visited[tempx][tempy + 1][tempz] != count;
			boolean xia = arr[tempx][tempy][tempz - 1] == 0 && visited[tempx][tempy][tempz - 1] != count && tempz > 1;
			boolean shang = arr[tempx][tempy][tempz + 1] == 0 && visited[tempx][tempy][tempz + 1] != count;
			if (zuo) {
				if (visited[tempx - 1][tempy][tempz] == 0) {
					visited[tempx - 1][tempy][tempz] = count;					
				}
				list.add(zuostr);
			}
			if (you) {
				if (visited[tempx + 1][tempy][tempz] == 0) {
					visited[tempx + 1][tempy][tempz] = count;					
				}
				list.add(youstr);
			}
			if (hou) {
				if (visited[tempx][tempy - 1][tempz] == 0) {
					visited[tempx][tempy - 1][tempz] = count;					
				}
				list.add(houstr);
			}
			if (qian) {
				if (visited[tempx][tempy + 1][tempz] == 0) {
					visited[tempx][tempy + 1][tempz] = count;					
				}
				list.add(qianstr);
			}
			if (xia) {
				if (visited[tempx][tempy][tempz - 1] == 0) {
					visited[tempx][tempy][tempz - 1] = count;					
				}
				list.add(xiastr);
			}
			if (shang) {
				if (visited[tempx][tempy][tempz + 1] == 0) {
					visited[tempx][tempy][tempz + 1] = count;					
				}
				list.add(shangstr);
			}
		}		
		return result;
	}
}
