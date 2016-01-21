package com.msu.sudoku;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Hello world!
 *
 */
public class Solver {

	public static Board solve(Board b) {

		Queue<Board> q = new LinkedList<Board>();
		q.add(b.copy());

		while (!q.isEmpty()) {

			Board next = q.poll();

			if (next.getEmptyFields().size() == 0) {
				return next;
			}

			int bestNext = Integer.MAX_VALUE;
			Field nextField = null;

			for (Field f : next.getEmptyFields()) {
				if (f.getPossibleValues().size() < bestNext) {
					bestNext = f.getPossibleValues().size();
					nextField = f;
				}
			}

			// no copy of board is needed
			if (nextField.getPossibleValues().size() == 1) {
				final int num = nextField.getPossibleValues().iterator().next();
				next.set(nextField.row, nextField.column, num);
				q.add(next);
			}

			for (int num : nextField.getPossibleValues()) {
				Board toAdd = next.copy();
				toAdd.set(nextField.row, nextField.column, num);
				q.add(toAdd);
			}

		}

		return null;
	}

	public static void main(String[] args) {

		String s = "0 0 0	2 6 0 	7 0 1 \n" + "6 8 0	0 7 0	0 9 0 \n" + "1 9 0	0 0 4	5 0 0 \n" +

		"8 2 0	1 0 0 	0 4 0 \n" + "0 0 4	6 0 2	9 0 0 \n" + "0 5 0 	0 0 3	0 2 8 \n" +

		"0 0 9	3 0 0 	0 7 4 \n" + "0 4 0 	0 5 0	0 3 6 \n" + "7 0 3 	0 1 8 	0 0 0 \n";

		Board b = Board.createFromString(s);

		long startTime = System.currentTimeMillis();
		System.out.println(solve(b));
		long estimatedTime = System.currentTimeMillis() - startTime;
		
		System.out.println(estimatedTime + " ms");

	}
}
