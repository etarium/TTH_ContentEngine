package tth_engine;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import pojos.entity.EnemyEntity;
import pojos.entity.Entity;
import pojos.entity.NPCEntity;
import pojos.environment.InspectableObjects;
import pojos.items.Item;


public class SharedButtons {
	
	public static DBConnector connect = new DBConnector();
	
	//write to database
	public static JButton makeSaveButton() {
		JButton save = new JButton("Save");
		save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
			//	CellEditor.cell.setLocation(location);
				System.out.println("&&&&&&&&&&&&&&&&& save to DB\n" + CellEditor.cell.getItems());
				connect.writeCell(CellEditor.cell);
			}
		});
		return save;
	}
	
	public static JButton saveInspectableObjectButton(List<InspectableObjects> objects) {
		JButton save = new JButton("Save");
		save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				CellEditor.cell.setInspectableObjects(objects);
			}
		});
		return save;
	}
	
	public static JButton saveItemsInCell(List<Item> items) {
		JButton save = new JButton("Save");
		save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				CellEditor.cell.setItems(items);
				
			}
		});
		return save;
	}
	
	public static JButton saveNPCs(List<Entity> npcs) {
		JButton save = new JButton("Save");
		save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				CellEditor.cell.setNpcs(npcs);
			}
		});
		return save;
	}
	
	public static JButton saveEnemies(List<EnemyEntity> enemies) {
		JButton save = new JButton("Save");
		save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				CellEditor.cell.setEnemies(enemies);
			}
		});
		return save;
	}
	
	public static JButton saveEnemiesInInspectable(InspectableObjects newObject, List<Entity> enemies) {
		JButton save = new JButton("Save");
		save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				newObject.setEnemies(enemies);
				for(InspectableObjects object : CellEditor.cell.getInspectableObjects()) {
					if(object.getName().equals(newObject.getName())) {
						CellEditor.cell.getInspectableObjects().remove(object);
					}
				}
				CellEditor.cell.getInspectableObjects().add(newObject);
			}
		});
		return save;
	}
	
	public static JButton saveItemsInInspectable(InspectableObjects newObject, List<Item> items) {
		JButton save = new JButton("Save");
		save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				newObject.setItems(items);
				for(InspectableObjects object : CellEditor.cell.getInspectableObjects()) {
					if(object.getName().equals(newObject.getName())) {
						CellEditor.cell.getInspectableObjects().remove(object);
					}
				}
				CellEditor.cell.getInspectableObjects().add(newObject);
			}
		});
		return save;
	}
	
	public static JButton makeCancelButton() {
		JButton cancel = new JButton("Cancel");
		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				CellEditor.window.dispose();
			}
		});
		return cancel;
	}
	
	
	public static JPanel mainWindowButtonPanel() {
		JPanel buttonPanel = new JPanel(new GridLayout(0,2));
		buttonPanel.add(makeSaveButton());
		buttonPanel.add(makeCancelButton());
		
		return buttonPanel;
	}
	
}
