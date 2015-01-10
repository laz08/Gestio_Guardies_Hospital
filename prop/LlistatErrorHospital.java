package prop;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;


public class LlistatErrorHospital extends PanelLlistatError {
	private CtrlVistaHospital ctrlvh;
    private BotoMesTextHospital bmth;

	public LlistatErrorHospital(CtrlVistaHospital cvh) {
		ctrlvh = cvh;
		llista1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		llista1.addListSelectionListener(this);
        actualitza_llista_docs();
        esborrarTotsErrors();
	}
    public void setBotoMesTextHospital(BotoMesTextHospital b){
        bmth = b;
    }
	
	public void valueChanged(ListSelectionEvent arg0) {
        bmth.ompleValuesDoctor();
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


    //MISSATGES ERROR
    public void errorUnOMesDunCampNull(){
        //missatgeErrors.setText("ERROR: Falten dades en un o més camps del doctor.");
        error.setText("ERROR: Falten dades en un o més camps del doctor.");
        calcula_mida_font();
    }
    //Per a sou i telèfon
    public void errorHaDeSerUnReal(String s){
        // missatgeErrors.setText("ERROR: " + s + " ha de ser un número positiu.");
        error.setText("ERROR: " + s + " ha de ser un número positiu.");
        calcula_mida_font();
    }

    public void noEsCorreu(){
        //missatgeErrors.setText("ERROR: El correu introduït no és vàlid.");
        error.setText("ERROR: El correu introduït no és vàlid.");
        calcula_mida_font();
    }

    public void esborrarTotsErrors(){
        //missatgeErrors.setText("");
        error.setText(" ");
        calcula_mida_font();
    }
    public void condicionsDNIError(){
        //missatgeErrors.setText("ERROR: DNI ha de ser una cadena de 8 caràcters numèrics.");
        error.setText("ERROR: DNI ha de ser una cadena de 8 caràcters numèrics.");
        calcula_mida_font();
    }

    public void jaExisteixDocAmbDNI(){
        error.setText("ERROR: Ja existeix un doctor amb aquest DNI.");
    }

    public void calcula_mida_font(){
        error.setFont(new Font(error.getFont().getName(), Font.PLAIN, 10));

        /*
        Font labelFont = error.getFont();
        String labelText = error.getText();

        int stringWidth = error.getFontMetrics(labelFont).stringWidth(labelText);
        int componentWidth = error.getWidth();

        // Find out how much the font can grow in width.
        double widthRatio = (double)componentWidth / (double)stringWidth;

        int newFontSize = (int)(labelFont.getSize() * widthRatio);
        int componentHeight = error.getHeight();

        // Pick a new font size so it will not be larger than the height of label.
        int fontSizeToUse = Math.min(newFontSize, componentHeight);

        // Set the label's font size to the newly determined size.
        error.setFont(new Font(labelFont.getName(), Font.PLAIN, fontSizeToUse));
        */
    }


}
