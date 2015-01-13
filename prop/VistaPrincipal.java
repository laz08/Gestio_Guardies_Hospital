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
    private CtrlVistaPlantilla ctrlvp;
    private CtrlVistaAlgorisme ctrlval;
    private CtrlVistaRestriccio ctrlvr;
    private CtrlVistaResultats ctrlvrs;
    private CtrlVistaAssignacio ctrlva;
	
	public VistaPrincipal(CtrlVistaPrincipal cvp,CtrlVistaResultats cvrs, CtrlDiscGuardar cdg, CtrlDiscCarregar cdc, CtrlVistaAssignacio cva, CtrlVistaHospital cvh, CtrlVistaPlantilla cvp2, CtrlVistaAlgorisme cval, CtrlVistaRestriccio cvr, CtrlVistaCalendari cvc){
		ctrlvh = cvh;
		ctrlvc = cvc;
        ctrlvp = cvp2;
        ctrlval = cval;
        ctrlvr = cvr;
        ctrlvrs = cvrs;
        ctrlva = cva;
        setTitle("Gestió de guàrdies d'un hospital");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(900,600));
		pestanyes.addTab("Hospital", cvh.tornavista());
		pestanyes.addTab("Plantilla", cvp2.tornavista());
		pestanyes.addTab("Calendari", cvc.tornavista());
		pestanyes.addTab("Restriccions", cvr.tornavista());
		pestanyes.addTab("Algorisme", ctrlval.tornavista());
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
                    ctrlvh.actualitza_Docs();
                    ctrlvh.swap(1, 1);
                    ctrlvh.swap(2, 1);
                    ctrlvh.reiniciaVista();
                }
                //PLANTILLA
                else if(pestanyes.getSelectedIndex() == 1){
                    ctrlvp.actualitzarLlistaPlantilles();
                    ctrlvp.reiniciaCamp();
                    ctrlvp.swap(1, 1);
                    ctrlvp.swap(2, 1);
                }
                else if(pestanyes.getSelectedIndex() == 2){
                    ctrlvc.actualitza();
                    ctrlvc.swap(1, 1);
                    ctrlvc.swap(2, 1);
                }
                else if(pestanyes.getSelectedIndex() == 3){
                	ctrlvr.actualitza();
                	ctrlvr.swap(2, 1);
                }
                else if(pestanyes.getSelectedIndex() == 4){
                	ctrlval.esborrarErrors();
                    ctrlval.actualitzaLlistaPlantilles();
                }
                else if(pestanyes.getSelectedIndex() == 5){
                    ctrlva.posaDocs();
                    ctrlva.posaTorns();
                }
                else if(pestanyes.getSelectedIndex() == 6){
                	ctrlvr.swap(2,1);
                }
            }
        });
		
	}
        
        public void posaResultat(int nr, long r){
            ctrlvrs.posaResultats(nr, r);
        }
        
        public void actualitzaDocTorns(){
            ctrlva.posaDocs();
            ctrlva.posaTorns();
        }
	
	public void visible() {
		pack();
		setVisible(true);
	}
}
