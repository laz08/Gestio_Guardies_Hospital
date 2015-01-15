package prop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;


public class BotoTextPlantilla extends BotoText{
	private CtrlVistaPlantilla ctrlvp;
	private LlistatErrorPlantilla llistat;
	
	public BotoTextPlantilla(CtrlVistaPlantilla cvp, LlistatErrorPlantilla ll) {
		remove(textfield1);
		textfield1 = new JTextField(20);
		setLayout(new GridBagLayout());
		//remove(textfield1);
		remove(b1);
		remove(b2);
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 3;
		add(textfield1, c);
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
		ctrlvp = cvp;
		llistat = ll;
		b1.setText("Enrere");
		b2.setText("Acceptar");
		b1.addActionListener(this);
		b2.addActionListener(this);
		setLayout(new GridBagLayout());
		
	}
	
	
	public void actionPerformed(ActionEvent arg0) {
		//b2=acceptar, creem la plantilla si no hi ha errors
		if (arg0.getSource() == b2) {
			llistat.esborrarErrors();
			String nom = textfield1.getText();
	        if(nom.equals("")){
	            llistat.errorNomPlantBuit();
	            
	        }
	        else {
	        	if(ctrlvp.existeixPlantilla(nom)) llistat.errorNomPlantRepe();
	        	else {
	        		ctrlvp.crearPlantilla(nom);
	        		llistat.actualitzarLlistatPlantilles();
	        		netejaNomPlant();
	        		llistat.esborrarErrors();
	        		ctrlvp.swap(2,1);
	        	}
	        }
		}
		
		//Tornem enrere
		else {
			netejaNomPlant();
			llistat.esborrarErrors();
			ctrlvp.swap(2,1);	
		}
		
	}

    /**
     * Neteja el nom de la plantilla
     */
	public void netejaNomPlant() {
		textfield1.setText("");
	}
	
	
}
