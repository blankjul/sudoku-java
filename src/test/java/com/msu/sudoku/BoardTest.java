package com.msu.sudoku;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;

public class BoardTest {

	protected Board empty;
	
	protected Board example;
	

	@Before
	public void setUp() {
		empty = new Board();
		
		String s = 
				"0 0 0	2 6 0 	7 0 1 \n" +  
				"6 8 0	0 7 0	0 9 0 \n" +  
				"1 9 0	0 0 4	5 0 0 \n" +  
			
				"8 2 0	1 0 0 	0 4 0 \n" +  
				"0 0 4	6 0 2	9 0 0 \n" +  
				"0 5 0 	0 0 3	0 2 8 \n" +  
			
				"0 0 9	3 0 0 	0 7 4 \n" +  
				"0 4 0 	0 5 0	0 3 6 \n" +  
				"7 0 3 	0 1 8 	0 0 0 \n" ;
			
		
		example = Board.createFromString(s);
		
	}

	
	@Test
	public void testInitialize() {
		for (int i = 0; i < Board.SIZE; i++) {
			for (int j = 0; j < Board.SIZE; j++) {
				assertEquals(0, empty.getAsValue(i, j));
				assertEquals(0, empty.get(i, j).getValue());
				assertEquals(true, empty.get(i, j).isMutable());
				assertEquals(i, empty.get(i, j).getRow());
				assertEquals(j, empty.get(i, j).getColumn());
				
			}
		}
	}

	
	
	
	
	@Test
	public void testSetValueOfField() {
		empty.set(0, 0, 5);
		assertEquals(5, empty.getAsValue(0, 0));
	}

	@Test(expected = RuntimeException.class)
	public void testFixValueOfField() {
		empty.set(0, 0, 5);
		empty.setImmutable(0, 0);
		empty.set(0, 0, 6);
	}
	
	@Test
	public void testCreateFromString() {
		
		String s = 
				"0 0 0	2 6 0 	7 0 1 \n" +  
				"6 8 0	0 7 0	0 9 0 \n" +  
				"1 9 0	0 0 4	5 0 0 \n" +  
			
				"8 2 0	1 0 0 	0 4 0 \n" +  
				"0 0 4	6 0 2	9 0 0 \n" +  
				"0 5 0 	0 0 3	0 2 8 \n" +  
			
				"0 0 9	3 0 0 	0 7 4 \n" +  
				"0 4 0 	0 5 0	0 3 6 \n" +  
				"7 0 3 	0 1 8 	0 0 0 \n" ;
			
		
		Board test = Board.createFromString(s);
		assertEquals(2, test.getAsValue(0, 3));
		assertEquals(8, test.getAsValue(3, 0));
		assertEquals(5, test.getAsValue(5, 1));
	}
	
	@Test
	public void testGetUnit() {
		assertEquals(Arrays.asList(1,0,0,6,0,2,0,0,3), Board.fieldsToInteger(example.getUnit(4)));
		assertEquals(Arrays.asList(0,7,4,0,3,6,0,0,0), Board.fieldsToInteger(example.getUnit(8)));
	}
	
	@Test
	public void testGetEmptyFields() {
		assertEquals(45, example.getEmptyFields().size());
	}
		
	
	@Test
	public void testGetRow() {
		assertEquals(Arrays.asList(8,2,0,1,0,0,0,4,0), Board.fieldsToInteger(example.getRow(3)));
	}
	
	@Test
	public void testGetColumn() {
		assertEquals(Arrays.asList(1,0,0,0,0,8,4,6,0), Board.fieldsToInteger(example.getColumn(8)));
	}
	
	@Test
	public void testCalcUnit() {
		assertEquals(0, Board.calcUnit(1, 1));
		assertEquals(8, Board.calcUnit(8, 8));
		assertEquals(4, Board.calcUnit(3, 3));
		assertEquals(6, Board.calcUnit(6, 0));
		assertEquals(7, Board.calcUnit(6, 3));
	}
	
	@Test
	public void testPossibleValues() {
		empty.set(0, 0, 0);
		assertEquals(new HashSet<Integer>(Arrays.asList(1,2,3,4,5,6,7,8,9)), empty.get(1, 0).getPossibleValues());
		empty.set(0, 0, 1);
		assertEquals(new HashSet<Integer>(Arrays.asList(2,3,4,5,6,7,8,9)), empty.get(1, 0).getPossibleValues());
		empty.set(0, 0, 0);
		assertEquals(new HashSet<Integer>(Arrays.asList(1,2,3,4,5,6,7,8,9)), empty.get(1, 0).getPossibleValues());
	}
	
	
	

}
