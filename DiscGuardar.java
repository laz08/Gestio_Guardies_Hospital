import java.awt.event.ActionEvent;
import java.io.File;

public class DiscGuardar extends DiscGeneral{
	
	public DiscGuardar(CtrlDiscGuardar cdg){
		b1.setText("Guardar Hospital");
		b2.setText("Guardar Plantilla");
		b3.setText("Guardar Calendari");
		b4.setText("Guardar Restriccions");
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent arg0) {
		int ret = directori.showSaveDialog(this);
        if(ret == directori.APPROVE_OPTION){
        	File f = directori.getSelectedFile();
            if (arg0.getSource() == b1){
                CtrlDiscGuardar.guardarh(f);
            }
            else if (arg0.getSource() == b2) {            
            	CtrlDiscGuardar.guardarp(f);
            }
            else if (arg0.getSource() == b3) {
            	CtrlDiscGuardar.guardarc(f);
            }
            else if (arg0.getSource() == b4) {
            	CtrlDiscGuardar.guardarr(f);
            }
        }
	}
}
