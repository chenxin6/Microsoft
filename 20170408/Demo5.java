package cn.edu.ustc.nsrl.c;

import java.util.Scanner;

public class Demo5 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		sc.nextLine();
		int[][] arr = new int[2][N];
		for (int i = 0; i < N; i++) {
			String str1 = sc.nextLine();
			String[] str1List = str1.split(" ");
			arr[1][i] = Integer.parseInt(str1List[0]);
			arr[0][i] = Integer.parseInt(str1List[1]);
		}
		sc.close();
		int sum = 0;
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < N; j++) {
				sum += arr[i][j];
			}
		}
		int pingjun = sum / 2 / N;
		long result = 0;
		for (int i = 0; i < N; i++) {
			int chazhi0 = arr[0][i] - pingjun;
			int chazhi1 = arr[1][i] - pingjun;
			if (arr[0][i] > pingjun && arr[1][i] > pingjun || arr[0][i] < pingjun && arr[1][i] < pingjun ) {
				result += Math.abs(chazhi0);
				result += Math.abs(chazhi1);
				arr[0][i] = pingjun;
				arr[1][i] = pingjun;
				arr[0][i + 1] += chazhi0; 
				arr[1][i + 1] += chazhi1; 
			} else if (arr[0][i] >= pingjun && arr[1][i] <= pingjun ) {
				int chazhisum = chazhi0 + chazhi1;
				if (chazhisum > 0) {
					result += chazhisum;
					result += Math.abs(chazhi1);
					arr[0][i] = pingjun;
					arr[1][i] = pingjun;
					arr[0][i + 1] += chazhisum;  						
				} else if (chazhisum == 0) {
					result += Math.abs(chazhi1);
					arr[0][i] = pingjun;
					arr[1][i] = pingjun;
				} else if (chazhisum < 0) {
					result += Math.abs(chazhi1);
					arr[0][i] = pingjun;
					arr[1][i] = pingjun;
					arr[1][i + 1] += chazhisum; 
				}	
			} else if (arr[0][i] <= pingjun && arr[1][i] >= pingjun ) {
				int chazhisum = chazhi0 + chazhi1;
				if (chazhisum > 0) {
					result += chazhisum;
					result += Math.abs(chazhi0);
					arr[0][i] = pingjun;
					arr[1][i] = pingjun;
					arr[1][i + 1] += chazhisum;  						
				} else if (chazhisum == 0) {
					result += Math.abs(chazhi0);
					arr[0][i] = pingjun;
					arr[1][i] = pingjun;
				} else if (chazhisum < 0) {
					result += Math.abs(chazhi0);
					arr[0][i] = pingjun;
					arr[1][i] = pingjun;
					arr[0][i + 1] += chazhisum; 
				}
			}
		}
		System.out.println(result);
	}
}
