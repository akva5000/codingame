package org.vadim;

import java.util.Objects;

public class IntersectDetector {

	public static boolean isIntersect(Rect r1, Rect r2) {
		int tw = r1.x2 - r1.x1 + 1; // width
		int th = r1.y2 - r1.y1 + 1; // height

		int rw = r2.x2 - r2.x1 + 1; // #2 width
		int rh = r2.y2 - r2.y1 + 1; // #2 height

		rw += r2.x1;
		rh += r2.y1;
		tw += r1.x1;
		th += r1.y1;
		// overflow || intersect
		return ((rw < r2.x1 || rw > r1.x1) && (rh < r2.y1 || rh > r1.y1) && (tw < r1.x1 || tw > r2.x1) && (th < r1.y1 || th > r2.y1));
	}
	
	static final class Rect {
		int x1, y1, x2, y2;

		public Rect(int x1, int y1, int x2, int y2) {
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
		}

		@Override
		public int hashCode() {
			return Objects.hash(x1, y1, x2, y2);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) return true;
			if (obj == null || getClass() != obj.getClass()) return false;
			Rect other = (Rect) obj;
			if (x1 != other.x1 || x2 != other.x2 || y1 != other.y1 || y2 != other.y2) return false;
			return true;
		}

		@Override
		public String toString() {
			return Integer.toString(x1) + ',' + y1 + '-' + x2 + ',' + y2;
		}
	}
}
