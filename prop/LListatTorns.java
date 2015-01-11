package prop;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;

public class LListatTorns extends PanelLlistat{
	private CtrlVistaAssignacio ctrlva;
	private JLabel titoltorns = new JLabel("LLISTA DE TORNS", SwingConstants.CENTER);
	
	public LListatTorns(CtrlVistaAssignacio cva) {
		ctrlva = cva;
		remove(scroll1);
		add(titoltorns, BorderLayout.NORTH);
		add(scroll1);
		super.setMaximumSize(new Dimension(350, 300));
		scroll1.setMaximumSize(new Dimension(350, 300));
		llista1.addListSelectionListener(this);
	}
	
	public void valueChanged(ListSelectionEvent arg0) {
		if (arg0.getSource() == this.llista1) {
			if (!arg0.getValueIsAdjusting()) {
               /*
                Graf g = ctrlVistaAssignacio.consultaGraf();

                Vertex vt= g.getVertex((String) llista1.getSelectedValue(), Vertex.TORN);
                ArrayList<Doctor> llista_d = Doc_Torn.getRel((Torn)vt.getObjecte());
                llr.removeAllElements();
                for(int i=0; i<llista_d.size(); i++){
                    Doctor doc = llista_d.get(i);
                    llr.addElement(doc.getdni()); // <----- canviar per algo llegible
                }
                */
			}
		}
	}
}
