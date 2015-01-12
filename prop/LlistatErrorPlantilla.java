package prop;

import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class LlistatErrorPlantilla extends PanelLlistatError implements ListSelectionListener{
	private CtrlVistaPlantilla ctrlvp;
	private BotoLlistaMesPlantilla pltconcreta;
	boolean mod = false;

	public LlistatErrorPlantilla(CtrlVistaPlantilla cvp){
		ctrlvp = cvp;
		esborrarErrors();
		//model1.addElement(String.valueOf("Clica aqui"));
		actualitzarLlistatPlantilles();
		llista1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		llista1.addListSelectionListener(this);
	}
	
	public void setBotoLlistaMesPlantilles(BotoLlistaMesPlantilla plt) {
		pltconcreta = plt;
	}
	
	public void valueChanged(ListSelectionEvent arg0) {
		//pltconcreta.setNomPlt(llista1.getSelectedValue().toString());
		if(!mod) {
			if(arg0.getSource() == this.llista1) {
				if(!arg0.getValueIsAdjusting()) {
					pltconcreta.setNomPlt(llista1.getSelectedValue().toString());
					pltconcreta.actualitzarLlistaDoctorsPlt();
					ctrlvp.swap(2,3); 
				}
			}
		}
		//llista1.clearSelection();
	}
	
	public void actualitzarLlistatPlantilles() {
		//ctrlvp.mod(true);
		mod = true;
		String content = ctrlvp.obteLlistaPlantilles();
        model1.removeAllElements();
        if(!content.equals("")) {
        	if(content.length() > 0) {
        		String separadors = "[ \n]";
        		String[] separat = content.split(separadors);
        		for (int i = 0; i < separat.length; i += 1) {
        			model1.addElement(separat[i]);
        		}
        	}
        }
        mod = false;
        //ctrlvp.mod(false);
	}
	
	public String nomSeleccionat() {
		return (String) llista1.getSelectedValue();
	}
	
	//MISSATGES D'ERROR
	public void errorNomPlantBuit() {
		error.setText("ERROR: El nom de la pantilla no pot ser buit");
	}
	
	public void errorNomPlantRepe() {
		error.setText("ERROR: El nom de la plantilla ja existeix");
	}
	
	public void esborrarErrors() {
		error.setText("");
	}

	public void removeselection() {
		mod = true;
		llista1.clearSelection();	
		mod = false;
	}

}
