package prop;

import java.awt.event.ActionEvent;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;


public class BotoLlistaPlantilla extends BotoLlista{
	private CtrlVistaPlantilla ctrlvp;
	private BotoLlistaMesPlantilla caractplt;
	
	public BotoLlistaPlantilla(CtrlVistaPlantilla cvp, BotoLlistaMesPlantilla ll) {
		caractplt = ll;
		ctrlvp = cvp;
		b1.setText("Enrere");
		b2.setText("Acceptar");
		b1.addActionListener(this);
		b2.addActionListener(this);
		llista1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		actualitzarLlistaDoctors();
	}
	
	public void actionPerformed(ActionEvent arg0) {
		//Tirem enrere
		if (arg0.getSource() == b1) {
		}
		
		//Afegim el doctor seleccionat a la plantilla
		else {
            if(llista1.getSelectedIndex()==-1) ctrlvp.swap(1,1);
            else {
            	String d = llista1.getSelectedValue();
            	String p = caractplt.getNom();
            	ctrlvp.assignarDocAPlt(d,p);
            	caractplt.actualitzarLlistaDoctorsPlt();
            	actualitzarLlistaDoctors();
            }
		}
		ctrlvp.swap(1,1);
	}
	
	public void actualitzarLlistaDoctors() {
		String content = ctrlvp.llistaDoctors();
        model1.removeAllElements();
        if (!content.equals("")) {
            if (content.length() > 0) {
                String separadors = "[ \n]";
                String[] separat = content.split(separadors);
                for (int i = 0; i < separat.length; i += 4) {
                    model1.addElement(separat[i] + " " + separat[i + 1] + " " + separat[i + 2] + " " + separat[i + 3]);
                }
            }
        }
	}
	
}
