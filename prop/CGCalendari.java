package prop;

import java.awt.event.ActionEvent;

import javax.swing.JFileChooser;


public class CGCalendari extends TresBotons{
	private CtrlVistaCalendari ctrlvc;
	public CGCalendari(CtrlVistaCalendari cvc) {
		//ctrlvc = cvc;
		b1.setText("Carregar Calendaris");
		b2.setText("Guardar Calendaris");
		b1.addActionListener(this);
		b2.addActionListener(this);
		remove(b3);
	}
	
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == b1) {
			if (directori.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
				//ctrlvc.carregar(directori.getSelectedFile());
				//ctrlvc.actualitzar();
			}
			
		}
		
		else {
			if (directori.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
				//ctrlvc.guardar(directori.getSelectedFile());
				//ctrlvc.actualitzar();
			}
			
		}
	}
	
	
}
