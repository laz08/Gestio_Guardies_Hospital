package prop;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

public class LlistatErrorAlgorisme extends PanelLlistatError {

    private CtrlVistaAlgorisme ctrlval;
    private JLabel textplantilla = new JLabel("PLANTILLES AMB CALENDARI ASSOCIAT", SwingConstants.CENTER);

    public LlistatErrorAlgorisme(CtrlVistaAlgorisme cval) {
        ctrlval = cval;
        remove(scroll1);
        error.setText(" ");
        add(textplantilla, BorderLayout.NORTH);
        add(scroll1);
        afegirPlantilles();
        esborrarErrors();
        llista1.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
            	esborrarErrors();
                String plantilla = (String) llista1.getSelectedValue();
                ctrlval.seleccionaPlantilla(plantilla);
            }
        });

    }

    public void valueChanged(ListSelectionEvent arg0) {
        if (arg0.getSource() == llista1) {
        	esborrarErrors();
            if (!arg0.getValueIsAdjusting()) {
            }
        }
    }
    

    public void afegirPlantilles() {
        String content = ctrlval.obteLlistaPlantilles();
        model1.removeAllElements();
        if (!content.equals("")) {
            if (content.length() > 0) {
                String separadors = "[ \n]";
                String[] separat = content.split(separadors);
                for (int i = 0; i < separat.length; i += 1) {
                    if(ctrlval.teCalendariAssociat(separat[i]))
                    model1.addElement(separat[i]);
                }
            }
        }
    }
    
    public void esborrarErrors() {
    	error.setText("");
    }
    
    public void errorNoSelect() {
    	error.setText("ERROR: No hi ha cap plantilla seleccionada");
    }
    
    public void errorNoCalendari() {
    	error.setText("ERROR: La plantilla no té calendari");
    }
}