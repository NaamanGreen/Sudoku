import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUI  {

	private JFrame frame;
	private JPanel messagePanel;
	private JLabel message;
	private JPanel gridPanel;
	private JPanel buttonPanel;
	private static JTextField[][] gridArray = new JTextField[9][9];
	
	private JButton button;

	
	public GUI() {
		frame = new JFrame();
		
		messagePanel = new JPanel();
		message = new JLabel("       Enter integers 1-9 the click the solve button       ");
		messagePanel.add(message);
		
		button = new JButton("Solve");
		button.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	int[][] sendPuzzle = new int[9][9];

		    	for(int i = 0; i < 9; i++) {
					for(int j= 0; j < 9; j++) {
						if(gridArray[i][j].getText() == "") {
							sendPuzzle[i][j] = 0;
						}
						else {
							sendPuzzle[i][j] = Integer.parseInt(gridArray[i][j].getText());							
						}
					}
				}
		    	
		    	SudokuSolver.setPuzzle(sendPuzzle);
		    	SudokuSolver.solvePuzzle();
		    	int[][] solvedPuzzle = SudokuSolver.getPuzzle();
		    	
		    	for(int i = 0; i < 9; i++) {
					for(int j= 0; j < 9; j++) {
						gridArray[i][j].setText(Integer.toString(solvedPuzzle[i][j]));
					}
				}
		      }
		    }
		  );
		
		gridPanel = new JPanel();
		gridPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
		gridPanel.setLayout(new GridLayout(9,9,3,3));
		
		buttonPanel = new JPanel();

		for(int i = 0; i < 9; i++) {
			for(int j= 0; j < 9; j++) {
				gridArray[i][j] = new JTextField("0");
				gridPanel.add(gridArray[i][j]);
			}
		}
		
		buttonPanel.add(button);
		
		frame.add(messagePanel, BorderLayout.PAGE_START);
		frame.add(gridPanel, BorderLayout.CENTER);
		frame.add(buttonPanel, BorderLayout.PAGE_END);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Our GUI");
		frame.pack();
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		new GUI();


	}


}
