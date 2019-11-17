package tth_engine;

import java.awt.GridLayout;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import environment.Cell;
import environment.Instance;
import environment.enums.Terrain;

public class CellMetadata {

	public static JPanel getInstanceInfo(Instance instance, JPanel panel) {
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

		return panel;
	}

	public static JPanel getUnNestedCellInfo( JPanel panel) {

		JTextField cellDescription = new JTextField((CellEditor.cell != null) ? CellEditor.cell.getDescription() : "");
		JComboBox terrainBox = new JComboBox(Terrain.values());
		if(CellEditor.cell != null) { terrainBox.setSelectedItem(CellEditor.cell.getTerrain()); }
		JCheckBox isNorth = new JCheckBox();
		JCheckBox isSouth = new JCheckBox();
		JCheckBox isEast = new JCheckBox();
		JCheckBox isWest = new JCheckBox();
		JCheckBox isLocked = new JCheckBox();
		JCheckBox canRest = new JCheckBox();

		if(CellEditor.cell != null) {
			isNorth.setSelected(CellEditor.cell.isNorth());
			isSouth.setSelected(CellEditor.cell.isSouth());
			isEast.setSelected(CellEditor.cell.isEast());
			isWest.setSelected(CellEditor.cell.isWest());
			isLocked.setSelected(CellEditor.cell.isLocked());
			canRest.setSelected(CellEditor.cell.canRest());
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
		
		JPanel otherButtons = new JPanel(new GridLayout(1,2));
		otherButtons.add(new JLabel("Locked"));
		otherButtons.add(isLocked);
		otherButtons.add(new JLabel("Can Rest"));
		otherButtons.add(canRest);

		panel.add(new JLabel("Cell Description"));
		panel.add(cellDescription);
		panel.add(new JLabel("Terrain"));
		panel.add(terrainBox);
		panel.add(new JLabel("Legal Movement"));
		panel.add(directionButtons);
		panel.add(otherButtons);

		return panel;
	}

}
