package shane.JMetal;

import java.awt.print.Printable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class City {
	public static int[][] cityDis = new int [34][34];
	public static int[][] cityCost = new int [34][34];
	static {
		for (int i = 0; i < 34; i++) {
			for (int j = i; j < 34; j++) {
				cityCost[i][j] = (int)(1+Math.random()*(1000-1+1));
				cityDis[i][j] = (int)(1+Math.random()*(1000-1+1));
			}
		}
		for (int i=1;i<34;i++) {
			for (int j=0;j<i;j++) {
				cityCost[i][j] = cityCost[j][i];
				cityDis[i][j] = cityDis[j][i];
			}
		}
	}
//	public static int[][] getCityDis() {
//		for (int i = 0; i < 34; i++) {
//			for (int j = 0; i < 34; i++) {
//				cityDis[i][j] = (int)(1+Math.random()*(1000-1+1));
//			}
//		}
//		return cityDis;
//		
//	}
//	public static int[][] getCityCost() {
//		for (int i = 0; i < 34; i++) {
//			for (int j = i; j < 34; j++) {
//				cityCost[i][j] = (int)(1+Math.random()*(1000-1+1));
//			}
//		}
//		for (int i=1;i<34;i++) {
//			for (int j=0;j<i;j++) {
//				cityCost[i][j] = cityCost[j][i];
//			}
//		}
//		return cityCost;
//		
////	}
//	public static void main(String[] args) {
//		System.out.println(City.cityDis[0][1] +" "+ City.cityDis[1][0]);
//	}

}
