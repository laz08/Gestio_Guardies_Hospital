package prop;

import javax.swing.*;

import java.awt.*;

public class CtrlVistaAlgorisme {

    private CtrlVistaPrincipal ctrlvp;
    private JPanel algorisme = new JPanel();
    private LlistatErrorAlgorisme llistatalgorisme = new LlistatErrorAlgorisme(this);
    private BotonsAlgorisme botonsalgorisme = new BotonsAlgorisme(this);

    public CtrlVistaAlgorisme(CtrlVistaPrincipal cvp) {
        ctrlvp = cvp;
        algorisme.setLayout(new BorderLayout());
        algorisme.setBorder(BorderFactory.createEmptyBorder(10, 15, 5, 60));
        algorisme.add(llistatalgorisme, BorderLayout.WEST);
        algorisme.add(botonsalgorisme, BorderLayout.EAST);
        llistatalgorisme.afegirPlantilles();
    }

    public JPanel tornavista() {
        return algorisme;
    }

    //FUNCIONS DE COMUNICACIO AMB CAPA DE DOMINI
    public String obteLlistaPlantilles() {
        return CtrlPlantilla.getLlista_plantilles();
    }

    public void actualitzaLlistaPlantilles() {
        llistatalgorisme.afegirPlantilles();
    }

    public boolean teCalendariAssociat(String plantilla) {
        return CtrlPlantilla.existeixCalendari(plantilla);
    }
    
    public void seleccionaPlantilla(String plantilla){
        CtrlPlantilla.setPlantillaActual(plantilla);
    }
    
    public void actualitzaDocTorns(){
        ctrlvp.actualitzaDocTorns();
    }
    
    public void iniciarGraf() {
    	if(llistatalgorisme.llista1.getSelectedIndex()!=-1) {
    		String plt = llistatalgorisme.llista1.getSelectedValue().toString();
    		CtrlAlgorisme.iniciarGraf(plt);
    		if(!CtrlCalendari.existeixCalendari(plt)) llistatalgorisme.errorNoCalendari();
    	}
    	else llistatalgorisme.errorNoSelect();
    }
    
    public boolean iniciarExecucio() {
    	if(llistatalgorisme.llista1.getSelectedIndex()!=-1) {
    		String plt = llistatalgorisme.llista1.getSelectedValue().toString();
    		CtrlPlantilla.setPlantillaActual(plt);
    		if(CtrlCalendari.existeixCalendari(plt)) return true;
    		else llistatalgorisme.errorNoCalendari();
    	}
    	else llistatalgorisme.errorNoSelect();
    	return false;
    }
    
    public void esborrarErrors() {
    	llistatalgorisme.esborrarErrors();
    }

	public void esborraAssignacio() {
		Doc_Torn.removeAll();
		
	}
}
