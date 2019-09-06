package tth_engine;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import pojos.Statblock;

public class StatMetadata {

	public static JPanel addStats() {
		
		JPanel panel = new JPanel(new GridLayout (0,2));
		SpinnerNumberModel hpSpStats = new SpinnerNumberModel(0, 0, 10000, 1);
		SpinnerNumberModel model = new SpinnerNumberModel(0, 0, 1000, 1);
		JSpinner hp = new JSpinner(hpSpStats);
		JSpinner sp = new JSpinner(hpSpStats);
		JSpinner atk = new JSpinner(model);
		JSpinner def = new JSpinner(model);
		JSpinner spatk = new JSpinner(model);
		JSpinner spdef = new JSpinner(model);
		JSpinner eva = new JSpinner(model);
		JSpinner intel = new JSpinner(model);
		JSpinner cha = new JSpinner(model);
		JSpinner sta = new JSpinner(model);
		JSpinner agi = new JSpinner(model);
		JSpinner acc = new JSpinner(model);
		
		panel.add(new JLabel("HP"));
		panel.add(hp);
		panel.add(new JLabel("SP"));
		panel.add(sp);
		panel.add(new JLabel("Attack"));
		panel.add(atk);
		panel.add(new JLabel("Defense"));
		panel.add(def);
		panel.add(new JLabel("Special Attack"));
		panel.add(spatk);
		panel.add(new JLabel("Special Defense"));
		panel.add(spdef);
		panel.add(new JLabel("Evasion"));
		panel.add(eva);
		panel.add(new JLabel("Intelligence"));
		panel.add(intel);
		panel.add(new JLabel("Charisma"));
		panel.add(cha);
		panel.add(new JLabel("Stamina"));
		panel.add(sta);
		panel.add(new JLabel("Agility"));
		panel.add(agi);
		panel.add(new JLabel("Accuracy"));
		panel.add(acc);
		
		return panel;
	}
	public static JPanel updateStats(Statblock stats) {
		
		return new JPanel();
	}
}
