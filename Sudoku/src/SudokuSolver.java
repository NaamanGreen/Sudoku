
public class SudokuSolver {

	public static Puzzle puzzle;
	
	public SudokuSolver(Puzzle p) {
		this.puzzle = p;
		
	}
	
	//Check puzzle before solving
	public static boolean checkSubmittedBoard() {
		int[][] board = puzzle.getPuzzle();
		for(int row = 0; row < 9; row++) {
			for(int col = 0; col < 9; col++) {
				int cellValue = board[row][col];
				if(cellValue != 0) {
					if(fullNumCheck(cellValue,row,col,board)) {
						return false;
					}
				}
			}
		}
		return true;
	}

	//Solves puzzle
	public static boolean solvePuzzle()	{
		int[][] board = puzzle.getPuzzle();
	    for(int row=0;row<9;row++){
	        for(int col=0;col<9;col++){
	            if(board[row][col] == 0){
	                for(int number=1;number<=9;number++){

	                    if(!fullNumCheck(number, row, col, board)){
	                        board[row][col] = number;
	                        if(solvePuzzle()){
	                            return true;
	                        }
	                        else{
	                            board[row][col] = 0;
	                        }
	                    }
	                }
	                return false;
	            }
	        }
	    }
	    return true;
	}
	
	//Checks cell value is in row
	public static boolean numInRow(int cell, int[] array) {
		int count = 0;
		for (int i : array) {
			if(cell==i) {
				count++;
			}
		}
		if(count > 0) {
			return true;
		}
		return false;
	}
	
	
	//Checks cell value is in column
	public static boolean numInColumn(int cell, int column, int[][] puzzle) {
		int count = 0;
		
		for(int[] array : puzzle) {
			if(array[column]==cell) {
				count++;
			}
		}
		
		if(count > 0) {
			return true;
		}		
		return false;
	}
	
	//Checks cell value is in sub-grid
	public static boolean numInSubGrid(int cell, int row, int column, int[][] puzzle) {
		int rowStart = row/3*3;
		int rowEnd = rowStart + 2;
		int columnStart = column/3*3;
		int columnEnd = columnStart + 2;
		int gridCount = 0;
		
		for(int i = rowStart; i <= rowEnd; i++) {
			for(int j = columnStart; j <= columnEnd; j++) {
				if(puzzle[i][j] == cell) {
					gridCount++;
				}
			}
		}

		if(gridCount > 0) {
			return true;
		}		

		return false;
	}
	
	
	//Checks cell value is in row, column, or sub-grid
	public static boolean fullNumCheck(int cell, int row, int column, int[][] puzzle) {
		if (numInRow(cell, puzzle[row]) || numInColumn(cell, column, puzzle) || numInSubGrid (cell, row, column, puzzle)) {
			return true;
		}
		return false;
	}
	
}
