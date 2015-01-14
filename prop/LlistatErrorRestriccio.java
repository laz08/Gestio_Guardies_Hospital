package prop;

import javax.swing.*;

import java.awt.*;
import java.util.ArrayList;

public class LlistatErrorRestriccio extends PanelLlistatError{
	private CtrlVistaRestriccio ctrlvr;
	private JLabel textres = new JLabel("LLISTAT DE RESTRICCIONS", SwingConstants.CENTER);
	
	public LlistatErrorRestriccio(CtrlVistaRestriccio cvr) {
		ctrlvr = cvr;
        error.setText(" ");
		remove(scroll1);
		add(textres, BorderLayout.NORTH);
		add(scroll1);

        setPreferredSize(new Dimension(343, 250));
        setMaximumSize(new Dimension(343, 250));
	}

    /**
     * Actualitza el llistat de restriccions (i tradueix les de torns per algo més amigable)
     * @param arrayList
     */
	public void actualitza(ArrayList<String> arrayList) {
        model1.clear();
		for (int i = 0; i < arrayList.size(); ++i) {
            //Traducció de la restricció per torns
            String r = arrayList.get(i);
            if(r.charAt(0) == 'H'){
                //Es per torn
                String r_trad = "H";
                for(int j = 1; j < r.length(); ++j){
                    if(r.charAt(j) == '5'){
                        r_trad += "MATI";
                    }
                    else if(r.charAt(j) == '1'){
                        ++j;
                        r_trad += "TARDA";
                    }
                    else if(r.charAt(j) == '2'){
                        ++j;
                        r_trad += "NIT";
                    }
                    else r_trad += r.charAt(j);
                }
                model1.addElement(r_trad);
            }
            else
			    model1.addElement(r);
		}
	}
}
