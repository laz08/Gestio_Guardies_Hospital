package prop;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;

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
        llistadoctors.setMaximumSize(new Dimension(350,300));
        llistatorns.setMaximumSize(new Dimension(350,300));
        esquerre.setSize(350,600);
        esquerre.add(llistatorns, BorderLayout.NORTH);
        esquerre.add(llistadoctors, BorderLayout.SOUTH);
        assignacio.add(esquerre, BorderLayout.WEST);
        assignacio.add(llistaassociacio, BorderLayout.EAST);
    }

	public JPanel tornavista() {
		return assignacio;
	}
}
