package prop;

import javax.swing.*;
import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CtrlVistaHospital {
	private JPanel dret = new JPanel();
	private JPanel esquerre = new JPanel();
	private JPanel hospital = new JPanel();
	private LlistatErrorHospital llistat = new LlistatErrorHospital(this);
	private BotoMesTextHospital doctor = new BotoMesTextHospital(this);
	private BotoLlistaHospital restriccions = new BotoLlistaHospital(this);
	private TresBotonsHospital cgc = new TresBotonsHospital(this);
	private CtrlVistaPrincipal ctrlvp;

	public CtrlVistaHospital(CtrlVistaPrincipal cvp) {
		ctrlvp = cvp;
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


    //AUXILIARS
    public void actualitza_llista_docs(){
        System.out.println("FIns aqui arribo");
        llistat.esborraElementsModel();
        System.out.println("FIns aqui arribo");
        String contingut = getLlistaDocs_nom();
        System.out.println("FIns aqui arribo");
        if (!contingut.equals("")){
            if (contingut.length() > 0) {
                String separadors = "[ \n]";
                String[] separat = contingut.split(separadors);
                for (int i = 0; i < separat.length; i += 7) {
                    llistat.model1.addElement(separat[i] + " " + separat[i + 1] + " " + separat[i + 2] + " " + separat[i + 3]);
                }
            }
        }
        else{
            System.out.println("FIns aqui arribo");
            llistat.model1.addElement("Hola");
        }
    }

    public boolean crea_doc(){
        boolean valid = true;
        String d = doctor.t1.getText();
        String n = doctor.t2.getText();
        String cg1 = doctor.t3.getText();
        String cg2 = doctor.t4.getText();
        String so = doctor.t5.getText();
        String telf = doctor.t6.getText();
        String cor = doctor.t7.getText();

        if(d.equals("")|| n.equals("") || cg1.equals("") || cg2.equals("") || so.equals("") || telf.equals("") || cor.equals("")){
            valid = false;
            errorUnOMesDunCampNull();
        }
        if(valid && d.length() == 8){
            try {
                int dni_num = Integer.parseInt(d);
            } catch (Exception e){
                condicionsDNIError();
                valid = false;
            }
        }
        else{
            valid = false;
            condicionsDNIError();
        }

        int s = 0;
        int t = 0;
        //Si tots els camps estan plens...
        if(valid){
            try {
                s = Integer.parseInt(so);
                if(s < 0){
                    valid = false;
                    errorHaDeSerUnReal("Sou");
                }
            } catch (Exception e){
                errorHaDeSerUnReal("Sou");
                valid = false;
            }
            //Si sou és valid
            if(valid){
                try {
                    t = Integer.parseInt(telf);
                    if (t < 0){
                        valid = false;
                        errorHaDeSerUnReal("Telèfon");
                    }
                } catch(Exception e){
                    errorHaDeSerUnReal("Telèfon");
                    valid = false;
                }
            }
        }
        //Si els camps estan plens i sou i telefon son correctes...
        if(valid){
            if(esCorreu(cor)){
                CtrlHospital.creariAfegirDoctor(d, n, cg1, cg2, s, t, cor);
            }
            else{
                noEsCorreu();
                valid = false;
            }
        }
        return valid;

    }

    public void modifica_doc(){

    }


    //Comunicació amb capa Domini
    private String getLlistaDocs_nom(){
        return CtrlHospital.getLlistatDoctorsenString_nom();
    }






    //MISSATGES ERROR
    public void errorUnOMesDunCampNull(){
        //missatgeErrors.setText("ERROR: Falten dades en un o més camps del doctor.");
    }
    //Per a sou i telèfon
    public void errorHaDeSerUnReal(String s){
       // missatgeErrors.setText("ERROR: " + s + " ha de ser un número positiu.");
    }

    public void noEsCorreu(){
        //missatgeErrors.setText("ERROR: El correu introduït no és vàlid.");
    }

    public void esborrarTotsErrors(){
        //missatgeErrors.setText("");
    }
    public void condicionsDNIError(){
        //missatgeErrors.setText("ERROR: DNI ha de ser una cadena de 8 caràcters numèrics.");
    }

    public boolean esCorreu(String correu){
        Pattern p = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
        Matcher m = p.matcher(correu);
        return m.matches();

    }

}
