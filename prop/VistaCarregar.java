import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class VistaCarregar implements ActionListener, ListSelectionListener {
	
	private JPanel carregar = new JPanel();
	private JButton carregargeneral = new JButton("Carregar");
	private JFileChooser obrirdirectori = new JFileChooser();

	public VistaCarregar() {
		carregar.setLayout(null);
		carregargeneral.setLocation(500,250);
		inicialitza_gestio();
	}

	
	private void inicialitza_gestio() {
		carregargeneral.addActionListener(this);
		carregargeneral.setSize(200, 100);
		carregar.add(carregargeneral);
	}
	
	public void valueChanged(ListSelectionEvent e) {

	}
	
	public void actionPerformed(ActionEvent arg0) {

		if (arg0.getSource() == carregargeneral) {
			obrirdirectori.showOpenDialog(carregar);
		}
	}
	
	public void itemStateChanged(ItemEvent evt) {
		
    }
	
	public JPanel tornapanel() {
		return carregar;
	}


}
