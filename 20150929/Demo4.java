package cn.edu.ustc.nsrl.e;

import java.util.Scanner;

class Elements {
	int N = 1;
	int[][] arr = new int[N][N];
	Elements a = null;
	Elements b = null;
	Elements c = null;
	Elements d = null;
	public Elements() {}
	public Elements(int N, int[][] arr) {
		this.N = N;
		this.arr = new int[N][N];
		this.arr = arr;
		if (N % 2 == 0 && N != 2) {
			int[][] temp1Arr = new int[N / 2][N / 2];
			int[][] temp2Arr = new int[N / 2][N / 2]; 
			int[][] temp3Arr = new int[N / 2][N / 2]; 
			int[][] temp4Arr = new int[N / 2][N / 2];
			for (int i = 0; i < N / 2; i++) {
				for (int j = 0; j < N / 2; j++) {
					temp1Arr[i][j] = arr[i][j];
					temp2Arr[i][j] = arr[i][j + N / 2];
					temp3Arr[i][j] = arr[i + N / 2][j];
					temp4Arr[i][j] = arr[i + N / 2][j + N / 2];
				}
			}
			this.a = new Elements(N / 2, temp1Arr);
			this.b = new Elements(N / 2, temp2Arr);
			this.c = new Elements(N / 2, temp3Arr);
			this.d = new Elements(N / 2, temp4Arr);
		}
	}
	public boolean equals(Object obj) {
		Elements ele = (Elements) obj;
		if (this.N % 2 == 1 || this.N == 2) {
			return Demo4.myMethod3(this.arr, ele.arr);
		} else {
			return this.a.equals(ele.a) && this.b.equals(ele.b) && this.d.equals(ele.d) && this.c.equals(ele.c) ||
					this.a.equals(ele.b) && this.b.equals(ele.d) && this.d.equals(ele.c) && this.c.equals(ele.a) ||
					this.a.equals(ele.d) && this.b.equals(ele.c) && this.d.equals(ele.a) && this.c.equals(ele.b) ||
					this.a.equals(ele.c) && this.b.equals(ele.a) && this.d.equals(ele.b) && this.c.equals(ele.d);
		}
	}
}

