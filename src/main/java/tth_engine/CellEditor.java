package tth_engine;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import pojos.environment.Cell;
import pojos.environment.Instance;
import pojos.environment.enums.Terrain;

public class CellEditor {
	protected final Dimension SCREEN_DIM = Toolkit.getDefaultToolkit().getScreenSize();
	protected final int SCREEN_WIDTH = SCREEN_DIM.width;
	protected final int SCREEN_HEIGHT = SCREEN_DIM.height;

	public CellEditor(Cell cell) {		

		JFrame window = new JFrame("Cell Editor");
		JPanel panel = new JPanel(new GridLayout(0, 1));
		getUnNestedCellInfo(cell, panel);
		//makeFormWindow(cell);
		window.add(panel);
		window.pack();
		window.setVisible(true);
	}

	private JPanel makeFormWindow(Cell cell) {
		//get all the values

		JPanel panel = new JPanel(new GridLayout(0, 1));

		getInstanceInfo(cell.getInstance(), panel);
		int result = JOptionPane.showConfirmDialog(null, panel, "Instance Editor",
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
		if (result == JOptionPane.OK_OPTION) {
		} else {
			System.out.println("Cancelled");
		}

		return panel;
	}

	private void getInstanceInfo(Instance instance, JPanel panel) {
		JTextField instanceName = new JTextField((instance) != null ? instance.getName() : "");
		JTextField instanceDescription = new JTextField((instance) != null ? instance.getDescription() : "");

		//sets level to only integers
		SpinnerNumberModel model = new SpinnerNumberModel((instance) != null ? instance.getMinLevel() : 0, 0, 200, 1);
		JSpinner instanceMinLevel = new JSpinner(model);


		panel.add(new JLabel("Instance Name"));
		panel.add(instanceName);

		panel.add(new JLabel("Instance Description"));
		panel.add(instanceDescription);

		panel.add(new JLabel("Instance Minimum Level"));
		panel.add(instanceMinLevel);
	}

	private JPanel getUnNestedCellInfo(Cell cell, JPanel panel) {

		JTextField cellDescription = new JTextField((cell != null) ? cell.getDescription() : "");
		JComboBox terrainBox = new JComboBox(Terrain.values());
		JCheckBox isNorth = new JCheckBox();
		JCheckBox isSouth = new JCheckBox();
		JCheckBox isEast = new JCheckBox();
		JCheckBox isWest = new JCheckBox();
		JCheckBox isLocked = new JCheckBox();

		if(cell != null) {
			isNorth.setSelected(cell.isNorth());
			isSouth.setSelected(cell.isSouth());
			isEast.setSelected(cell.isEast());
			isWest.setSelected(cell.isWest());
			isLocked.setSelected(cell.isLocked());
		}

		JPanel directionButtons = new JPanel(new GridLayout(1,4));
		directionButtons.add(new JLabel("North"));
		directionButtons.add(isNorth);
		directionButtons.add(new JLabel("South"));
		directionButtons.add(isSouth);
		directionButtons.add(new JLabel("East"));
		directionButtons.add(isEast);
		directionButtons.add(new JLabel("West"));
		directionButtons.add(isWest);


		panel.add(new JLabel("Cell Description"));
		panel.add(cellDescription);
		panel.add(new JLabel("Terrain"));
		panel.add(terrainBox);
		panel.add(new JLabel("Legal Movement"));
		panel.add(directionButtons);
		panel.add(new JLabel("Locked"));
		panel.add(isLocked);

		return panel;
	}
}
