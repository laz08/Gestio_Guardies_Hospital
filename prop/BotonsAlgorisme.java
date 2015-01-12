package prop;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

public class BotonsAlgorisme extends DosBotons {

    CtrlVistaAlgorisme ctrlval;
    private JLabel textinfo = new JLabel("Informació del Graf:");
    private JLabel textvertexs = new JLabel("Nombre de vertex:");
    private JLabel textarestes = new JLabel("Nombre d'arestes:");
    private String[] algorismes = {"Ford-Fulkerson", "Edmond's Karp", "FF + Dijkstra"};
    private String[] funcionscost = {"Mínim Doctors", "Mínim Sou"};
    private JRadioButton alg1 = new JRadioButton("Ford-Fulkerson");
    private JRadioButton alg2 = new JRadioButton("Edmonds-Karp");
    private JRadioButton alg3 = new JRadioButton("FF+Dijkstra");
    private JRadioButton cost1 = new JRadioButton("Mínim de Doctors");
    private JRadioButton cost2 = new JRadioButton("Mínim Doctors i Sou");
    private GridBagConstraints c = new GridBagConstraints();
    private ButtonGroup grupAlg = new ButtonGroup();
    private ButtonGroup grupCost = new ButtonGroup();

    public BotonsAlgorisme(CtrlVistaAlgorisme cval) {
        ctrlval = cval;
        remove(b1);
        remove(b2);
        setLayout(new GridBagLayout());
        b1.setText("Crea Graf");
        b2.setText("Executa");
        b2.setEnabled(false);
        alg1.setSelected(true);
        grupAlg.add(alg1);
        grupAlg.add(alg2);
        grupAlg.add(alg3);
        cost1.setSelected(true);
        grupCost.add(cost1);
        grupCost.add(cost2);
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
        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	ctrlval.iniciarGraf();
                //CtrlAlgorisme.initGraf();
                String tv = textvertexs.getText();
                String[] text = tv.split(":");
                textvertexs.setText(text[0] + ": " + CtrlAlgorisme.getNumVertex());
                String ta = textarestes.getText();
                text = ta.split(":");
                textarestes.setText(text[0] + ": " + CtrlAlgorisme.getNumArestes());
                ctrlval.actualitzaDocTorns();
                b2.setEnabled(true);
            }
        });

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 1;
        add(alg1, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 4;
        c.gridwidth = 1;
        add(cost1, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 5;
        c.gridwidth = 1;
        add(alg2, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 5;
        c.gridwidth = 1;
        add(cost1, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 6;
        c.gridwidth = 1;
        add(alg3, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 6;
        c.gridwidth = 1;
        add(cost2, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 3;
        c.gridwidth = 1;
        add(b2, c);

        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                ExecutaAlgorisme(evt);
            }
        });

    }

    private void ExecutaAlgorisme(ActionEvent evt) {
        Algorisme algo;
        boolean b = ctrlval.iniciarExecucio();
        if(b) {
	        if (alg1.isSelected()) {
	            if (cost1.isSelected()) {
	                CtrlAlgorisme.executaFordFulkerson(false);
	            } else {
	                CtrlAlgorisme.executaFordFulkerson(true);
	            }
	        } else if (alg2.isSelected()) {
	            if (cost1.isSelected()) {
	                CtrlAlgorisme.executaEdmondsKarp(false);
	            } else {
	                CtrlAlgorisme.executaEdmondsKarp(true);
	            }
	        } else {
	            if (cost1.isSelected()) {
	                CtrlAlgorisme.executaDijkstra(false);
	            } else {
	                CtrlAlgorisme.executaDijkstra(true);
	            }
	        }
        }
    }
}
