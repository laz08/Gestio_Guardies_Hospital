package prop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class BotoMesTextHospital extends BotoMesText{
	protected JButton b3 = new JButton("Eliminar Doctor");
	protected JButton b4 = new JButton("Enrere");
	protected JButton b5 = new JButton("Acceptar");
	protected JLabel l7 = new JLabel("Correu:");
	protected GridBagConstraints c = new GridBagConstraints();
	private boolean mod = false;

	private CtrlVistaHospital ctrlvh;
	private LlistatErrorHospital llistat;

	public BotoMesTextHospital(CtrlVistaHospital cvh, LlistatErrorHospital ll) {
		ctrlvh = cvh;
        llistat = ll;

		b1.setText("Afegir Restricció");
		b2.setText("Eliminar Restricció");
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		b5.addActionListener(this);
		l1.setText("DNI: ");
		l2.setText("Nom: ");
		l3.setText("Primer Cognom: ");
		l4.setText("Segon Cognom: ");
		l5.setText("Sou: ");
		l6.setText("Telèfon: ");
		l7.setText("Correu: ");
		remove(textfield1);
		remove (b1);
		remove(b2);
		setLayout(new GridBagLayout());
		setAlignmentX(Component.LEFT_ALIGNMENT);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.gridwidth = 1;
		add(l1, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 0;
		c.weighty = 1.0;
		c.gridwidth = 2;
		add(t1, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		c.weighty = 1.0;
		c.gridwidth = 1;
		add(l2, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 1;
		c.weighty = 1.0;
		c.gridwidth = 2;
		add(t2, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 2;
		c.weighty = 1.0;
		c.gridwidth = 1;
		add(l3, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 2;
		c.gridwidth = 2;
		add(t3, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 1;
		add(l4, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 3;
		c.gridwidth = 2;
		add(t4, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 1;
		add(l5, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 4;
		c.gridwidth = 2;
		add(t5, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 5;
		c.gridwidth = 1;
		add(l6, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 5;
		c.gridwidth = 2;
		add(t6, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 6;
		c.gridwidth = 1;
		add(l7, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 6;
		c.gridwidth = 2;
		add(t7, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 8;
		c.gridwidth = 1;
		add(b1, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 8;
		c.gridwidth = 1;
		add(b2, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 8;
		c.gridwidth = 1;
		add(b3, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 9;
		c.gridwidth = 1;
		add(b4, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 9;
		c.gridwidth = 1;
		add(b5, c);
	}

	public void assignallista(LlistatErrorHospital ll) {
		llistat = ll;
	}
	public BotoMesTextHospital(CtrlVistaHospital cvh) {
		ctrlvh = cvh;

		b1.setText("Afegir Restricció");
		b2.setText("Eliminar Restricció");
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		b5.addActionListener(this);
		l1.setText("DNI: ");
		l2.setText("Nom: ");
		l3.setText("Primer Cognom: ");
		l4.setText("Segon Cognom: ");
		l5.setText("Sou: ");
		l6.setText("Telèfon: ");
		l7.setText("Correu: ");
		remove(textfield1);
		remove (b1);
		remove(b2);
		setLayout(new GridBagLayout());
		setAlignmentX(Component.LEFT_ALIGNMENT);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.gridwidth = 1;
		add(l1, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 0;
		c.weighty = 1.0;
		c.gridwidth = 2;
		add(t1, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		c.weighty = 1.0;
		c.gridwidth = 1;
		add(l2, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 1;
		c.weighty = 1.0;
		c.gridwidth = 2;
		add(t2, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 2;
		c.weighty = 1.0;
		c.gridwidth = 1;
		add(l3, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 2;
		c.gridwidth = 2;
		add(t3, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 1;
		add(l4, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 3;
		c.gridwidth = 2;
		add(t4, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 1;
		add(l5, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 4;
		c.gridwidth = 2;
		add(t5, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 5;
		c.gridwidth = 1;
		add(l6, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 5;
		c.gridwidth = 2;
		add(t6, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 6;
		c.gridwidth = 1;
		add(l7, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 6;
		c.gridwidth = 2;
		add(t7, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 8;
		c.gridwidth = 1;
		add(b1, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 8;
		c.gridwidth = 1;
		add(b2, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 8;
		c.gridwidth = 1;
		add(b3, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 9;
		c.gridwidth = 1;
		add(b4, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 9;
		c.gridwidth = 1;
		add(b5, c);
	}

	public void actionPerformed(ActionEvent arg0) {
        //AFEGIR RESTRICCIÓ
		if(arg0.getSource() == b1) {
			ctrlvh.swap(1,2);
            ctrlvh.carregaLlistaRestriccions();
		}

        //ELIMINAR RESTRICCIÓ
		else if (arg0.getSource() == b2) {
			ctrlvh.swap(1,3);
		}

        //ELIMINAR DOCTOR
		else if (arg0.getSource() == b3) {
            esborraDoctor();
            llistat.actualitza_llista_docs();
            ctrlvh.swap(2,1);
		}

        //ENRERE
		else if (arg0.getSource() == b4) {
            ctrlvh.activaBotonsRestriccionsEliminar();
			ctrlvh.swap(2,1);
            esborraTotsElsCamps();
            llistat.esborrarTotsErrors();
            t1.setEditable(true);

		}

        //ACCEPTAR
		else if (arg0.getSource() == b5) {
            if(t1.isEditable()) {
                boolean v = crea_doc();
                if (v) {
                    ctrlvh.activaBotonsRestriccionsEliminar();
                    ctrlvh.swap(2, 1);
                    llistat.actualitza_llista_docs();
                    esborraTotsElsCamps();
                    llistat.esborrarTotsErrors();

                }
            }
            else {
            	if (!mod) {
            		boolean v = modifica_doc();
                    if (v){
                        ctrlvh.activaBotonsRestriccionsEliminar();
                        ctrlvh.swap(2, 1);
                        llistat.actualitza_llista_docs();
                        esborraTotsElsCamps();
                        llistat.esborrarTotsErrors();
                        t1.setEditable(true);
                    }
            	}
                
            }
		}
	}

    //FUNCIONS AUXILIARS
    public void esborraTotsElsCamps(){
        t1.setText("");
        t2.setText("");
        t3.setText("");
        t4.setText("");
        t5.setText("");
        t6.setText("");
        t7.setText("");
    }

    public boolean crea_doc(){
        boolean valid = true;
        String d = t1.getText();
        String n = t2.getText();
        String cg1 = t3.getText();
        String cg2 = t4.getText();
        String so = t5.getText();
        String telf = t6.getText();
        String cor = t7.getText();

        if(d.equals("")|| n.equals("") || cg1.equals("") || cg2.equals("") || so.equals("") || telf.equals("") || cor.equals("")){
            valid = false;
            llistat.errorUnOMesDunCampNull();
        }
        else if(valid && d.length() == 8){
            try {
                int dni_num = Integer.parseInt(d);
            } catch (Exception e){
                llistat.condicionsDNIError();
                valid = false;
            }
            if (ctrlvh.existeix_Doc(d)){
                valid = false;
                llistat.jaExisteixDocAmbDNI();
            }
        }
        else{
            valid = false;
            llistat.condicionsDNIError();
        }

        int s = 0;
        int t = 0;
        //Si tots els camps estan plens...
        if(valid){
            try {
                s = Integer.parseInt(so);
                if(s < 0){
                    valid = false;
                    llistat.errorHaDeSerUnReal("Sou");
                }
            } catch (Exception e){
                llistat.errorHaDeSerUnReal("Sou");
                valid = false;
            }
            //Si sou és valid
            if(valid){
                try {
                    t = Integer.parseInt(telf);
                    if (t < 0){
                        valid = false;
                        llistat.errorHaDeSerUnReal("Telèfon");
                    }
                } catch(Exception e){
                    llistat.errorHaDeSerUnReal("Telèfon");
                    valid = false;
                }
            }
        }
        //Si els camps estan plens i sou i telefon son correctes...
        if(valid){
            if(esCorreu(cor)){
                ctrlvh.crea_doctor(d, n, cg1, cg2, s, t, cor);
            }
            else{
                llistat.noEsCorreu();
                valid = false;
            }
        }
        return valid;
    }

    public boolean modifica_doc(){
        boolean valid = true;
        String d = t1.getText();
        String n = t2.getText();
        String cg1 = t3.getText();
        String cg2 = t4.getText();
        String so = t5.getText();
        String telf = t6.getText();
        String cor = t7.getText();

        if(n.equals("") || cg1.equals("") || cg2.equals("") || so.equals("") || telf.equals("") || cor.equals("")){
            valid = false;
            llistat.errorUnOMesDunCampNull();
        }

        int s = 0;
        int t = 0;
        //Si tots els camps estan plens...
        if(valid){
            try {
                s = Integer.parseInt(so);
                if(s < 0){
                    valid = false;
                    llistat.errorHaDeSerUnReal("Sou");
                }
            } catch (Exception e){
                llistat.errorHaDeSerUnReal("Sou");
                valid = false;
            }
            //Si sou és valid
            if(valid){
                try {
                    t = Integer.parseInt(telf);
                    if (t < 0){
                        valid = false;
                        llistat.errorHaDeSerUnReal("Telèfon");
                    }
                } catch(Exception e){
                    llistat.errorHaDeSerUnReal("Telèfon");
                    valid = false;
                }
            }
        }
        //Si els camps estan plens i sou i telefon son correctes...
        if(valid){
            if(esCorreu(cor)){
                ctrlvh.modifica_doctor(d, n, cg1, cg2, s, t, cor);
            }
            else{
                llistat.noEsCorreu();
                valid = false;
            }
        }
        return valid;
    }


    public boolean esCorreu(String correu){
        Pattern p = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
        Matcher m = p.matcher(correu);
        return m.matches();
    }

    public void ompleValuesDoctor(){
        //agafem els valors
        String selected = llistat.llista1.getSelectedValue().toString();
        //String separadors = "[ \n]";
        String[] separat = selected.split(" ");
        String d = separat[0]; //Dni
        //omplim segons el dni
        ompleDoctorDni(d);
        t1.setEditable(false);
    }

    public void ompleDoctorDni(String d){
        String ret = ctrlvh.getDoctorEspecific(d);
        String separadors = "[ \n]";
        String[] separat = ret.split(separadors);
        t1.setText(d);
        t2.setText(separat[1]);
        t3.setText(separat[2]);
        t4.setText(separat[3]);
        t5.setText(separat[4]);
        t6.setText(separat[5]);
        t7.setText(separat[6]);
    }

    private void esborraDoctor(){
        ctrlvh.esborrar_doctor(t1.getText());
    }

	public void mod(boolean b) {
		mod = b;
	}
}
