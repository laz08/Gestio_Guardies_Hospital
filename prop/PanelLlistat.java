package prop;
import java.awt.BorderLayout;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class PanelLlistat extends Panel implements ListSelectionListener{
	
	protected DefaultListModel<String> model1 = new DefaultListModel<String>();
	protected JList<String> llista1 = new JList<String>(model1);
	protected JScrollPane scroll1 = new JScrollPane(llista1);
	public PanelLlistat(){
		setLayout(new BorderLayout());
		add(scroll1, BorderLayout.CENTER);
	}

	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
