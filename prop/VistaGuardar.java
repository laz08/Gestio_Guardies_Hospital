import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class VistaGuardar implements ActionListener, ListSelectionListener {
	
	private JPanel guardar = new JPanel();
	private JButton guardargeneral = new JButton("Guardar");
	private JFileChooser obrirdirectori = new JFileChooser();

	public VistaGuardar() {
		guardar.setLayout(null);
		guardargeneral.setLocation(500,250);
		inicialitza_gestio();
	}

	
	private void inicialitza_gestio() {
		guardargeneral.addActionListener(this);
		guardargeneral.setSize(200, 100);
		guardar.add(guardargeneral);
	}
	
	public void valueChanged(ListSelectionEvent e) {

	}
	
	public void actionPerformed(ActionEvent arg0) {

		if (arg0.getSource() == guardargeneral) {
			obrirdirectori.showOpenDialog(guardar);
		}
	}
	
	public void itemStateChanged(ItemEvent evt) {
		
    }
	
	public JPanel tornapanel() {
		return guardar;
	}


}
