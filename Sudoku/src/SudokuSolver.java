
public class SudokuSolver {

	public static int[][] puzzle;
	
	public SudokuSolver(int[][] puzzleToSolve) {
		this.puzzle = puzzleToSolve;
		
	}

	public static void printPuzzle() {
		for(int[] array : puzzle) {
			for (int j : array) {
				System.out.print(j + " ");
			}
			System.out.println();
		}
	}
	
	
	public static boolean solvePuzzle()
	{
	    for(int row=0;row<9;row++){
	        for(int col=0;col<9;col++){
	            if(puzzle[row][col] == 0){
	                for(int number=1;number<=9;number++){

	                    if(!fullNumCheck(number, row, col, puzzle)){
	                        puzzle[row][col] = number;
	                        if(solvePuzzle()){
	                            return true;
	                        }
	                        else{
	                            puzzle[row][col] = 0;
	                        }
	                    }
	                }
	                return false;
	            }
	        }
	    }
	    return true;
	}
	
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
	
	public static boolean fullNumCheck(int cell, int row, int column, int[][] puzzle) {
		if (numInRow(cell, puzzle[row]) || numInColumn(cell, column, puzzle) || numInSubGrid (cell, row, column, puzzle)) {
			return true;
		}
		return false;
	}
		
	public static void setPuzzle(int[][] sentPuzzle) {
		puzzle = sentPuzzle;
	}
	
	public static int[][] getPuzzle(){
		return puzzle;
	}

}
