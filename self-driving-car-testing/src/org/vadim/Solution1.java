package org.vadim;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * <pre>
 * Input
 * Line 1: The number N of lines describing the road pattern
 * Line 2: The position X (starting from 1) of the car at the beginning, then a list of command separated by a semi-colon ;
 * N next lines: The number R of similar consecutive patterns, then, separated with a semi-colon ; the pattern of the road to be repeated R times.
 * 
 * Output
 * The road, line by line, with the character # representing the self driving car at its current position
 * </pre>
 * 
 * @author akva
 */
public class Solution1 {

	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		if (in.hasNextLine()) in.nextLine();

		String xthenCOMMANDS = in.nextLine();

		final StringTokenizer tok = new StringTokenizer(xthenCOMMANDS, ";");
		int startPos = Integer.parseInt(tok.nextToken()) - 1;

		ArrayList<Integer> cmds = new ArrayList<>();
		ArrayList<Integer> cmdSteps = new ArrayList<>();
		while (tok.hasMoreTokens()) {
			String cmd = tok.nextToken();
			cmds.add(cmd.charAt(cmd.length() - 1) == 'S' ? 0 : cmd.charAt(cmd.length() - 1) == 'L' ? 1 : 2);
			cmdSteps.add(Integer.parseInt(cmd.substring(0, cmd.length() - 1)));
		}

		cmds.trimToSize();
		cmdSteps.trimToSize();

		int totalSteps = 0;
		final int[] patternRepeat = new int[N];
		final char[][] pattern = new char[N][];
		for (int i = 0; i < N; i++) {
			String rthenROADPATTERN = in.nextLine();
			int pos = rthenROADPATTERN.indexOf(';');
			patternRepeat[i] = Integer.parseInt(rthenROADPATTERN.substring(0, pos));
			totalSteps += patternRepeat[i];
			pattern[i] = rthenROADPATTERN.substring(pos + 1).toCharArray();
		}

		simulator(cmds, cmdSteps, patternRepeat, pattern, startPos, totalSteps);
	}

	private static void simulator(List<Integer> cmds, List<Integer> cmdSteps, final int[] patternRepeat,
			final char[][] pattern, int startPos, int N) {
		int cmdIdx = 0;
		int cmdRest = cmdSteps.get(cmdIdx);

		int patternIdx = 0;
		int patternRest = patternRepeat[patternIdx];

		final StringBuilder buf = new StringBuilder();
		int carPos = startPos;
		while (cmdIdx < cmds.size()) {
			buf.append(pattern[patternIdx]);
			switch (cmds.get(cmdIdx)) {
				case 0:
					
					break;

				case 1:
					--carPos;
					break;

				case 2:
					++carPos;
					break;

				default:
					break;
			}
			
			buf.setCharAt(carPos, '#');
			System.out.println(buf.toString());
			buf.setLength(0);
			
			--cmdRest;
			if (cmdRest == 0) {
				++cmdIdx;
				if (cmdIdx < cmds.size()) cmdRest = cmdSteps.get(cmdIdx);
			}

			--patternRest;
			if (patternRest == 0) {
				++patternIdx;
				if (patternIdx < pattern.length) patternRest = patternRepeat[patternIdx];
			}
		}

	}
}
