import gdp.stdlib.StdDraw;

// solver 
public class MazeSolver {

	// utility function to check if current field is valid (i.e. not out of bounds or an obstacle)
	private static boolean validField(int[][] maze, int row, int col) {
		int nrow = maze.length;
		int ncol = maze[0].length;
		boolean isValid = row >= 0 && 
						  row < nrow &&
						  col >= 0 &&
						  col < ncol &&
						  maze[row][col] != 0;
		return (isValid);
	}

	public static boolean solve(int[][] maze, int row, int col) {

		// check if current field is valid
		if (validField(maze, row, col)) {

            // check if maze is solved on current field
		    if (maze[row][col] == 3) {
			    maze[row][col] = 2;
			    return true;
	    	}

			// record field value
            int maze_val = maze[row][col];
			// set field to valid path 
			maze[row][col] = 2;

			// traverse maze leftward in current row 
			if (solve(maze, row, col - 1)) {
				return true;
			}

			// traverse maze donward in current col 
			if (solve(maze, row + 1, col)) {
				return true;
			}

			// if traversal failed set field back to original value (i.e. invalidate path and backtrack)
			maze[row][col] = maze_val;
			return false;
		}
		return false; 
	}
	
	// drawer
	public static void draw(int[][] maze) {

		// numbers of rows and columns (technically redundant since maze is square)
		int ncol = maze[0].length;
		int nrow = maze.length;

		// set scale
		StdDraw.setXscale(0, (double) ncol + 1);
        StdDraw.setYscale(0, (double) nrow + 1);

		//draw cells
        for(int row = 0; row < nrow; row++){
            for(int col = 0; col < ncol; col++){
                if(maze[row][col] == 0){
                    StdDraw.setPenColor(StdDraw.GRAY);
                    StdDraw.filledSquare(col + 0.5, nrow - row - 0.5, 0.5);
				}

				if(maze[row][col] == 1){
					StdDraw.setPenColor(StdDraw.WHITE);
					StdDraw.filledSquare(col + 0.5, nrow - row - 0.5, 0.5);
				}

				if(maze[row][col] == 2){
					StdDraw.setPenColor(StdDraw.GREEN);
					StdDraw.filledSquare(col + 0.5, nrow - row - 0.5, 0.5);
				}

				if(maze[row][col] == 3){
					StdDraw.setPenColor(StdDraw.BLUE);
					StdDraw.filledSquare(col + 0.5, nrow - row - 0.5, 0.5);
				}
            }
        }

        //draw grid cols
        for(int i = 0; i < ncol + 1; i++){
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.line((double) i, 0, (double) i, (double) ncol);
        }
        //draw grid rows 
        for(int i = 0; i < nrow + 1; i++){
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.line(0, (double) i, (double) nrow, (double) i);
        }

	}
	
}