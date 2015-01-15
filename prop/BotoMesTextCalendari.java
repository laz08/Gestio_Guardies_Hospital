package prop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.GregorianCalendar;


public class BotoMesTextCalendari extends BotoMesText {
	private CtrlVistaCalendari ctrlvc;
	private JCheckBox festiu = new JCheckBox();
	private JTextField t8 = new JTextField();
	private JTextField t9 = new JTextField();
	private JTextField t10 = new JTextField();
	private JTextField t11 = new JTextField();
	private JTextField t12 = new JTextField();
	private JTextField t13 = new JTextField();
	private JLabel l7 = new JLabel("Inici");
	private JLabel l8 = new JLabel("Fi");
	private JLabel l9 = new JLabel("% sou");
	private JLabel l10 = new JLabel("Min. Docs.");
	private GridBagConstraints c = new GridBagConstraints();

	public BotoMesTextCalendari(CtrlVistaCalendari cvc) {
		ctrlvc = cvc;

        setPreferredSize(new Dimension(300, 250));
        setMaximumSize(new Dimension(300, 250));

		t1.setEditable(false);
		t2.setEditable(false);
		t3.setEditable(false);
		t6.setEditable(false);
		t7.setEditable(false);
		t10.setEditable(false);
		t11.setEditable(false);
		l1.setText("Dia: ");
		l2.setText("Festiu: ");
		l3.setText("Torns: ");
		l4.setText("Matí: ");
		l5.setText("Tarda: ");
		l6.setText("Nit: ");
		b1.setText("Enrere");
		b2.setText("Acceptar");
		b1.addActionListener(this);
		b2.addActionListener(this);
		setLayout(new GridBagLayout());
		remove(textfield1);
		remove(b1);
		remove(b2);
		t2.setText("0");
		t3.setText("8");
		t6.setText("8");
		t7.setText("16");
		t10.setText("16");
		t11.setText("0");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		add(l1, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 0;
		c.gridwidth = 1;
		add(t1, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		add(l2, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 1;
		add(festiu, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 1;
		add(l3, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 2;
		add(l7, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 2;
		add(l8, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 2;
		add(l9, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 4;
		c.gridy = 2;
		add(l10, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 1;
		add(l4, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 3;
		c.gridwidth = 1;
		add(t2, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 3;
		c.gridwidth = 1;
		add(t3, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 3;
		c.gridwidth = 1;
		add(t4, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 4;
		c.gridy = 3;
		c.gridwidth = 1;
		add(t5, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 1;
		add(l5, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 4;
		c.gridwidth = 1;
		add(t6, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 4;
		c.gridwidth = 1;
		add(t7, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 4;
		c.gridwidth = 1;
		add(t8, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 4;
		c.gridy = 4;
		c.gridwidth = 1;
		add(t9, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 5;
		c.gridwidth = 1;
		add(l6, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 5;
		c.gridwidth = 1;
		add(t10, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 5;
		c.gridwidth = 1;
		add(t11, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 5;
		c.gridwidth = 1;
		add(t12, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 4;
		c.gridy = 5;
		c.gridwidth = 1;
		add(t13, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 6;
		c.gridwidth = 1;
		add(b1, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 4;
		c.gridy = 6;
		c.gridwidth = 1;
		add(b2, c);

	}
	
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == this.b1) {
			ctrlvc.swap(1,1);
		}
		else if (arg0.getSource() == this.b2){
			GregorianCalendar data = ctrlvc.getdata();
			if (!isnumber(t4.getText(),2) || !isnumber(t8.getText(),2) || !isnumber(t12.getText(),2)) {
        		ctrlvc.texterror("ERROR: Un o més percentatges no són percentatges valids");
        	}
        	else if (!isnumber(t5.getText(),1) || !isnumber(t9.getText(),1) || !isnumber(t13.getText(),1)) {
        		ctrlvc.texterror("ERROR: El nombre de doctors d'un o més torns no és un nombre");
        	}
        	else if(Integer.parseInt(t5.getText()) < 0 || Integer.parseInt(t9.getText()) < 0 || Integer.parseInt(t13.getText()) < 0) {
        		ctrlvc.texterror("ERROR: El nombre de doctors d'un o més torns és inferior a 0");
        	}
        	else {
        		if (festiu.isSelected()) CtrlCalendari.modificarDiaFestiu(ctrlvc.seleccio(), data, true);
            	else CtrlCalendari.modificarDiaFestiu(ctrlvc.seleccio(), data, false);
                ctrlvc.texterror(" ");
        		CtrlCalendari.modificarPercentatgeTorn(Float.parseFloat(t4.getText()), data, ctrlvc.seleccio(), 0);
        		CtrlCalendari.modificarPercentatgeTorn(Float.parseFloat(t8.getText()), data, ctrlvc.seleccio(), 1);
        		CtrlCalendari.modificarPercentatgeTorn(Float.parseFloat(t12.getText()), data, ctrlvc.seleccio(), 2);
        		CtrlCalendari.modificarMinimTorn(Integer.parseInt(t5.getText()), data, ctrlvc.seleccio(), 0);
        		CtrlCalendari.modificarMinimTorn(Integer.parseInt(t9.getText()), data, ctrlvc.seleccio(), 1);
        		CtrlCalendari.modificarMinimTorn(Integer.parseInt(t13.getText()), data, ctrlvc.seleccio(), 2);
        	}
			ctrlvc.swap(1,1);
		}
		
	}

    /**
     * Carrega el dia a partir dels valors seleccionats
     * @param rany
     * @param mes
     * @param dia
     * @param selectedItem
     * @param selectedIndex
     * @param object
     */
	public void carregardia(int rany, int mes, int dia, Object selectedItem, int selectedIndex, Object object) {
		GregorianCalendar data = new GregorianCalendar(rany, mes, dia);
		festiu.setSelected(CtrlCalendari.consultarDiaFestiu(ctrlvc.seleccio(),data));
		t4.setText(String.valueOf(CtrlVistaCalendari.consultarpercenttorn(new GregorianCalendar(Integer.parseInt((String) selectedItem), selectedIndex, Integer.parseInt((String) object)),ctrlvc.seleccio(), 0)));
		t5.setText(String.valueOf(CtrlVistaCalendari.consultaminimtorn(new GregorianCalendar(Integer.parseInt((String) selectedItem), selectedIndex, Integer.parseInt((String) object)),ctrlvc.seleccio(),0)));
		t8.setText(String.valueOf(CtrlVistaCalendari.consultarpercenttorn(new GregorianCalendar(Integer.parseInt((String) selectedItem), selectedIndex, Integer.parseInt((String) object)),ctrlvc.seleccio(), 1)));
		t9.setText(String.valueOf(CtrlVistaCalendari.consultaminimtorn(new GregorianCalendar(Integer.parseInt((String) selectedItem), selectedIndex, Integer.parseInt((String) object)),ctrlvc.seleccio(), 1)));
		t12.setText(String.valueOf(CtrlVistaCalendari.consultarpercenttorn(new GregorianCalendar(Integer.parseInt((String) selectedItem), selectedIndex, Integer.parseInt((String) object)),ctrlvc.seleccio(), 2)));
		t13.setText(String.valueOf(CtrlVistaCalendari.consultaminimtorn(new GregorianCalendar(Integer.parseInt((String) selectedItem), selectedIndex, Integer.parseInt((String) object)),ctrlvc.seleccio(), 2)));
		t1.setText(Integer.toString(dia));
		
	}

    /**
     * Retorna si és un número o no
     * @param str
     * @param check
     * @return
     */
	public boolean isnumber(String str, int check) {  
        try  { 
        	if (check == 1) {
        		int d = Integer.parseInt(str);
        	}	
        	else {
        		Float d = Float.parseFloat(str);  
        	}
        }  
        catch(NumberFormatException nfe)  {  
      	  return false;
        }  
        return true; 
      }
}
