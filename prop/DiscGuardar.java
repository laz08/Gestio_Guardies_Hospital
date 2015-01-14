package prop;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class DiscGuardar extends QuatreBotons{
	protected ImageIcon load = new ImageIcon("Cercle.gif");
	private GridBagConstraints c = new GridBagConstraints();
	private JButton b5 = new JButton("Guardar Doc/Res");
	
	public DiscGuardar(CtrlDiscGuardar cdg){
		b1.setText("Guardar Hospital");
		b2.setText("Guardar Plantilla");
		b3.setText("Guardar Calendari");
		b4.setText("Guardar Restriccions");
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		b5.addActionListener(this);
		setLayout(new GridBagLayout());
		remove(b1);
		remove(b2);
		remove(b3);
		remove(b4);
		setLayout(new GridBagLayout());
		c.ipadx = 30;
		c.ipady = 30;
		c.insets = new Insets(40,20,20,40);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		add(b1, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 0;
		add(b2, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 2;
		add(b3, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 2;
		add(b4, c);
	}
	
	public void actionPerformed(ActionEvent arg0) {
		int ret = directori.showSaveDialog(this);
        if(ret == directori.APPROVE_OPTION){
        	File f = directori.getSelectedFile();
            if (arg0.getSource() == b1){
                CtrlDiscGuardar.guardarh(f);
            }
            else if (arg0.getSource() == b2) {    
            	CtrlDiscGuardar.guardarp(f);
            }
            else if (arg0.getSource() == b3) {
            	CtrlDiscGuardar.guardarc(f);
            }
            else if (arg0.getSource() == b4) {
            	CtrlDiscGuardar.guardarr(f);
            }
            else if (arg0.getSource() == b5) {
            	CtrlDiscGuardar.guardardr(f);
            }
        }
	}
}
