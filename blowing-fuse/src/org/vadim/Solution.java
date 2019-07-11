package org.vadim;

import java.util.BitSet;
import java.util.Scanner;

/**
 * <pre>
 * Input
 * Line 1: Integers n m c, separated by a space
 * n is the number of devices, assume the devices have IDs from 1 to n
 * m is the number of button-clicking going to happen
 * c is the capacity of the main fuse in amperes [A]
 *
 * Line 2: n integers, space separated, representing the electrical current consumption value 
 * of each appliance, listed from ID 1 to n
 * Line 3: m integers, space separated - a sequence of ID# you are going to click power buttons,
 * that will toggle the device status in that exact sequence.
 * 
 * Output
 * If the fuse was blown during the operation sequence, output one line:
 * Fuse was blown.
 * 
 * If the fuse did not blow, find the maximal consumed power by turned-on devices 
 * that occurred during the sequence. Output two lines:
 * Fuse was not blown.
 * Maximal consumed current was ?? A.
 * 
 * Follow examples of test cases for the expected format.
 * </pre>
 *
 * @author akva
 */
public class Solution {

	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		int[] deviceConsumes = new int[in.nextInt()];
		BitSet deviceStatus = new BitSet(deviceConsumes.length);
		int eventsNumber = in.nextInt();
		int fuseCapacity = in.nextInt();

		for (int i = 0; i < deviceConsumes.length; i++) {
			deviceConsumes[i] = in.nextInt();
		}

		int currentConsumes = 0;
		int maxConsumes = 0;
		for (int i = 0; i < eventsNumber; i++) {
			int deviceId = in.nextInt() - 1;
			if (deviceStatus.get(deviceId)) { // turning off
				currentConsumes -= deviceConsumes[deviceId];
				deviceStatus.clear(deviceId);
			} else { // turning on
				currentConsumes += deviceConsumes[deviceId];
				deviceStatus.set(deviceId);
				if (currentConsumes > fuseCapacity) {
					// overloading detected
					System.out.println("Fuse was blown.");
					return;
				}
				if (currentConsumes > maxConsumes) maxConsumes = currentConsumes;
			}
		}

		System.out.println("Fuse was not blown.");
		System.out.println("Maximal consumed current was " + maxConsumes + " A.");
	}
}
