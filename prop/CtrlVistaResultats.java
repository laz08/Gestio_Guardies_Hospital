package prop;

import javax.swing.JPanel;

public class CtrlVistaResultats {
	private Resultats resultat = new Resultats(this);
	private static CtrlVistaPrincipal ctrlvp;
	
	public CtrlVistaResultats(CtrlVistaPrincipal cvp) {
		ctrlvp = cvp;	
	}
	
	public JPanel tornavisa() {
		return resultat;
	}
        
        public void posaResultats(int nr, long r){
            resultat.setResultats(nr, r);
        }
	
}
