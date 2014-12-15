package prop;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

public class VistaRestriccio implements ActionListener{
	private JPanel restriccions = new JPanel();
	private JPanel buttonsrestriccio = new JPanel();
	private JPanel insertrestriccio = new JPanel();
	private JPanel llistatrestriccio = new JPanel();
	private JPanel gestiorestriccio = new JPanel();
	private JButton crearrestriccio = new JButton("Crear");
	private JButton eliminarrestriccio = new JButton("Eliminar");
	private JButton carregarrestriccio = new JButton("Carregar");
	private JButton guardarrestriccio = new JButton("Guardar");
	private JTextArea textrestriccio = new JTextArea(5,30);
	private JScrollPane scrollpane = new JScrollPane(textrestriccio);
	private JFileChooser obrirdirectori = new JFileChooser();
	private JTextField introduirrestriccio = new JTextField(40);
	private JButton acceptarrestriccio = new JButton("Acceptar");
	private JPanel insertaracceptarrestriccio = new JPanel();
	private JPanel insertartextrestriccio = new JPanel();
	String[] tres = {"H", "D"};
	private JList tipusrestriccio = new JList(tres);
	
	public VistaRestriccio() {

		restriccions.setLayout(new BorderLayout());
		inicialitza_llistat();
		inicialitza_gestio();
		inicialitza_insercio();
		
	}
	
	public void inicialitza_llistat() {
		scrollpane.setPreferredSize(new Dimension(600,460));
		scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		llistatrestriccio.setLayout(new BoxLayout(llistatrestriccio, BoxLayout.Y_AXIS));
		llistatrestriccio.add(scrollpane);//, BorderLayout.NORTH);
		llistatrestriccio.add(textrestriccio);
		restriccions.add(llistatrestriccio, BorderLayout.WEST);
	}
	public void inicialitza_insercio() {
		insertaracceptarrestriccio.setLayout(new BorderLayout());
		insertartextrestriccio.setLayout(new FlowLayout());
		insertrestriccio.setLayout(new BorderLayout());
		
		insertartextrestriccio.add(tipusrestriccio);
		insertartextrestriccio.add(introduirrestriccio);
		insertaracceptarrestriccio.add(acceptarrestriccio);
		
		insertrestriccio.add(insertartextrestriccio, BorderLayout.NORTH);
		insertrestriccio.add(insertaracceptarrestriccio, BorderLayout.SOUTH);
		insertrestriccio.setPreferredSize(new Dimension(600,300));
		
		tipusrestriccio.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		gestiorestriccio.add(insertrestriccio, BorderLayout.NORTH);
	}
	
	public void inicialitza_gestio() {
		
		carregarrestriccio.addActionListener(this);
		guardarrestriccio.addActionListener(this);
		buttonsrestriccio.setLayout(new GridBagLayout());
		buttonsrestriccio.setPreferredSize(new Dimension(600,250));
		gestiorestriccio.setLayout(new BorderLayout());
		gestiorestriccio.add(buttonsrestriccio, BorderLayout.SOUTH);
		buttonsrestriccio.add(crearrestriccio);
		buttonsrestriccio.add(eliminarrestriccio);
		buttonsrestriccio.add(guardarrestriccio);
		buttonsrestriccio.add(carregarrestriccio);
		restriccions.add(gestiorestriccio, BorderLayout.EAST);
	}
	public JPanel tornapanel() {
		return restriccions;
	}
	
	public void actionPerformed(ActionEvent ev) {
		JFileChooser obrirdirectori = new JFileChooser();
		obrirdirectori.showOpenDialog(restriccions);
	}
}
