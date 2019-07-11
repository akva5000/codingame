package org.vadim;

import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.TimeUnit;

/**
 * <pre>
 * Input:
 * Line 1: W H for the width and height of the whole rectangle
 * Next H lines: space separated integers. Each line contains one row of data for the rectangle
 * 
 * Output:
 * The sum of the "maximum" sub-rectangle
 * 
 * Constraints
 * 1 ≤ W, H ≤ 100
 * -99 ≤ n ≤ 99, where n is any integer within the rectangle
 * No data line is longer than 500 characters
 * </pre>
 * 
 * @author akva
 */
public class Solution1 {
	public static int[][] desk;

	public static void main(String args[]) throws InterruptedException, ExecutionException {
		Scanner in = new Scanner(System.in);
		int W = in.nextInt();
		int H = in.nextInt();
		if (in.hasNextLine()) in.nextLine();

		desk = new int[H][W];
		for (int y = 0; y < H; y++) {
			int x = 0;
			for (final StringTokenizer tok = new StringTokenizer(in.nextLine(), " "); tok.hasMoreTokens();) {
				desk[y][x++] = Integer.parseInt(tok.nextToken());
			}
		}

//		final ForkJoinPool pool = ForkJoinPool.commonPool();
		final ForkJoinPool pool = new ForkJoinPool(4); 
		final BlockingQueue<Object> queue = new ArrayBlockingQueue<>(100, true);

		ForkJoinTask<Integer> task = pool.submit(new SelectJob(queue));

		// create rectangle cover tasks
		for (int w = 0; w <= W; w++) {
			for (int h = 0; h <= H; h++) {
				int y1 = 0;
				int y2 = y1 + h;
				while (y2 < H) {
					int x1 = 0;
					int x2 = x1 + w;
					while (x2 < W) {
//						System.err.println("TASK: " + x1 + "," + y1 + " - " + x2 + "," + y2);
						pool.execute(new RectangleJob(queue, x1, x2, y1, y2));
						++x1;
						++x2;
					}
					++y1;
					++y2;
				}
			}
		}

		queue.put(Boolean.TRUE);
		System.out.println(task.get());
		pool.shutdownNow();
	}

	private static class SelectJob implements Callable<Integer> {
		private final BlockingQueue<Object> queue;

		public SelectJob(final BlockingQueue<Object> queue) {
			this.queue = queue;
		}

		@Override
		public Integer call() throws Exception {
			int maxSum = 0;
			while (true) {
				Object v = queue.take();
				if (v instanceof Boolean) break;
				if (maxSum < (Integer) v) maxSum = (Integer) v;
			}
			return maxSum;
		}
	}

	public static class RectangleJob implements Runnable {
		private final BlockingQueue<Object> queue;
		private final int x1;
		private final int x2;
		private final int y1;
		private final int y2;

		public RectangleJob(final BlockingQueue<Object> queue, int x1, int x2, int y1, int y2) {
			this.queue = queue;
			this.x1 = x1;
			this.x2 = x2;
			this.y1 = y1;
			this.y2 = y2;
		}

		@Override
		public void run() {
			int sum = 0;
			for (int y = y1; y <= y2; y++) {
				for (int x = x1; x <= x2; x++) {
					sum += desk[y][x];
				}
			}

			try {
				queue.put((Integer) sum);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
