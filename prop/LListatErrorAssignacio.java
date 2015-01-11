package prop;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;

public class LListatErrorAssignacio extends PanelLlistatError{
	private CtrlVistaAssignacio ctrlva;
	private JLabel titoldoctor = new JLabel("LLISTA DOCTORS", SwingConstants.CENTER);
	
	public LListatErrorAssignacio(CtrlVistaAssignacio cva) {
		ctrlva = cva;
		remove(scroll1);
		remove(error);
		add(titoldoctor, BorderLayout.NORTH);
		add(scroll1);
		add(error, BorderLayout.SOUTH);
		scroll1.setSize(getSize());
		llista1.addListSelectionListener(this);
	}
	
	public void valueChanged(ListSelectionEvent arg0) {
		if (arg0.getSource() == this.llista1) {
			if (!arg0.getValueIsAdjusting()) {
                /*
                Graf g = ctrlVistaAssignacio.consultaGraf();
                Vertex vd= g.getVertex((String) llista_doc.getSelectedValue(), Vertex.DOCTOR);
                ArrayList<Torn> llista_t = Doc_Torn.getRel((Doctor)vd.getObjecte());

                System.out.println(llista_t.size());

                llr.removeAllElements();
                for(int i=0; i<llista_t.size(); i++){
                    llr.addElement(llista_t.get(i).toString()); // <----- canviar per algo llegible
                }
                */
			}
		}
	}
}
