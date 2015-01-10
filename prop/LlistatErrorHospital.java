package prop;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class LlistatErrorHospital extends PanelLlistatError {
	private CtrlVistaHospital ctrlvh;
	public LlistatErrorHospital(CtrlVistaHospital cvh) {
		ctrlvh = cvh;
		llista1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//model1.addElement("Clica2");
		llista1.addListSelectionListener(this);
        actualitza_llista_docs();
	}
	
	public void valueChanged(ListSelectionEvent arg0) {
        actualitza_llista_docs();
        ctrlvh.swap(2,2);
		llista1.clearSelection();
	}

    public void esborraElementsModel(){
        model1.removeAllElements();
    }

    //AUXILIARS
    public void actualitza_llista_docs(){
        esborraElementsModel();
        String contingut = ctrlvh.getLlistaDocs_nom();
        if (!contingut.equals("")) {
            if (contingut.length() > 0) {
                String separadors = "[ \n]";
                String[] separat = contingut.split(separadors);
                for (int i = 0; i < separat.length; i += 7) {
                    model1.addElement(separat[i] + " " + separat[i + 1] + " " + separat[i + 2] + " " + separat[i + 3]);
                }
            }
        }
    }





    public void modifica_doc(){

    }


    //MISSATGES ERROR
    public void errorUnOMesDunCampNull(){
        //missatgeErrors.setText("ERROR: Falten dades en un o més camps del doctor.");
    }
    //Per a sou i telèfon
    public void errorHaDeSerUnReal(String s){
        // missatgeErrors.setText("ERROR: " + s + " ha de ser un número positiu.");
    }

    public void noEsCorreu(){
        //missatgeErrors.setText("ERROR: El correu introduït no és vàlid.");
    }

    public void esborrarTotsErrors(){
        //missatgeErrors.setText("");
    }
    public void condicionsDNIError(){
        //missatgeErrors.setText("ERROR: DNI ha de ser una cadena de 8 caràcters numèrics.");
    }



}
