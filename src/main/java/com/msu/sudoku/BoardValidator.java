package com.msu.sudoku;

public class BoardValidator {

	public static boolean isValid(Board b) {
		
		for (int i = 0; i < Board.SIZE; i++) {
			
			boolean isValid = !Util.hasDuplicatesFromOneToNine(Board.fieldsToInteger(b.getRow(i)));
			if (!isValid) return false;
			
			isValid = !Util.hasDuplicatesFromOneToNine(Board.fieldsToInteger(b.getColumn(i)));
			if (!isValid) return false;
			
			isValid = !Util.hasDuplicatesFromOneToNine(Board.fieldsToInteger(b.getUnit(i)));
			if (!isValid) return false;
			
		}
		
		return true;
		
	}
	

	

}
