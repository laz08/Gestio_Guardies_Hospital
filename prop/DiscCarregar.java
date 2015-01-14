package prop;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JButton;

//import prop.CtrlHospital;


public class DiscCarregar extends QuatreBotons{
	private GridBagConstraints c = new GridBagConstraints();
	
	public DiscCarregar(CtrlDiscCarregar cdc) {
		b1.setText("Carregar Hospital");
		b2.setText("Carregar Plantilla");
		b3.setText("Carregar Calendari");
		b4.setText("Carregar Restriccions");
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		remove(b1);
		remove(b2);
		remove(b3);
		remove(b4);
		setLayout(new GridBagLayout());
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.ipadx = 30;
		c.ipady = 30;
		c.gridwidth = 2;
		c.insets = new Insets(40,20,20,40);
		add(b1, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 0;
		add(b2, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 2;
		add(b3, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 2;
		add(b4, c);
	}
	
	public void actionPerformed(ActionEvent arg0) {
		int ret = directori.showOpenDialog(this);
        if(ret == directori.APPROVE_OPTION){
        	File f = directori.getSelectedFile();
            if (arg0.getSource() == b1){
                CtrlDiscCarregar.carregarh(f);
            }
            else if (arg0.getSource() == b2) {            
            	CtrlDiscCarregar.carregarp(f);
            }
            else if (arg0.getSource() == b3) {
            	CtrlDiscCarregar.carregarc(f);
            }
            else if (arg0.getSource() == b4) {
            	CtrlDiscCarregar.carregarr(f);
            }
        }
	}
}
