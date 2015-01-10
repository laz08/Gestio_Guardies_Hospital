/*
package prop;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class VistaRestriccio {

    private static OLDCtrlVistaRestriccions ctrlVistaRestriccions;
    private JPanel restriccions = new JPanel();
    private JPanel buttonsrestriccio = new JPanel();
    private JPanel insertrestriccio = new JPanel();
    private JPanel llistatrestriccio = new JPanel();
    private JPanel gestiorestriccio = new JPanel();
    private JButton crearrestriccio = new JButton("Crear");
    private JButton eliminarrestriccio = new JButton("Eliminar");
    private JButton carregarrestriccio = new JButton("Carregar");
    private JButton guardarrestriccio = new JButton("Guardar");
    private DefaultListModel DefLlistaRestriccions = new DefaultListModel();
    private JList llistaRestriccions = new JList(DefLlistaRestriccions);
    private JScrollPane scrollpane = new JScrollPane(llistaRestriccions);
    private JTextField introduirrestriccio = new JTextField(40);
    private JPanel insertaracceptarrestriccio = new JPanel();
    private JPanel insertartextrestriccio = new JPanel();
    private JTextField mostraErrors = new JTextField(40);
    
    private JPanel espaiblanc = new JPanel();


    public VistaRestriccio(OLDCtrlVistaRestriccions cvr) {
        ctrlVistaRestriccions = cvr;
        restriccions.setLayout(new BorderLayout());

        inicialitza_llistat();
        inicialitza_gestio();
        inicialitza_insercio();

    }

    public void inicialitza_llistat() {
        scrollpane.setPreferredSize(new Dimension(600, 520));
        scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

       // llistatrestriccio.setSize(new Dimension(600, 520));
        llistatrestriccio.setLayout(new BoxLayout(llistatrestriccio, BoxLayout.Y_AXIS));
        llistatrestriccio.add(scrollpane);//, BorderLayout.NORTH);
        llistatrestriccio.add(mostraErrors, BorderLayout.SOUTH);
        mostraErrors.setEditable(false);
        mostraErrors.setForeground(Color.RED);


        carregaRestriccions();
        restriccions.add(llistatrestriccio, BorderLayout.WEST);
        //restriccions.add(mostraErrors, BorderLayout.SOUTH);
    }

    private void carregaRestriccions() {
        ArrayList<String> llistaERestriccions = ctrlVistaRestriccions.getLlistaRestriccions();
        DefLlistaRestriccions.removeAllElements();
        for (int i = 0; i < llistaERestriccions.size(); i++) {
            DefLlistaRestriccions.addElement(llistaERestriccions.get(i));
        }
    }

    public void inicialitza_insercio() {
        /*insertaracceptarrestriccio.setLayout(new BorderLayout());
        insertartextrestriccio.setLayout(new FlowLayout());
        insertrestriccio.setLayout(new BorderLayout());
        insertartextrestriccio.add(introduirrestriccio, BorderLayout.CENTER);

        insertrestriccio.add(insertartextrestriccio, BorderLayout.CENTER);
        insertrestriccio.add(insertaracceptarrestriccio, BorderLayout.SOUTH);
        insertrestriccio.setPreferredSize(new Dimension(600, 300));

        gestiorestriccio.add(insertrestriccio, BorderLayout.CENTER);
        
        insertaracceptarrestriccio.setLayout(new BorderLayout());
        insertartextrestriccio.setLayout(new FlowLayout());
        insertrestriccio.setLayout(new BorderLayout());
        //espaiblanc.setBounds(0,0,200,1000);
        insertrestriccio.add(espaiblanc);
        introduirrestriccio.setBounds(50,50,500,25);
        insertrestriccio.add(introduirrestriccio);
        insertrestriccio.add(insertaracceptarrestriccio);
        introduirrestriccio.setLocation(50, 260);

        insertrestriccio.setPreferredSize(new Dimension(600, 300));

        gestiorestriccio.add(insertrestriccio, BorderLayout.NORTH);
      
        
    }

    public void inicialitza_gestio() {
        buttonsrestriccio.setLayout(new GridBagLayout());
        buttonsrestriccio.setPreferredSize(new Dimension(600, 250));
        gestiorestriccio.setLayout(new BorderLayout());
        gestiorestriccio.add(buttonsrestriccio, BorderLayout.SOUTH);
        crearrestriccio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crearRestriccio();
            }
        });
        buttonsrestriccio.add(crearrestriccio);
        eliminarrestriccio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarRestriccio();
            }
        });
        buttonsrestriccio.add(eliminarrestriccio);
        guardarrestriccio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarRestriccio();
            }
        });
        buttonsrestriccio.add(guardarrestriccio);
        carregarrestriccio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carregarRestriccio();
            }
        });
        buttonsrestriccio.add(carregarrestriccio);
        restriccions.add(gestiorestriccio, BorderLayout.EAST);
    }

    public JPanel tornapanel() {
        return restriccions;
    }

    private void crearRestriccio() {
        if (!introduirrestriccio.getText().isEmpty()) {
            try{
                ctrlVistaRestriccions.creaRestriccio(introduirrestriccio.getText());
                esborraErrors();
                introduirrestriccio.setText("");
                carregaRestriccions();
            } catch (Exception e){
                mostraErrors.setText("ERROR: L'expressió de restriccions introduïda no és vàlida.");
            }

        }
        else{
            mostraErrors.setText("ERROR: El camp d'introducció de restriccions no pot estar en blanc.");
        }
    }

    private void eliminarRestriccio() {
        esborraErrors();
        String rSeleccionada = (String) llistaRestriccions.getSelectedValue();
        if(rSeleccionada.equals("")){
            mostraErrors.setText("ERROR: Has de seleccionar una restricció de la llista.");
        }
        else {
            ctrlVistaRestriccions.eliminaRestriccio(rSeleccionada);
            carregaRestriccions();
        }
    }

    private void carregarRestriccio() {
        try{
            JFileChooser chooser = new JFileChooser();
            chooser.showOpenDialog(restriccions);
            File f = chooser.getSelectedFile();
            String[] sRestriccions  = ctrlVistaRestriccions.carregaRestriccions(f);
            for (int i = 0; i < sRestriccions.length-1; i++) {
                ctrlVistaRestriccions.creaRestriccio(sRestriccions[i]);
            }
            carregaRestriccions();
        }catch(Exception e){
            mostraErrors.setText("ERROR: No s'han pogut carregar les restriccions.");
        }
        carregaRestriccions();
        esborraErrors();
    }

    private void guardarRestriccio() {
        JFileChooser chooser = new JFileChooser();

        chooser.showSaveDialog(restriccions);
        File f = chooser.getSelectedFile();
        ctrlVistaRestriccions.guardaRestriccions(f);
    }

    public void esborraErrors(){
        mostraErrors.setText("");
    }
}

*/