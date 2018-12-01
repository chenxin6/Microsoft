package cn.edu.ustc.nsrl.b;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

class Monster {
	String ij;
	int hp;
	int ap;
	public Monster(String ij, int hp, int ap) {
		this.ij = ij;
		this.hp = hp;
		this.ap = ap;
	}
}

public class Demo4 {
	static int N = 0;
	static int M = 0;
	static int MAX_STATE = (1 << 20);
	static int ROUND = 5;
	static HashMap<String, Monster> monsterMap = new HashMap<String, Monster>();
	static int HP = 0;
	static int AP = 0;
	static int[][] chenxin = new int[ROUND + 1][MAX_STATE + 1];
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str1 = sc.nextLine();
		String[] str1List = str1.split(" ");
		N = Integer.parseInt(str1List[0]);
		M = Integer.parseInt(str1List[1]);
//		
		char[][] mazMap = new char[N][M];
		ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < N; i++) {
			String str2 = sc.nextLine();
			for (int j = 0; j < M; j++) {
				mazMap[i][j] = str2.charAt(j);
				if (mazMap[i][j] == 'S' || mazMap[i][j] == 'M') {
					list.add(i + "" + j);
				}
			}
		}
		for (String string : list) {
			String str3 = sc.nextLine();
			String[] str3List = str3.split(" ");
			int hp = Integer.parseInt(str3List[0]);
			int ap = Integer.parseInt(str3List[1]);
			Monster monster = new Monster(string, hp, ap);
			monsterMap.put(string, monster);
		}
		String str4 = sc.nextLine();
		String[] str4List = str4.split(" ");
		HP = Integer.parseInt(str4List[0]);
		AP = Integer.parseInt(str4List[1]);
		for (int i = 0; i < chenxin.length; i++) {
			for (int j = 0; j < chenxin[i].length; j++) {
				chenxin[i][j] = Integer.MAX_VALUE;
			}
		}
		int result = HP - needHp(mazMap, ROUND);
		if (result <= 0) {
			System.out.println("DEAD");
		} else {
			System.out.println(result);
		}
		sc.close();
	}
	
	public static int needHp(char[][] mazMapNow, int buffNow) {
//		state为0意味着所有的点都是'D'
		int state = 0; 
		int remainMonster = 0;
		
		HashSet<String> chooseWay = new HashSet<String>();
		for (int i = 0; i < mazMapNow.length; i++) {
			for (int j = 0; j < mazMapNow[i].length; j++) {
				state <<= 1;
				if (mazMapNow[i][j] != 'D') {
					state++;
					if (mazMapNow[i][j] == 'S' || mazMapNow[i][j] == 'M') {
						remainMonster++;
					}
					if (i - 1 >= 0 && mazMapNow[i - 1][j] == 'D' || i + 1 < N && mazMapNow[i + 1][j] == 'D' ||  
							j - 1 >= 0 && mazMapNow[i][j - 1] == 'D' || j + 1 < M && mazMapNow[i][j + 1] == 'D') {
						chooseWay.add(i + "" + j);
					}
				}
			}
		}
		
		if (state == 0 || remainMonster == 0) {
			return 0;
		}
		if (chenxin[buffNow][state] != Integer.MAX_VALUE) {
			return chenxin[buffNow][state];
		}
		
		for (String string : chooseWay) {
			int buff = buffNow;
			int nowNeedHp = 0;
			int i = string.charAt(0) - '0';
			int j = string.charAt(1) - '0';
			buff--;
			if (mazMapNow[i][j] == 'S' || mazMapNow[i][j] == 'M') {
				Monster monster = monsterMap.get(string);
				int mosterHp = monster.hp;
				while (mosterHp > 0) {
					mosterHp -= AP;
					if (buff <= 0) {
						nowNeedHp += monster.ap;
					}
					buff--;
				}
			}
			if (mazMapNow[i][j] == 'S') {
				buff = ROUND;
			} else {
				buff = buff < 0 ? 0 : buff;
			}
			
			if (nowNeedHp >= HP) {
				continue;
			}
			
			char haoyuan = mazMapNow[i][j];
			mazMapNow[i][j] = 'D';
			int temp = needHp(mazMapNow, buff);
			mazMapNow[i][j] = haoyuan;
			if (temp >= HP) {
				continue;
			}
			chenxin[buffNow][state] = Math.min(nowNeedHp + temp, chenxin[buffNow][state]);
		}
		return chenxin[buffNow][state];
	}
}
