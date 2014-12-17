package prop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

public class VistaHospital implements ActionListener/*, ListSelectionListener*/{
    private static CtrlVistaHospital ctrlVistaHospital;

    private JPanel hospital = new JPanel();
    private JPanel switchgestio = new JPanel();
    private JPanel buttonshospital = new JPanel();
    private JPanel inserthospital = new JPanel();
    private JPanel llistathospital = new JPanel();
    private JPanel gestiohospital = new JPanel();
    private JButton creardoctor = new JButton("Crear Doctor");
    private JButton carregarhospital = new JButton("Carregar Doctors");
    private JButton guardarhospital = new JButton("Guardar Doctors");
    private JFileChooser obrirdirectori = new JFileChooser();
    private JPanel insertaracceptarhospital = new JPanel();
    private DefaultListModel llistaDocs = new DefaultListModel();
    private JList texthospital = new JList(llistaDocs);
    private JScrollPane scrollpane = new JScrollPane(texthospital);
    private JPanel insertartexthospital = new JPanel();

    //Panel i Textos de doctor
    private JButton eliminardoctor = new JButton("Eliminar Doctor");
    private JButton afegirrestriccio = new JButton("Afegir Restricció");
    private JButton eliminarrestriccio = new JButton("Eliminar Restricció");
    private JButton acceptardoctor = new JButton("Acceptar");
    private JButton enreredoctor = new JButton("Enrere");
    private JPanel modificadoctor = new JPanel();
    private JLabel labeldni = new JLabel("DNI:");
    private JTextField dni = new JTextField();
    private JLabel labelnom = new JLabel("Nom:");
    private JTextField nom = new JTextField();
    private JLabel labelcognom = new JLabel("Primer Cognom:");
    private JTextField cognom = new JTextField();
    private JLabel labelcognom2 = new JLabel("Segon Cognom:");
    private JTextField cognom2 = new JTextField();
    private JLabel labelsou = new JLabel("Sou:");
    private JTextField sou = new JTextField();
    private JLabel labeltelefon = new JLabel("Telefon:");
    private JTextField telefon = new JTextField(9);
    private JLabel labelcorreu = new JLabel("Correu:");
    private JTextField correu = new JTextField();
    GridBagConstraints c = new GridBagConstraints();

    //Panel Gestio Restriccions

    /*
    private DefaultListModel llistaDocs = new DefaultListModel();
    private JList texthospital = new JList(llistaDocs);
    private JScrollPane scrollpane = new JScrollPane(texthospital);
     */
    private JPanel switchllista = new JPanel();
    private JPanel restriccions = new JPanel();
    private DefaultListModel llistaRes = new DefaultListModel();
    private JList llistarestriccions = new JList(llistaRes);
    private JButton enrererestriccions = new JButton("Enrere");
    private JButton acceptarrestriccions = new JButton("Acceptar");


    // ----------------------FUNCIONS----------------------
    public VistaHospital(CtrlVistaHospital cvh) {
        ctrlVistaHospital = cvh;
        hospital.setLayout(new BorderLayout());
        switchllista.setLayout(new CardLayout());
        switchllista.add(llistathospital, "llistathospital");
        switchllista.add(restriccions, "restriccions");
        switchgestio.setLayout(new CardLayout());
        gestiohospital.setLayout(new GridLayout(2, 1));
        inicialitza_Docs();
        inicialitza_llistat();
        inicialitza_modificacio();
        inicialitza_insercio();
        inicialitza_gestio();
    }



