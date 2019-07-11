package org.vadim;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * <pre>
 * Input:
 * Line 1: A number N for the amount of tributes.
 * Next N lines: Each tribute's name.
 * Next Line: A number T for the amount of turns in the game
 * Next T lines: Information of who killed who. Player killed Victim
 * 
 * If a tribute kills more than one person in a single turn, then it will be formatted like so.
 * 
 * TributeName killed VictimName1, VictimName2, VictimName3...
 * 
 * It is also possible that the same killer appears in multiple lines. Eg:
 * John killed Steve
 * John killed Marcus
 * 
 * Output:
 * Print each tribute's name, who they killed (if anyone), and who killed them.
 * Separate each Tribute's information with a blank line
 * 
 * Example (Tribute's name is John, they killed Sebastian and Denny, and Marcus won) :
 * Name: John
 * Killed: Denny, Sebastian
 * Killer: Marcus
 * 
 * Name: Marcus
 * Killed: John
 * Killer: Winner
 * 
 * Name: Sebastian
 * Killed: None
 * Killer: John
 * 
 * If the player was not killed (is the winner), then print "Winner" in place for the killer.
 * If the player did not kill anyone, then print "None" in place for who they've killed.
 * 
 * Constraints
 * 1 < N
 * Tribute Names will always be unique, and will only contain alphabetical characters. (No accent marks or special characters).
 * 
 * There will only be one tribute left alive.
 * </pre>
 * 
 * @author akva
 */
public class Solution1 {
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);

		int tributes = in.nextInt();
		if (in.hasNextLine()) in.nextLine();

		String[] players = new String[tributes];
		for (int i = 0; i < tributes; i++) {
			players[i] = in.nextLine();
		}

		int turns = in.nextInt();
		if (in.hasNextLine()) in.nextLine();

		Map<String, Object> killTo = new HashMap<>();
		Map<String, Object> killFrom = new HashMap<>();
		for (int i = 0; i < turns; i++) {
			String info = in.nextLine();
			int pos = info.indexOf(' ');
			String player = info.substring(0, pos);
			if (info.indexOf(',', pos + 8) > 0) {
				Object item = killTo.get(player);
				List<String> listTo;
				if (item == null) {
					listTo = new ArrayList<>();
					killTo.put(player, listTo);
				} else if (item instanceof String) {
					listTo = new ArrayList<>();
					listTo.add((String) item);
					killTo.put(player, listTo);
				} else {
					listTo = (List<String>) item;
				}

				for (final StringTokenizer tok = new StringTokenizer(info.substring(pos + 8), ","); tok.hasMoreTokens();) {
					String victum = tok.nextToken().trim();
					listTo.add(victum);
					fillFrom(killFrom, player, victum);
				}

			} else {
				String victum = info.substring(pos + 8);
				Object item = killTo.get(player);
				if (item == null) {
					killTo.put(player, victum);
				} else if (item instanceof List) {
					((List<String>) item).add(victum);
				} else {
					List<String> list = new ArrayList<>(2);
					list.add((String) item);
					list.add(victum);
					killTo.replace(player, list);
				}

				fillFrom(killFrom, player, victum);
			}
		}

		Arrays.sort(players);
		printPlayer(players[0], killTo, killFrom);
		for (int i = 1; i < players.length; i++) {
			System.out.println();
			printPlayer(players[i], killTo, killFrom);
		}
	}

	private static void fillFrom(Map<String, Object> map, String player, String victum) {
		Object item = map.get(victum);
		if (item == null) {
			map.put(victum, player);
		} else if (item instanceof List) {
			((List<String>) item).add(player);
		} else {
			List<String> list = new ArrayList<>(2);
			list.add((String) item);
			list.add(player);
			map.replace(victum, list);
		}
	}

	private static void printPlayer(String player, Map<String, Object> killTo, Map<String, Object> killFrom) {
		System.out.println("Name: "
				+ player);
		System.out.print("Killed: ");
		Object item = killTo.get(player);
		if (item == null) {
			System.out.print("None");
		} else if (item instanceof String) {
			System.out.print(item);
		} else {
			printList((List<String>) item);
		}

		System.out.print("\nKiller: ");
		item = killFrom.get(player);
		if (item == null) {
			System.out.print("Winner");
		} else if (item instanceof String) {
			System.out.print(item);
		} else {
			printList((List<String>) item);
		}
		System.out.println();
	}

	private static void printList(List<String> list) {
		Collections.sort(list);
		System.out.print(list.get(0));
		for (int j = 1; j < list.size(); j++) {
			System.out.print(", ");
			System.out.print(list.get(j));
		}
	}

}
