package prop;

import vistes_carles.CtrlDiscCarregar;
import vistes_carles.CtrlDiscGuardar;
import vistes_carles.CtrlVistaPrincipal;

import javax.swing.*;
import java.awt.*;

public class VistaPrincipal extends JFrame {
	private CtrlVistaPrincipal ctrlvistaprincipal;
	private JTabbedPane pestanyes = new JTabbedPane();
	
	public VistaPrincipal(CtrlVistaPrincipal cvp, CtrlDiscGuardar cdg, CtrlDiscCarregar cdc){
		setPreferredSize(new Dimension(800,600));
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
