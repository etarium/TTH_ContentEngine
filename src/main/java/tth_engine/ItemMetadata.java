package tth_engine;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import pojos.environment.InspectableObjects;
import pojos.items.Item;
import pojos.items.enums.ItemType;

public class ItemMetadata {
	
	public static JPanel getInspectableObjects( List<InspectableObjects> objects, JPanel panel) {
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

	public static JPanel getItems(List<Item> items, JPanel panel) {
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
		} else {
			panel.add(new JLabel("No Items in this Cell"));
		}
		JButton addItem = new JButton ("Add Item");
		panel.add(addItem);

		return panel;
	}
}
