package prop;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;

public class BotoLlistaHospital extends BotoLlista implements ListSelectionListener {
	private CtrlVistaHospital ctrlvh;
    private BotoMesTextHospital bmth;
    private boolean mode;                               //Mode == 0  => Afegir lligam restricció
                                                        //Mode == 1  => Eliminar lligam restricció
	public BotoLlistaHospital(CtrlVistaHospital cvh) {
		ctrlvh = cvh;
		b1.addActionListener(this);
		b2.addActionListener(this);
		b1.setText("Enrere");
		b2.setText("Acceptar");
        b2.setEnabled(false);
        llista1.addListSelectionListener(this);
        b2.setEnabled(false);

	}

	public void actionPerformed(ActionEvent arg0) /*throws Error*/{
        //Enrere
		if (arg0.getSource() == b1) {
            ctrlvh.swap(1,1);
            b2.setEnabled(false);
		}

        //Acceptar
		else if (arg0.getSource() == b2) {
            //Afegir lligam restricció
            if (!mode){
                int pos = llista1.getSelectedIndex();
                String expressio = ctrlvh.consultaExpressioRealNOAssociada(pos);
                ctrlvh.associaRestriccio(expressio, bmth.retornaDNI());
                b2.setEnabled(false);
                ctrlvh.swap(1,1);
            }

            //Eliminar lligam restricció
            else{
                int pos = llista1.getSelectedIndex();
                String expressio = ctrlvh.consultaExpressioRealAssociada(pos);
                ctrlvh.desassociaRestriccio(expressio, bmth.retornaDNI());
                b2.setEnabled(false);
                ctrlvh.swap(1,1);


            }
		}

	}



    /**
     *  Mode == 0  => Afegir lligam restricció
     *  Mode == 1  => Eliminar lligam restricció
     * @param b
     */
    public void setMode(boolean b){
        mode = b;
    }

    /**
     *
     * @param b
     */
    public void setBotoMesTextHospital(BotoMesTextHospital b){
        bmth = b;
    }

	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		if (arg0.getSource() == this.llista1) {
			if(!arg0.getValueIsAdjusting()) {
				b2.setEnabled(true);
			}
		}
	}


}
