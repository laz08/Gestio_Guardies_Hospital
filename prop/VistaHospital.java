package prop;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VistaHospital implements ActionListener, ListSelectionListener {
    private static OLDCtrlVistaHospital ctrlVistaHospital;

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
    private JTextField dni = new JTextField(8);
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
    private JPanel switchllista = new JPanel();
    private JPanel restriccions = new JPanel();
    private DefaultListModel<String> llistaRes = new DefaultListModel<String>();
    private JList<String> llistarestriccions = new JList<String>(llistaRes);
    private JButton enrererestriccions = new JButton("Enrere");
    private JButton acceptarrestriccions = new JButton("Acceptar");


    //	Per mostrar els errors
    private JTextField missatgeErrors = new JTextField(40);


    // ----------------------FUNCIONS----------------------
    public VistaHospital(OLDCtrlVistaHospital cvh) {
        ctrlVistaHospital = cvh;
        hospital.setLayout(new BorderLayout());
        switchllista.setLayout(new CardLayout());
        switchllista.add(llistathospital, "llistathospital");
        switchllista.add(restriccions, "restriccions");
        switchgestio.setLayout(new CardLayout());
        switchgestio.setAlignmentX(Component.LEFT_ALIGNMENT);
        gestiohospital.setLayout(new BoxLayout(gestiohospital, BoxLayout.Y_AXIS));
        inicialitza_Docs();
        inicialitza_llistat();
        inicialitza_modificacio();
        inicialitza_insercio();
        inicialitza_gestio();
    }

    public void pantalla_principal(){
        CardLayout cl = (CardLayout)(switchllista.getLayout());
        cl.show(switchllista, "llistathospital");
        CardLayout cl2 = (CardLayout)(switchgestio.getLayout());
        cl2.show(switchgestio, "gestiohospital");
    }

    public void inicialitza_llistat() {
        enrererestriccions.addActionListener(this);
        acceptarrestriccions.addActionListener(this);
        restriccions.setLayout(new GridBagLayout());
        texthospital.addMouseListener(new ratonllistaDocs());
        texthospital.setPreferredSize(new Dimension(600, 520));


        scrollpane.setPreferredSize(new Dimension(600, 520));
        scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        llistathospital.setLayout(new BoxLayout(llistathospital, BoxLayout.Y_AXIS));
        llistathospital.add(scrollpane); //, BorderLayout.NORTH);


        llistathospital.add(missatgeErrors, BorderLayout.SOUTH);
        missatgeErrors.setEditable(false);
        missatgeErrors.setText("");
        missatgeErrors.setForeground(Color.RED);

        hospital.add(switchllista, BorderLayout.WEST);
        hospital.add(switchgestio, BorderLayout.EAST);
        texthospital.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
       // llistathospital.setVisible(false);
        //Panel restriccions
        llistarestriccions.addMouseListener(new ratonllistaRes());
        llistarestriccions.setPreferredSize(new Dimension(500,400));
       /*
        llistarestriccions.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                try{
                String r = (String) llistarestriccions.getSelectedValue();
                    System.out.println("Expressió llegida: "+r);
                ctrlVistaHospital.associaRestriccio(r, dni.getText());
                }catch(Error error){
                    
                }
            }
        });*/

        llistarestriccions.addListSelectionListener(this);
//       new ListSelectionListener()// {
//            @Override
//            public void valueChanged(ListSelectionEvent e) {
//                try{
//                String r = (String) llistarestriccions.getSelectedValue();
//                ctrlVistaHospital.associaRestriccio(r, dni.getText());
//                }catch(Error error){
//                    
//                }
//            }
//        });
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
        insertaracceptarhospital.setLayout(new BoxLayout(insertaracceptarhospital, BoxLayout.Y_AXIS));
        insertartexthospital.setLayout(new BoxLayout(insertartexthospital, BoxLayout.Y_AXIS));
        inserthospital.setLayout(new BoxLayout(inserthospital, BoxLayout.Y_AXIS));
        creardoctor.addActionListener(this);
        gestiohospital.add(inserthospital);
        inserthospital.setAlignmentX(Component.LEFT_ALIGNMENT);
    }
    public void inicialitza_modificacio() {
        enreredoctor.addActionListener(this);
        acceptardoctor.addActionListener(this);
        afegirrestriccio.addActionListener(this);
        eliminarrestriccio.addActionListener(this);
        eliminardoctor.addActionListener(this);
        modificadoctor.setAlignmentX(Component.LEFT_ALIGNMENT);
        modificadoctor.setLayout(new GridBagLayout());
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1.0;
        c.weighty = 1.0;
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

//    public void inicialitza_Res(){
//        String content = ctrlVistaHospital.getRestriccions();
//    }
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
            esborrarTotsErrors();

        }
        else if (accio == acceptardoctor) {
            boolean valid = true;
            if(dni.isEditable()){
                //Creem nou doctor

                valid = creaDoctor();
            }
            else
                valid = modificaDoctor();

            if(valid){
                esborrarTotsErrors();
                inicialitza_Docs();
                reiniciaTextFieldsDocs();
                CardLayout cl = (CardLayout)(switchgestio.getLayout());
                cl.show(switchgestio, "gestiohospital");
            }
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
             ArrayList<String> lr = ctrlVistaHospital.carregaLlistaRestriccions();
             System.out.println("MIDA "+llistaRes.size());
             llistaRes.clear();//llistaRes.removeAllElements(); // buidam llista restriccions anteriors
            for(int i=0; i<lr.size(); i++){
                llistaRes.addElement(lr.get(i));
            }
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
    public boolean modificaDoctor(){
        boolean valid = true;
        String d = dni.getText();
        String n = nom.getText();
        String cg1 = cognom.getText();
        String cg2 = cognom2.getText();
        String so = sou.getText();
        String telf = telefon.getText();
        String cor = correu.getText();

        if(n.equals("") || cg1.equals("") || cg2.equals("") || so.equals("") || telf.equals("") || cor.equals("")){
            valid = false;
            errorUnOMesDunCampNull();
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
        if(valid) {
            if (esCorreu(cor)) {
                CtrlHospital.modificaAtributs(d, n, cg1, cg2, s, t, cor);
            } else {
                noEsCorreu();
                valid = false;
            }
        }
        return valid;
    }

    public boolean creaDoctor(){
        boolean valid = true;
        String d = dni.getText();
        String n = nom.getText();
        String cg1 = cognom.getText();
        String cg2 = cognom2.getText();
        String so = sou.getText();
        String telf = telefon.getText();
        String cor = correu.getText();

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


    //MISSATGES ERROR
    public void errorUnOMesDunCampNull(){
        missatgeErrors.setText("ERROR: Falten dades en un o més camps del doctor.");
    }
    //Per a sou i telèfon
    public void errorHaDeSerUnReal(String s){
        missatgeErrors.setText("ERROR: " + s + " ha de ser un número positiu.");
    }

    public void noEsCorreu(){
        missatgeErrors.setText("ERROR: El correu introduït no és vàlid.");
    }

    public void esborrarTotsErrors(){
        missatgeErrors.setText("");
    }
    public void condicionsDNIError(){
        missatgeErrors.setText("ERROR: DNI ha de ser una cadena de 8 caràcters numèrics.");
    }

    public boolean esCorreu(String correu){
        Pattern p = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
        Matcher m = p.matcher(correu);
        return m.matches();

    }

	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
