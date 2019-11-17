package tth_engine;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import entity.EnemyEntity;
import entity.Entity;

public class EntityMetadata {
	
	public static JPanel getNPCEntities(List<Entity> entities, JPanel panel) {
		if(entities != null) {
			//TODO
		} else {
			panel.add(new JLabel("No NPCs in this Cell"));
		}
		JButton addNPC = new JButton ("Add NPC");
		panel.add(addNPC);

		return panel;
	}

	public static JPanel getEnemyEntities(List<EnemyEntity> entities, JPanel panel) {
		if(entities != null) {
			//TODO
		} else {
			panel.add(new JLabel("No Enemies in this Cell"));
		}
		JButton addEnemy = new JButton ("Add Enemy");
		panel.add(addEnemy);
		return panel;
	}
}
