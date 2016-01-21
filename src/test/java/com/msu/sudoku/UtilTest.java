package com.msu.sudoku;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class UtilTest {
	
	
	@Test
	public void testHasDuplicatesFromOneToNineYes() {
		assertTrue(Util.hasDuplicatesFromOneToNine(Arrays.asList(1,2,0,4,-6,6,7,8,1)));
	}
	
	@Test
	public void testHasDuplicatesFromOneToNineNo() {
		assertFalse(Util.hasDuplicatesFromOneToNine(Arrays.asList(0,2,3,4,0,6,7,8,0)));
	}

}
