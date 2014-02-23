SlidePuzzleGenerator
====================

A utility to generate slide puzzle

### Install

Download `.jar` files from [here](https://github.com/heroandtn3/SlidePuzzleGenerator/releases) then add to your class path.

To use with GWT, add to your GWT module file:

`<inherits name="com.bh.slidepuzzle.SlidePuzzleGenerator"/>`

### How to use

#### Example

```Java
		SlidePuzzleGenerator generator = new SlidePuzzleGenerator();
		
		int rows = 3;
		int cols = 3;
		int[][] out = generator.generate(rows, cols);
		for (int row = 0; row < rows; ++row) {
			for (int col = 0; col < cols; ++col) {
				System.out.print(" " + out[row][col]);
			}
			System.out.println();
		}
```

### Error?

Add a issue [here](https://github.com/heroandtn3/SlidePuzzleGenerator/issues)
