package prop;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

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
        llistatorns.model1.clear();
        if (g != null) {
            for (int i = 0; i < g.numV(); i++) {
                if (g.getVertex(i).getClasse() == Vertex.TORN) {
                    String torn = g.getDiaMesAnydeTorn(i);
                    llistatorns.model1.addElement(torn);
                }
            }
        }

    }

    public void posaDocs() {
        Graf g = consultaGraf();
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
        for (int i = 0; i < torns.size(); i++) {
            llistaassociacio.model1.addElement(torns.get(i).toString());
        }
    }
    
    public void mostraDoctorsAssociats(String Torn) {
        llistaassociacio.model1.clear();
        ArrayList<Doctor> docs = Doc_Torn.getDocRel(Torn);
        for (int i = 0; i < docs.size(); i++) {
            llistaassociacio.model1.addElement(docs.get(i).getdni());
        }
    }


}