    public void inicialitza_llistat() {
        enrererestriccions.addActionListener(this);
        acceptarrestriccions.addActionListener(this);
        restriccions.setLayout(new GridBagLayout());
        texthospital.addMouseListener(new ratonllistaDocs());
        texthospital.setPreferredSize(new Dimension(600,460));
        scrollpane.setPreferredSize(new Dimension(600, 460));
        scrollpane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        llistathospital.setLayout(new BorderLayout());
        llistathospital.add(scrollpane,BorderLayout.NORTH);
        hospital.add(switchllista, BorderLayout.WEST);
        hospital.add(switchgestio, BorderLayout.EAST);
        texthospital.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        llistathospital.setVisible(false);
        //Panel restriccions
        llistarestriccions.addMouseListener(new ratonllistaRes());
        llistarestriccions.setPreferredSize(new Dimension(500,400));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weighty = 1.0;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 3;
        restriccions.add(llistarestriccions, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weighty = 1;
        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 1;
        restriccions.add(enrererestriccions, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weighty = 1;
        c.gridx = 2;
        c.gridy = 4;
        c.gridwidth = 1;
        restriccions.add(acceptarrestriccions, c);

    }
    public void inicialitza_insercio() {
        switchgestio.add(gestiohospital, "gestiohospital");
        switchgestio.add(modificadoctor, "modificardoctor");
        insertaracceptarhospital.setLayout(new GridBagLayout());
        insertartexthospital.setLayout(new FlowLayout());
        inserthospital.setLayout(new GridBagLayout());
        creardoctor.addActionListener(this);
        gestiohospital.add(inserthospital);
    }
    public void inicialitza_modificacio() {
        enreredoctor.addActionListener(this);
        acceptardoctor.addActionListener(this);
        afegirrestriccio.addActionListener(this);
        eliminarrestriccio.addActionListener(this);
        eliminardoctor.addActionListener(this);
        modificadoctor.setLayout(new GridBagLayout());
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weighty = 1.0;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        modificadoctor.add(labeldni, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 0;
        c.weighty = 1.0;
        c.gridwidth = 2;
        modificadoctor.add(dni, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        c.weighty = 1.0;
        c.gridwidth = 1;
        modificadoctor.add(labelnom, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 1;
        c.weighty = 1.0;
        c.gridwidth = 2;
        modificadoctor.add(nom, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 2;
        c.weighty = 1.0;
        c.gridwidth = 1;
        modificadoctor.add(labelcognom, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 2;
        modificadoctor.add(cognom, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 1;
        modificadoctor.add(labelcognom2, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 3;
        c.gridwidth = 2;
        modificadoctor.add(cognom2, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 1;
        modificadoctor.add(labelsou, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 4;
        c.gridwidth = 2;
        modificadoctor.add(sou, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 5;
        c.gridwidth = 1;
        modificadoctor.add(labeltelefon, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 5;
        c.gridwidth = 2;
        modificadoctor.add(telefon, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 6;
        c.gridwidth = 1;
        modificadoctor.add(labelcorreu, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 6;
        c.gridwidth = 2;
        modificadoctor.add(correu, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 8;
        c.gridwidth = 1;
        modificadoctor.add(afegirrestriccio, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 8;
        c.gridwidth = 1;
        modificadoctor.add(eliminarrestriccio, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 8;
        c.gridwidth = 1;
        modificadoctor.add(eliminardoctor, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 9;
        c.gridwidth = 1;
        modificadoctor.add(enreredoctor, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 9;
        c.gridwidth = 1;
        modificadoctor.add(acceptardoctor, c);

    }
    public void inicialitza_gestio() {
        carregarhospital.addActionListener(this);
        guardarhospital.addActionListener(this);
        buttonshospital.setLayout(new GridBagLayout());
        gestiohospital.add(buttonshospital);
        buttonshospital.add(creardoctor);
        buttonshospital.add(guardarhospital);
        buttonshospital.add(carregarhospital);
    }

    public void inicialitza_Docs() {
        String content = ctrlVistaHospital.getLlistaDocs_nom();
        llistaDocs.removeAllElements();
        if (!content.equals("")) {
                if (content.length() > 0) {
                    String separadors = "[ \n]";
                    String[] separat = content.split(separadors);
                    for (int i = 0; i < separat.length; i += 7) {
                        llistaDocs.addElement(separat[i] + " " + separat[i + 1] + " " + separat[i + 2] + " " + separat[i + 3]);
                    }
            }
        }
    }

    public void inicialitza_Res(){
        String content = ctrlVistaHospital.getRestriccions();
    }
    public JPanel tornapanel() {
        return hospital;
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        JComponent accio = (JComponent) ev.getSource();

        if(accio == carregarhospital) {
            int ret = obrirdirectori.showOpenDialog(hospital);
            //L'usuari ha escollit un fitxer
            if(ret == obrirdirectori.APPROVE_OPTION){
                File f = obrirdirectori.getSelectedFile();
                CtrlHospital.carregar(f);
            }
            inicialitza_Docs();
        }
        else if (accio == guardarhospital) {
            int ret = obrirdirectori.showSaveDialog(hospital);
            if (ret == obrirdirectori.APPROVE_OPTION){
                File f = obrirdirectori.getSelectedFile();
                CtrlHospital.guardar(f);
            }

        }
        else if (accio == enreredoctor) {
            CardLayout cl = (CardLayout)(switchllista.getLayout());
            cl.show(switchllista, "llistathospital");
            CardLayout cl2 = (CardLayout)(switchgestio.getLayout());
            cl2.show(switchgestio, "gestiohospital");
        }
        else if (accio == acceptardoctor) {
            if(dni.isEditable()){
                //Creem nou doctor
                if(!CtrlHospital.existeixDoctor(dni.getText())){
                    creaDoctor();
                }
            }
            else{
                modificaDoctor();
            }
            inicialitza_Docs();
            reiniciaTextFieldsDocs();
            CardLayout cl = (CardLayout)(switchgestio.getLayout());
            cl.show(switchgestio, "gestiohospital");
        }
        else if (accio == eliminardoctor) {
            esborraDoctor();
            reiniciaTextFieldsDocs();
            inicialitza_Docs();
            CardLayout cl = (CardLayout)(switchgestio.getLayout());
            cl.show(switchgestio, "gestiohospital");
        }
        else if (accio == creardoctor) {
            reiniciaTextFieldsDocs();
            CardLayout cl = (CardLayout)(switchgestio.getLayout());
            cl.show(switchgestio, "modificardoctor");
            afegirrestriccio.setEnabled(false);
            eliminarrestriccio.setEnabled(false);
            dni.setEditable(true);
            eliminardoctor.setEnabled(false);
        }
        else if (accio == afegirrestriccio) {
            CardLayout cl = (CardLayout)(switchllista.getLayout());
            cl.show(switchllista, "restriccions");
        }

        else if (accio == eliminarrestriccio) {
            CardLayout cl = (CardLayout)(switchllista.getLayout());
            cl.show(switchllista, "restriccions");
        }
        else if (accio == enrererestriccions) {
            CardLayout cl = (CardLayout)(switchllista.getLayout());
            cl.show(switchllista, "llistathospital");
        }
        else if (accio == acceptarrestriccions) {
            CardLayout cl = (CardLayout)(switchllista.getLayout());
            cl.show(switchllista, "llistathospital");
        }
    }

    /** Funcions auxiliars **/
    public void modificaDoctor(){
        String d = dni.getText();
        String n = nom.getText();
        String cg1 = cognom.getText();
        String cg2 = cognom2.getText();
        int s = Integer.parseInt(sou.getText());
        int t = Integer.parseInt(telefon.getText());
        String cor = correu.getText();
        CtrlHospital.modificaAtributs(d, n, cg1, cg2, s, t, cor);
    }

    public void creaDoctor(){
        String d = dni.getText();
        String n = nom.getText();
        String cg1 = cognom.getText();
        String cg2 = cognom2.getText();
        int s = Integer.parseInt(sou.getText());
        int t = Integer.parseInt(telefon.getText());
        String cor = correu.getText();
        CtrlHospital.creariAfegirDoctor(d, n, cg1, cg2, s, t, cor);
    }

    public void esborraDoctor(){
        String d = dni.getText();
        CtrlHospital.eliminarDoctor(d);
    }

    public void ompleDoctorDni(String d){
        String ret = ctrlVistaHospital.getDoctorEspecific(d);
        String separadors = "[ \n]";
        String[] separat = ret.split(separadors);
        dni.setText(d);
        nom.setText(separat[1]);
        cognom.setText(separat[2]);
        cognom2.setText(separat[3]);
        sou.setText(separat[4]);
        telefon.setText(separat[5]);
        correu.setText(separat[6]);
    }


    private void reiniciaTextFieldsDocs(){
        dni.setText("");
        nom.setText("");
        cognom.setText("");
        cognom2.setText("");
        sou.setText("");
        telefon.setText("");
        correu.setText("");
    }
    public void ompleValuesDoctor(){
        //agafem els valors
        String selected = texthospital.getSelectedValue().toString();
        //String separadors = "[ \n]";
        String[] separat = selected.split(" ");
        String d = separat[0]; //Dni
        //omplim segons el dni
        ompleDoctorDni(d);
    }


    /*public void valueChanged(ListSelectionEvent lse) {
        if (!lse.getValueIsAdjusting()) {
            if (lse.getSource() == texthospital){
                System.out.println("he hecho click EN LA LISTA");
                ompleValuesDoctor();
                System.out.println("he rellenado los fields");
                CardLayout cl = (CardLayout)(switchgestio.getLayout());
                cl.show(switchgestio, "modificardoctor");
                afegirrestriccio.setEnabled(true);
                eliminarrestriccio.setEnabled(true);
                dni.setEditable(false);
                eliminardoctor.setEnabled(true);
            }

        }
    }
    */

    public class ratonllistaDocs implements MouseListener {
        public void mousePressed(MouseEvent e) {
        }

        public void mouseReleased(MouseEvent e) {
        }

        public void mouseEntered(MouseEvent e) {
        }

        public void mouseExited(MouseEvent e) {
        }

        public void mouseClicked(MouseEvent e) {
                ompleValuesDoctor();
                CardLayout cl = (CardLayout) (switchgestio.getLayout());
                cl.show(switchgestio, "modificardoctor");
                afegirrestriccio.setEnabled(true);
                eliminarrestriccio.setEnabled(true);
                dni.setEditable(false);
                eliminardoctor.setEnabled(true);
        }

    }

    public class ratonllistaRes implements MouseListener {
        public void mousePressed(MouseEvent e) {
        }

        public void mouseReleased(MouseEvent e) {
        }

        public void mouseEntered(MouseEvent e) {
        }

        public void mouseExited(MouseEvent e) {
        }

        public void mouseClicked(MouseEvent e) {
            CardLayout cl = (CardLayout) (switchgestio.getLayout());
            cl.show(switchgestio, "modificardoctor");
            afegirrestriccio.setEnabled(true);
            eliminarrestriccio.setEnabled(true);
            dni.setEditable(false);
            eliminardoctor.setEnabled(true);
        }

    }
}
