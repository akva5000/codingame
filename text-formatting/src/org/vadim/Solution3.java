package org.vadim;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.Reader;

/**
 * <pre>
 * Input:
 * A line of text.
 * 
 * Output:
 * A line containing the Formatted text.
 * 
 * Constraints
 * The length of the input string is less than 1000.
 * </pre>
 * 
 * @author akva
 */
public class Solution3 {
	public static void main(String args[]) {
		Reader in = new InputStreamReader(System.in);
		PrintStream out = System.out;

		ParseContext ctx = new ParseContext();
		ctx.in = in;
		ctx.out = out;

		try {
			if (in.ready()) {
				parser(ctx);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		System.out.println();
	}

	private static void parser(ParseContext ctx) throws IOException {
		Token tok = readNextLexem(ctx);
		if (tok == Token.END) return;
		if (tok != Token.WORD) throw new IOException("expect word");

		boolean isStatement = true;
		printWord(true, ctx);

		Token prevTok = Token.WORD;
		while (tok != Token.END) {
			tok = readNextLexem(ctx);

			switch (tok) {
				case WORD:
					ctx.out.write(' '); // space between tokens
					printWord(!isStatement, ctx);
					if (!isStatement) isStatement = true;
					break;

				case DOT:
					if (prevTok == Token.DOT) break;
					isStatement = false;
					ctx.out.write('.');
					break;

				case PUNCTUATION:
					if (prevTok != Token.PUNCTUATION) ctx.out.write(ctx.ch);
					ctx.ch = null;
					break;

				default:
					break;
			} // switch

			prevTok = tok;
		}
	}

	private static void printWord(boolean isFirst, ParseContext ctx) {
		if (isFirst) {
			char ch = ctx.buf.charAt(0); // first upper case
			if (ch >= 'a' && ch <= 'z') {
				ch -= 32;
				ctx.buf.setCharAt(0, ch);
			}
		} else { // word in a statement
			char ch = ctx.buf.charAt(0); // first is lower case
			if (ch >= 'A' && ch <= 'Z') {
				ch += 32;
				ctx.buf.setCharAt(0, ch);
			}
		}

		if (ctx.buf.length() > 1) { // make lower case all other letters
			for (int i = 1; i < ctx.buf.length(); i++) {
				char ch = ctx.buf.charAt(i);
				if (ch >= 'A' && ch <= 'Z') {
					ch += 32;
					ctx.buf.setCharAt(i, ch);
				}
			}
		}

		ctx.out.print(ctx.buf.toString());
		ctx.buf.setLength(0);
	}

	private static Token readNextLexem(ParseContext ctx) throws IOException {
		ctx.buf.setLength(0);
		if (ctx.ch == null) {
			if (!ctx.in.ready()) return Token.END;
			ctx.ch = (char) ctx.in.read();
		}

		if (ctx.ch == '\n') return Token.END;

		// word
		if ((ctx.ch >= '0' && ctx.ch <= '9') || (ctx.ch >= 'a' && ctx.ch <= 'z') || (ctx.ch >= 'A' && ctx.ch <= 'Z')) { return readWordToken(ctx); }

		// DOT
		if (ctx.ch == '.') {
			ctx.ch = null;
			return Token.DOT;
		}

		// space
		if (ctx.ch == ' ') {
			while (ctx.in.ready()) {
				ctx.ch = (char) ctx.in.read();
				if (ctx.ch == ' ') continue;
				if (ctx.ch == '\n') return Token.END;
				break;
			}
			return readNextLexem(ctx);
		}

		// punctuation
		return Token.PUNCTUATION;
	}

	private static Token readWordToken(ParseContext ctx) throws IOException {
		ctx.buf.setLength(0);
		if (ctx.ch != null) {
			if ((ctx.ch >= '0' && ctx.ch <= '9') || (ctx.ch >= 'a' && ctx.ch <= 'z') || (ctx.ch >= 'A' && ctx.ch <= 'Z')) {
				ctx.buf.append(ctx.ch);
				ctx.ch = null;
			} else {
				throw new IOException("word expected");
			}
		}

		while (ctx.in.ready()) {
			char ch = (char) ctx.in.read();
			if (ch == '\n') return ctx.buf.length() == 0 ? Token.END
					: Token.WORD;

			if ((ch >= '0' && ch <= '9') || (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')) {
				ctx.buf.append(ch);
			} else {
				ctx.ch = ch;
				break;
			}
		}

		return Token.WORD;
	}

	private static enum Token {
		WORD,
		DOT,
		PUNCTUATION,
		END;
	}

	private static class ParseContext {
		final StringBuilder buf = new StringBuilder();
		Reader in;
		PrintStream out;
		Character ch = null;
	}
}
