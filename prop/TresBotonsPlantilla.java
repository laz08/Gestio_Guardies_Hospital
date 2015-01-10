package prop;

import java.awt.event.ActionEvent;


public class TresBotonsPlantilla extends TresBotons{
	private CtrlVistaPlantilla ctrlvp;
	
	public TresBotonsPlantilla(CtrlVistaPlantilla cvp){
		ctrlvp = cvp;
		b1.setText("Crear Plantilla");
		b2.setText("Guardar Plantilla");
		b3.setText("Carregar Plantilla");
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == b1) {
			ctrlvp.swap(2,2);
		}
		else if (arg0.getSource() == b2) {
			int ret = directori.showSaveDialog(this);
			if (ret == directori.APPROVE_OPTION) CtrlDiscGuardar.guardarh(directori.getSelectedFile());
		}
		else {
			int ret = directori.showOpenDialog(this);
			if (ret == directori.APPROVE_OPTION) CtrlDiscCarregar.carregarh(directori.getSelectedFile());
		}
	}
}
