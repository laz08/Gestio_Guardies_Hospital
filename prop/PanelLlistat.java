package prop;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;


public class PanelLlistat extends Panel implements ListSelectionListener{
	
	protected DefaultListModel<String> model1 = new DefaultListModel<String>();
	protected JList<String> llista1 = new JList<String>(model1);
	protected JScrollPane scroll1 = new JScrollPane(llista1);
	public PanelLlistat(){
		setMinimumSize(new Dimension(350,300));
		setMaximumSize(new Dimension(350,600));
		setLayout(new BorderLayout());
		add(scroll1, BorderLayout.CENTER);
        scroll1.setMinimumSize(new Dimension(350, 250));
        scroll1.setPreferredSize(new Dimension(350,250));
        scroll1.setMaximumSize(new Dimension(350, 600));
	}

	public void valueChanged(ListSelectionEvent arg0) {
		
	}
}
