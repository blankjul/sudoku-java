package com.msu.sudoku;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * This class represents one
 *
 */
public class Field {
	
	protected int row;
	
	protected int column;

	// ! value of that specific field
	protected int value;

	// ! if value is mutable or not
	protected boolean isMutable;

	// ! possible number for this field
	protected Set<Integer> possibleValues;
	


	public Field(int row, int column, int value, boolean isMutable) {
		super();
		this.row = row;
		this.column = column;
		this.value = value;
		this.isMutable = isMutable;
		this.possibleValues = new HashSet<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
	}



	

	public Field(int row, int column, int value, boolean isMutable, Set<Integer> possibleValues) {
		super();
		this.row = row;
		this.column = column;
		this.value = value;
		this.isMutable = isMutable;
		this.possibleValues = possibleValues;
	}






	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public boolean isMutable() {
		return isMutable;
	}

	public void setMutable(boolean isMutable) {
		this.isMutable = isMutable;
	}

	@Override
	public String toString() {
		return String.valueOf(value);
	}

	public boolean removePossibility(int value) {
		return possibleValues.remove(value);
	}

	public boolean addPossibility(int value) {
		return possibleValues.add(value);
	}

	public boolean isEmpty() {
		return getValue() == 0;
	}

	public Set<Integer> getPossibleValues() {
		return possibleValues;
	}

	public Field copy() {
		return new Field(row, column, value, isMutable, new HashSet<Integer>(possibleValues));
	}



	public int getRow() {
		return row;
	}



	public int getColumn() {
		return column;
	}
	
	

}
