
public class Puzzle {

	int[][] puzzle = {
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0}
	};
	
	public void setPuzzle(int[][] board) {
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				puzzle[i][j] = board[i][j];
			}
		}
	}
	
	public int[][] getPuzzle(){
		return puzzle;
	}

	public String getCell(int row, int col) {
		return String.valueOf(puzzle[row][col]);
	}
	
	public static void printPuzzle(int[][] puzzle) {
		for(int[] array : puzzle) {
			for (int j : array) {
				System.out.print(j + " ");
			}
			System.out.println();
		}
	}
}
