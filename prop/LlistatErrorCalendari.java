package prop;

import javax.swing.event.ListSelectionEvent;


public class LlistatErrorCalendari extends PanelLlistatError{
	private CtrlVistaCalendari ctrlvc;

	public LlistatErrorCalendari(CtrlVistaCalendari cvc) {
		ctrlvc = cvc;
		llista1.addListSelectionListener(this);
		model1.removeAllElements();
		CtrlPlantilla.creariAfegirPlantilla("Clica");
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
