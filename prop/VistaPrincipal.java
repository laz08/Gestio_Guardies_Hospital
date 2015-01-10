package prop;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class VistaPrincipal extends JFrame {
	private CtrlVistaPrincipal ctrlvistaprincipal;
	private JTabbedPane pestanyes = new JTabbedPane();
	private CtrlVistaHospital ctrlvh;
	
	public VistaPrincipal(CtrlVistaPrincipal cvp, CtrlDiscGuardar cdg, CtrlDiscCarregar cdc, CtrlVistaHospital cvh, CtrlVistaPlantilla cvp2){
		ctrlvh = cvh;
		setPreferredSize(new Dimension(800,600));
		pestanyes.addTab("Hospital", cvh.tornavista());
		pestanyes.addTab("Plantilla", cvp2.tornavista());
		pestanyes.addTab("Guardar",cdg.getDiscGuardar());
		pestanyes.addTab("Carregar",cdc.getDiscCarregar());
		add(pestanyes);
		visible();
	}
	
	public void visible() {
		pack();
		setVisible(true);
	}
}
