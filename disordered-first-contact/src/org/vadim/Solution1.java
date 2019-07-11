package org.vadim;

import java.util.Scanner;

/**
 * <pre>
 * Input
 * Line 1: An integer N indicating the number of times the message was transformed.
 * If N is positive you have to decode i.e. retrieve the original message.
 * If N is negative you have to encode i.e. transform the message.
 * 
 * Line 2: A string message to be decoded or encoded.
 * 
 * Output
 * One line: The original message (if N is positive) or the transformed message (if N is negative).
 * 
 * Constraints
 * -10 ≤ N ≤ 10
 * 0 < message length < 1024
 * </pre>
 * 
 * @author akva
 */
public class Solution1 {
	public static void main( String args[] ) {
		Scanner in = new Scanner( System.in );
		int N = in.nextInt();
		System.err.println("" + N);
		if ( in.hasNextLine() ) {
			in.nextLine();
		}
		String MESSAGE = in.nextLine();
		System.err.println(MESSAGE);
		StringBuilder buf = new StringBuilder( MESSAGE );
		if(N != 0) {
			for (int i = 0, len = (N < 0 ? -N : N); i < len; i++) {
				if (N < 0) encode(buf);
				else decode(buf);
			}
		}

		System.out.println( buf );
	}

	private static void encode( StringBuilder buf ) {
		char a = buf.charAt( 0 );
		buf.setCharAt( 0, buf.charAt( 1 ) );
		buf.setCharAt( 1, buf.charAt( 2 ) );
		buf.setCharAt( 2, a );

		String text = buf.substring( 3, 6 );
		buf.delete( 3, 6 );
		buf.append( text );

		text = buf.substring( 3, 6 );
		buf.delete( 3, 6 );
		buf.insert( 0, text );
	}

	private static void decode( StringBuilder buf ) {
		String text = buf.substring( 0, 3 );
		buf.delete( 0, 3 );
		buf.insert( 3, text );

		text = buf.substring( buf.length() - 3 );
		buf.setLength( buf.length() - 3 );
		buf.insert( 3, text );

		char a = buf.charAt( 2 );
		buf.setCharAt( 2, buf.charAt( 1 ) );
		buf.setCharAt( 1, buf.charAt( 0 ) );
		buf.setCharAt( 0, a );
	}
}
