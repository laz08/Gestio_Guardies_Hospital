package prop;

import javax.swing.*;
import java.awt.*;


public class CtrlVistaHospital {
	private JPanel dret = new JPanel();
	private JPanel esquerre = new JPanel();
	private JPanel hospital = new JPanel();
	private LlistatErrorHospital llistat;
	private BotoMesTextHospital doctor;
	private BotoLlistaHospital restriccions;
	private TresBotonsHospital cgc;
	private CtrlVistaPrincipal ctrlvp;

	public CtrlVistaHospital(CtrlVistaPrincipal cvp) {
		ctrlvp = cvp;
        inicialitzacions_creadora();
		hospital.setLayout(new BorderLayout());
		esquerre.setLayout(new CardLayout());
		esquerre.add(llistat, "1-1");
		esquerre.add(restriccions, "1-2");
		dret.setLayout(new CardLayout());
		dret.add(cgc, "2-1");
		dret.add(doctor, "2-2");
		hospital.add(esquerre, BorderLayout.WEST);
		hospital.add(dret, BorderLayout.EAST);
	}

	public JPanel tornavista() {
		return hospital;
	}


    public void inicialitzacions_creadora(){
        llistat = new LlistatErrorHospital(this);
        doctor = new BotoMesTextHospital(this, llistat);
        restriccions = new BotoLlistaHospital(this);
        cgc = new TresBotonsHospital(this);
    }
    /*
        X - Y
        =============
        X = Banda (1 == Esq. ; 2 == Dreta)
        Y = Núm. del panell
        =============

     */
	public void swap(int banda, int numpanelins) {
		if (banda == 1) {
			CardLayout cl = (CardLayout) (esquerre.getLayout());
			cl.show(esquerre, String.valueOf(banda)+"-"+String.valueOf(numpanelins));
		}
		else {
			CardLayout cl = (CardLayout) (dret.getLayout());
			cl.show(dret, String.valueOf(banda)+"-"+String.valueOf(numpanelins));
		}
	}

    //Inicialització detecció entrada a la pestanya
    public void click_pestanya(){
        llistat.actualitza_llista_docs();
    }


    //Comunicació amb capa Domini
    public String getLlistaDocs_nom(){
        return CtrlHospital.getLlistatDoctorsenString_nom();
    }

    public void crea_doctor(String d, String n, String cg1, String cg2, int s, int t, String cor){
        CtrlHospital.creariAfegirDoctor(d, n, cg1, cg2, s, t, cor);
    }






}
