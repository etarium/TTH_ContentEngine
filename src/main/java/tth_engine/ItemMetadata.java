package tth_engine;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import pojos.environment.Cell;
import pojos.environment.InspectableObjects;
import pojos.environment.Location;
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
				enemyButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent evt) {
						JFrame itemFrame = addItem();
						itemFrame.pack();
						itemFrame.setVisible(true);
					}
				});
				JButton itemButton = new JButton("Add/Edit Items");
				itemButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent evt) {
						if(object.getItems().isEmpty()) {
							JFrame itemFrame = addItem();
							itemFrame.pack();
							itemFrame.setVisible(true);
						} else {
							JFrame itemFrame = new JFrame("Edit Items");
							JPanel itemPanel = getItems(object.getItems());
							itemFrame.add(itemPanel);
							itemFrame.pack();
							itemFrame.setVisible(true);
						}
					}
				});
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
		} else {
			panel.add(new JLabel("No Inspectable Objects in this Cell"));
		}
		JButton addObject = new JButton ("Add Inspectable Object");
		panel.add(addObject);
		return panel;
	}

	public static JPanel getItems(List<Item> items) {
		JPanel panel = new JPanel(new GridLayout(0,2));
		JPanel itemPanel;
		JPanel statPanel;
		if(items != null) {
			for(Item item : items) {
				//reinitialize them back to empty at each iteration
				statPanel = new JPanel();
				itemPanel = new JPanel(new GridLayout(0,1));
				statPanel = StatMetadata.updateStats(item.getStats());
				JTextField itemName = new JTextField((item.getName() != null) ? item.getName() : "");
				JTextField itemDescription = new JTextField((item.getDescription() != null) ? item.getDescription() : "");
				JTextField itemUsedDescription = new JTextField((item.getUsedDescription() != null) ? item.getUsedDescription() : "");

				JComboBox itemTypeBox = new JComboBox(ItemType.values());
				if(item.getType() != null) { itemTypeBox.setSelectedItem(item.getType()); }

				//sets level to only integers
				SpinnerNumberModel model = new SpinnerNumberModel((item) != null ? item.getMinLevel() : 0, 0, 200, 1);
				JSpinner itemMinLevel = new JSpinner(model);

				JButton remove = new JButton("Remove Item");

				itemPanel.add(new JLabel("Item Name"));
				itemPanel.add(itemName);
				itemPanel.add(new JLabel("Item Description"));
				itemPanel.add(itemDescription);
				itemPanel.add(new JLabel("Item Used Description"));
				itemPanel.add(itemUsedDescription);
				itemPanel.add(new JLabel("Item Type"));
				itemPanel.add(itemTypeBox);
				itemPanel.add(new JLabel("Item Minimum Level"));
				itemPanel.add(itemMinLevel);
				itemPanel.add(remove);
				//itemPanel.add(statPanel);
				itemPanel.add(new JLabel("\n"));
				panel.add(itemPanel);
				panel.add(statPanel);
			}
		} else {
			panel.add(new JLabel("No Items in this Cell"));
		}
		JButton addItem = new JButton ("Add Item");
		addItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				JFrame itemFrame = addItem();
				itemFrame.pack();
				itemFrame.setVisible(true);
			}
		});
		JPanel farBottomPanel = new JPanel();
		farBottomPanel.add(addItem);
		panel.add(farBottomPanel, "Add Item");
		return panel;
	}

	public static JFrame addItem() {
		JPanel panel = new JPanel(new GridLayout(0,2));
		JPanel itemPanel = new JPanel(new GridLayout(0,1));
		JPanel statPanel = StatMetadata.addStats();
		JFrame frame = new JFrame("Add An Item");
		frame.setPreferredSize(new Dimension(CellEditor.SCREEN_WIDTH / 3, CellEditor.SCREEN_HEIGHT / 2));

		JTextField itemName = new JTextField("");
		JTextField itemDescription = new JTextField("");
		JTextField itemUsedDescription = new JTextField("");

		JComboBox itemTypeBox = new JComboBox(ItemType.values());

		//sets level to only integers
		SpinnerNumberModel model = new SpinnerNumberModel(0, 0, 200, 1);
		JSpinner itemMinLevel = new JSpinner(model);

		itemPanel.add(new JLabel("Item Name"));
		itemPanel.add(itemName);
		itemPanel.add(new JLabel("Item Description"));
		itemPanel.add(itemDescription);
		itemPanel.add(new JLabel("Item Used Description"));
		itemPanel.add(itemUsedDescription);
		itemPanel.add(new JLabel("Item Type"));
		itemPanel.add(itemTypeBox);
		itemPanel.add(new JLabel("Item Minimum Level"));
		itemPanel.add(itemMinLevel);
		itemPanel.add(SharedButtons.buttonPanel());

		panel.add(itemPanel);
		panel.add(statPanel);

		frame.add(panel);
		return frame;
	}
}