public class Demo4 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str1 = sc.nextLine();
		int caseNumber = Integer.parseInt(str1);
		String[] result = new String[caseNumber];
		for (int i = 0; i < caseNumber; i++) {
			String str2 = sc.nextLine();
			int N = Integer.parseInt(str2);
			int[][] A = new int[N][N];
			for (int j = 0; j < N; j++) {
				String str3 = sc.nextLine();
				String[] str3List = str3.split(" ");
				for (int k = 0; k < N; k++) {
					A[j][k] = Integer.parseInt(str3List[k]);
				}
			}
			int[][] B = new int[N][N];
			for (int j = 0; j < N; j++) {
				String str3 = sc.nextLine();
				String[] str3List = str3.split(" ");
				for (int k = 0; k < N; k++) {
					B[j][k] = Integer.parseInt(str3List[k]);
				}
			}
			Elements e1 = new Elements(N, A);
			Elements e2 = new Elements(N, B);
			if (e1.equals(e2)) {
				result[i] = "Yes";
			} else {				
				result[i] = "No";
			}
		}
		sc.close();
		for (int i = 0; i < caseNumber; i++) {
			System.out.println(result[i]);
		}
	}
		
	static boolean myMethod3(int[][] A, int[][] B) {
//		String result = "";
		int N = A.length;
		boolean flag = false;
		if (A[0][0] == B[0][0] && !flag) {
			flag = true;
			int ai = 0;
			int aj = 0;
			int bi = 0;
			int bj = 0;
			for (int i = N; i >= 1; i -= 2) {
				if (flag) {
					for (int j = 0; j <= (i - 1) * 4; j++) {
						String tempA = myMethod(N, ai, aj);
						String[] tempAList = tempA.split(" ");
						String tempB = myMethod(N, bi, bj);
						String[] tempBList = tempB.split(" ");
						ai = Integer.parseInt(tempAList[0]);
						aj = Integer.parseInt(tempAList[1]);
						bi = Integer.parseInt(tempBList[0]);
						bj = Integer.parseInt(tempBList[1]);
						if (A[ai][aj] != B[bi][bj]) {
							flag = false;
							break;
						}
					}
				} else {
					break;
				}
				ai = ai + 1;
				aj = aj + 1;
				bi = bi + 1;
				bj = bj + 1;
			}
		}
		
		if (A[0][0] == B[0][N - 1] && !flag) {
			flag = true;
			int ai = 0;
			int aj = 0;
			int bi = 0;
			int bj = N - 1;
			for (int i = N; i >= 1; i -= 2) {
				if (flag) {
					for (int j = 0; j <= (i - 1) * 4; j++) {
						String tempA = myMethod(N, ai, aj);
						String[] tempAList = tempA.split(" ");
						String tempB = myMethod(N, bi, bj);
						String[] tempBList = tempB.split(" ");
						ai = Integer.parseInt(tempAList[0]);
						aj = Integer.parseInt(tempAList[1]);
						bi = Integer.parseInt(tempBList[0]);
						bj = Integer.parseInt(tempBList[1]);
						if (A[ai][aj] != B[bi][bj]) {
							flag = false;
							break;
						}
					}
				} else {
					break;
				}
				ai = ai + 1;
				aj = aj + 1;
				bi = bi + 1;
				bj = bj - 1;
			}
		}
		
		if (A[0][0] == B[N - 1][N - 1] && !flag) {
			flag = true;
			int ai = 0;
			int aj = 0;
			int bi = N - 1;
			int bj = N - 1;
			for (int i = N; i >= 1; i -= 2) {
				if (flag) {
					for (int j = 0; j <= (i - 1) * 4; j++) {
						String tempA = myMethod(N, ai, aj);
						String[] tempAList = tempA.split(" ");
						String tempB = myMethod(N, bi, bj);
						String[] tempBList = tempB.split(" ");
						ai = Integer.parseInt(tempAList[0]);
						aj = Integer.parseInt(tempAList[1]);
						bi = Integer.parseInt(tempBList[0]);
						bj = Integer.parseInt(tempBList[1]);
						if (A[ai][aj] != B[bi][bj]) {
							flag = false;
							break;
						}
					}
				} else {
					break;
				}
				ai = ai + 1;
				aj = aj + 1;
				bi = bi - 1;
				bj = bj - 1;
			}
		}
		
		if (A[0][0] == B[N - 1][0] && !flag) {
			flag = true;
			int ai = 0;
			int aj = 0;
			int bi = N - 1;
			int bj = 0;
			for (int i = N; i >= 1; i -= 2) {
				if (flag) {
					for (int j = 0; j <= (i - 1) * 4; j++) {
						String tempA = myMethod(N, ai, aj);
						String[] tempAList = tempA.split(" ");
						String tempB = myMethod(N, bi, bj);
						String[] tempBList = tempB.split(" ");
						ai = Integer.parseInt(tempAList[0]);
						aj = Integer.parseInt(tempAList[1]);
						bi = Integer.parseInt(tempBList[0]);
						bj = Integer.parseInt(tempBList[1]);
						if (A[ai][aj] != B[bi][bj]) {
							flag = false;
							break;
						}
					}
				} else {
					break;
				}
				ai = ai + 1;
				aj = aj + 1;
				bi = bi - 1;
				bj = bj + 1;
			}
		}
//		if (flag) {
//			result = "Yes";
//		} else {
//			result = "No";			
//		}
		return flag;
	}
	
	static String myMethod(int N, int i, int j) {
		N--;
		int resulti = 0;
		int resultj = 0;
		if (i + j < N) {
			if (i > j) {
				resulti = i - 1;
				resultj = j;
			} else {
				resulti = i;
				resultj = j + 1;
			}
		} else if (i + j == N) {
			if (i > j) {
				resulti = i - 1;
				resultj = j;
			} else if (i == j) {
				resulti = i;
				resultj = j;
			} else if (i < j) {
				resulti = i + 1;
				resultj = j;
			}
		} else if (i + j > N) {
			if (i >= j) {
				resulti = i;
				resultj = j - 1;
			} else {
				resulti = i + 1;
				resultj = j;
			}
		}
		return resulti + " " + resultj;
	}
	
}
