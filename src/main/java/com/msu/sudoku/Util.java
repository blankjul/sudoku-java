package com.msu.sudoku;

import java.util.Collection;

public class Util {

	/**
	 * It is NOT a general duplicates function. Checks whether 1 - 9 is not more
	 * than ones in the collection. Other values are not tested!!
	 * 
	 * @param c collection of integer
	 * @return true if 1-9 are not more than two times in that collection
	 */
	public static boolean hasDuplicatesFromOneToNine(Collection<Integer> c) {

		boolean[] flag = new boolean[9];
		
		// for all elements of the collection
		for (int i : c) {
			
			// if it is a soduko number
			if (i >= 1 && i <= 9) {
				if (flag[i-1]) {
					return true; 
				}
				else flag[i-1] = true;
			}
		}
		
		return false;
		
	}

}
