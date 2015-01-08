package prop;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;

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
		esquerre.add(llistat, "E1");
		esquerre.add(restriccions, "E2");
		dret.setLayout(new CardLayout());
		dret.add(cgc, "D1");
		dret.add(doctor, "D2");
		hospital.add(esquerre, BorderLayout.WEST);
		hospital.add(dret, BorderLayout.EAST);
	}

	public JPanel tornavista() {
		return hospital;
	}

	public void swap(int banda) {
		if (banda == 1) {
			CardLayout cl = (CardLayout) (esquerre.getLayout());
			int i = 0;
			while (i < esquerre.getComponents().length) {
				if (esquerre.getComponent(i).isVisible()) {
					if (i == 0) {
						cl.show(esquerre, "E2");
						break;
					}
					else {
						cl.show(esquerre, "E1");
						break;
					}
				}
				++i;
			}
		}
		
		else {
			CardLayout cl = (CardLayout) (dret.getLayout());
			int i = 0; 
			while (i < dret.getComponents().length) {
				if (dret.getComponent(i).isVisible()) {
					if (i == 0) {
						cl.show(dret, "D2");
						break;
					}
					else {
						cl.show(dret, "D1");
						break;
					}
				}
				++i;
			}
		}
		ctrlvp.actualitza();
		System.out.println("Actualitza");
	}
}
