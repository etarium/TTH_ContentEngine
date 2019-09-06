package tth_engine;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class SharedButtons {

	public static JButton makeSaveButton() {
		JButton save = new JButton("Save");
		return save;
	}
	
	public static JButton makeCancelButton() {
		JButton cancel = new JButton("Cancel");
		return cancel;
	}
	
	public static JPanel buttonPanel() {
		JPanel buttonPanel = new JPanel(new GridLayout(0,2));
		buttonPanel.add(makeSaveButton());
		buttonPanel.add(makeCancelButton());
		
		return buttonPanel;
	}
}
