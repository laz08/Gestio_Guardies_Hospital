package prop;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class BotoLlista extends DosBotons{
	
	protected DefaultListModel<String> model1 = new DefaultListModel<String>();
	protected JList<String> llista1 = new JList<String>();
	protected JScrollPane scroll1 = new JScrollPane(llista1);
	
	public BotoLlista() {
		setLayout(new GridBagLayout());
		remove(b1);
		remove(b2);
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 3;
		add(scroll1, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 1;
		add(b1,c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 2;
		c.gridwidth = 1;
		add(b2,c);
	}
	
}
