package prop;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;

public class BotonsAlgorisme extends DosBotons{
	CtrlVistaAlgorisme ctrlval;
	private JLabel textinfo = new JLabel("Informació del Graf:");
	private JLabel textvertexs = new JLabel("Nombre de vertex:");
	private JLabel textarestes = new JLabel("Nombre d'arestes:");
	private String[] algorismes = {"Ford-Fulkerson", "Edmond's Karp", "FF + Dijkstra"};
	private String[] funcionscost = {"Mínim Doctors", "Mínim Sou"};
	private JComboBox algs = new JComboBox(algorismes);
	private JComboBox costs = new JComboBox(funcionscost);
	private GridBagConstraints c = new GridBagConstraints();
	
	public BotonsAlgorisme(CtrlVistaAlgorisme cval) {
		ctrlval = cval;
		remove(b1);
		remove(b2);
		setLayout(new GridBagLayout());
		b1.setText("Crea Graf");
		b2.setText("Executa");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		add(textinfo, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		add(textvertexs, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 1;
		add(textarestes, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 1;
		c.gridwidth = 1;
		add(b1, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 4;
		c.gridwidth = 1;
		add(algs, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 5;
		c.gridwidth = 1;
		add(costs, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 3;
		c.gridwidth = 1;
		add(b2, c);
		
	}
}
