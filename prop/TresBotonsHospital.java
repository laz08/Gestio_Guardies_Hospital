package prop;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class TresBotonsHospital extends TresBotons {
	
	private CtrlVistaHospital ctrlvh;
    private JLabel titolllistat = new JLabel("LLISTAT DE PLANTILLES", SwingConstants.CENTER);


    public TresBotonsHospital(CtrlVistaHospital cvh) { //CtrlVistaHospital cvh) {
		ctrlvh = cvh;
		b1.setText("<html> Crear <br /> Doctor </html>");
		b2.setText("<html> Guardar <br /> Doctors </html>");
		b3.setText("<html> Carregar <br /> Doctors </html>");
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
        setBorder(BorderFactory.createEmptyBorder(250, 15, 5, 30));
	}
	
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == b1) {
			ctrlvh.swap(2,2);
            ctrlvh.desactivaBotonsRestriccionsEliminar();
		}
        else if (arg0.getSource() == b2) {
            int ret = directori.showSaveDialog(this);
            if (ret == directori.APPROVE_OPTION){
                CtrlDiscGuardar.guardarh(directori.getSelectedFile());
                ctrlvh.actualitza_Docs();
            }
        }
        else if (arg0.getSource() == b3) {
            int ret = directori.showOpenDialog(this);
            if (ret == directori.APPROVE_OPTION){
                CtrlDiscCarregar.carregarh(directori.getSelectedFile());
            }
            ctrlvh.actualitza_Docs();
        }
	}
}
