import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUI  {

	//Initialize variables
	private JFrame frame;
	private JPanel messagePanel;
	private JPanel gridPanel;
	private JPanel buttonPanel;
	private JLabel message;
	
	private static JNumberTextField[][] gridArray = new JNumberTextField[9][9];
	private Font font1 = new Font("SansSerif", Font.BOLD, 20);
	
	private JButton solveButton;
	private JButton resetButton;

	private static Puzzle puzzle = new Puzzle();
	private static SudokuSolver solver = new SudokuSolver(puzzle);
	
	public GUI() {
		//Set frame and panels
		frame = new JFrame();
		frame.setSize(500,500);
		frame.setResizable(false);
		
		messagePanel = new JPanel();
		message = new JLabel("       Enter integers 1-9 then click the solve button       ");
		messagePanel.add(message);
		
		gridPanel = new JPanel() {
			public void paintComponent(Graphics g) {
		          super.paintComponent(g);
		          Graphics2D g2 = (Graphics2D) g;
		          g2.setStroke(new BasicStroke(2));
		          g2.setColor(Color.BLACK);  
		          //vertical lines
		          g2.drawLine(30,30,30,388);
		          g2.drawLine(171,30,171,388);
		          g2.drawLine(312,30,312,388);
		          g2.drawLine(453,30,453,388);
		          //horizontal lines
		          g2.drawLine(31,29,452,29);
		          g2.drawLine(31,149,452,149);
		          g2.drawLine(31,269,452,269);
		          g2.drawLine(31,389,452,389);
		       }
		};
		gridPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
		gridPanel.setLayout(new GridLayout(9,9,3,3));
		buttonPanel = new JPanel();

		//Initialize grid
		for(int i = 0; i < 9; i++) {
			for(int j= 0; j < 9; j++) {
				gridArray[i][j] = new JNumberTextField("");
				gridArray[i][j].setHorizontalAlignment(JTextField.CENTER); 
				gridArray[i][j].setFont(font1);
				gridArray[i][j].setDocument(new JTextFieldLimit(1));
				
				gridPanel.add(gridArray[i][j]);
			}
		}
		
		//Buttons
		solveButton = new JButton("Solve");
		solveButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	int[][] sendPuzzle = gridToPuzzle();
		    	puzzle.setPuzzle(sendPuzzle);
		    	solver.solvePuzzle();
		    	int[][] solvedPuzzle = puzzle.getPuzzle();
		    	puzzleToGrid(solvedPuzzle);
		      }
		    }
		  );
		
		resetButton = new JButton("Reset");
		resetButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	for(int i = 0; i < 9; i++) {
					for(int j= 0; j < 9; j++) {
						gridArray[i][j].setText("");
					}
				}
		    }
		  }
		);
		
		buttonPanel.add(solveButton);
		buttonPanel.add(resetButton);
		
		//Final frame setup
		frame.add(messagePanel, BorderLayout.PAGE_START);
		frame.add(gridPanel, BorderLayout.CENTER);
		frame.add(buttonPanel, BorderLayout.PAGE_END);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Sudoku GUI");
		frame.setVisible(true);
	}

	//Turn grid input into board format
	public int[][] gridToPuzzle(){
		int[][] initPuzzle = new int[9][9];
    	for(int i = 0; i < 9; i++) {
			for(int j= 0; j < 9; j++) {
				if(gridArray[i][j].getText().equals("")) {
					initPuzzle[i][j] = 0;
				}
				else {
					initPuzzle[i][j] = Integer.parseInt(gridArray[i][j].getText());	
				}
			}
		}
		return initPuzzle;
	}
	
	//Populate grid with solved puzzle
	public void puzzleToGrid(int[][] solvedPuzzle) {
		for(int i = 0; i < 9; i++) {
			for(int j= 0; j < 9; j++) {
				gridArray[i][j].setText(Integer.toString(solvedPuzzle[i][j]));
			}
		}
	}
	
	
	public static void main(String[] args) {
		new GUI();
	}


}
