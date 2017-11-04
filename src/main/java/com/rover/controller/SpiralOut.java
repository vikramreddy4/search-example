package com.rover.controller;

import org.junit.Test;

public class SpiralOut {

	private void spiral(int[][] numbers) {
		int x = numbers.length;
		int y = numbers[0].length;
		int t = 0, b = y - 1, l = 0, r = x - 1;
		int path = 1;
		int i = 0,j = 0;
		while(true) {
			if(path == 1) {
				while(i<r) {
					System.out.println(String.format("%s-->[%s,%s]->[t:%s,b:%s,l:%s,r:%s]", numbers[i][j], i, j, t, b, l, r));
					i++;
				}
				t++;
				path++;
			}
			if(t > b && l > r) {
				System.out.println(String.format("%s-->[%s,%s]->[t:%s,b:%s,l:%s,r:%s]", numbers[i][j], i, j, t, b, l, r));
				break;
			}
			if(path == 2) {
				while(j<b) {
					System.out.println(String.format("%s-->[%s,%s]->[t:%s,b:%s,l:%s,r:%s]", numbers[i][j], i, j, t, b, l, r));
					j++;
				}
				r--;
				path++;
			}
			if(t > b && l > r) {
				break;
			}
			if(path == 3) {
				while(i>l) {
					System.out.println(String.format("%s-->[%s,%s]->[t:%s,b:%s,l:%s,r:%s]", numbers[i][j], i, j, t, b, l, r));
					i--;
				}
				b--;
				path++;
			}
			if(t > b && l > r) {
				System.out.println(String.format("%s-->[%s,%s]->[t:%s,b:%s,l:%s,r:%s]", numbers[i][j], i, j, t, b, l, r));
				break;
			}
			if(path == 4) {
				while(j>t) {
					System.out.println(String.format("%s-->[%s,%s]->[t:%s,b:%s,l:%s,r:%s]", numbers[i][j], i, j, t, b, l, r));
					j--;
				}
				l++;
				path = 1;
			}
			if(t > b && l > r) {
				System.out.println(String.format("%s-->[%s,%s]->[t:%s,b:%s,l:%s,r:%s]", numbers[i][j], i, j, t, b, l, r));
				break;
			}
		}
//		return new ArrayList<Integer>(1);
	}

	@Test
	public void topLeftToRight() {
		spiral(new int[][] {
		        new int[] {1,2,3,4},
//		        new int[] {5,6,7,8},
//		        new int[] {9,10,11,12},
		        new int[] {13,14,15,16}
		});
	}
	
	/*
	 * xy
	 * 
	 * (0->x)0
	 * x(1->y)
	 * ((x-1)->1)y
	 * 0(y->1)
	 * 
	 * 
	 * 
	 * 
	 */
	
}
