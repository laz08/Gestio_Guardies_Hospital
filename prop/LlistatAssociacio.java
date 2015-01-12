package prop;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class LlistatAssociacio extends PanelLlistat{
	private CtrlVistaAssignacio ctrlva;
	private JLabel titolassociat= new JLabel("ASSOCIAT AMB", SwingConstants.CENTER);
	
	public LlistatAssociacio(CtrlVistaAssignacio cva) {
		ctrlva = cva;
		remove(scroll1);
		add(titolassociat, BorderLayout.NORTH);
		add(scroll1);
	}
        
        public void mostra(ArrayList<String> llista){
            model1.clear();
            for(int i=0; i<llista.size(); i++){
                model1.addElement(llista.get(i)); 
            }
        }
}
