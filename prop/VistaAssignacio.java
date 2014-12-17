package prop;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class VistaAssignacio {
    private static final int W_WIDTH = 1200;
    private static final int W_HEIGHT = 600;
    private static CtrlVistaAssignacio ctrlVistaAssignacio;
    // SELECCIO DE DADES
    private static JPanel assignacio = new JPanel();
    private static JPanel panelLlistes = new JPanel();
    private static JPanel panelMostra = new JPanel();
    private static JLabel label_torn ;
    private static JLabel label_doc;
    private static JList llista_torns = new JList();
    private static JScrollPane scrollPaneTorns ;
    private static JList llista_doc = new JList();
    private static JScrollPane scrollPaneDoc;
    // MOSTRA DADES
    private static JScrollPane scrollPaneResult;
    private static JList llista_res;
    private static JLabel label_res;
    
    
    public VistaAssignacio(CtrlVistaAssignacio cva){
        ctrlVistaAssignacio = cva;
        assignacio.setLayout(null);
        panelLlistes.setLayout(null);
        panelMostra.setLayout(null);
        
        // posam la llista de torns
        label_torn = new JLabel();
        label_torn.setText("LLISTA DE TORNS");
        label_torn.setBounds(250, 0, 200, 20);
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
        scrollPaneDoc = new JScrollPane(llista_doc);
        scrollPaneDoc.setBounds(20, W_HEIGHT/2-15, W_WIDTH/2-40, W_HEIGHT/2-70);
        llista_torns.addListSelectionListener(new ListSelectionListener() {
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
        
        llista_res = new JList();
        llista_res.setDragEnabled(false);
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
        
    }
    
    
    private static void seleccioDoc(ListSelectionEvent e){
        
    }
    
    public JPanel tornapanel(){
        return assignacio;
    }
}
