package prop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class BotonsRestriccio extends QuatreBotons {

    private CtrlVistaRestriccio ctrlvr;
    private JRadioButton bototorn = new JRadioButton("Torn");
    private JRadioButton botodia = new JRadioButton("Dia");
    private JLabel obrircalendari = new JLabel("Selecciona una data:");
    private JLabel textcomplet = new JLabel("Restricció Completa");
    private JTextField restricciocompleta = new JTextField("");
    private GridBagConstraints c = new GridBagConstraints();
    private JButton neteja = new JButton("Neteja Restriccio");
    private ButtonGroup gruprestriccio = new ButtonGroup();
    private JButton carrega = new JButton("Carregar Restriccions");
    private JButton guarda = new JButton("Guardar Restriccions");
    private JButton crearrestriccio = new JButton("Crear Restricció");
    //control per crear la restriccio
    private int restriccioA = 1;
    private boolean acumulat = false;
    private String restriccio = "";

    public BotonsRestriccio(CtrlVistaRestriccio cvr) {
        ctrlvr = cvr;
        remove(b1);
        remove(b2);
        remove(b3);
        remove(b4);
        b1.setText("Obrir Calendari");
        b2.setText("XOR");
        b3.setText("AND");
        b4.setText("NOP");
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        carrega.addActionListener(this);
        guarda.addActionListener(this);
        habilitaoperacions(false);
        bototorn.addActionListener(this);
        botodia.addActionListener(this);
        neteja.addActionListener(this);
        setLayout(new GridBagLayout());
        b1.addActionListener(this);
        gruprestriccio.add(bototorn);
        gruprestriccio.add(botodia);
        crearrestriccio.addActionListener(this);
        restricciocompleta.setEditable(false);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.insets = new Insets(20, 10, 10, 20);
        add(bototorn, c);
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        add(botodia, c);
        c.gridx = 1;
        c.gridy = 0;
        add(obrircalendari);
        c.gridx = 1;
        c.gridy = 1;
        add(b1, c);
        c.gridx = 0;
        c.gridy = 2;
        add(b2, c);
        c.gridx = 1;
        c.gridy = 2;
        add(b3, c);
        c.gridx = 2;
        c.gridy = 2;
        add(b4, c);
        c.gridx = 0;
        c.gridy = 3;
        add(textcomplet, c);
        c.gridx = 0;
        c.gridy = 4;
        add(restricciocompleta, c);
        c.gridx = 0;
        c.gridy = 5;
        add(neteja, c);
        c.gridx = 1;
        c.gridy =5;
        add(crearrestriccio, c);
        c.gridx = 0;
        c.gridy = 7;
        add(carrega, c);
        c.gridx = 1;
        c.gridy = 7;
        add(guarda, c);
        bototorn.doClick();
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        if (arg0.getSource() == this.b1) {
            ctrlvr.habilitatorns(bototorn.isSelected());
            ctrlvr.desactivaacceptar();
            ctrlvr.swap(2, 2);
        } else if (arg0.getSource() == this.b2) { // XOR
            boolean esRestriccio = agrupa_anterior();
            restricciocompleta.setText(restricciocompleta.getText() + "XOR(");
            restriccio = restriccio + "XOR(";
            b3.setEnabled(false);
            b2.setEnabled(false);
            if (esRestriccio) {
                b4.setEnabled(true);
                acumulat = true;
                restriccioA ++;
            } else {
                b4.setEnabled(false);
            }
            b1.setEnabled(true);
            ctrlvr.actualitzaEstatBotons();

        } else if (arg0.getSource() == this.b3) { // AND
            boolean esRestriccio = agrupa_anterior();
            restricciocompleta.setText(restricciocompleta.getText() + "AND(");
            restriccio = restriccio + "AND(";
            b3.setEnabled(false);
            b2.setEnabled(false);
            if (esRestriccio) {
                b4.setEnabled(true);
                acumulat = true;
                restriccioA ++;
            } else {
                b4.setEnabled(false);
            }
            b1.setEnabled(true);
            ctrlvr.actualitzaEstatBotons();

        } else if (arg0.getSource() == this.b4) { //NOP
            b4.setEnabled(false);
            b3.setEnabled(false);
            b2.setEnabled(false);
            b1.setEnabled(true);
            restricciocompleta.setText(restricciocompleta.getText() + "NOP(");
            restriccio = restriccio + "NOP(";
            if (restriccioA > 0) {
                restriccioA--;
            }
            ctrlvr.actualitzaEstatBotons();

        } else if (arg0.getSource() == neteja) {
            if (bototorn.isSelected()) {
                restricciocompleta.setText("H ");
                restriccio = "H ";
                restriccioA = 1;
                acumulat = false;
            } else if (botodia.isSelected()) {
                restricciocompleta.setText("D ");
                restriccio = "D ";
                restriccioA = 1;
                acumulat = false;
            } else {
                restricciocompleta.setText("");
                restriccio = "";
                restriccioA = 1;
                acumulat = false;
            }
        } else if (arg0.getSource() == bototorn) {
            botodia.setSelected(false);
            restricciocompleta.setText("H ");
            restriccio = "H ";
            restriccioA = 1;
            acumulat = false;
            b3.setEnabled(false);
            b2.setEnabled(false);
            b4.setEnabled(true);
            b1.setEnabled(true);
            ctrlvr.actualitzaEstatBotons();

        } else if (arg0.getSource() == botodia) {
            bototorn.setSelected(false);
            restricciocompleta.setText("D ");
            restriccio = "D ";
            restriccioA = 1;
            acumulat = false;
            b3.setEnabled(false);
            b2.setEnabled(false);
            b4.setEnabled(true);
            b1.setEnabled(true);
            ctrlvr.actualitzaEstatBotons();

        } else if (arg0.getSource() == carrega) {
            if (directori.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                ctrlvr.carregar(directori.getSelectedFile());
                ctrlvr.actualitza();
            }
        } else if (arg0.getSource() == guarda) {
            if (directori.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                ctrlvr.guardar(directori.getSelectedFile());
                ctrlvr.actualitza();
            }
        }
        
        else if (arg0.getSource() == crearrestriccio) {
            if(!restricciocompleta.getText().equals("")){
                ctrlvr.afegeixRestriccio(restriccio);
            }
        	
        }
    }

    private boolean agrupa_anterior() {
        boolean acaba = false;
        String resultat = restricciocompleta.getText();
        String agrupacio = "";
        int cont = 0;
        while (!acaba) {
            
            if (resultat.length() != 0) {
                String c = resultat.substring(resultat.length() - 1, resultat.length());
                resultat = resultat.substring(0, resultat.length() - 1);
                if (c.equals(")")) {
                    cont++;
                } else if (c.equals("(")) {
                    cont--;
                }
                else if (c.equals(" ")){
                    acaba = true;
                }
                
                if (cont < 0 || c.equals(" ")) {
                    acaba = true;
                    resultat = resultat + c;
                } else if(!c.equals(" ")){
                    agrupacio = c + agrupacio;
                }
                if(c.equals(" ")){
                    resultat = resultat + c;
                }
            } else {
                acaba = true;
            }
        }
        restricciocompleta.setText(resultat + "(" + agrupacio + ")");

        // feim el mateix per la restriccio que es fica a l'arbre
        acaba = false;
        cont = 0;
        agrupacio = "";
        resultat = restriccio;
        while (!acaba) {
            if (resultat.length() != 0) {
                String c = resultat.substring(resultat.length() - 1, resultat.length());
                resultat = resultat.substring(0, resultat.length() - 1);
                if (c.equals(")")) {
                    cont++;
                } else if (c.equals("(")) {
                    cont--;
                }
                else if (c.equals(" ")){
                    acaba = true;
                }
                
                if (cont < 0 || c.equals(" ")) {
                    acaba = true;
                    resultat = resultat + c;
                } else if(!c.equals(" ")){
                    agrupacio = c + agrupacio;
                }
            } else {
                acaba = true;
            }
        }
        restriccio = resultat + "(" + agrupacio + ")";
        boolean sortida = false;
        if (agrupacio.contains("AND") || agrupacio.contains("XOR") || agrupacio.contains("NOP")) {
            sortida = true;
        }
        return sortida;
    }

    public void habilitaoperacions(boolean operacions) {
        b2.setEnabled(operacions);
        b3.setEnabled(operacions);
        b4.setEnabled(operacions);
    }

    public void seleccioDia(int dia, int mes) {
        if (restriccioA == 0) {
            restricciocompleta.setText(restricciocompleta.getText() + dia + "-" + mes + ")");
            restriccio = restriccio + dia + "-" + mes + ")";
            if (acumulat) {
                restricciocompleta.setText(restricciocompleta.getText() + ")");
                restriccio = restriccio + ")";
            }
        } else {
            restricciocompleta.setText(restricciocompleta.getText() + dia + "-" + mes);
            restriccio = restriccio + dia + "-" + mes;
            if (restriccioA > 0) {
                restriccioA--;
            }
        }
        b3.setEnabled(true);
        b2.setEnabled(true);
        b4.setEnabled(false);
        b1.setEnabled(false);
        ctrlvr.actualitzaEstatBotons();
    }

    public void seleccioTorn(int ntorn) {
        int hora = 0;
        String torn = "";
        switch (ntorn) {
            case 0:
                hora = 5;
                torn = "MATÍ";
                break;
            case 1:
                hora = 12;
                torn = "TARDA";
                break;
            case 2:
                hora = 20;
                torn = "NIT";
                break;
        }
        if (restriccioA == 0) {
            restricciocompleta.setText(restricciocompleta.getText() + torn + ")");
            restriccio = restriccio + hora + ")";
            if (acumulat) {
                restricciocompleta.setText(restricciocompleta.getText() + ")");
                restriccio = restriccio + ")";
                System.out.println("acumulat");
            }
        } else {
            restricciocompleta.setText(restricciocompleta.getText() + torn);
            restriccio = restriccio + hora;
            if (restriccioA > 0) {
                restriccioA--;
            }
        }
        b3.setEnabled(true);
        b2.setEnabled(true);
        b4.setEnabled(false);
        b1.setEnabled(false);
        ctrlvr.actualitzaEstatBotons();
    }
    
    public boolean seleccioPerTorns(){
        return bototorn.isSelected();
    }
}
