package prop;

import javax.swing.*;
import java.awt.event.ActionEvent;


public class CGCalendari extends TresBotons{
	private CtrlVistaCalendari ctrlvc;
	public CGCalendari(CtrlVistaCalendari cvc) {
		ctrlvc = cvc;
		b1.setText("Carregar Calendaris");
		b2.setText("Guardar Calendaris");
		b1.addActionListener(this);
		b2.addActionListener(this);
        remove(b3);
        setBorder(BorderFactory.createEmptyBorder(250, 15, 5, 0));
    }
	
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == b1) {
			if (directori.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
				ctrlvc.carregar(directori.getSelectedFile());
				ctrlvc.actualitza();
			}
		}
		
		else if (arg0.getSource() == b2){
			if (directori.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
				ctrlvc.guardar(directori.getSelectedFile());
			}
		}
	}
	
	
}
