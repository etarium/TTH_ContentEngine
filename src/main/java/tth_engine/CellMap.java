package tth_engine;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class CellMap extends JPanel {
	private static DBConnector connect = new DBConnector();
	public static int SML_SIDE = 3;
	private static final int SIDE = SML_SIDE * SML_SIDE;
	public static int GAP = 3;
	private static final Color BG = Color.BLACK;
	private static final Dimension BTN_PREF_SIZE = new Dimension(80, 80);
	private JButton[][] buttons = new JButton[SIDE][SIDE];

	public CellMap() {
		setBackground(BG);
		setLayout(new GridLayout(SML_SIDE, SML_SIDE, GAP, GAP));
		setBorder(BorderFactory.createEmptyBorder(GAP, GAP, GAP, GAP));
		JPanel[][] smallPanels = new JPanel[SML_SIDE][SML_SIDE];
		for (int i = 0; i < smallPanels.length; i++) {
			for (int j = 0; j < smallPanels[i].length; j++) {
				smallPanels[i][j] = new JPanel(new GridLayout(SML_SIDE, SML_SIDE));
				add(smallPanels[i][j]);
			}
		}
		for (int i = 0; i < buttons.length; i++) {
			int panelI = i / SML_SIDE;
			for (int j = 0; j < buttons[i].length; j++) {
				int panelJ = j / SML_SIDE;
				String text = String.format("[%d, %d, 0]", j, (buttons.length - 1 - i));
				buttons[i][j] = new JButton(text);
				buttons[i][j].setPreferredSize(BTN_PREF_SIZE);
				smallPanels[panelI][panelJ].add(buttons[i][j]);
			}
		}
		addListeners();
	}

	private static void createAndShowGui() {
		CellMap mainPanel = new CellMap();

		JFrame frame = new JFrame("JPanelGrid");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(mainPanel);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			createAndShowGui();
		});
	}

	private void addListeners() {
		for(JButton[] buttons1D : buttons) {
			for(JButton button : buttons1D) {
				button.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent evt) {
						char charX = button.getText().charAt(1);
						char charY = button.getText().charAt(4);
						int x = Character.getNumericValue(charX);
						int y = Character.getNumericValue(charY);
						int z = 0;
						Location location = new Location (x, y, z);
						String cell = connect.getCellByLocation(location);
						new CellEditor(cell);
					}
				});
			}
		}
	}
}
