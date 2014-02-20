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
package com.bh.slidepuzzle.test;

import junit.framework.TestCase;

import com.bh.slidepuzzle.utils.SlidePuzzleGenerator;

/**
 * @author heroandtn3
 *
 */
public class PuzzleTest extends TestCase {

	private SlidePuzzleGenerator slidePuzzleGenerator;
	private int[][] input;

	/**
	 * 
	 */
	public PuzzleTest(String name) {
		super(name);
		slidePuzzleGenerator = new SlidePuzzleGenerator();
	}
	
	public void testCountInversion() {
		input = new int[][] {
				{1, 2},
				{3, 0}
			};
		assertEquals(0, slidePuzzleGenerator.totalInversion(input));
		
		input = new int[][] {
				{2, 3},
				{1, 0}
		};
		assertEquals(2, slidePuzzleGenerator.totalInversion(input));
		
		input = new int[][] {
				{0, 3},
				{1, 2}
			};
		assertEquals(2, slidePuzzleGenerator.totalInversion(input));
		
		input = new int[][] {
				{1,  8,  5},
				{3,  6,  0},
				{4,  7,  2}
		};
		assertEquals(0, slidePuzzleGenerator.countInversions(input, 1, 2));
		assertEquals(14, slidePuzzleGenerator.totalInversion(input));
	}
	
	public void testResovable() {
		input = new int[][] {
				{0, 3},
				{1, 2}
			};
		assertEquals(false, slidePuzzleGenerator.isSolvable(input));
		
		input = new int[][] {
			{1, 0},
			{3, 2}
		};
		assertEquals(true, slidePuzzleGenerator.isSolvable(input));
		
		input = new int[][] {
				{3,  0,  4},
				  {2,  5,  1}
		};
		assertEquals(true, slidePuzzleGenerator.isSolvable(input));
	}
	
	public void testWhereIsEmptyElement() {
		int[] emptyElement;
		input = new int[][] {
				{0, 3},
				{1, 2}
			};
		emptyElement = slidePuzzleGenerator.whereIsEmptyElement(input);
		assertEquals(0, emptyElement[0]);
		assertEquals(0, emptyElement[1]);
		
		input = new int[][] {
				{1,  8,  5},
				{3,  6,  0},
				{4,  7,  2}
		};
		emptyElement = slidePuzzleGenerator.whereIsEmptyElement(input);
		assertEquals(1, emptyElement[0]);
		assertEquals(2, emptyElement[1]);
	}

}
