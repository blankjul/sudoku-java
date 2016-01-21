package com.msu.sudoku;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class BoardValidatorTest {
	
	protected Board example;

	@Before
	public void setUp() {
		
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
	public void testIsValidYes() {
		assertEquals(true, BoardValidator.isValid(example));
	}
	
	
	@Test
	public void testIsValidNoDuplicateRow() {
		example.set(0, 0, 2);
		assertEquals(false, BoardValidator.isValid(example));
	}
	
	@Test
	public void testIsValidNoDuplicateColumn() {
		example.set(0, 0, 9);
		assertEquals(false, BoardValidator.isValid(example));
	}
	
	@Test
	public void testIsValidNoDuplicateSection() {
		example.set(0, 0, 7);
		assertEquals(false, BoardValidator.isValid(example));
	}

}
