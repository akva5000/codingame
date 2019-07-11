package org.vadim;

public class Test {

	public static void main(String[] args) {
//		int[] list = new int[] { 2, -1, -1, -2, -6, 4 };
//		int[] list = new int[] { -2, -1, -1, -2, -6, -4 };
		int[] list = new int[] { 1, -5, -2, -1, 2, -6, -4, -7 };
		int rc = maxColumn(list);
		System.out.println(rc);
	}

	private static int maxColumn(final int array[]) {
		int maxSum = Integer.MIN_VALUE;
		int runningSum = 0;

		int t = array[0];
		for (int i = 1; i < array.length; i++) {
			
		}
		
		for (int i = 0; i < array.length; i++) {
			runningSum += array[i];
			if (runningSum > maxSum) maxSum = runningSum;
			if (runningSum < 0) runningSum = 0;
			System.err.println(runningSum);
		}
		return maxSum;
	}
}
