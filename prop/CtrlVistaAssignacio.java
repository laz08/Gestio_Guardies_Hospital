package prop;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class CtrlVistaAssignacio {

    private CtrlVistaPrincipal ctrlVistaPrincipal;
    private JPanel assignacio = new JPanel();
    private LListatErrorAssignacio llistadoctors = new LListatErrorAssignacio(this);
    private LListatTorns llistatorns = new LListatTorns(this);
    private LlistatAssociacio llistaassociacio = new LlistatAssociacio(this);
    private JPanel esquerre = new JPanel();

    public CtrlVistaAssignacio(CtrlVistaPrincipal vpc) {
        ctrlVistaPrincipal = vpc;
        assignacio.setLayout(new BorderLayout());
        esquerre.setLayout(new BorderLayout());
        llistadoctors.setMaximumSize(new Dimension(320, 250));

        llistadoctors.setPreferredSize(new Dimension(320, 250));
        llistatorns.setPreferredSize(new Dimension(320, 250));

        //llistadoctors.setBounds(20, 300, 350, 300);
        llistatorns.setMaximumSize(new Dimension(320, 250));
        //llistatorns.setBounds(20, 0, 350, 300);
        esquerre.setSize(350, 600);
        //Top left bottom right
        esquerre.setBorder(BorderFactory.createEmptyBorder(10, 15, 5, 15));
        esquerre.add(llistatorns, BorderLayout.NORTH);
        esquerre.add(llistadoctors, BorderLayout.SOUTH);
        assignacio.add(esquerre, BorderLayout.WEST);
        assignacio.setBorder(BorderFactory.createEmptyBorder(10, 0, 5, 15));
        assignacio.add(llistaassociacio, BorderLayout.EAST);
    }

    public void posaTorns() {
        Graf g = consultaGraf();
        llistatorns.event(false);
        llistatorns.model1.clear();
        if (g != null) {
            for (int i = 0; i < g.numV(); i++) {
                if (g.getVertex(i).getClasse() == Vertex.TORN) {
                    String torn = g.getDiaMesAnydeTorn(i);
                    llistatorns.model1.addElement(torn + " || " + g.getVertex(i).getId());
                }
            }
        }
        llistatorns.event(true);

    }

    public void posaDocs() {
        Graf g = consultaGraf();
        llistadoctors.event(false);
        llistadoctors.model1.clear();
        if (g != null) {
            for (int i = 0; i < g.numV(); i++) {
                if (g.getVertex(i).getClasse() == Vertex.DOCTOR) {
                    //String doc = g.getVertex(i).getId();
                    String doc = g.retornaDoctorDeVertex(i);
                    llistadoctors.model1.addElement(doc);
                }
            }
        }
        llistadoctors.event(true);

    }

    public JPanel tornavista() {
        return assignacio;
    }

    public Graf consultaGraf() {
        return CtrlAlgorisme.getGraf();
    }

    public Calendari consultaCalendari() {
        Plantilla p = CtrlPlantilla.getPlantillaActual();
        return CtrlCalendari.consultarCalendari(p.getNomPlantilla());
    }

    public void mostraTornsAssociats(String Doctor) {
        llistaassociacio.model1.clear();
        ArrayList<Torn> torns = Doc_Torn.getTornsRel(Doctor);
        //System.out.println(torns.size());
        for (int i = 0; i < torns.size(); i++) {
            String res = transformaTornAStringLlista(torns.get(i));
            llistaassociacio.model1.addElement(res);
        }
    }

    public String transformaTornAStringLlista(Torn t){
        String resultat = "";
        int h = t.getHora_inici();
        if (h == 5){
            resultat = "(Matí) ";
        }
        else if(h == 12){
            resultat = "(Tarda) ";
        }
        else resultat = "(Nit) ";

        int pos = t.getPosicio();

        Plantilla plantilla = CtrlPlantilla.getPlantillaActual();

        int any_i = CtrlCalendari.consultarCalendari(plantilla.getNomPlantilla()).getAny();
        int any_pos = pos/365;
        int any = any_i + any_pos;

        GregorianCalendar d = CtrlCalendari.quinDia(pos, any);
        int mes = d.get(Calendar.MONTH);
        int dia = d.get(Calendar.DAY_OF_MONTH);

        resultat = resultat + dia + " de ";
        switch(mes){
            case 0: resultat = resultat + "Gener" + " ";
                break;
            case 1: resultat = resultat + "Febrer" + " ";
                break;
            case 2: resultat = resultat + "Març" + " ";
                break;
            case 3: resultat = resultat + "Abril" + " ";
                break;
            case 4: resultat = resultat + "Maig" + " ";
                break;
            case 5: resultat = resultat + "Juny" + " ";
                break;
            case 6: resultat = resultat + "Juliol" + " ";
                break;
            case 7: resultat = resultat + "Agost" + " ";
                break;
            case 8: resultat = resultat + "Setembre" + " ";
                break;
            case 9: resultat = resultat + "Octubre" + " ";
                break;
            case 10: resultat = resultat + "Novembre" + " ";
                break;
            case 11: resultat = resultat + "Desembre" + " ";
                break;
        }
        resultat = resultat + "del " + any;
        System.out.println(resultat);
        return resultat;

    }
    
    public void mostraDoctorsAssociats(String Torn) {
        llistaassociacio.model1.clear();
        ArrayList<Doctor> docs = Doc_Torn.getDocRel(Torn);
        for (int i = 0; i < docs.size(); i++) {
            //3System.out.println(docs.get(i).getdni());
            Doctor doc = docs.get(i);
            String content = doc.getdni() + " | " + doc.getCognom1() + " "
                    + doc.getCognom2() + ", " + doc.getNom() ;
            llistaassociacio.model1.addElement(content);
        }
    }


}
