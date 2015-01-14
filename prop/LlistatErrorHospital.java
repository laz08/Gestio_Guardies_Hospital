package prop;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;


public class LlistatErrorHospital extends PanelLlistatError {
	private CtrlVistaHospital ctrlvh;
    private BotoMesTextHospital bmth;
    private JLabel titolllistat = new JLabel("LLISTAT DE DOCTORS", SwingConstants.CENTER);
    boolean mod = false;
    
	public LlistatErrorHospital(CtrlVistaHospital cvh) {
		ctrlvh = cvh;
        remove(scroll1);
        add(titolllistat, BorderLayout.NORTH);
        add(scroll1);

		llista1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		llista1.addListSelectionListener(this);
        actualitza_llista_docs();
        esborrarTotsErrors();
	}
    public void setBotoMesTextHospital(BotoMesTextHospital b){
        bmth = b;
    }
	
	public void valueChanged(ListSelectionEvent arg0) {
		if (!mod) {
			if (arg0.getSource() == this.llista1) {
				if (!arg0.getValueIsAdjusting()) {
					bmth.ompleValuesDoctor();
			        ctrlvh.swap(2,2);
				}
			}
		}        
	}

    /**
     * S'esborren els doctors de la llista
     */
    public void esborraElementsModel(){
        model1.removeAllElements();
    }

    //AUXILIARS

    /**
     * Actualitza el llistat de doctors a mostrar
     * Es mostra el DNI, els cognoms i el nom de pila
     */
    public void actualitza_llista_docs(){
    	ctrlvh.mod(true);
    	mod = true;
    	esborraElementsModel();
        String contingut = ctrlvh.getLlistaDocs_nom();
        if (!contingut.equals("")) {
            if (contingut.length() > 0) {
                String separadors = "[ \n]";
                String[] separat = contingut.split(separadors);
                for (int i = 0; i < separat.length; i += 7) {
                    model1.addElement(separat[i] + " | " + separat[i + 2] + " " + separat[i + 3]+ ", " +separat[i + 1] );
                }
            }
        }
        mod = false;
        ctrlvh.mod(false);
    }


    //MISSATGES ERROR

    /**
     * Mostra el missatge d'error de que un o més camps del doctor estan buits
     */
    public void errorUnOMesDunCampNull(){
        //missatgeErrors.setText("ERROR: Falten dades en un o més camps del doctor.");
        error.setText("ERROR: Falten dades en un o més camps.");
        //calcula_mida_font();
    }



    /**
     * (Per a sou i tèlefon)
     * Escriu el missatge d'error de que S (telèfon o sou) ha de ser un numero positiu
     * @param s
     */
    public void errorHaDeSerUnReal(String s){
        // missatgeErrors.setText("ERROR: " + s + " ha de ser un número positiu.");
        error.setText("ERROR: " + s + " ha de ser un número positiu.");
        //calcula_mida_font();
    }

    /**
     * Escriu el missatge d'error de que el correu no és valid
     */
    public void noEsCorreu(){
        error.setText("ERROR: El correu introduït no és vàlid.");
    }

    /**
     * Esborra el missatge d'error
     */
    public void esborrarTotsErrors(){
        error.setText(" ");
    }

    /**
     * Escriu el missatge d'error de que el DNI ha de tenir 8 caracters numerics
     */
    public void condicionsDNIError(){
        error.setText("ERROR: DNI ha de ser una cadena de 8 caràcters numèrics.");
    }

    /**
     * Escriu el missatge d'error que ja existeix un doctor amb aquell dni
     */
    public void jaExisteixDocAmbDNI(){
        error.setText("ERROR: Ja existeix un doctor amb aquest DNI.");
    }

}
