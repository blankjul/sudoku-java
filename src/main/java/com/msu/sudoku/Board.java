package com.msu.sudoku;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Board {

	//! size of one row, column or section of the board
	public static final int SIZE = 9;
	
	//! board where the numbers are
	protected List<Field> board;
	
	
	/**
	 * Creates an empty board with only 0's
	 */
	public Board() {
		super();
		board = new ArrayList<Field>(SIZE * SIZE);
		for (int i = 0; i <  SIZE * SIZE; i++) {
				Field f = new Field(i / 9, i % 9, 0, true);
				board.add(f);
		}
	}
	
	
	public Board(List<Field> board) {
		super();
		this.board = board;
	}





	/**
	 * @return value at the specific field
	 */
	public int getAsValue(int row, int column) {
		return get(row, column).getValue();
	}

	
	
	/**
	 * @return field object at specified indexes 
	 */
	public Field get(int row, int column) {
		return board.get(column + SIZE * row);
	}
	
	
	/**
	 * @param value value that should be specified at this field
	 */
	public void set(int row, int column, int value) {
		Field f = get(row, column);
		if(!f.isMutable()) throw new RuntimeException(String.format("Field %s %s is immutable and value can not be changed.", column, row));
		else {
			
			// only check if a new number is set
			if (f.getValue() == value) return;
			
			List<Field> fieldsToUpdate = new ArrayList<Field>();
			fieldsToUpdate.addAll(getRow(row));
			fieldsToUpdate.addAll(getColumn(column));
			fieldsToUpdate.addAll(getUnit(calcUnit(row, column)));
			
			
			
			// if field was not empty allow new values now
			if (isSodukoNumber(f.getValue())) {
				for (Field field : fieldsToUpdate) {
					field.addPossibility(f.getValue());
				}
			}
			
			// if new field will not be empty
			if (isSodukoNumber(value)) {
				for (Field field : fieldsToUpdate) {
					field.removePossibility(value);
				}
			}
			
			f.setValue(value);
		}
	}

	
	public static boolean isSodukoNumber(int i) {
		return (i >= 1 && i <= 9);
	}

	public void setImmutable(int row, int column) {
		get(row, column).setMutable(false);
	}
	
	
	public List<Field> getRow(int r) {
		List<Field> row = new ArrayList<Field>();
		for (int i = 0; i < SIZE; i++) {
			row.add(get(r, i));
		}
		return row;
	}
	
	public List<Field> getColumn(int c) {
		List<Field> row = new ArrayList<Field>();
		for (int i = 0; i < SIZE; i++) {
			row.add(get(i, c));
		}
		return row;
	}
	
	
	public static int calcUnit(int row, int column) {
		return (row / 3) * 3 + column / 3;
	}
	
	
	public List<Field> getUnit(int u) {
		
		List<Field> result = new ArrayList<Field>();
		final int row = (u / 3) * 3;
		final int column = (u % 3) * 3;
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				result.add(get(row + i, column + j));
			}
		}
		return result;
	}
	
	public Collection<Field> getEmptyFields() {
		List<Field> result = new ArrayList<Field>();
		for (Field field : board) {
			if (field.isEmpty()) result.add(field);
		}
		return result;
	}
	
	public static List<Integer> fieldsToInteger(Collection<Field> c) {
		List<Integer> l = new ArrayList<Integer>();
		for (Field f : c) {
			l.add(f.getValue());
		}
		return l;
	}
	
	
	
	public static Board createFromString(String s) {
		Board b = new Board();
		String[] row = s.split("\n");
		for (int i = 0; i < row.length; i++) {
			String[] values = row[i].split("\\s+");
			for (int j = 0; j < values.length; j++) {
				final int value = Integer.valueOf(values[j]);
				b.set(i,j,value);
				if (Board.isSodukoNumber(value)) b.setImmutable(i, j);
			}
		}
		return b;
	}
	
	
	public Board copy() {
		 List<Field> copy = new ArrayList<Field>();
		 for (Field field : board) {
			copy.add(field.copy());
		}
		 return new Board(copy);
	}


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i <  SIZE * SIZE; i++) {
			
			if (i != 0 && i % 9 == 0) sb.append('\n');
			if (i % 27 == 0) sb.append('\n');
			if (i % 3 == 0) sb.append('\t');
			
			sb.append(getAsValue(i / 9, i % 9));
			sb.append(' ');
	}
		return sb.toString();
	}
	
	
	
	
	
	

}
