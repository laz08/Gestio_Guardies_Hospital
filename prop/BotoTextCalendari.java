package prop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;


public class BotoTextCalendari extends BotoText {
	private CtrlVistaCalendari ctrlvc;
	private JLabel anyi = new JLabel("Any inici");
	private JLabel anyf = new JLabel("Any fi");
	private JTextField textfield2 = new JTextField();
	private GridBagConstraints c = new GridBagConstraints();

    private LlistatErrorCalendari llistatErrorCalendari;
	
	public BotoTextCalendari(CtrlVistaCalendari cvc, LlistatErrorCalendari llec) {
		ctrlvc = cvc;
        llistatErrorCalendari = llec;
		remove(textfield1);
		remove(b1);
		remove(b2);
		setLayout(new GridBagLayout());
		c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        add(anyi, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        add(textfield1, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
        add(anyf, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 1;
        add(textfield2, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 1;
        add(b1, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 4;
        c.gridwidth = 1;
        add(b2, c);
        b1.setText("Enrere");
        b2.setText("Acceptar");
		b1.addActionListener(this);
		b2.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent arg0) {
        //ENRERE
		if (arg0.getSource() == this.b1) {
            llistatErrorCalendari.esborraError();
			ctrlvc.removeselection();
			textfield1.setText("");
			textfield2.setText("");
			ctrlvc.swap(2,1);
		}
        //ACCEPTAR
		else if (arg0.getSource() == this.b2) {
			if (!isnumber(textfield1.getText(),1) || !isnumber(textfield2.getText(),1)){
        		ctrlvc.texterror("ERROR: Any inici i/o any fi no són numeros");
        	}
        	else if (Integer.parseInt(textfield1.getText()) <= 0 ||	 Integer.parseInt(textfield2.getText()) <= 0) {
        		ctrlvc.texterror("ERROR: Any inici i/o any fi inferior a 1");
        	}
        	else if (Integer.parseInt(textfield1.getText()) > Integer.parseInt(textfield2.getText())) {
        		ctrlvc.texterror("ERROR: Any inici inferior a any fi");
        	}
        	else {
                llistatErrorCalendari.esborraError();
        		ctrlvc.crearcalendari(ctrlvc.seleccio(), Integer.parseInt(textfield1.getText()), Integer.parseInt(textfield2.getText()));
    			ctrlvc.removeselection();
    			textfield1.setText("");
    			textfield2.setText("");
    			ctrlvc.swap(2,1);
        	}
		}
		
	}
	
	public boolean isnumber(String str, int check) {  
        try  { 
        	if (check == 1) {
        		int d = Integer.parseInt(str);
        	}	
        	else {
        		Float d = Float.parseFloat(str);  
        	}
        }  
        catch(NumberFormatException nfe)  {  
      	  return false;
        }  
        return true; 
      }
	
}
