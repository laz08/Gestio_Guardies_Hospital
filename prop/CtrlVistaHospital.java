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

    private ArrayList<String> rest_ass = new ArrayList<String>();
    private ArrayList<String> rest_no_ass = new ArrayList<String>();

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


    /**
     * Inicialitza la vista d'hospital
     */
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


        //SIZE




        cgc = new TresBotonsHospital(this);
    }
    /*
        X - Y
        =============
        X = Banda (1 == Esq. ; 2 == Dreta)
        Y = Núm. del panell
        =============

     */

    /**
     * Canvia els panells a mostrar en la vista d'Hospital
     * @param banda
     * @param numpanelins
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




    //Comunicació amb capa Domini

    /**
     *
     * Retorna la llista de doctors existents a l'hospital ordenats per nom
     * @return
     */
    public String getLlistaDocs_nom(){
        return CtrlHospital.getLlistatDoctorsenString_nom();
    }

    /**
     * Crea un doctor amb tots els atributs
     * (Dni, nom, cognoms, sou, telèfon i correu electrònic)
     * @param d
     * @param n
     * @param cg1
     * @param cg2
     * @param s
     * @param t
     * @param cor
     */
    public void crea_doctor(String d, String n, String cg1, String cg2, int s, int t, String cor){
        CtrlHospital.creariAfegirDoctor(d, n, cg1, cg2, s, t, cor);
    }

    /**
     * Modifica el doctor amb dni d amb tots els atributs nous
     * (Es modifica el nom, cognoms, sou, telèfon i correu electrònic)
     * @param d
     * @param n
     * @param cg1
     * @param cg2
     * @param s
     * @param t
     * @param cor
     */
    public void modifica_doctor(String d, String n, String cg1, String cg2, int s, int t, String cor){
        CtrlHospital.modificaAtributs(d, n, cg1, cg2, s, t, cor);
    }

    /**
     * Retorna un String amb tota la informació del doctor amb dni dni
     * desde la capa de Domini
     * @param dni
     * @return
     */
    public String getDoctorEspecific(String dni){
        return CtrlHospital.getDoctorEspecificString(dni);
    }

    /**
     * Activa els botons de Restriccions i eliminar
     */
    public void activaBotonsRestriccionsEliminar(){
        doctor.b1.setEnabled(true);
        doctor.b2.setEnabled(true);
        doctor.b3.setEnabled(true);
    }

    /**
     * Desactiva els botons de Restriccions i eliminar
     */
    public void desactivaBotonsRestriccionsEliminar(){
        doctor.b1.setEnabled(false);
        doctor.b2.setEnabled(false);
        doctor.b3.setEnabled(false);
    }

    /**
     * Esborra el doctor amb dni d de l'hospital
     * @param d
     */
    public void esborrar_doctor(String d){
        CtrlHospital.eliminarDoctor(d);

    }

    /**
     * Retorna un booleà indicant si existeix doctor amb aquell DNI o no
     * @param d
     * @return
     */
    public boolean existeix_Doc(String d){
        return CtrlHospital.existeixDoctor(d);
    }

    /**
     * Actualitza el llistat de doctors de l'hospital
     */
    public void actualitza_Docs(){
        llistat.actualitza_llista_docs();
    }


    /**
     * Modifica el booleà que indica si s'han de tenir en compte els events o no
     * @param b
     */
	public void mod(boolean b) {
		doctor.mod(b);
	}

    /**
     * Donat un DNI d'un doctor, carrega a la llista de restriccions associades
     * totes aquelles restriccions associades al metge amb aquell DNI
     * @param dni
     */
    public void carregaRestriccionsAssociades(String dni){
        //Donat un DNI d'un doctor, agafem les restriccions associades de Doc_res
        ArrayList<Integer> res = Doc_Res.getRestriccions(dni);
        //Neteja
        restriccionsAssociades.model1.clear();
        rest_ass.clear();
        for(int i = 0; i < res.size(); ++i) {
            String r = CtrlRestriccio.consulta_explesio_res(res.get(i));
            rest_ass.add(r);
            restriccionsAssociades.model1.addElement(tradueix_restriccio(r));
        }
    }

    /**
     * Donat un DNI d'un doctor, carrega a la llista de restriccions associades
     * totes aquelles restriccions no associades al metge amb aquell DNI
     * @param dni
     */
    public void carregaRestriccionsNOAssociades(String dni){
        ArrayList<Integer> res = Doc_Res.getRestriccionsNoAssociades(dni);
        restriccionsNoAssociades.model1.clear();
        rest_no_ass.clear();
        for(int i = 0; i < res.size(); ++i){
            String r = CtrlRestriccio.consulta_explesio_res(res.get(i));
            rest_no_ass.add(r);
            restriccionsNoAssociades.model1.addElement(tradueix_restriccio(r));
        }
    }

    /**
     * Retorna l'expressió de la restricció real (sense traduir)
     * @param pos
     * @return
     */
    public String consultaExpressioRealAssociada(int pos){
        return rest_ass.get(pos);
    }

    /**
     * Retorna l'expressió de la restricció real (sense traduir)
     * @param pos
     * @return
     */
    public String consultaExpressioRealNOAssociada(int pos){
        return rest_no_ass.get(pos);
    }



    /**
     * Tradueix, si es una restricció d'H (de torns), a una expressió entendible per a l'usuari
     * (User-friendly)
     * @param r
     */
    public String tradueix_restriccio(String r){
        String r_trad = "";
        if(r.charAt(0) == 'H'){
            //Es per torn
            r_trad = "H";
            for(int j = 1; j < r.length(); ++j){
                if(r.charAt(j) == '5'){
                    r_trad += "MATI";
                }
                else if(r.charAt(j) == '1'){
                    ++j;
                    r_trad += "TARDA";
                }
                else if(r.charAt(j) == '2'){
                    ++j;
                    r_trad += "NIT";
                }
                else r_trad += r.charAt(j);
            }
            return r_trad;
        }
        else {
            //Es per dia
            r_trad = "D";

            for (int j = 1; j < r.length(); ++j) {
                char a = r.charAt(j);
                if (a == '-') {
                    //Comença un mes
                    ++j;
                    r_trad += " ";
                    a = r.charAt(j);
                    if (a == '0') {
                        //GENER
                        r_trad += "GENER";
                    } else if (a == '1') {
                        //FEBRER, NOVEMBRE O DESEMBRE
                        ++j;
                        a = r.charAt(j);
                        if (a == '0') {
                            //NOVEMBRE
                            r_trad += "NOVEMBRE";
                        } else if (a == '1') {
                            //DESEMBRE
                            r_trad += "DESEMBRE";
                        } else {
                            r_trad += "FEBRER";
                            --j;
                        }
                    } else if (a == '2') {
                        //MARÇ
                        r_trad += "MARÇ";
                    } else if (a == '3') {
                        //ABRIL
                        r_trad += "ABRIL";
                    } else if (a == '4') {
                        //MAIG
                        r_trad += "MAIG";
                    } else if (a == '5') {
                        //JUNY
                        r_trad += "JUNY";
                    } else if (a == '6') {
                        //JULIOL
                        r_trad += "JULIOL";
                    } else if (a == '7') {
                        //AGOST
                        r_trad += "AGOST";
                    } else if (a == '8') {
                        //SETEMBRE
                        r_trad += "SETEMBRE";
                    } else if (a == '9') {
                        //OCTUBRE
                        r_trad += "OCTUBRE";
                    }
                } else
                    r_trad += a;
            }
            return r_trad;
        }
    }

    /**
     * Reinicia tots els camps de doctor (els esborra)
     */
    public void reiniciaVista(){
        doctor.esborraTotsElsCamps();
    }

    /**
     * Donada una expressió d'una restricció i un DNI, els associa
     * @param r
     * @param dni
     */
    public void associaRestriccio(String r, String dni) {
        Doc_Res.relaciona(dni, CtrlRestriccio.consulta_pos(r));
    }

    /**
     * Donada una expressió d'una restricció i un DNI, els desassocia
     * @param r
     * @param dni
     */
    public void desassociaRestriccio(String r, String dni){
        Doc_Res.elimina(dni, CtrlRestriccio.consulta_pos(r));
    }


}
