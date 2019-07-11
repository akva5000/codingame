package org.vadim;

import static org.vadim.VarName.*;
import static org.vadim.VarName.Y1;
import static org.vadim.VarName.Y2;

import java.util.Scanner;

/**
 * <pre>
 * Input
 * Line 1: Size of the block, size.
 * Line 2: Angle angle.
 * size following lines: Content of the block. ASCII characters are separated with spaces.
 * 
 * Output
 * Rotated block with spaces on both ends.
 * 
 * Constraints
 * 2 =< size <= 100
 * 45 =< angle <= 2295
 * angle % 90 == 45 (Dimond shape)
 * </pre>
 * 
 * @author akva
 */
public class Solution3 {
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		int size = in.nextInt();
		int angle = in.nextInt();

		angle = angle % 360;
		int rotate = angle / 45;
		System.err.println("angle=" + angle);
		System.err.println("rotate=" + rotate);

		if (in.hasNextLine()) {
			in.nextLine();
		}

		final char[][] data = new char[size][size];
		for (int y = 0; y < size; y++) {
			String line = in.nextLine();
			int pos = 0;
			for (int x = 0; x < size; x++) {
				data[y][x] = line.charAt(pos);
				pos += 2;
			}
		}

		switch (rotate) {
			case 0:
				System.err.println("Error angle = 0");
				break;

			case 1: // 1 time left
//				angle1(data);
				new MainLoopTop(
						data.length,
						i -> i >= 0, 
						i -> i - 1, 
						(vars, varIdxId) -> {
							vars[Y2] = 0;
							vars[X2] = vars[varIdxId];
						},
						new InnerLoopImpl(
								X2, 
								i -> i < size, 
								i -> i + 1, 
								(vars, varId) -> vars[Y2] += 1,
								(vars) -> data[vars[Y2]][vars[X2]]))
				.run(X1, size - 1);
				
				new MainLoopBottom(
						i -> i < size, 
						i -> i + 1, 
						(vars, varIdxId) -> {
							vars[Y2] = vars[varIdxId];
							vars[X2] = 0;
						},
						new InnerLoopImpl(
								Y2, 
								i -> i < size, 
								i -> i + 1, 
								(vars, varId) -> vars[X2] += 1,
								(vars) -> data[vars[Y2]][vars[X2]]))
				.run(Y1, 1);
				break;

			case 3: // 2 times left
//				angle2(data);
				new MainLoopTop(
						data.length,
						i -> i >= 0, 
						i -> i - 1, 
						(vars, varIdxId) -> {
							vars[X2] = size - 1;
							vars[Y2] = vars[varIdxId];
						},
						new InnerLoopImpl(
								Y2, 
								i -> i < size, 
								i -> i + 1, 
								(vars, varId) -> vars[X2] -= 1,
								(vars) -> data[vars[Y2]][vars[X2]]))
				.run(Y1, size - 1);
				
				new MainLoopBottom(
						i -> i >= 0, 
						i -> i - 1, 
						(vars, varIdxId) -> {
							vars[X2] = vars[varIdxId];
							vars[Y2] = 0;
						},
						new InnerLoopImpl(
								X2, 
								i -> i >= 0, 
								i -> i - 1, 
								(vars, varId) -> vars[Y2] += 1,
								(vars) -> data[vars[Y2]][vars[X2]]))
				.run(X1, size - 2);
				break;

			case 5: // 2 times right or 1 time left and mirror
//				angleR2(data);
				new MainLoopTop(
						data.length,
						i -> i < size, 
						i -> i + 1, 
						(vars, varIdxId) -> {
							vars[Y2] = size - 1;
							vars[X2] = vars[varIdxId];
						},
						new InnerLoopImpl(
								X2, 
								i -> i >= 0, 
								i -> i - 1, 
								(vars, varId) -> vars[Y2] -= 1,
								(vars) -> data[vars[Y2]][vars[X2]]))
				.run(X1, 0);
				
				new MainLoopBottom(
						i -> i >= 0, 
						i -> i - 1, 
						(vars, varIdxId) -> {
							vars[Y2] = vars[varIdxId];
							vars[X2] = size - 1;
						},
						new InnerLoopImpl(
								Y2, 
								i -> i >= 0, 
								i -> i - 1, 
								(vars, varId) -> vars[X2] -= 1,
								(vars) -> data[vars[Y2]][vars[X2]]))
				.run(Y1, size - 2);
				break;

			case 7: // 1 time right or 2 times left and mirror
//				angleR1(data);
				new MainLoopTop(
						data.length,
						i -> i < size, 
						i -> i + 1, 
						(vars, varIdxId) -> {
							vars[X2] = 0;
							vars[Y2] = vars[varIdxId];
						},
						new InnerLoopImpl(
								Y2, 
								i -> i >= 0, 
								i -> i - 1, 
								(vars, varId) -> vars[X2] += 1,
								(vars) -> data[vars[Y2]][vars[X2]]))
				.run(Y1, 0);
				
				new MainLoopBottom(
						i -> i < size, 
						i -> i + 1, 
						(vars, varIdxId) -> {
							vars[X2] = vars[varIdxId];
							vars[Y2] = size - 1;
						},
						new InnerLoopImpl(
								X2, 
								i -> i < size, 
								i -> i + 1, 
								(vars, varId) -> vars[Y2] -= 1,
								(vars) -> data[vars[Y2]][vars[X2]]))
				.run(X1, 1);
				break;

			default:
				System.err.println("Error angle");
				break;
		}
	}

}
