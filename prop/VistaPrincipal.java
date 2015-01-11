package prop;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;

public class VistaPrincipal extends JFrame {
	private CtrlVistaPrincipal ctrlvistaprincipal;
	private JTabbedPane pestanyes = new JTabbedPane();
	private CtrlVistaHospital ctrlvh;
	private CtrlVistaCalendari ctrlvc;
	
	public VistaPrincipal(CtrlVistaPrincipal cvp,CtrlVistaResultats cvrs, CtrlDiscGuardar cdg, CtrlDiscCarregar cdc, CtrlVistaAssignacio cva, CtrlVistaHospital cvh, CtrlVistaPlantilla cvp2, CtrlVistaCalendari cvc){
		ctrlvh = cvh;
		ctrlvc = cvc;
		setPreferredSize(new Dimension(900,600));
		pestanyes.addTab("Hospital", cvh.tornavista());
		pestanyes.addTab("Plantilla", cvp2.tornavista());
		pestanyes.addTab("Calendari", cvc.tornavista());
		pestanyes.addTab("Assignació", cva.tornavista());
		pestanyes.addTab("Resultats", cvrs.tornavisa());
		pestanyes.addTab("Guardar",cdg.getDiscGuardar());
		pestanyes.addTab("Carregar",cdc.getDiscCarregar());
		add(pestanyes);
		visible();
		pestanyes.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                //HOSPITAL 
                if(pestanyes.getSelectedIndex() == 0){

                }
                else if(pestanyes.getSelectedIndex() == 1){
                }
                else if(pestanyes.getSelectedIndex() == 2){
                    ctrlvc.actualitza();
                    ctrlvc.swap(1, 1);
                    ctrlvc.swap(2, 1);
                }
                else if(pestanyes.getSelectedIndex() == 3){
                }
                else if(pestanyes.getSelectedIndex() == 4){
                }
                else if(pestanyes.getSelectedIndex() == 5){
                }
            }
        });
		
	}
	
	public void visible() {
		pack();
		setVisible(true);
	}
}
