package prop;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;

import javax.swing.JPanel;

public class CtrlVistaRestriccio {
	private CtrlVistaPrincipal ctrlvp;
	private JPanel restriccions = new JPanel();
	private JPanel dret = new JPanel();
	private LlistatErrorRestriccio llistat= new LlistatErrorRestriccio();
	private BotonsRestriccio botons = new BotonsRestriccio();
	
	public CtrlVistaRestriccio(CtrlVistaPrincipal cvp) {
		ctrlvp = cvp;
		restriccions.setLayout(new BorderLayout());
		dret.setLayout(new CardLayout());
		dret.add(botons, "1-1");
		restriccions.add(llistat, BorderLayout.WEST);
		restriccions.add(dret, BorderLayout.EAST);
	}

	public Component tornavista() {
		return restriccions;
	}
}
