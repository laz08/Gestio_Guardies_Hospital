package prop;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;

public class VistaCarregar implements ActionListener, ListSelectionListener {
	private static CtrlVistaCarregar ctrlVistaCarregar;

	private JPanel carregar = new JPanel();
	private JButton carregargeneral = new JButton("Carregar");
	private JFileChooser obrirdirectori = new JFileChooser();

	public VistaCarregar(CtrlVistaCarregar cvc) {
        ctrlVistaCarregar = cvc;
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
