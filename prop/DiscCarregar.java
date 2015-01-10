package prop;

import java.awt.event.ActionEvent;
import java.io.File;

//import prop.CtrlHospital;


public class DiscCarregar extends QuatreBotons{
	
	public DiscCarregar(CtrlDiscCarregar cdc) {
		b1.setText("Carregar Hospital");
		b2.setText("Carregar Plantilla");
		b3.setText("Carregar Calendari");
		b4.setText("Carregar Restriccions");
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
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
