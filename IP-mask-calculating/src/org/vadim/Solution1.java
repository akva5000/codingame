package org.vadim;

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
public class Solution1 {
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		String ip = in.nextLine();
		int pos = ip.indexOf('/');

		final int[] addr = new int[4];

		// 3 octets
		int pos2 = -1;
		int pos3;
		for (int i = 0; i < 3; i++) {
			pos3 = ip.indexOf('.', pos2 + 1);
			addr[i] = Integer.parseInt(ip.substring(pos2 + 1, pos3)) & 0xFF;
			pos2 = pos3;
		}

		// last octet
		addr[3] = Integer.parseInt(ip.substring(pos2 + 1, pos)) & 0xFF;

		printIp(addr, Integer.parseInt(ip.substring(pos + 1)));
	}

	private static void printIp(final int[] addr, int bitNetwork) {
		if (bitNetwork == 0) {
			System.out.println("0.0.0.0");
			System.out.println("255.255.255.255");
		}		
		
		int mask = 0;
		int p = bitNetwork % 8;
		if (p != 0) {
			for (int i = 0; i < p; i++) {
				mask <<= 1;
				mask |= 1;
			}
			mask <<= (8 - p);
		}

		int[] net = new int[4];
		int[] host = new int[4];
		if (bitNetwork < 9) { // 1 octet
			host[1] = 255;
			host[2] = 255;
			host[3] = 255;
			if (p == 0) {
				host[0] = addr[0];
				net[0] = addr[0];
			} else {
				net[0] = addr[0] & mask;
				host[0] = addr[0] | (~mask);
			}

		} else if (bitNetwork < 17) { // 2 octets
			host[0] = addr[0];
			net[0] = addr[0];
			host[2] = 255;
			host[3] = 255;
			if (p == 0) {
				host[1] = addr[1];
				net[1] = addr[1];
			} else {
				net[1] = addr[1] & mask;
				host[1] = addr[1] | (~mask);
			}

		} else if (bitNetwork < 25) { // 3 octets
			host[0] = addr[0];
			net[0] = addr[0];
			host[1] = addr[1];
			net[1] = addr[1];
			host[3] = 255;
			if (p == 0) {
				host[2] = addr[2];
				net[2] = addr[2];
			} else {
				net[2] = addr[2] & mask;
				host[2] = addr[2] | (~mask);
			}

		} else { // 4 octets
			host[0] = addr[0];
			net[0] = addr[0];
			host[1] = addr[1];
			net[1] = addr[1];
			host[2] = addr[2];
			net[2] = addr[2];

			if (p == 0) {
				host[3] = addr[3];
				net[3] = addr[3];
			} else {
				net[3] = addr[3] & mask;
				host[3] = addr[3] | (~mask);
			}
		}

		// print
		System.out.print(String.valueOf(net[0]));
		for (int i = 1; i < 4; i++) {
			System.out.print('.');
			System.out.print(String.valueOf(net[i]));
		}
		System.out.println();

		System.out.print(String.valueOf(host[0]));
		for (int i = 1; i < 4; i++) {
			System.out.print('.');
			System.out.print(String.valueOf(host[i]));
		}
		System.out.println();
	}
}
