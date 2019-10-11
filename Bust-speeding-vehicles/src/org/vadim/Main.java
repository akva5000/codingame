package org.vadim;

public class Main {

	public static void main(String[] args) {
		float t = 23.0f;
		int s = (int) t;
		if(s != t) ++s;
		System.out.println(s);
	}

}
