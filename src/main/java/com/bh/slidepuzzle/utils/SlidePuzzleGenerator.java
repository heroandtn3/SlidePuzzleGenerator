/*
 * Copyright 2014 heroandtn3 (heroandtn3 [at] gmail.com) 
 * 
 * This file is part of slidepuzzle.
 * slidepuzzle is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.

 * slidepuzzle is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.

 * You should have received a copy of the GNU General Public License
 * along with slidepuzzle.  If not, see <http://www.gnu.org/licenses/>.
 */

/**
 * 
 */
package com.bh.slidepuzzle.utils;

import java.util.logging.Logger;


/**
 * @author heroandtn3
 *
 */
public class SlidePuzzleGenerator {
	
	private Logger logger = Logger.getLogger(SlidePuzzleGenerator.class.getName());

	/**
	 * 
	 */
	public SlidePuzzleGenerator() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Generate slide puzzle 
	 * @param rows number of tiles in a column
	 * @param cols number of tiles in a row
	 * @return array of integers represents puzzle.
	 *     if a[i][j] == 0: empty tile
	 * Example:
	 * in: 3x3
	 * out: [4, 2, 0, 1, 5, 3, 6, 7, 8]
	 * In matrix format:
	 * 4	2	0
	 * 1	5	3
	 * 6	7	8
	 */
	public int[][] generate(int rows, int cols) {
		int[][] input = new int[rows][cols];
		
		// initialize
		int count = 1;
		for (int row = 0; row < rows; ++row) {
			for (int col = 0; col < cols; ++col) {
				input[row][col] = count;
				++count;
			}
		}
		input[rows - 1][cols - 1] = 0; // empty last element
		
		// shuffle matrix
		shuffle(input);
		
		// find empty element
		int[] emptyElement = whereIsEmptyElement(input);
		int emptyRow = emptyElement[0];
		int emptyCol = emptyElement[1];
		
		// check if solvable
		if (!isSolvable(input)) {
			logger.info("Puzzle is not solvable, swapping element...");
			if (emptyRow == 0 && emptyCol < 2) {
				logger.info("swap last two elements");
				int tmp = input[rows - 1][cols - 1];
				input[rows - 1][cols - 1] = input[rows - 1][cols - 2];
				input[rows - 1][cols - 2] = tmp;
			} else {
				logger.info("swap two first elements");
				int tmp = input[0][0];
				input[0][0] = input[0][1];
				input[0][1] = tmp;
			}
		}
		
		return input;
	}
	
	private void shuffle(int[][] input) {
		int rows = input.length;
		int cols = input[0].length;
		int i = rows * cols - 1;
		
		while (i > 0) {
			int row1 = i / cols;
			int col1 = i % cols;
			
			int j = (int)(Math.random() * i);
			int row2 = j / cols;
			int col2 = j % cols;
			
			// swap
			int tmp = input[row1][col1];
			input[row1][col1] = input[row2][col2];
			input[row2][col2] = tmp;
			
			--i;
		}
	}
	
	public int countInversions(int[][] input, int row, int col) {
		int rows = input.length;
		int cols = input[0].length;
		int size = rows * cols;
		int value = input[row][col];
		if (value == 0) {
			return 0;
		}
		int count = 0;
		int start = row * cols + col;
		for (int i = start; i < size; ++i) {
			int tmp = input[i / cols][i % cols];
			if (value > tmp && tmp != 0) {
				++count;
			}
		}
		
		return count;
	}
	
	public int totalInversion(int[][] input) {
		int rows = input.length;
		int cols = input[0].length;
		
		int inversions = 0;
		System.out.println();
		for (int row = 0; row < rows; ++row) {
			for (int col = 0; col < cols; ++col) {
				inversions += countInversions(input, row, col);
			}
		}
		logger.info("Total inversion: " + inversions);
		return inversions;
	}
	
	public boolean isSolvable(int[][] input) {
		int rows = input.length;
		int cols = input[0].length;
		
		int emptyRow = whereIsEmptyElement(input)[0];
		
		int totalInversion = totalInversion(input);
		if (cols % 2 == 0) {
			// plus 1 because row starts with zero
			return (((totalInversion + rows - emptyRow + 1)) % 2 == 0);
		} else {
			return (totalInversion % 2 == 0);
		}
	}
	
	public int[] whereIsEmptyElement(int[][] input) {
		int rows = input.length;
		int cols = input[0].length;
		for (int row = 0; row < rows; ++row) {
			for (int col = 0; col < cols; ++col) {
				if (input[row][col] == 0) {
					return new int[] {row, col};
				}
			}
		}
		
		// not found
		return new int[] {-1, -1};
	}
}
