package prop;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;


public class LlistatErrorCalendari extends PanelLlistatError{
	private CtrlVistaCalendari ctrlvc;
    private JLabel titolllistat = new JLabel("LLISTAT DE PLANTILLES", SwingConstants.CENTER);

	public LlistatErrorCalendari(CtrlVistaCalendari cvc) {
		ctrlvc = cvc;

        remove(scroll1);
        add(titolllistat, BorderLayout.NORTH);
        add(scroll1);

		llista1.addListSelectionListener(this);
		model1.removeAllElements();
		actualitzar();
	}

	public void valueChanged(ListSelectionEvent arg0) {
		if (arg0.getSource() == this.llista1) {
			if (!llista1.isSelectionEmpty()) {
				if (!arg0.getValueIsAdjusting()) {
					if (!ctrlvc.existeixCalendari(llista1.getSelectedValue())) {
						ctrlvc.swap(2,2);
					}
					else {
						int anyinici = CtrlVistaCalendari.getAny(llista1.getSelectedValue());
						int anyfi = CtrlVistaCalendari.getAnyfi(llista1.getSelectedValue());
						ctrlvc.ompleanys(anyinici, anyfi);
						ctrlvc.swap(2,3);
					}
				}
			}

		}
	} 

	public void actualitzar() {
		String content = CtrlPlantilla.getLlista_plantilles();
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

	public String getselected() {
		return llista1.getSelectedValue();
	}

	public void removeselection() {
		this.llista1.clearSelection();
	}

	public void texterror(String string) {
		error.setText(string);
	}
}
