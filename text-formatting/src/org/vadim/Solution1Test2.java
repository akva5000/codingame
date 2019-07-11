package org.vadim;

import static org.junit.Assert.*;

import org.junit.Test;

public class Solution1Test2 {

	@Test
	public void testHandle1() {
		String rc = Solution1.handle("aaa   bbb ccc  ddd");
		assertEquals("aaa bbb ccc ddd", rc);
	}
	
	@Test
	public void testHandle2() {
		String rc = Solution1.handle("aaa ,  bbb . ccc  .ddd");
		assertEquals("aaa, bbb. ccc. ddd", rc);
	}
	
	@Test
	public void testHandle3() {
		String rc = Solution1.handle("aaa ,,  bbb ..ccc  .ddd");
		assertEquals("aaa, bbb. ccc. ddd", rc);
	}

}
