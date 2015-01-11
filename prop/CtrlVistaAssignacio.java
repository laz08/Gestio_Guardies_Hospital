package prop;

import javax.swing.*;
import java.awt.*;


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
        llistatorns.setMaximumSize(new Dimension(320,250));
        //llistatorns.setBounds(20, 0, 350, 300);
        esquerre.setSize(350,600);
        //Top left bottom right
        esquerre.setBorder(BorderFactory.createEmptyBorder(10, 15, 5, 15));
        esquerre.add(llistatorns, BorderLayout.NORTH);
        esquerre.add(llistadoctors, BorderLayout.SOUTH);
        assignacio.add(esquerre, BorderLayout.WEST);
        assignacio.setBorder(BorderFactory.createEmptyBorder(10, 0, 5, 15));
        assignacio.add(llistaassociacio, BorderLayout.EAST);
    }



    public void posaTorns(){
        /*
        Graf g = ctrlVistaAssignacio.consultaGraf();
        llt.removeAllElements();
        if(g!=null){
            for(int i=0; i<g.numV(); i++){
                if(g.getVertex(i).getClasse() == Vertex.TORN){
                    String torn = g.getVertex(i).getId();
                    llt.addElement(torn);
                }
            }
        }
        */
    }

    public void posaDocs(){
        /*
        Graf g = ctrlVistaAssignacio.consultaGraf();
        lld.removeAllElements();
        if(g!=null){
            for(int i=0; i<g.numV(); i++){
                if(g.getVertex(i).getClasse() == Vertex.DOCTOR){
                    String torn = g.getVertex(i).getId();
                    lld.addElement(torn);
                }
            }
        }
        */
    }


    public JPanel tornavista() {
		return assignacio;
	}





    // OLD
/*
    public Graf consultaGraf(){
        return CtrlVistaAlgorismes.getGraf();
    }

    /*
    public Calendari consultaCalendari(){
        Plantilla p = CtrlPlantilla.getPlantillaActual();
        return CtrlCalendari.consultarCalendari(p.getNomPlantilla());
    }
    */


}
