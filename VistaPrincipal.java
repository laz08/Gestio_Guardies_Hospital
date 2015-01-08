import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

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
