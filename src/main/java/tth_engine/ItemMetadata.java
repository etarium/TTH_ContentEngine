package tth_engine;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import environment.InspectableObjects;
import items.Item;
import items.enums.ItemType;

public class ItemMetadata {
	
	public static List<InspectableObjects> newInspectableObjects = new ArrayList<InspectableObjects>();

	public static JPanel getInspectableObjects( List<InspectableObjects> objects, JPanel panel) {
		if(objects != null) {
			for(InspectableObjects object : objects) {

				JTextField inspectableName = new JTextField((object) != null ? object.getName() : "");
				JTextField inspectableDescription = new JTextField((object) != null ? object.getDescription() : "");
				
				
				JButton enemyButton = new JButton("Add/Edit Enemies");
				enemyButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent evt) {
						JFrame itemFrame = addItem();
						itemFrame.pack();
						itemFrame.setVisible(true);
					}
				});
				JButton itemButton = addEditItems(object.getItems());
				JCheckBox isLocked = new JCheckBox();
				isLocked.setSelected((object) != null ? object.isLocked() : false);
				
				InspectableObjects newObject = new InspectableObjects();
				newObject.setLocked(isLocked.isSelected());
				newObject.setName(inspectableName.getText());
				newObject.setDescription(inspectableDescription.getText());
				newInspectableObjects.add(newObject);
				
				panel.add(new JLabel("Inspectable Name"));
				panel.add(inspectableName);

				panel.add(new JLabel("Inspectable Description"));
				panel.add(inspectableDescription);

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
		panel.add(SharedButtons.saveInspectableObjectButton(newInspectableObjects));
		return panel;
	}

	public static JPanel listItems(List<Item> items) {
		//only shows a list of present items. Not editable.
		JPanel panel = new JPanel(new GridLayout(0, 1));
		if (items != null && !items.isEmpty()){
			for(Item item : items) {
				JTextArea itemName = new JTextArea((item.getName() != null) ? item.getName() : "");
				itemName.setEditable(false);
				panel.add(itemName);
			}
		} else {
			panel.add(new JLabel("No Items"));
		}
		JButton addItem = addEditItems(items);
		panel.add(addItem);
		return panel;
	}

	public static JPanel editItems(List<Item> items) {
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

				item.setName(itemName.getText());
				item.setDescription(itemDescription.getText());
				item.setUsedDescription(itemUsedDescription.getText());
				item.setType((ItemType) itemTypeBox.getSelectedItem());
				item.setMinLevel((int) itemMinLevel.getValue()); 
				
				JButton remove = new JButton("Remove Item");
				remove.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent evt) {
						items.remove(item);
					}
				});

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
				itemPanel.add(new JLabel("\n"));
				panel.add(itemPanel);
				panel.add(statPanel);
			}
		} else {
			panel.add(new JLabel("No Items"));
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
		
		//item.setStats();
//		JPanel buttonPanel = new JPanel(new GridLayout(0, 2));
		
//		System.out.println(itemList);
//		buttonPanel.add(SharedButtons.saveItemsInCell(itemList));
		
		JButton save = new JButton("Save");
		save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				
				System.out.println("SaveButton Hit");
				//actually read the data to a pojo
				Item item = new Item();
				item.setName(itemName.getText());
				item.setDescription(itemDescription.getText());
				item.setUsedDescription(itemUsedDescription.getText());
				item.setType((ItemType) itemTypeBox.getSelectedItem());
				item.setMinLevel((int) itemMinLevel.getValue());
				List<Item> itemList = new ArrayList<Item>();
				itemList.add(item);
				CellEditor.cell.setItems(itemList);
				System.out.println("******\n" + CellEditor.cell.getItems());
			}
		});
	
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
		
		itemPanel.add(save);

		panel.add(itemPanel);
		panel.add(statPanel);

		frame.add(panel);
		return frame;
	}

	public static JButton addEditItems(List<Item> items) {
		JButton button = new JButton("Add/Edit Items");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {

				if(items == null || items.isEmpty()) {
					JFrame itemFrame = addItem();
					itemFrame.pack();
					itemFrame.setVisible(true);
				} else {
					JFrame itemFrame = new JFrame("Edit Items");
					JPanel itemPanel = editItems(items);
					itemFrame.add(itemPanel);
					itemFrame.pack();
					itemFrame.setVisible(true);
				}
			}
		});

		return button;
	}
}
