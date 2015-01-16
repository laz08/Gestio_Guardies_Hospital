package prop;

import javax.swing.*;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;

public class CtrlVistaRestriccio {
	private CtrlVistaPrincipal ctrlvp;
	private JPanel restriccions = new JPanel();
	private JPanel dret = new JPanel();
	private CalendariRestriccio calendari = new CalendariRestriccio(this);
	private LlistatErrorRestriccio llistat= new LlistatErrorRestriccio(this);
	private BotonsRestriccio botons = new BotonsRestriccio(this);

	
	public CtrlVistaRestriccio(CtrlVistaPrincipal cvp) {
		ctrlvp = cvp;
		restriccions.setLayout(new BorderLayout());
		dret.setLayout(new CardLayout());
		dret.add(botons, "2-1");
		dret.add(calendari, "2-2");

        //llistat.setSize(450,500);
        restriccions.setPreferredSize(new Dimension(415, 250));
        restriccions.setMaximumSize(new Dimension(415, 250));
        restriccions.setBorder(BorderFactory.createEmptyBorder(10, 15, 5, 0));
        restriccions.add(llistat, BorderLayout.WEST);
		restriccions.add(dret, BorderLayout.EAST);
	}

	public Component tornavista() {
		return restriccions;
	}
	
	public void swap(int banda, int numpanelins) {
		if (banda == 2) {
			CardLayout cl = (CardLayout) dret.getLayout();
			cl.show(dret, String.valueOf(banda)+"-"+String.valueOf(numpanelins));
		}
	}
	public void habilitatorns(boolean torns) {
		calendari.habilitatorns(torns);
	}
	
	public void habilitaoperacions(boolean operacions) {
		botons.habilitaoperacions(operacions);
	}

	public void carregar(File selectedFile) {
		CtrlRestriccio.carregar(selectedFile);
	}

	public void guardar(File selectedFile) {
		CtrlRestriccio.guardar(selectedFile);
	}

	public void actualitza() {
		llistat.actualitza(getLlistaRestriccions());
	}
	
	public ArrayList<String> getLlistaRestriccions(){
        ArrayList<String> llista = new ArrayList<String>();
        for(int i=0; i<CtrlRestriccio.consulta_llista_res().size(); i++){
            llista.add(CtrlRestriccio.consulta_explesio_res(i));
        }
        return llista;
    }

	public void desactivaacceptar() {
		calendari.desactivaacceptar();
	}
        
        public void seleccioDia(int dia, int mes){
            botons.seleccioDia(dia, mes);
        }
	
        public void seleccioTorn(int ntorn){
            botons.seleccioTorn(ntorn); 
        }
        
        public void actualitzaEstatBotons(){
            dret.revalidate();
        }

    public void afegeixRestriccio(String r){
        CtrlRestriccio.nova_res(r);
        actualitza();
    }
    
    public boolean seleccioPerTorns(){
        return botons.seleccioPerTorns();
    }
}
