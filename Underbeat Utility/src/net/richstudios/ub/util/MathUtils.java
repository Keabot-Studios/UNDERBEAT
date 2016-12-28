package net.richstudios.ub.util;

public class MathUtils {
	
	public int approach(int ti, int i, int dt) {
		int dist = ti - i;
		
		if(dist > dt) {
			return i + dt;
		} else if(dist < dt) {
			return i - dt;
		} else {
			return ti;
		}
	}

}
