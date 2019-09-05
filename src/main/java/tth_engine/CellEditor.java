package tth_engine;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class CellEditor {
	protected final Dimension SCREEN_DIM = Toolkit.getDefaultToolkit().getScreenSize();
	protected final int SCREEN_WIDTH = SCREEN_DIM.width;
	protected final int SCREEN_HEIGHT = SCREEN_DIM.height;

	public CellEditor(String cell) {
		JTextArea cellContent = new JTextArea(cell);
		int margin = SCREEN_HEIGHT/24;
		cellContent.setSize(SCREEN_WIDTH / 2, SCREEN_HEIGHT /2 );
		cellContent.setMargin(new Insets(margin,margin,margin, margin));
		cellContent.setLineWrap(true);
		cellContent.setWrapStyleWord(true);
		
//		while(!cell.isEmpty()) {
//			String tempText = "";
//			if(cell.length() > 100) {
//				tempText = cell.substring(0, 100);
//			} else {
//				tempText = cell.substring(0, cell.length());
//			}
//			cell = cell.replace(tempText, "");
//			cellContent.setText(cellContent.getText() + "\n" + tempText);
//			cell.trim();
//			System.out.println(cell);
//		}
		JFrame frame = new JFrame("Cell Editor");

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(cellContent);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
