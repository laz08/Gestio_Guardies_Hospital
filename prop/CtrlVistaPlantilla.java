package prop;

import javax.swing.*;
import java.awt.*;
import java.io.File;


public class CtrlVistaPlantilla {
	private CtrlVistaPrincipal ctrlvp;
	private JPanel plantilla = new JPanel();
	private JPanel dret = new JPanel();
	private JPanel esquerre = new JPanel();
	private TresBotonsPlantilla crearguardarcarregar;
	private BotoTextPlantilla creacioplantilla;
	private BotoLlistaMesPlantilla caractplantilla;
	private LlistatErrorPlantilla plantilles;
	private BotoLlistaPlantilla doctorssenseplantilla;
	
	public CtrlVistaPlantilla(CtrlVistaPrincipal cvp) {
		ctrlvp = cvp;
		inicialitzacions_creadora();
		plantilla.setLayout(new BorderLayout());
		dret.setLayout(new CardLayout());
		esquerre.setLayout(new CardLayout());
		dret.add(crearguardarcarregar, "2-1");
		dret.add(creacioplantilla, "2-2");
		dret.add(caractplantilla, "2-3");
		esquerre.add(plantilles, "1-1");
		esquerre.add(doctorssenseplantilla, "1-2");
		plantilla.add(esquerre, BorderLayout.WEST);
		plantilla.add(dret, BorderLayout.EAST);
	}
	
	public void inicialitzacions_creadora() {
		crearguardarcarregar = new TresBotonsPlantilla(this);
		plantilles = new LlistatErrorPlantilla(this);
		creacioplantilla = new BotoTextPlantilla(this,plantilles);
		caractplantilla = new BotoLlistaMesPlantilla(this,plantilles);
		plantilles.setBotoLlistaMesPlantilles(caractplantilla);
		crearguardarcarregar.setBotoLlistaMesPlantilla(plantilles);
		doctorssenseplantilla = new BotoLlistaPlantilla(this,caractplantilla);
		
	}
	
	public JPanel tornavista() {
		return plantilla;
	}
	
	public void swap(int banda, int numpanelins) {
		if (banda == 1) {
			CardLayout cl = (CardLayout) esquerre.getLayout();
			cl.show(esquerre, String.valueOf(banda)+"-"+String.valueOf(numpanelins));
		}
		else {
			CardLayout cl = (CardLayout) dret.getLayout();
			cl.show(dret, String.valueOf(banda)+"-"+String.valueOf(numpanelins));
		}
	}
	
	public void actualitzarLlistaDocs() {
		doctorssenseplantilla.actualitzarLlistaDoctors();
	}
    public void actualitzarLlistaPlantilles(){
        plantilles.actualitzarLlistatPlantilles();
    }
	
	public void mod(boolean b) {
		caractplantilla.mod(b);
	}
	
	//FUNCIONS DE COMUNICACIO AMB CAPA DE DOMINI
	
	public String obteLlistaPlantilles() {
		return CtrlPlantilla.getLlista_plantilles();
	}
	
	public void crearPlantilla(String plt) {
		CtrlPlantilla.creariAfegirPlantilla(plt);
	}
	
	public boolean existeixPlantilla(String plt) {
		return CtrlPlantilla.existeixPlantilla(plt);
	}
	
	public String llistaDoctorsPlantilla(String plt) {
		return CtrlPlantilla.getPlantillaespecifica(plt);
	}
	
	public String llistaDoctorsSensePlt() {
		return CtrlHospital.getDoctorsSensePlt();
	}
	
	public void assignarDocAPlt(String dni, String plt) {
		CtrlPlantilla.afegirDoctorAPlantilla(dni, plt);
	}
	
	public void eliminarPlantilla(String plt) {
		CtrlPlantilla.esborrarPlantilla(plt);
	}
	
	public void desasignarDoctorPlantilla(String dni, String plt) {
		CtrlPlantilla.esborrarDoctordePlantilla(dni, plt);
	}
	
	public void guardarPlantilles(File f) {
		CtrlPlantilla.guardar(f);
		plantilles.actualitzarLlistatPlantilles();
	}
	
	public void carregarPlantilles(File f) {
		CtrlPlantilla.carregar(f);
		plantilles.actualitzarLlistatPlantilles();
	}

	public void removeselection() {
		plantilles.removeselection();
	}

    public void reiniciaCamp(){
        creacioplantilla.netejaNomPlant();
    }

}
