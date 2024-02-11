import java.util.*;

class Oktadoku {
    public enum Variante { normal, mitDiagonalen };
    private Variante v;
    private int[][] board;

    public Oktadoku(Variante v) {
        this.v = v;
        this.board = new int[8][8];
    }

	// read oktadoku and replace '.' with 0
    public void read() {
        for (int i = 0; i < 8; i++) {
            String line = StdIn.readLine();
            line = line.replaceAll("\\s", ""); 
            for (int j = 0; j < 8; j++) {
                char c = line.charAt(j);
                if (c != '.') {
                    this.board[i][j] = Character.getNumericValue(c);
                } else {
                    this.board[i][j] = 0;
                }
            }
        }
    }

	// write oktadoku in specified format 
    public void write() {
        StdOut.println("Oktadoku");
        StdOut.println("+-----+-----+-----+-----+");
        for (int i = 0; i < 8; i++) {
            StdOut.print("| ");
            for (int j = 0; j < 8; j++) {
                if (this.board[i][j] == 0) {
                    StdOut.print("  ");
                } else {
                    StdOut.print(this.board[i][j] + " ");
                }
                if (j % 2 == 1) {
                    StdOut.print("| ");
                }
            }
            StdOut.println();
            if (i % 4 == 3) {
                StdOut.println("+-----+-----+-----+-----+");
            }
        }
    }

	// check that row has no duplicate numbers 
    private boolean checkRow(int row) {
        BitSet bitSet = new BitSet(8);
        for (int j = 0; j < 8; j++) {
			int num = this.board[row][j];
			// make sure to disregard 0s 
            if (num != 0 && bitSet.get(num - 1)) {
                return false;
            } 

			if (num != 0) {
				bitSet.set(num - 1);
			}
        }
        return true;
    }

	// check that column has no duplicate numbers
    private boolean checkColumn(int col) {
        BitSet bitSet = new BitSet(8);
        for (int i = 0; i < 8; i++) {
			int num = this.board[i][col];
			// make sure to disregard 0s
            if (num != 0 && bitSet.get(num - 1)) {
                return false;
            } 

			if (num != 0) {
				bitSet.set(num - 1);
			}
            
        }
        return true;
    }

	// check that 4x2 field has no duplicate numbers
    private boolean checkField(int startRow, int startCol) {
		for (int i = startRow; i < startRow + 4; i++) {
			BitSet bitSet = new BitSet(8); 
			for (int j = startCol; j < startCol + 2; j++) {
				int num = this.board[i][j];
				// make sure to disregard 0s
				if (num != 0 && bitSet.get(num - 1)) {
					return false;
				} 

				if (num != 0) {
					bitSet.set(num - 1);
				}
			}
		}
		return true;
	}

	// check that main diagonal has no duplicate numbers
    private boolean checkDiagonals() {
		BitSet mainDiagonalSet = new BitSet(8);
		for (int i = 0; i < 8; i++) {
			int num = this.board[i][i];
			// make sure to disregard 0s
			if (num != 0 && mainDiagonalSet.get(num - 1)) {
				return false;
			}

			if (num != 0) {
				mainDiagonalSet.set(num - 1);
			}
		}
		
		return true;
	}

	// combine check helpers to full check method
    public boolean check() {
		for (int i = 0; i < 8; i++) {
			if (!checkRow(i) || !checkColumn(i)) {
				return false;
			}
		}
		for (int i = 0; i < 8; i += 4) {
			for (int j = 0; j < 8; j += 2) {
				if (!checkField(i, j)) {
					return false;
				}
			}
		}
		if (this.v == Variante.mitDiagonalen && !checkDiagonals()) {
			return false;
		}
		return true;
	}

	// solver
	public void solve() {
		if (solveHelper(0, 0)) {
			// if diagonal option set check diagonal solution 
			if (this.v == Variante.mitDiagonalen && !checkDiagonals()) {
				StdOut.println("nicht loesbar");
				return;
			}
			write();
		} else {
			StdOut.println("nicht loesbar");
		}
	}
	
	// solve oktadoku via backtracking 
	private boolean solveHelper(int row, int col) {
		// Reached the end, puzzle solved
		if (row == 8) {
			return true; 
		}
		// Move to the next row
		if (col == 8) {
			return solveHelper(row + 1, 0); 
		}
		
		// Cell already filled, move to the next cell
		if (this.board[row][col] != 0) {
			return solveHelper(row, col + 1); 
		}
	
		for (int num = 1; num <= 8; num++) {
			// try placing the number
			if (isValidPlacement(row, col, num)) {
				this.board[row][col] = num; 
				// finish if placing the number leads to a solution 
				if (solveHelper(row, col + 1)) {
					return true; 
				}
				// backtrack if placing the number doesn't lead to a solution
				this.board[row][col] = 0; 
			}
		}
		// if no valid number found for this cell
		return false; 
	}
	
	private boolean isValidPlacement(int row, int col, int num) {
		return isRowValid(row, num) && isColumnValid(col, num) && isFieldValid(row, col, num);
	}
	
	private boolean isRowValid(int row, int num) {
		for (int col = 0; col < 8; col++) {
			if (this.board[row][col] == num) {
				return false; // Number already present in this row
			}
		}
		return true;
	}
	
	private boolean isColumnValid(int col, int num) {
		for (int row = 0; row < 8; row++) {
			if (this.board[row][col] == num) {
				return false; // Number already present in this column
			}
		}
		return true;
	}
	
	private boolean isFieldValid(int row, int col, int num) {
		int startRow = row - (row % 4);
		int startCol = col - (col % 2);
		for (int i = startRow; i < startRow + 4; i++) {
			for (int j = startCol; j < startCol + 2; j++) {
				if (this.board[i][j] == num) {
					return false; // Number already present in this field
				}
			}
		}
		return true;
	}
}
