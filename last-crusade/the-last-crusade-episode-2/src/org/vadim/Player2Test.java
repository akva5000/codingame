package org.vadim;

import static org.junit.Assert.*;

import java.util.BitSet;
import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Player2Test {

	@Test
	public void testMain1() {
//		SIZE=5 x 3
//		0 0 -3 0 0
//		0 0  2 0 0
//		0 0 -3 0 0
//		EXIT=2
//		TURN: 2,0 / T
				
		int[][] grid = new int[][] {
			{0, 0, 3, 0, 0},
			{0, 0, 2, 0, 0},
		  {0, 0, 3, 0, 0}
		};
		
		BitSet isRoomRotate = new BitSet(grid[0].length * grid.length);
		isRoomRotate.set(0, grid[0].length * grid.length - 1);
		isRoomRotate.clear(2);
		isRoomRotate.clear(2 * grid[0].length + 2);
		
		LinkedList<int[]> rc = Player2.calculateStrategy(2, 1, 2, grid, isRoomRotate);
		printResult(rc);
	} 
	
	@Test
	public void testMain2() {
//		SIZE 3 x 3
//	  0 3 0
//	  0 3 0
//	  0 3 0
//	EXIT=1
//	Turn: 1,0 / T
				
		int[][] grid = new int[][] {
			{0, 3, 0},
			{0, 3, 0},
		  {0, 3, 0}
		};
		
		BitSet isRoomRotate = new BitSet(grid[0].length * grid.length);
		isRoomRotate.set(0, grid[0].length * grid.length - 1);
				
		LinkedList<int[]> rc = Player2.calculateStrategy(1, 1, 1, grid, isRoomRotate);
		printResult(rc);
	}
	
	@Test
	public void testMain3() {
//	SIZE 8 x 4
//  0  -3 0 0 0 0  0 0
//  0  12 3 3 2 3 12 0
//  0   0 0 0 0 0  2 0
//  0 -12 3 2 2 3 13 0
//  EXIT=1
//  Turn: 1,0 / T
		
		int[][] grid = new int[][] {
			{0, 3, 0, 0, 0, 0, 0, 0},
			{0, 12, 3, 3, 2, 3, 12, 0},
		  {0, 0, 0, 0, 0, 0, 2, 0},
		  {0, 12, 3, 2, 2, 3, 13, 0}
		};
		
		BitSet isRoomRotate = new BitSet(grid[0].length * grid.length);
		isRoomRotate.set(0, grid[0].length * grid.length - 1);
		isRoomRotate.clear(1);
		isRoomRotate.clear(3*grid[0].length + 1);
		
		LinkedList<int[]> rc = Player2.calculateStrategy(1, 1, 1, grid, isRoomRotate);
		printResult(rc);
	}
	
	@Test
	public void testMain4() {
		//	SIZE 13 x 10
		//  -3 12  8  6  3  2  7  2  7  0  0  0  0
		//  11  5 13  0  0  0  3  0  3  0  0  0  0
		//   0 11  2  2  3  3  8  2 -9  2  3 13  0
		//   0  0  0  0  0 12  8  3  1  3  2  7  0
		//   0  0 11  2  3  1  5  2 10  0  0 11 13
		//   0  0  3  0  0  6  8  0  0  0  0  0  2
		//   0  0 11  3  3 10 11  2  3  2  3  2  8
		//   0 12  6  3  2  3  3  6  3  3  2  3 12
		//   0 11  4  2  3  2  2 11 12 13 13 13  0
		//   0  0 -3 12  7  8 13 13  4  5  4 10  0
		// EXIT=2
		// Turn: 0,0 / T

		int[][] grid = new int[][] {
			{ 3, 12,  8,  6, 3,  2,  7,  2,  7,  0,  0,  0,  0},
			{11,  5, 13,  0, 0,  0,  3,  0,  3,  0,  0,  0,  0},
			{ 0, 11,  2,  2, 3,  3,  8,  2,  9,  2,  3, 13,  0},
			{ 0,  0,  0,  0, 0, 12,  8,  3,  1,  3,  2,  7,  0},
			{ 0,  0, 11,  2, 3,  1,  5,  2, 10,  0,  0, 11, 13},
			{ 0,  0,  3,  0, 0,  6,  8,  0,  0,  0,  0,  0,  2},
			{ 0,  0, 11,  3, 3, 10, 11,  2,  3,  2,  3,  2,  8},
			{ 0, 12,  6,  3, 2,  3,  3,  6,  3,  3,  2,  3, 12},
			{ 0, 11,  4,  2, 3,  2,  2, 11, 12, 13, 13, 13,  0},
			{ 0,  0,  3, 12, 7,  8, 13, 13,  4,  5,  4, 10,  0}
		};

		BitSet isRoomRotate = new BitSet(grid[0].length * grid.length);
		isRoomRotate.set(0, grid[0].length * grid.length - 1);
		isRoomRotate.clear(0);
		isRoomRotate.clear(2*13 + 8);
		isRoomRotate.clear(9*13 + 2);

		LinkedList<int[]> rc = Player2.calculateStrategy(0, 1, 2, grid, isRoomRotate);
		printResult(rc);
	}

	private void printResult(List<int[]> result) {
		for(int[] node : result) {
			System.out.println("" + node[0] + ',' + node[1] + " from=" + node[2] + " angle=" + node[3]);
		}
	}
}
