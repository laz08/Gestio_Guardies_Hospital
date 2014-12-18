package prop;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class VistaPrincipal {
    private static CtrlVistaPrincipal ctrlVistaPrincipal;
	private JFrame frameVista = new JFrame("Gestió de guàrdies d'un hospital");
	private JTabbedPane pestanyes = new JTabbedPane();

    /// Vistes reutilitzables
    private static VistaHospital vh;
    private static VistaPlantilla vp;
    private static VistaCalendari vcal;
    private static VistaRestriccio vr;
    private static VistaAlgorismes va;
    private static VistaAssignacio vass;
    private static VistaGuardar vg;
    private static VistaCarregar vcarr;


    public VistaPrincipal(CtrlVistaPrincipal cvp, VistaHospital VH, VistaPlantilla VPL, VistaCalendari VCal, VistaRestriccio VRes, VistaAlgorismes VAlg, VistaAssignacio VAss, VistaGuardar VGu, VistaCarregar VCarr){
        ctrlVistaPrincipal = cvp;
        inicialitzar(VH, VPL, VCal, VRes, VAlg, VAss, VGu, VCarr);
    }

	public void inicialitzar(VistaHospital VH, VistaPlantilla VPL, VistaCalendari VCal, VistaRestriccio VRes, VistaAlgorismes VAlg, VistaAssignacio VAss, VistaGuardar VGu, VistaCarregar VCarr) {
		vh = VH;
        vp = VPL;
        vcal = VCal;
        vr = VRes;
        va = VAlg;
        vass = VAss;
        vg = VGu;
        vcarr = VCarr;
        inicializar_frameVista();
	}
	
	public void ferVisible() {
	    frameVista.pack();
	    frameVista.setVisible(true);

	  }

	  public void inicializar_frameVista() {
            //Mesures
		    frameVista.setMinimumSize(new Dimension(1200,600));
		    frameVista.setPreferredSize(frameVista.getMinimumSize());
		    frameVista.setResizable(false);

		    frameVista.setLocationRelativeTo(null);
		    frameVista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    frameVista.setLayout(new BorderLayout());
		    pestanyes.addTab("Hospital", vh.tornapanel());
		    pestanyes.addTab("Plantilla", vp.tornapanel());
		    pestanyes.addTab("Calendari", vcal.tornapanel());
		    pestanyes.addTab("Restriccions", vr.tornapanel());
                    pestanyes.addTab("Assignacions", vass.tornapanel());
		    pestanyes.addTab("Algorisme", va.tornapanel());
		    pestanyes.addTab("Guardar", vg.tornapanel());
		    pestanyes.addTab("Carregar", vcarr.tornapanel());
		    frameVista.getContentPane().add(pestanyes);
		    ferVisible();
             pestanyes.addChangeListener(new ChangeListener() {
                 @Override
                 public void stateChanged(ChangeEvent e) {
                     if(pestanyes.getSelectedIndex() == 0){
                         vh.inicialitza_Docs();
                     }
                     else if(pestanyes.getSelectedIndex() == 5){
                         va.insereixPlantilles();
                     }
                 }
             });
		    
	  }
}
