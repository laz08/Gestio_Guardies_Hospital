import java.awt.event.ActionEvent;

public class BotonsHospital extends PanelBotons {
	
	public BotonsHospital() { //CtrlVistaHospital cvh) {
		b1.setText("Crear Doctor");
		b2.setText("Guardar Doctors");
		b3.setText("Carregar Doctors");
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == b1) {
			
		}
		else {
			
			if (arg0.getSource() == b2) {
				int ret = directori.showSaveDialog(this);
				if (ret == directori.APPROVE_OPTION) CtrlDiscGuardar.guardarh(directori.getSelectedFile());
			}
			else if (arg0.getSource() == b3) {
				int ret = directori.showOpenDialog(this);
				if (ret == directori.APPROVE_OPTION) CtrlDiscCarregar.carregarh(directori.getSelectedFile());
			}
		}
		
	}
}
