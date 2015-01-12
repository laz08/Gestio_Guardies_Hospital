package prop;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;

public class LlistatErrorAlgorisme extends PanelLlistatError {
	private CtrlVistaAlgorisme ctrlval;
	private JLabel textplantilla = new JLabel("PLANTILLES AMB CALENDARI ASSOCIAT", SwingConstants.CENTER);
	
	public LlistatErrorAlgorisme(CtrlVistaAlgorisme cval) {
		ctrlval = cval;
		remove(scroll1);
		add(textplantilla, BorderLayout.NORTH);
		add(scroll1);
		afegirPlantilles();
		llista1.addListSelectionListener(this);
		
	}
	
	public void valueChanged(ListSelectionEvent arg0) {
		if (arg0.getSource() == llista1) {
			if (!arg0.getValueIsAdjusting()) {
				
			}
		}
	}
        
        
    public void afegirPlantilles(){
        String content = ctrlval.obteLlistaPlantilles();
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
    }
}