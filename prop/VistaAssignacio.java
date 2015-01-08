package prop;

import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class VistaAssignacio {
    private static final int W_WIDTH = 1200;
    private static final int W_HEIGHT = 600;
    private static OLDCtrlVistaAssignacio ctrlVistaAssignacio;
    // SELECCIO DE DADES
    private static JPanel assignacio = new JPanel();
    private static JPanel panelLlistes = new JPanel();
    private static JPanel panelMostra = new JPanel();
    private static JLabel label_torn ;
    private static JLabel label_doc;
    private static DefaultListModel llt = new DefaultListModel(); 
    private static JList llista_torns = new JList(llt);
    private static JScrollPane scrollPaneTorns ;
    private static DefaultListModel lld = new DefaultListModel(); 
    private static JList llista_doc = new JList(lld);
    private static JScrollPane scrollPaneDoc;
    // MOSTRA DADES
    private static DefaultListModel llr = new DefaultListModel(); 
    private static JList llista_res = new JList(llr);
    private static JScrollPane scrollPaneResult;
    private static JLabel label_res;
    
    
    public VistaAssignacio(OLDCtrlVistaAssignacio cva){
        ctrlVistaAssignacio = cva;
        assignacio.setLayout(null);
        panelLlistes.setLayout(null);
        panelMostra.setLayout(null);
        
        // posam la llista de torns
        label_torn = new JLabel();
        label_torn.setText("LLISTA DE TORNS");
        label_torn.setBounds(250, 0, 200, 20);
        posaTorns();
        panelLlistes.add(label_torn);
        llista_torns.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                seleccioTorn(e);
            }
        }); 
        scrollPaneTorns = new JScrollPane(llista_torns);
        scrollPaneTorns.setBounds(20, 20, W_WIDTH/2-40, W_HEIGHT/2-70);
        panelLlistes.add(scrollPaneTorns);
        
        //posam la llista de doctors
        label_doc = new JLabel();
        label_doc.setText("LLISTA DE DOCTORS");
        label_doc.setBounds(220, W_HEIGHT/2-40, 200, 20);
        panelLlistes.add(label_doc);
        posaDocs();
        scrollPaneDoc = new JScrollPane(llista_doc);
        scrollPaneDoc.setBounds(20, W_HEIGHT/2-15, W_WIDTH/2-40, W_HEIGHT/2-70);
        llista_doc.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                seleccioDoc(e);
            }
        }); 
        panelLlistes.add(scrollPaneDoc);
        
        
        // posam la llista (inicialment buida) on es mostraran les asociacions
        label_res = new JLabel();
        label_res.setText("ASSOCIAT AMB");
        label_res.setBounds(220, 0, 200, 20);
        panelMostra.add(label_res);
        
        scrollPaneResult = new JScrollPane(llista_res);
        scrollPaneResult.setBounds(0, 20, W_WIDTH/2-30, W_HEIGHT-105);
        panelMostra.add(scrollPaneResult);
        
        
        //posam els panels dins el panel general
        panelLlistes.setBounds(0, 0, W_WIDTH/2, W_HEIGHT);
        assignacio.add(panelLlistes);
        panelMostra.setBounds(W_WIDTH/2, 0, W_WIDTH/2, W_HEIGHT);
        assignacio.add(panelMostra);
    }
    
    
    private static void seleccioTorn(ListSelectionEvent e){
        Graf g = ctrlVistaAssignacio.consultaGraf();
        Vertex vt= g.getVertex((String) llista_torns.getSelectedValue(), Vertex.TORN);
        ArrayList<Doctor> llista_d = Doc_Torn.getRel((Torn)vt.getObjecte());
        llr.removeAllElements();
        for(int i=0; i<llista_d.size(); i++){
            Doctor doc = llista_d.get(i);
            llr.addElement(doc.getdni()); // <----- canviar per algo llegible
        }
    }
    
    
    private static void seleccioDoc(ListSelectionEvent e){
        Graf g = ctrlVistaAssignacio.consultaGraf();
        Vertex vd= g.getVertex((String) llista_doc.getSelectedValue(), Vertex.DOCTOR);
        ArrayList<Torn> llista_t = Doc_Torn.getRel((Doctor)vd.getObjecte());
        
        System.out.println(llista_t.size());
        
        llr.removeAllElements();
        for(int i=0; i<llista_t.size(); i++){
            llr.addElement(llista_t.get(i).toString()); // <----- canviar per algo llegible
        }
    }
    
    public void posaTorns(){
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
    }
    
    public void posaDocs(){
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
    }
    
    public JPanel tornapanel(){
        return assignacio;
    }
}
