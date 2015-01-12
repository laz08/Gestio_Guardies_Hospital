package prop;

import javax.swing.*;
import java.awt.event.ActionEvent;


public class TresBotonsPlantilla extends TresBotons{
	private CtrlVistaPlantilla ctrlvp;
	private LlistatErrorPlantilla llista;
	
	public TresBotonsPlantilla(CtrlVistaPlantilla cvp){
		ctrlvp = cvp;

		b1.setText("<html> Crear <br /> Plantilles </html>");
		b2.setText("<html> Guardar <br /> Plantilles </html>");
		b3.setText("<html> Carregar <br /> Plantilles </html>");
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
        setBorder(BorderFactory.createEmptyBorder(250, 15, 5, 80));

    }
	
	public void actionPerformed(ActionEvent arg0) {
		//b1=crear plantilla, canviem de panell a BotoTextPlantilla
		if (arg0.getSource() == b1) {
			esborrarErrors();
			ctrlvp.swap(2,2);
		}
		
		//b2=guardar
		else if (arg0.getSource() == b2) {
			esborrarErrors();
			int ret = directori.showSaveDialog(this);
			if (ret == directori.APPROVE_OPTION) {
				ctrlvp.guardarPlantilles(directori.getSelectedFile());
			}
		}
		
		//b3=carregar
		else {
			esborrarErrors();
			int ret = directori.showOpenDialog(this);
			if (ret == directori.APPROVE_OPTION) {
				ctrlvp.carregarPlantilles(directori.getSelectedFile());
			}
		}
		
	}
	
	public void setBotoLlistaMesPlantilla(LlistatErrorPlantilla caractplantilla) {
		llista = caractplantilla;
		esborrarErrors();
	}
	
	public void esborrarErrors() {
		llista.esborrarErrors();
	}
}
