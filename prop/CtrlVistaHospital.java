package prop;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JPanel;


public class CtrlVistaHospital {
	private JPanel dret = new JPanel();
	private JPanel esquerre = new JPanel();
	private JPanel hospital = new JPanel();
	private LlistatErrorHospital llistat = new LlistatErrorHospital(this);
	private BotoMesTextHospital doctor = new BotoMesTextHospital(this);
	private BotoLlistaHospital restriccions = new BotoLlistaHospital(this);
	private TresBotonsHospital cgc = new TresBotonsHospital(this);
	private CtrlVistaPrincipal ctrlvp;

	public CtrlVistaHospital(CtrlVistaPrincipal cvp) {
		ctrlvp = cvp;
		hospital.setLayout(new BorderLayout());
		esquerre.setLayout(new CardLayout());
		esquerre.add(llistat, "1-1");
		esquerre.add(restriccions, "1-2");
		dret.setLayout(new CardLayout());
		dret.add(cgc, "2-1");
		dret.add(doctor, "2-2");
		hospital.add(esquerre, BorderLayout.WEST);
		hospital.add(dret, BorderLayout.EAST);
	}

	public JPanel tornavista() {
		return hospital;
	}

	public void swap(int banda, int numpanelins) {
		if (banda == 1) {
			CardLayout cl = (CardLayout) (esquerre.getLayout());
			cl.show(esquerre, String.valueOf(banda)+"-"+String.valueOf(numpanelins));
		}
		else {
			CardLayout cl = (CardLayout) (dret.getLayout());
			cl.show(dret, String.valueOf(banda)+"-"+String.valueOf(numpanelins));
		}
	}
}
