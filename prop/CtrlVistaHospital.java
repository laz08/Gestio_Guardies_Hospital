package prop;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class CtrlVistaHospital {
	private JPanel dret = new JPanel();
	private JPanel esquerre = new JPanel();
	private JPanel hospital = new JPanel();
	private LlistatErrorHospital llistat;                   //Llista de doctors
	private BotoMesTextHospital doctor;                     //Tots els valors d'un Doctor (modificar i crear)
	private BotoLlistaHospital restriccionsAssociades;      //Llistat de restriccions relacionades amb el doctor
	private BotoLlistaHospital restriccionsNoAssociades;    //Llistat de restriccions NO relacionades amb el doctor
	private TresBotonsHospital cgc;
	private CtrlVistaPrincipal ctrlvp;

	public CtrlVistaHospital(CtrlVistaPrincipal cvp) {
		ctrlvp = cvp;
        inicialitzacions_creadora();
		hospital.setLayout(new BorderLayout());
		esquerre.setLayout(new CardLayout());
        esquerre.setBorder(BorderFactory.createEmptyBorder(10, 15, 5, 60));
		esquerre.add(llistat, "1-1");
		esquerre.add(restriccionsNoAssociades, "1-2");
        esquerre.add(restriccionsAssociades, "1-3");
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
        //stubRestriccions();
    	doctor = new BotoMesTextHospital(this);
        llistat = new LlistatErrorHospital(this);
        doctor.assignallista(llistat);
        restriccionsNoAssociades = new BotoLlistaHospital(this);
        restriccionsAssociades = new BotoLlistaHospital(this);

        //Per quan afegim
        restriccionsNoAssociades.setMode(false);
        //Per quan eliminem
        restriccionsAssociades.setMode(true);

        llistat.setBotoMesTextHospital(doctor);
        restriccionsNoAssociades.setBotoMesTextHospital(doctor);
        restriccionsAssociades.setBotoMesTextHospital(doctor);


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
    public void modifica_doctor(String d, String n, String cg1, String cg2, int s, int t, String cor){
        CtrlHospital.modificaAtributs(d, n, cg1, cg2, s, t, cor);
    }
    public String getDoctorEspecific(String dni){
        return CtrlHospital.getDoctorEspecificString(dni);
    }

    public void activaBotonsRestriccionsEliminar(){
        doctor.b1.setEnabled(true);
        doctor.b2.setEnabled(true);
        doctor.b3.setEnabled(true);
    }

    public void desactivaBotonsRestriccionsEliminar(){
        doctor.b1.setEnabled(false);
        doctor.b2.setEnabled(false);
        doctor.b3.setEnabled(false);
    }


    public void esborrar_doctor(String d){
        CtrlHospital.eliminarDoctor(d);

    }

    public boolean existeix_Doc(String d){
        return CtrlHospital.existeixDoctor(d);
    }

    public void actualitza_Docs(){
        llistat.actualitza_llista_docs();
    }

	public void mod(boolean b) {
		doctor.mod(b);
	}

    public void carregaLlistaRestriccions(){
        ArrayList<String> lr = new ArrayList<String>();
        for(int i=0; i<CtrlRestriccio.consulta_llista_res().size(); i++){
            lr.add(CtrlRestriccio.consulta_explesio_res(i));
        }
        restriccionsNoAssociades.model1.clear();

        //llistaRes.clear();//llistaRes.removeAllElements(); // buidam llista restriccions anteriors
        for(int i=0; i<lr.size(); i++){
            restriccionsNoAssociades.model1.addElement(lr.get(i));
        }

    }

    public void carregaRestriccionsAssociades(String dni){
        //Donat un DNI d'un doctor, agafem les restriccions associades de Doc_res
        ArrayList<Integer> res = Doc_Res.getRestriccions(dni);
        //Neteja
        restriccionsAssociades.model1.clear();
        for(int i = 0; i < res.size(); ++i) {
            restriccionsAssociades.model1.addElement(CtrlRestriccio.consulta_explesio_res(res.get(i)));
        }
    }

    public void carregaRestriccionsNOAssociades(String dni){
        ArrayList<Integer> res = Doc_Res.getRestriccionsNoAssociades(dni);
        restriccionsNoAssociades.model1.clear();
        for(int i = 0; i < res.size(); ++i){
            restriccionsNoAssociades.model1.addElement(CtrlRestriccio.consulta_explesio_res(res.get(i)));
        }
    }

    public void stubRestriccions(){
        CtrlRestriccio.nova_res("H (1)XOR(2)");
        CtrlRestriccio.nova_res("D (1-1)XOR(2-1)");
        CtrlRestriccio.nova_res("D (1-2)AND(2-4)");

    }


    public void reiniciaVista(){
        doctor.esborraTotsElsCamps();
    }

    public void associaRestriccio(String r, String dni) {
        Doc_Res.relaciona(dni, CtrlRestriccio.consulta_pos(r));
    }

    public void desassociaRestriccio(String r, String dni){
        Doc_Res.elimina(dni, CtrlRestriccio.consulta_pos(r));
    }


}
