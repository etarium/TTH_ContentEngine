package tth_engine;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import environment.Cell;

public class CellEditor {
	protected static  final Dimension SCREEN_DIM = Toolkit.getDefaultToolkit().getScreenSize();
	public static final int SCREEN_WIDTH = SCREEN_DIM.width;
	public static final int SCREEN_HEIGHT = SCREEN_DIM.height;
	public static Cell cell;
	public static Cell originalCell;
	public static JFrame window = new JFrame("Cell Editor");
	
	public CellEditor(Cell selectedCell) {		
		cell = selectedCell;
		window = new JFrame(cell.getLocation().toString());
		JPanel panel = new JPanel(new GridLayout(0, 3));
		window.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		JPanel leftPanel = new JPanel(new GridLayout(0,2));
		JPanel middlePanel = new JPanel(new GridLayout(0,2));
		JPanel rightPanel = new JPanel(new GridLayout(0,2));

		leftPanel.add(new JLabel("Instance Information"));
		leftPanel.add(CellMetadata.getInstanceInfo(cell.getInstance(), new JPanel(new GridLayout(0, 1))));
		leftPanel.add(new JLabel("Cell Information"));
		leftPanel.add(CellMetadata.getUnNestedCellInfo(new JPanel(new GridLayout(0, 1))));
		leftPanel.add(new JLabel("Inspectable Item Information"));
		leftPanel.add(ItemMetadata.getInspectableObjects(cell.getInspectableObjects(), new JPanel(new GridLayout (0,1))));

		middlePanel.add(new JLabel("Enemy Information"));
		middlePanel.add(EntityMetadata.getEnemyEntities(cell.getEnemies(), new JPanel(new GridLayout(0,1))));
		middlePanel.add(new JLabel("NPC Information"));
		middlePanel.add(EntityMetadata.getNPCEntities(cell.getNpcs(), new JPanel(new GridLayout(0,1))));
		middlePanel.add(new JLabel("Item Information"));
		middlePanel.add(ItemMetadata.listItems(cell.getItems()));

		rightPanel.add(SharedButtons.mainWindowButtonPanel());
		panel.add(leftPanel);
		panel.add(middlePanel);
		panel.add(rightPanel);
		window.add(panel);
		window.pack();
		window.setVisible(true);
	}

}
