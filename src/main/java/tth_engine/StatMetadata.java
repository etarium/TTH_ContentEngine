package tth_engine;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import general.Statblock;

public class StatMetadata {

	public static JPanel addStats() {

		JPanel panel = new JPanel(new GridLayout (0,2));
		SpinnerNumberModel hpStat = new SpinnerNumberModel(0, -1000, 10000, 1);
		SpinnerNumberModel spStat = new SpinnerNumberModel(0, -1000, 10000, 1);
		SpinnerNumberModel atkStat = new SpinnerNumberModel(0, -1000, 10000, 1);
		SpinnerNumberModel defStat = new SpinnerNumberModel(0, -1000, 10000, 1);
		SpinnerNumberModel spAtkStat = new SpinnerNumberModel(0, -1000, 10000, 1);
		SpinnerNumberModel spDefStat = new SpinnerNumberModel(0, -1000, 10000, 1);
		SpinnerNumberModel evaStat = new SpinnerNumberModel(0, -1000, 10000, 1);
		SpinnerNumberModel intelStat = new SpinnerNumberModel(0, -1000, 10000, 1);
		SpinnerNumberModel chaStat = new SpinnerNumberModel(0, -1000, 10000, 1);
		SpinnerNumberModel staStat = new SpinnerNumberModel(0, -1000, 10000, 1);
		SpinnerNumberModel agiStat = new SpinnerNumberModel(0, -1000, 10000, 1);
		SpinnerNumberModel accStat = new SpinnerNumberModel(0, -1000, 10000, 1);

		JSpinner hp = new JSpinner(hpStat);
		JSpinner sp = new JSpinner(spStat);
		JSpinner atk = new JSpinner(atkStat);
		JSpinner def = new JSpinner(defStat);
		JSpinner spatk = new JSpinner(spAtkStat);
		JSpinner spdef = new JSpinner(spDefStat);
		JSpinner eva = new JSpinner(evaStat);
		JSpinner intel = new JSpinner(intelStat);
		JSpinner cha = new JSpinner(chaStat);
		JSpinner sta = new JSpinner(staStat);
		JSpinner agi = new JSpinner(agiStat);
		JSpinner acc = new JSpinner(accStat);

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
		if(stats != null) {
			JPanel panel = new JPanel(new GridLayout (0,2));
			SpinnerNumberModel hpStat = new SpinnerNumberModel((stats.getHp() != 0) ? stats.getHp() : 0, -1000, 10000, 1);
			SpinnerNumberModel spStat = new SpinnerNumberModel((stats.getSp() != 0) ? stats.getSp() :0, -1000, 10000, 1);
			SpinnerNumberModel atkStat = new SpinnerNumberModel((stats.getAtk() != 0) ? stats.getAtk() :0, -1000, 10000, 1);
			SpinnerNumberModel defStat = new SpinnerNumberModel((stats.getDef() != 0) ? stats.getDef() :0, -1000, 10000, 1);
			SpinnerNumberModel spAtkStat = new SpinnerNumberModel((stats.getSpatk() != 0) ? stats.getSpatk() :0, -1000, 10000, 1);
			SpinnerNumberModel spDefStat = new SpinnerNumberModel((stats.getSpdef() != 0) ? stats.getSpdef() :0, -1000, 10000, 1);
			SpinnerNumberModel evaStat = new SpinnerNumberModel((stats.getEva() != 0) ? stats.getEva() :0, -1000, 10000, 1);
			SpinnerNumberModel intelStat = new SpinnerNumberModel((stats.getIntel() != 0) ? stats.getIntel() :0, -1000, 10000, 1);
			SpinnerNumberModel chaStat = new SpinnerNumberModel((stats.getCha() != 0) ? stats.getCha() :0, -1000, 10000, 1);
			SpinnerNumberModel staStat = new SpinnerNumberModel((stats.getSta() != 0) ? stats.getSta() :0, -1000, 10000, 1);
			SpinnerNumberModel agiStat = new SpinnerNumberModel((stats.getAgi() != 0) ? stats.getAgi() :0, -1000, 10000, 1);
			SpinnerNumberModel accStat = new SpinnerNumberModel((stats.getAcc() != 0) ? stats.getAcc() :0, -1000, 10000, 1);

			JSpinner hp = new JSpinner(hpStat);
			JSpinner sp = new JSpinner(spStat);
			JSpinner atk = new JSpinner(atkStat);
			JSpinner def = new JSpinner(defStat);
			JSpinner spatk = new JSpinner(spAtkStat);
			JSpinner spdef = new JSpinner(spDefStat);
			JSpinner eva = new JSpinner(evaStat);
			JSpinner intel = new JSpinner(intelStat);
			JSpinner cha = new JSpinner(chaStat);
			JSpinner sta = new JSpinner(staStat);
			JSpinner agi = new JSpinner(agiStat);
			JSpinner acc = new JSpinner(accStat);

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
		} else {
			return addStats();
		}
	}
}
