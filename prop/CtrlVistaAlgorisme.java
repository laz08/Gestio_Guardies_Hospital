package prop;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JPanel;

public class CtrlVistaAlgorisme {
	private CtrlVistaPrincipal ctrlvp;
	private JPanel algorisme = new JPanel();
	private LlistatErrorAlgorisme llistatalgorisme = new LlistatErrorAlgorisme(this);
	private BotonsAlgorisme botonsalgorisme = new BotonsAlgorisme(this);
	
	public CtrlVistaAlgorisme(CtrlVistaPrincipal cvp) {
		ctrlvp = cvp;
		algorisme.setLayout(new BorderLayout());
		algorisme.add(llistatalgorisme, BorderLayout.WEST);
		algorisme.add(botonsalgorisme, BorderLayout.EAST);
	}

	public JPanel tornavista() {
		return algorisme;
	}
}
