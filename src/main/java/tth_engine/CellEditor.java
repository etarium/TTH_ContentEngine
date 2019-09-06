package tth_engine;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import pojos.environment.Cell;

public class CellEditor {
	protected static  final Dimension SCREEN_DIM = Toolkit.getDefaultToolkit().getScreenSize();
	public static final int SCREEN_WIDTH = SCREEN_DIM.width;
	public static final int SCREEN_HEIGHT = SCREEN_DIM.height;
	public static Cell cell = new Cell();

	public CellEditor(Cell selectedCell) {		
		cell = selectedCell;
		JFrame window = new JFrame("Cell Editor");
		JPanel panel = new JPanel(new GridLayout(0, 3));
		window.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		JPanel leftPanel = new JPanel(new GridLayout(0,2));
		JPanel middlePanel = new JPanel(new GridLayout(0,2));
		JPanel rightPanel = new JPanel(new GridLayout(0,2));

		leftPanel.add(new JLabel("Instance Information"));
		leftPanel.add(CellMetadata.getInstanceInfo(cell.getInstance(), new JPanel(new GridLayout(0, 1))));
		leftPanel.add(new JLabel("Cell Information"));
		leftPanel.add(CellMetadata.getUnNestedCellInfo(cell, new JPanel(new GridLayout(0, 1))));
		leftPanel.add(new JLabel("Inspectable Item Information"));
		leftPanel.add(ItemMetadata.getInspectableObjects(cell.getInspectableObjects(), new JPanel(new GridLayout (0,1))));

		middlePanel.add(new JLabel("Enemy Information"));
		middlePanel.add(EntityMetadata.getEnemyEntities(cell.getEnemies(), new JPanel(new GridLayout(0,1))));
		middlePanel.add(new JLabel("NPC Information"));
		middlePanel.add(EntityMetadata.getNPCEntities(cell.getNpcs(), new JPanel(new GridLayout(0,1))));
		middlePanel.add(new JLabel("Item Information"));
		middlePanel.add(ItemMetadata.getItems(cell.getItems(), new JPanel(new GridLayout(0,1))));

//		rightPanel.add(cancelButton);
//		rightPanel.add(saveButton);
		panel.add(leftPanel);
		panel.add(middlePanel);
		panel.add(rightPanel);
		window.add(panel);
		window.pack();
		window.setVisible(true);
	}

	private JPanel updateStats(JPanel panel) {
		return panel;
	}
}
