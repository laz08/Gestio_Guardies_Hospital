package prop;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JPanel;


public class CtrlVistaAssignacio {
    private CtrlVistaPrincipal ctrlVistaPrincipal;
    private JPanel assignacio = new JPanel(); 
    private LListatErrorAssignacio llistadoctors = new LListatErrorAssignacio(this);
    private LListatTorns llistatorns = new LListatTorns(this);
    private LlistatAssociacio llistaassociacio = new LlistatAssociacio(this);
    private JPanel esquerre = new JPanel();
    
    public CtrlVistaAssignacio(CtrlVistaPrincipal vpc) {
        ctrlVistaPrincipal = vpc;
        assignacio.setLayout(new BorderLayout());
        esquerre.setLayout(new BorderLayout());
        esquerre.add(llistatorns, BorderLayout.NORTH);
        esquerre.add(llistadoctors, BorderLayout.SOUTH);
        assignacio.add(esquerre, BorderLayout.WEST);
        assignacio.add(llistaassociacio, BorderLayout.EAST);
    }

	public JPanel tornavista() {
		return assignacio;
	}
}
