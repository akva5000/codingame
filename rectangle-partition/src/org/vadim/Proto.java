package org.vadim;

public class Proto {

	public static void main(String[] args) {

		int w = 10;
		int[] sizes = new int[] { 2, 3, 5 };

		for (int i1 = 0; i1 < sizes.length; i1++) {
			int size = sizes[i1];
			System.out.println(size);
			
			if (i1 != sizes.length - 1) {
				int size2 = size;
				for (int i2 = i1 + 1; i2 < sizes.length; i2++) {
					size2 += sizes[i2];
					System.out.println(size2);
				}
			}
		}
	}

}
