package prop;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;

public class VistaGuardar implements ActionListener, ListSelectionListener {
	private static CtrlVistaGuardar ctrlVistaGuardar;

	private JPanel guardar = new JPanel();
	private JButton guardargeneral = new JButton("Guardar");
	private JFileChooser obrirdirectori = new JFileChooser();

	public VistaGuardar(CtrlVistaGuardar cvg) {
        ctrlVistaGuardar = cvg;
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
