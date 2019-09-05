package tth_engine;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import pojos.entity.EnemyEntity;
import pojos.entity.NPCEntity;
import pojos.environment.Cell;
import pojos.environment.InspectableObjects;
import pojos.environment.Instance;
import pojos.environment.enums.Terrain;
import pojos.items.Item;
import pojos.items.enums.ItemType;

public class CellEditor {
	protected final Dimension SCREEN_DIM = Toolkit.getDefaultToolkit().getScreenSize();
	protected final int SCREEN_WIDTH = SCREEN_DIM.width;
	protected final int SCREEN_HEIGHT = SCREEN_DIM.height;

	public CellEditor(Cell cell) {		

		JFrame window = new JFrame("Cell Editor");
		JPanel panel = new JPanel(new GridLayout(0, 3));
		window.setSize(SCREEN_WIDTH / 4, SCREEN_HEIGHT);
		window.setBounds(0, 0, SCREEN_WIDTH / 4, SCREEN_HEIGHT / 2);
		JPanel leftPanel = new JPanel(new GridLayout(0,2));
		JPanel middlePanel = new JPanel(new GridLayout(0,2));
		JPanel rightPanel = new JPanel(new GridLayout(0,2));
		
		leftPanel.add(new JLabel("Instance Information"));
		leftPanel.add(getInstanceInfo(cell.getInstance(), new JPanel(new GridLayout(0, 1))));
		leftPanel.add(new JLabel("Cell Information"));
		leftPanel.add(getUnNestedCellInfo(cell, new JPanel(new GridLayout(0, 1))));
		leftPanel.add(new JLabel("Inspectable Item Information"));
		leftPanel.add(getInspectableObjects(cell.getInspectableObjects(), new JPanel(new GridLayout (0,1))));
		
		middlePanel.add(new JLabel("Enemy Information"));
		//TODO Enemies
		middlePanel.add(new JLabel("NPC Information"));
		//TODO NPCs
		middlePanel.add(new JLabel("Item Information"));
		middlePanel.add(getItems(cell.getItems(), new JPanel(new GridLayout(0,1))));
		
		//DELETE THIS
		rightPanel.add(new JLabel("Instance Information"));
		rightPanel.add(getInstanceInfo(cell.getInstance(), new JPanel(new GridLayout(0, 1))));
		rightPanel.add(new JLabel("Cell Information"));
		rightPanel.add(getUnNestedCellInfo(cell, new JPanel(new GridLayout(0, 1))));
		rightPanel.add(new JLabel("Inspectable Item Information"));
		rightPanel.add(getInspectableObjects(cell.getInspectableObjects(), new JPanel(new GridLayout (0,1))));
		
		
		panel.add(leftPanel);
		panel.add(middlePanel);
		panel.add(rightPanel);
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

	private JPanel getInstanceInfo(Instance instance, JPanel panel) {
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

	private JPanel getUnNestedCellInfo(Cell cell, JPanel panel) {

		JTextField cellDescription = new JTextField((cell != null) ? cell.getDescription() : "");
		JComboBox terrainBox = new JComboBox(Terrain.values());
		if(cell != null) { terrainBox.setSelectedItem(cell.getTerrain()); }
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

	private JPanel getInspectableObjects( List<InspectableObjects> objects, JPanel panel) {
		if(objects != null) {
			for(InspectableObjects object : objects) {

				JTextField inspectableName = new JTextField((object) != null ? object.getName() : "");
				JTextField inspectableObject = new JTextField((object) != null ? object.getDescription() : "");

				//TODO
				//add item and npc windows
				JButton enemyButton = new JButton("Add/Edit Enemies");
				JButton itemButton = new JButton("Add/Edit Items");

				JCheckBox isLocked = new JCheckBox();
				isLocked.setSelected((object) != null ? object.isLocked() : false);

				panel.add(new JLabel("Inspectable Name"));
				panel.add(inspectableName);

				panel.add(new JLabel("Inspectable Description"));
				panel.add(inspectableObject);

				panel.add(enemyButton);
				panel.add(itemButton);

				panel.add(new JLabel("Locked"));
				panel.add(isLocked);
			}
		}
		return panel;
	}

	private JPanel getItems(List<Item> items, JPanel panel) {
		if(items != null) {
			for(Item item : items) {

				JTextField itemName = new JTextField((item != null) ? item.getName() : "");
				JTextField itemDescription = new JTextField((item != null) ? item.getDescription() : "");
				JTextField itemUsedDescription = new JTextField((item != null) ? item.getUsedDescription() : "");

				JComboBox itemTypeBox = new JComboBox(ItemType.values());
				if(item != null) { itemTypeBox.setSelectedItem(item.getType()); }

				//sets level to only integers
				SpinnerNumberModel model = new SpinnerNumberModel((item) != null ? item.getMinLevel() : 0, 0, 200, 1);
				JSpinner itemMinLevel = new JSpinner(model);

				JButton remove = new JButton("Remove " + item!= null ? item.getName() : "Item");

				panel.add(remove);
				panel.add(new JLabel("Item Name"));
				panel.add(itemName);
				panel.add(new JLabel("Item Description"));
				panel.add(itemDescription);
				panel.add(new JLabel("Item Used Description"));
				panel.add(itemUsedDescription);
				panel.add(new JLabel("Item Type"));
				panel.add(itemTypeBox);
				panel.add(new JLabel("Item Minimum Level"));
				panel.add(itemMinLevel);
				//TODO
				//Item Stats
			}
		}
		JButton add = new JButton ("Add Item");
		panel.add(add);

		return panel;
	}

	private JPanel getNPCEntities(List<NPCEntity> entities, JPanel panel) {

		return panel;
	}

	private JPanel getEnemyEntities(List<EnemyEntity> entities, JPanel panel) {

		return panel;
	}

	private JPanel updateStats(JPanel panel) {
		return panel;
	}
}
