package prop;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
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
	String[] docprova = {"Ramón", "Juan", "Ricardo", "Sergi", "Juan", "Ricardo", "Sergi", "Juan", "Ricardo", "Sergi", "Juan", "Ricardo", "Sergi", "Juan", "Ricardo", "Sergi"};
	private JList<String> texthospital = new JList<String>(docprova);
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
	private JTextField dni = new JTextField();
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
	String[] exemplerestriccions = {"D 25 AND 26", "H 10 XOR 12"};
	private JList<String> llistarestriccions = new JList<String>(exemplerestriccions);
	private JButton enrererestriccions = new JButton("Enrere");
	private JButton acceptarrestriccions = new JButton("Acceptar");
	
	public VistaHospital() {
		
		hospital.setLayout(new BorderLayout());
		switchllista.setLayout(new CardLayout());
		switchllista.add(llistathospital, "llistathospital");
		switchllista.add(restriccions, "restriccions");
		switchgestio.setLayout(new CardLayout());
		gestiohospital.setLayout(new GridLayout(2,1));
		inicialitza_llistat();
		inicialitza_modificacio();
		inicialitza_insercio();
		inicialitza_gestio();
	}
	
	public void inicialitza_llistat() {
		enrererestriccions.addActionListener(this);
		acceptarrestriccions.addActionListener(this);
		restriccions.setLayout(new GridBagLayout());
		texthospital.addListSelectionListener(this);
		texthospital.setPreferredSize(new Dimension(600,460));
		scrollpane.setPreferredSize(new Dimension(600,460));
		scrollpane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		llistathospital.setLayout(new BorderLayout());
		llistathospital.add(scrollpane,BorderLayout.NORTH);
		hospital.add(switchllista, BorderLayout.WEST);
		hospital.add(switchgestio, BorderLayout.EAST);
		texthospital.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//Panel restriccions
		llistarestriccions.setPreferredSize(new Dimension(500,400));
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
		insertaracceptarhospital.setLayout(new GridBagLayout());
		insertartexthospital.setLayout(new FlowLayout());
		inserthospital.setLayout(new GridBagLayout());
		creardoctor.addActionListener(this);
		gestiohospital.add(inserthospital);
	}
	public void inicialitza_modificacio() {
		enreredoctor.addActionListener(this);
		acceptardoctor.addActionListener(this);
		afegirrestriccio.addActionListener(this);
		eliminarrestriccio.addActionListener(this);
		eliminardoctor.addActionListener(this);
		modificadoctor.setLayout(new GridBagLayout());
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weighty = 1.0;
		c.gridx = 0;
		c.gridy = 0;
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
	public JPanel tornapanel() {
		return hospital;
	}
	
	public void actionPerformed(ActionEvent ev) {
		JComponent accio = (JComponent) ev.getSource();
		if(accio == carregarhospital) {
			obrirdirectori.showOpenDialog(hospital);
		}
		else if (accio == guardarhospital) {
			obrirdirectori.showOpenDialog(hospital);
		}
		else if (accio == enreredoctor) {
			CardLayout cl = (CardLayout)(switchgestio.getLayout());
	        cl.show(switchgestio, "gestiohospital");
		}
		else if (accio == acceptardoctor) {
			CardLayout cl = (CardLayout)(switchgestio.getLayout());
	        cl.show(switchgestio, "gestiohospital");
		}
		else if (accio == eliminardoctor) {
			CardLayout cl = (CardLayout)(switchgestio.getLayout());
        	cl.show(switchgestio, "gestiohospital");
		}
		else if (accio == creardoctor) {
			CardLayout cl = (CardLayout)(switchgestio.getLayout());
        	cl.show(switchgestio, "modificardoctor");
        	afegirrestriccio.setEnabled(false);
        	eliminarrestriccio.setEnabled(false);
        	dni.setEditable(true);
        	eliminardoctor.setEnabled(false);
		}
		else if (accio == afegirrestriccio) {
			CardLayout cl = (CardLayout)(switchllista.getLayout());
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

	public void valueChanged(ListSelectionEvent lse) {
		if (!lse.getValueIsAdjusting()) {
			if (lse.getSource() == texthospital){
				CardLayout cl = (CardLayout)(switchgestio.getLayout());
	        	cl.show(switchgestio, "modificardoctor");
	        	afegirrestriccio.setEnabled(true);
	        	eliminarrestriccio.setEnabled(true);
	        	dni.setEditable(false);
	        	eliminardoctor.setEnabled(true);
			}
				
		}
		
	}
}
