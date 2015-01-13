package prop;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class Resultats extends BotoText{
	private CtrlVistaResultats ctrlvrs;
	private JLabel textresultat = new JLabel("RESULTAT");
	private JLabel texta1 = new JLabel("Ford-Fulkerson");
	private JLabel texta2 = new JLabel("Edmond's Karp");
	private JLabel texta3 = new JLabel("3r Algorisme");
	private JTextField textfield2 = new JTextField("-");
	private JTextField textfield3 = new JTextField("-");
	private GridBagConstraints c = new GridBagConstraints();
	
	
	public Resultats(CtrlVistaResultats cvrs) {
		ctrlvrs = cvrs;
		textfield1.setEditable(false);
		textfield2.setEditable(false);
		textfield3.setEditable(false);
                textfield1.setHorizontalAlignment(JTextField.CENTER);
                textfield2.setHorizontalAlignment(JTextField.CENTER);
                textfield3.setHorizontalAlignment(JTextField.CENTER);

		setLayout(new GridBagLayout());
		remove(b1);
		remove(b2);
		textfield1.setText("-");
		c.insets = new Insets(20, 5, 5,20);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 0;
		c.gridwidth = 1;
		add(textresultat, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		add(texta1, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 1;
		add(textfield1, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 1;
		add(texta2, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 2;
		c.gridwidth = 1;
		add(textfield2, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 1;
		add(texta3, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 3;
		c.gridwidth = 1;
		add(textfield3, c);
		
		
	}
        
        public void setResultats(int nr, long r){
            switch(nr){
                case 0:
                    textfield1.setText(r+" ms");
                    break;
                case 1:
                    textfield2.setText(r+" ms");
                    break;
                case 2:
                    textfield3.setText(r+" ms");
                    break;
            }
        }
}
