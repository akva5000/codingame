package org.vadim;

import java.util.Arrays;
import java.util.Scanner;

/**
 * <pre>
 * Input:
 * A ip address and a mask in CIDR format.
 * Example: 192.168.0.5/24
 * 
 * Output:
 * The network address and the broadcast address on two different lines.
 * Example: 192.168.0.0 192.168.0.255
 * 
 * Constraints
 * All entries can be calculated.
 * </pre>
 * 
 * @author akva
 */
public class Solution2 {
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		String ip = in.nextLine();
		int pos = ip.indexOf('/');

		int addr = 0;

		// 3 octets
		int pos2 = -1;
		int pos3;
		for (int i = 0; i < 3; i++) {
			pos3 = ip.indexOf('.', pos2 + 1);
			addr |= Integer.parseInt(ip.substring(pos2 + 1, pos3)) & 0xFF;
			addr <<= 8;
			pos2 = pos3;
		}

		// last octet
		addr |= Integer.parseInt(ip.substring(pos2 + 1, pos)) & 0xFF;

		int bitMask = Integer.parseInt(ip.substring(pos + 1));

		final int[] net = new int[4];
		final int[] broadcast = new int[4];

		if (bitMask == 0) {
			Arrays.fill(broadcast, 255);
		} else if (bitMask == 32) {
			int t = addr;
			for (int i = 3; i >= 0; i--) {
				net[i] = t & 0xFF;
				broadcast[i] = net[i];
				t >>= 8;
			}
		} else {
			parseIp(addr, buildMask(bitMask), net, broadcast);
		}

		printIp(net);
		printIp(broadcast);
	}

	private static int buildMask(int bits) {
		int mask = 0;
		if (bits != 0) {
			for (int i = 0; i < bits; i++) {
				mask <<= 1;
				mask |= 1;
			}
			mask <<= (32 - bits);
		}
		return mask;
	}

	private static void parseIp(final int addr, int mask, final int[] net, int[] broadcast) {
		int t = addr;
		int tMask = mask;
		for (int i = 3; i >= 0; i--) {
			net[i] = (t & 0xFF) & (tMask & 0xFF);
			broadcast[i] = (t & 0xFF) | (~(tMask & 0xFF) & 0xFF);
			t >>= 8;
			tMask >>= 8;
		}
	}

	private static void printIp(int[] addr) {
		System.out.print(String.valueOf(addr[0]));
		for (int i = 1; i < 4; i++) {
			System.out.print('.');
			System.out.print(String.valueOf(addr[i]));
		}
		System.out.println();
	}
}
