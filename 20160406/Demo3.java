package cn.edu.ustc.nsrl.d;

import java.util.Scanner;

public class Demo3 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str1 = sc.nextLine();
		String[] str1List = str1.split(" ");
		int N = Integer.parseInt(str1List[0]);
		int M = Integer.parseInt(str1List[1]);
		char[][] maze = new char[N][M];
		for (int i = 0; i < N; i++) {
			String str2 = sc.nextLine();
			for (int j = 0; j < M; j++) {
				maze[i][j] = str2.charAt(j);
			}
		}		
		sc.close();
		int result = 0;
		if (maze[0][0] == 'b') {
			result++;
			maze[0][0] = '.';
		}
		if (maze[N - 1][M - 1] == 'b') {
			result++;
			maze[N - 1][M - 1] = '.';
		}
		int[][][] dp = new int[N][M][2];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				for (int k = 0; k < 2; k++) {
					dp[i][j][k] = Integer.MAX_VALUE;					
				}
			}
		}	
//		前两个0代表一开始在左上角，第三个0代表向右走，1代表向下走
		int temp = myMethod(maze, 0, 0, 0, dp);
		System.out.println(result + temp);
	}
	
	static int myMethod(char[][] maze, int x, int y, int fangxiang, int[][][] dp) {
		if (x == maze[0].length - 1 && y == maze.length - 1) {
			return 0;
		}
		if (dp[y][x][fangxiang] != Integer.MAX_VALUE) {
			return dp[y][x][fangxiang];
		}
		long xiangyouzou = Integer.MAX_VALUE;
		int tempx = x + 1;
		if (tempx < maze[0].length) {
			if (fangxiang == 0) {
				if (maze[y][tempx] == 'b') {
					xiangyouzou = (long) 1 + myMethod(maze, tempx, y, 0, dp);
				} else {
					xiangyouzou = (long) myMethod(maze, tempx, y, 0, dp);
				}
			} else {
				if (y + 1 >= maze.length || maze[y + 1][x] == 'b') {
					if (maze[y][tempx] == 'b') {
						xiangyouzou = (long) 1 + myMethod(maze, tempx, y, 0, dp);
					} else {
						xiangyouzou = (long) myMethod(maze, tempx, y, 0, dp);
					}
				} else {
					if (maze[y][tempx] == 'b') {
						xiangyouzou = (long) 2 + myMethod(maze, tempx, y, 0, dp);
					} else {
						xiangyouzou = (long) 1 + myMethod(maze, tempx, y, 0, dp);
					}
				}
			}
		}
		if (xiangyouzou > Integer.MAX_VALUE) {
			xiangyouzou = Integer.MAX_VALUE;
		}
		long xiangxiazou = Integer.MAX_VALUE;
		int tempy = y + 1;
		if (tempy < maze.length) {
			if (fangxiang == 1) {
				if (maze[tempy][x] == 'b') {
					xiangxiazou = (long) 1 + myMethod(maze, x, tempy, 1, dp);
				} else {
					xiangxiazou = (long) myMethod(maze, x, tempy, 1, dp);					
				}
			} else {
				if (x + 1 >= maze[0].length || maze[y][x + 1] == 'b') {
					if (maze[tempy][x] == 'b') {
						xiangxiazou = (long) 1 + myMethod(maze, x, tempy, 1, dp);
					} else {
						xiangxiazou = (long) myMethod(maze, x, tempy, 1, dp);					
					}
				} else {
					if (maze[tempy][x] == 'b') {
						xiangxiazou = (long) 2 + myMethod(maze, x, tempy, 1, dp);
					} else {
						xiangxiazou = (long) 1 + myMethod(maze, x, tempy, 1, dp);					
					}
				}
			}
		}
		if (xiangxiazou > Integer.MAX_VALUE) {
			xiangxiazou = Integer.MAX_VALUE;
		}
		dp[y][x][fangxiang] = Math.min((int) xiangyouzou, (int) xiangxiazou);
		return dp[y][x][fangxiang];
	}
}
