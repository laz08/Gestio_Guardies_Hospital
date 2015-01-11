package prop;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;


public class BotoLlistaMesPlantilla extends BotoLlista {
	private CtrlVistaPlantilla ctrlvp;
	private JLabel nomPlantilla = new JLabel();
	private JLabel plt = new JLabel();
	private JButton b3 = new JButton("Eliminar Plantilla");
	private JButton b4 = new JButton("Enrere");
	private LlistatErrorPlantilla llistat;
	private JTextField nom = new JTextField();
	
	public BotoLlistaMesPlantilla(CtrlVistaPlantilla cvp, LlistatErrorPlantilla ll) {
		llistat = ll;
		ctrlvp = cvp;
		nom.setEditable(false);
		plt = new JLabel("Plantilla ");
		nomPlantilla.setLayout(new GridLayout(1,2));
		nomPlantilla.add(plt);
		nomPlantilla.add(nom);
		remove(b1);
		remove(b2);
		remove(scroll1);
		setLayout(new GridLayout(6,1));
		add(nomPlantilla);
		add(scroll1);
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		b1.setText("Assignar Doctor");
		b2.setText("Desassignar Doctor");
		add(b1);
		add(b2);
		add(b3);
		add(b4);
		//Actualitzem llista de doctors de la plantilla on ens trobem
		//faig el if perque no dongui error de nullpointer, pero es redundant
		if(llistat.llista1.getSelectedIndex()!=-1) actualitzarLlistaDoctorsPlt();
		
	}
	
	public void actualitzarLlistaDoctorsPlt() {
		//Mostrem tots els doctors que pertanyen a la plantilla
		String s = nom.getText();
		model1.addElement(s);
		String content = ctrlvp.llistaDoctorsPlantilla(s);
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
	
	public void actionPerformed(ActionEvent arg0) {
		//b1=assignar doctor, canviem de panell (BotoLlistaPlantilla)
		if (arg0.getSource() == b1) {
			ctrlvp.swap(1,2);
		}
		
		//b2=desasignar doctor, treiem de la llista el que tenim seleccionat
		else if (arg0.getSource() == b2) {
			desasignarDoctor();
			actualitzarLlistaDoctorsPlt();
			//Pot ser tambe haig d'actualitzar la llista de doctors que no tenen plt
		}
		
		//b3=eliminar plantilla, es posem tots els doctors a inactius i eliminem la plantilla
		else if (arg0.getSource() == b3) {
			eliminarPlt();
			ctrlvp.swap(2,1);
		}
		//Tirem enrere
		else {
			ctrlvp.swap(2,1);
		}
	}
	
	
	//Funcions auxiliars
	public void eliminarPlt() {
		ctrlvp.eliminarPlantilla(nom.getText());
		llistat.actualitzarLlistatPlantilles();
	}
	
	public void desasignarDoctor() {
		if(llista1.getSelectedIndex()!=-1) {
			String d = getDniDocSeleccionat();
			ctrlvp.desasignarDoctorPlantilla(d,nom.getText());
		}
	}
	
	public String getDniDocSeleccionat() {
		String content = (String) llista1.getSelectedValue();
		String separadors = "[ \n]";
		String[] separat = content.split(separadors);
		return separat[0];
	}
	
	public String getNom() {
		return nom.getText();
	}
	
	public void setNomPlt(String s) {
		nom.setText(s);
	}
}
