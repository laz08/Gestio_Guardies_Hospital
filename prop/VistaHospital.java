package prop;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class VistaHospital implements ActionListener, ListSelectionListener{
	private JPanel hospital = new JPanel();
	private JPanel buttonshospital = new JPanel();
	private JPanel inserthospital = new JPanel();
	private JPanel llistathospital = new JPanel();
	private JPanel gestiohospital = new JPanel();
	private JButton crearhospital = new JButton("Crear");
	private JButton eliminarhospital = new JButton("Eliminar");
	private JButton carregarhospital = new JButton("Carregar");
	private JButton guardarhospital = new JButton("Guardar");
	private JFileChooser obrirdirectori = new JFileChooser();
	private JTextField introduirhospital = new JTextField(40);
	private JButton acceptarhospital = new JButton("Acceptar");
	private JPanel insertaracceptarhospital = new JPanel();
	private JPanel insertartexthospital = new JPanel();
	String[] tres = {"H", "D"};
	String[] docprova = {"Ramón", "Juan", "Ricardo", "Sergi", "Juan", "Ricardo", "Sergi", "Juan", "Ricardo", "Sergi", "Juan", "Ricardo", "Sergi", "Juan", "Ricardo", "Sergi"};
	private JList texthospital = new JList(docprova);
	private JScrollPane scrollpane = new JScrollPane(texthospital);
	private JList tipushospital = new JList(tres);
	
	
	public VistaHospital() {

		hospital.setLayout(new BorderLayout());
		inicialitza_llistat();
		inicialitza_gestio();
		inicialitza_insercio();
		
	}
	
	public void inicialitza_llistat() {
		texthospital.addListSelectionListener(this);
		texthospital.setPreferredSize(new Dimension(600,600));
		scrollpane.setPreferredSize(new Dimension(460,460));
		scrollpane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		llistathospital.setLayout(new BorderLayout());
		llistathospital.add(scrollpane,BorderLayout.NORTH);
		hospital.add(llistathospital, BorderLayout.WEST);
		texthospital.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}
	public void inicialitza_insercio() {
		insertaracceptarhospital.setLayout(new BorderLayout());
		insertartexthospital.setLayout(new FlowLayout());
		inserthospital.setLayout(new BorderLayout());
		
		insertartexthospital.add(tipushospital);
		insertartexthospital.add(introduirhospital);
		insertaracceptarhospital.add(acceptarhospital);
		
		inserthospital.add(insertartexthospital, BorderLayout.NORTH);
		inserthospital.add(insertaracceptarhospital, BorderLayout.SOUTH);
		inserthospital.setPreferredSize(new Dimension(600,300));
		
		tipushospital.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		gestiohospital.add(inserthospital, BorderLayout.NORTH);
	}
	
	public void inicialitza_gestio() {
		
		carregarhospital.addActionListener(this);
		guardarhospital.addActionListener(this);
		buttonshospital.setLayout(new GridBagLayout());
		buttonshospital.setPreferredSize(new Dimension(600,250));
		gestiohospital.setLayout(new BorderLayout());
		gestiohospital.add(buttonshospital, BorderLayout.SOUTH);
		buttonshospital.add(crearhospital);
		buttonshospital.add(eliminarhospital);
		buttonshospital.add(guardarhospital);
		buttonshospital.add(carregarhospital);
		hospital.add(gestiohospital, BorderLayout.EAST);
	}
	public JPanel tornapanel() {
		return hospital;
	}
	
	public void actionPerformed(ActionEvent ev) {
		obrirdirectori.showOpenDialog(hospital);
	}

	@Override
	public void valueChanged(ListSelectionEvent lse) {
		if (!lse.getValueIsAdjusting()) {
			String selection = (String) texthospital.getSelectedValue();
			texthospital.clearSelection();
			introduirhospital.setText(selection);
		}
		
	}
}

