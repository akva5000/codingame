package org.vadim;

import static org.junit.Assert.*;

import org.junit.Test;
import org.vadim.IntersectDetector.Rect;

public class IntersectDetectorTest {

	@Test
	public void testIsIntersect() {
		
		// r2 inside r1
		Rect r1 = new Rect(11, 11, 13, 13);
		Rect r2 = new Rect(10, 10, 20, 20);
		boolean rc = IntersectDetector.isIntersect(r1, r2);
		assertTrue(rc);
		
		// cross r2 top left corner
		r1 = new Rect(0, 0, 13, 13);
		r2 = new Rect(10, 10, 20, 20);
		rc = IntersectDetector.isIntersect(r1, r2);
		assertTrue(rc);
		
		// cross r2 bottom left corner
		r1 = new Rect(5, 13, 13, 25);
		r2 = new Rect(10, 10, 20, 20);
		rc = IntersectDetector.isIntersect(r1, r2);
		assertTrue(rc);
		
		// cross r2 top right corner
		r1 = new Rect(13, 5, 25, 13);
		r2 = new Rect(10, 10, 20, 20);

		rc = IntersectDetector.isIntersect(r1, r2);
		assertTrue(rc);
		
		// cross r2 bottom right corner
		r1 = new Rect(13, 13, 25, 25);
		r2 = new Rect(10, 10, 20, 20);

		rc = IntersectDetector.isIntersect(r1, r2);
		assertTrue(rc);
		
		// cross r2 top side
		r1 = new Rect(13, 5, 18, 13);
		r2 = new Rect(10, 10, 20, 20);

		rc = IntersectDetector.isIntersect(r1, r2);
		assertTrue(rc);
		
		// cross r2 bottom side
		r1 = new Rect(13, 13, 18, 25);
		r2 = new Rect(10, 10, 20, 20);

		rc = IntersectDetector.isIntersect(r1, r2);
		assertTrue(rc);
		
		// cross r2 left side
		r1 = new Rect(5, 13, 18, 18);
		r2 = new Rect(10, 10, 20, 20);

		rc = IntersectDetector.isIntersect(r1, r2);
		assertTrue(rc);
		
		// cross r2 right side
		r1 = new Rect(13, 13, 25, 18);
		r2 = new Rect(10, 10, 20, 20);

		rc = IntersectDetector.isIntersect(r1, r2);
		assertTrue(rc);
	}

}
