package prop;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;


public class BotoLlistaPlantilla extends BotoLlista{
	private CtrlVistaPlantilla ctrlvp;
	private BotoLlistaMesPlantilla caractplt;
	
	public BotoLlistaPlantilla(CtrlVistaPlantilla cvp, BotoLlistaMesPlantilla ll) {
		caractplt = ll;
		ctrlvp = cvp;
		JLabel buit = new JLabel();
		remove(b1);
		remove(b2);
		remove(scroll1);
		setLayout(new GridLayout(4,1));
		add(buit);
		add(scroll1);
		b1.addActionListener(this);
		b2.addActionListener(this);
		b1.setText("Enrere");
		b2.setText("Acceptar");
		JPanel btns = new JPanel();
		btns.add(b1);
		btns.add(b2);
		add(btns);

		llista1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		actualitzarLlistaDoctors();
		
	}
	
	public void actionPerformed(ActionEvent arg0) {
		//Tirem enrere
		if (arg0.getSource() == b1) {
		}
		
		//Afegim el doctor seleccionat a la plantilla
		else {
            if(llista1.getSelectedIndex()==-1) {
            	ctrlvp.swap(1,1);
            }
            else {
            	String d = getDNIseleccionatdePlt();
            	String p = caractplt.getNom();
            	ctrlvp.assignarDocAPlt(d,p);
            	caractplt.actualitzarLlistaDoctorsPltNom();
            	actualitzarLlistaDoctors();
            }
		}
		ctrlvp.swap(1,1);
	}
	
    public String getDNIseleccionatdePlt(){
        String content = (String) llista1.getSelectedValue();
        String separadors = "[ \n]";
        String[] separat = content.split(separadors);
        return separat[0];
    }
	
	public void actualitzarLlistaDoctors() {
		String content = ctrlvp.llistaDoctorsSensePlt();
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
