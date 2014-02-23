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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import junit.framework.TestCase;

import org.junit.Test;

import com.bh.slidepuzzle.utils.SlidePuzzleGenerator;

/**
 * @author heroandtn3
 *
 */
public class PuzzleTest extends TestCase {

	private static SlidePuzzleGenerator slidePuzzleGenerator;
	private int[][] input;

	/**
	 * 
	 */
	public PuzzleTest(String name) {
		super(name);
		slidePuzzleGenerator = new SlidePuzzleGenerator();
	}
	
	private static int totalInversion(int[][] input) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method method = SlidePuzzleGenerator.class.
				getDeclaredMethod("totalInversion", int[][].class);
		method.setAccessible(true);
		Object[] params = {input};
		int val = (int) method.invoke(slidePuzzleGenerator, params);
		return val;
	}
	
	
	@Test
	public void testTotalInversion() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		input = new int[][] {
				{1, 2},
				{3, 0}
			};
		assertEquals(0, totalInversion(input));
		
		input = new int[][] {
				{2, 3},
				{1, 0}
		};
		assertEquals(2, totalInversion(input));
		
		input = new int[][] {
				{0, 3},
				{1, 2}
			};
		assertEquals(2, totalInversion(input));
		
		input = new int[][] {
				{1,  8,  5},
				{3,  6,  0},
				{4,  7,  2}
		};
		assertEquals(14, totalInversion(input));
	}
	
	private static int countInversions(int[][] input, int row, int col) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method method = SlidePuzzleGenerator.class.
				getDeclaredMethod("countInversions", int[][].class, int.class, int.class);
		method.setAccessible(true);
		int val = (int) method.invoke(slidePuzzleGenerator, input, row, col);
		return val;
	}
	
	@Test
	public void testCountInversion() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		input = new int[][] {
				{1,  8,  5},
				{3,  6,  0},
				{4,  7,  2}
		};
		assertEquals(0, countInversions(input, 1, 2));
	}
	
	private static boolean isSolvable(int[][] input) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method method = SlidePuzzleGenerator.class.
				getDeclaredMethod("isSolvable", int[][].class);
		method.setAccessible(true);
		Object[] params = {input};
		boolean val = (boolean) method.invoke(slidePuzzleGenerator, params);
		return val;
	}
	
	@Test
	public void testIsSovable() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		input = new int[][] {
				{0, 3},
				{1, 2}
			};
		assertEquals(false, isSolvable(input));
		
		input = new int[][] {
			{1, 0},
			{3, 2}
		};
		assertEquals(true, isSolvable(input));
		
		input = new int[][] {
				{3,  0,  4},
				  {2,  5,  1}
		};
		assertEquals(true, isSolvable(input));
	}
	
	private static int[] whereIsEmptyElement(int[][] input) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method method = SlidePuzzleGenerator.class.
				getDeclaredMethod("whereIsEmptyElement", int[][].class);
		method.setAccessible(true);
		Object[] params = {input};
		int[] val = (int[]) method.invoke(slidePuzzleGenerator, params);
		return val;
	}
	
	@Test
	public void testWhereIsEmptyElement() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		int[] emptyElement;
		input = new int[][] {
				{0, 3},
				{1, 2}
			};
		emptyElement = whereIsEmptyElement(input);
		assertEquals(0, emptyElement[0]);
		assertEquals(0, emptyElement[1]);
		
		input = new int[][] {
				{1,  8,  5},
				{3,  6,  0},
				{4,  7,  2}
		};
		emptyElement = whereIsEmptyElement(input);
		assertEquals(1, emptyElement[0]);
		assertEquals(2, emptyElement[1]);
	}
	
	@Test
	public void generate() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		assertEquals(true, isSolvable(slidePuzzleGenerator.generate(0, 0)));
		assertEquals(true, isSolvable(slidePuzzleGenerator.generate(1, 0)));
		assertEquals(true, isSolvable(slidePuzzleGenerator.generate(0, 1)));
		assertEquals(true, isSolvable(slidePuzzleGenerator.generate(3, 3)));
		assertEquals(true, isSolvable(slidePuzzleGenerator.generate(3, 4)));
		assertEquals(true, isSolvable(slidePuzzleGenerator.generate(3, 5)));
		assertEquals(true, isSolvable(slidePuzzleGenerator.generate(4, 6)));
		assertEquals(true, isSolvable(slidePuzzleGenerator.generate(9, 9)));
	}

}
