package prop;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class VistaPlantilla implements ActionListener, ListSelectionListener {
    private static CtrlVistaPlantilla ctrlVistaPlantilla;

	private JPanel plantilla = new JPanel();
	//Canvia entre la llista de plantilles i la llista de doctors
	private JPanel switchllista = new JPanel();
	//Canvia entre la gestio simple de plantilla i la gestio d'una en concret
	private JPanel switchgestio = new JPanel();
	private JPanel consultaplantilla = new JPanel();
	private JPanel gestioplantilla = new JPanel();
	
    private DefaultListModel llistaPlt = new DefaultListModel();
    private JList textplantilla = new JList(llistaPlt);
	
	private JFileChooser obrirdirectori = new JFileChooser();
	//String[] exempleplantilla = { "Plantilla1", "Plantilla2", "Plantilla3", "Plantilla4", "Plantilla5", "Plantilla6"};
	private JList llistaplantilla = new JList(llistaPlt);
	private JScrollPane scrollpane = new JScrollPane(llistaplantilla);
	private JTextField introduirrestriccio = new JTextField(40);
	private JButton buttoncrearplantilla = new JButton("Crear Plantilla");
	private JButton guardarplantilla = new JButton("Guardar Plantilles");
	private JButton carregarplantilla = new JButton("Carregar Plantilles");
	
	//Afegir Plantilla
	private JPanel crearplantilla = new JPanel();
	private JButton acceptarcreacioplantilla = new JButton("Ok");
	private JButton enrerecreacioplantilla = new JButton("Enrere");
	private JTextField textcrearplantilla = new JTextField(20);
	
	// Caracteristiques plantilla
    private DefaultListModel llistaCPlt = new DefaultListModel();
	private JPanel gestioseleccioplantilla = new JPanel();
	//String[] exempledoctorsplantilla = {"Rosa que l'amor si posa", "XD", "No pas."};
	private JList llistacplantilla = new JList(llistaCPlt);
	private JScrollPane scrollcplantilla = new JScrollPane(llistacplantilla);
	private JButton enrerellista = new JButton("Enrere");
	private JButton acceptarllista = new JButton("Ok");
	private JLabel nomplantilla = new JLabel("Nom Plantilla");
	private JTextField textnomplantilla = new JTextField();
	private JButton assignardoctorplantilla = new JButton("Assignar Doctor");
	private JButton treuredoctorplantilla = new JButton("Desassignar Doctor");
	private JButton crearassociarcalendari = new JButton("Crear Calendari \n associat");
	private JButton eliminardesasociarcalendari = new JButton("Eliminar Calendari \n associat");
	private JButton eliminarplantilla = new JButton("Eliminar");

	
	//Afegir/eliminar doctors
	private JPanel gestiodoctorplantilla = new JPanel();
	private JList doctorsllista = new JList();
	private JScrollPane scrolldoctorsllista = new JScrollPane(doctorsllista);
	private JButton enreredoctors = new JButton("Enrere");
	private JButton acceptardllista = new JButton("Ok");
	GridBagConstraints c = new GridBagConstraints();
	
	
	//-------------------------------------------------
	


	public VistaPlantilla(CtrlVistaPlantilla cvp) {
        ctrlVistaPlantilla = cvp;
		plantilla.setLayout(new GridLayout(1,2));
		switchllista.setLayout(new CardLayout());
		switchgestio.setLayout(new CardLayout());
		inicialitza_plt();
		inicialitza_llistat();
		inicialitza_gestio();
	}
	
    public void inicialitza_plt(){
        String content = ctrlVistaPlantilla.getLlista_plt();
        llistaPlt.removeAllElements();
        if(!content.equals("")) {
        	if(content.length() > 0) {
        		String separadors = "[ \n]";
        		String[] separat = content.split(separadors);
        		for (int i = 0; i < separat.length; i += 1) {
        			llistaPlt.addElement(separat[i]);
        		}
        	}
        }
    
    }
	
	private void inicialitza_gestio() {
		guardarplantilla.addActionListener(this);
		carregarplantilla.addActionListener(this);
		llistacplantilla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		gestioplantilla.setLayout(new GridLayout(3,1));
		gestioplantilla.add(buttoncrearplantilla);
		gestioplantilla.add(guardarplantilla);
		gestioplantilla.add(carregarplantilla);
		switchgestio.add(gestioplantilla, "gestioplantilla");
		
		//Part caracteristiques plantilla
		//Actions Listeners
		crearassociarcalendari.addActionListener(this);
		eliminardesasociarcalendari.addActionListener(this);
		assignardoctorplantilla.addActionListener(this);
		treuredoctorplantilla.addActionListener(this);
		eliminarplantilla.addActionListener(this);
		enrerellista.addActionListener(this);
		acceptarllista.addActionListener(this);
		textnomplantilla.setEditable(false);
		//if(llistaplantilla.getSelectedValue() != null ) {
			//String s = llistaplantilla.getSelectedValue().toString();
			//textnomplantilla.setText(s);
		
		
		//	ESTAS AQUII !!!!!!!!!!---------------
		
		
		
		//Inicialització + Adds
		gestioseleccioplantilla.setLayout(new GridLayout(10,1));
		gestioseleccioplantilla.add(nomplantilla);
		gestioseleccioplantilla.add(textnomplantilla);
		gestioseleccioplantilla.add(scrollcplantilla);
		gestioseleccioplantilla.add(acceptarllista);
		gestioseleccioplantilla.add(assignardoctorplantilla);
		gestioseleccioplantilla.add(treuredoctorplantilla);
		gestioseleccioplantilla.add(crearassociarcalendari);
		gestioseleccioplantilla.add(eliminardesasociarcalendari);
		gestioseleccioplantilla.add(eliminarplantilla);
		gestioseleccioplantilla.add(enrerellista);
		switchgestio.add(gestioseleccioplantilla, "gestioseleccioplantilla");
		plantilla.add(switchgestio);
		
		//Afegir / Eliminar doctors
		//Action Listeners
		buttoncrearplantilla.addActionListener(this);
		enreredoctors.addActionListener(this);
		acceptardllista.addActionListener(this);
		doctorsllista.addListSelectionListener(this);
		
		//Inicialitzacio + Adds
		gestiodoctorplantilla.setLayout(new GridLayout(3,1));
		scrolldoctorsllista.setPreferredSize(new Dimension(550,460));
		scrolldoctorsllista.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		gestiodoctorplantilla.add(scrolldoctorsllista);
		gestiodoctorplantilla.add(acceptardllista);
		gestiodoctorplantilla.add(enreredoctors);
		
		//Afegir Plantilla
		//Action Listeners
		switchgestio.add(crearplantilla, "crearplantilla");
		acceptarcreacioplantilla.addActionListener(this);
		enrerecreacioplantilla.addActionListener(this);
		
		//Incialitzacions +Adds
		crearplantilla.setLayout(new GridLayout(3,1));
		crearplantilla.add(textcrearplantilla);
		crearplantilla.add(acceptarcreacioplantilla);
		crearplantilla.add(enrerecreacioplantilla);
	}
	
	private void inicialitza_llistat() {
		//Listeners
		llistaplantilla.addMouseListener(new raton());
		llistaplantilla.addListSelectionListener(this);

		//Inicialitzacions +Adds
		llistaplantilla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		consultaplantilla.setPreferredSize(new Dimension(600,460));
		scrollpane.setPreferredSize(new Dimension(600,460));
		scrollpane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		consultaplantilla.setLayout(new BorderLayout());
		consultaplantilla.add(scrollpane, BorderLayout.NORTH);
		consultaplantilla.add(introduirrestriccio, BorderLayout.SOUTH);
		switchllista.add(consultaplantilla, "consultaplantilla");
		switchllista.add(gestiodoctorplantilla, "gestiodoctorplantilla");
		plantilla.add(switchllista);	
	}

	public void valueChanged(ListSelectionEvent e) {
		if (e.getSource() == llistaplantilla) {
			String s = llistaplantilla.getSelectedValue().toString();
			textnomplantilla.setText(s);
			ompleDoctorDni(s);
			CardLayout cl = (CardLayout)(switchgestio.getLayout());
	        cl.show(switchgestio, "gestioseleccioplantilla");
		}
		if (e.getSource() == textplantilla) {
			String s = llistaplantilla.getSelectedValue().toString();
			textnomplantilla.setText(s);
			CardLayout cl = (CardLayout)(switchllista.getLayout());
	        cl.show(switchllista, "gestiodoctorplantilla");
		}
	}
	
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == enrerellista) {
			CardLayout cl = (CardLayout)(switchgestio.getLayout());
	        cl.show(switchgestio, "gestioplantilla");
		}
		else if (arg0.getSource() == acceptarllista) {
			CardLayout cl = (CardLayout)(switchgestio.getLayout());
	        cl.show(switchgestio, "gestioplantilla");
		}
		else if (arg0.getSource() == carregarplantilla) {
			obrirdirectori.showOpenDialog(plantilla);
		}
		else if (arg0.getSource() == guardarplantilla) {
			obrirdirectori.showOpenDialog(plantilla);
		}
		else if (arg0.getSource() == enreredoctors) {
			CardLayout cl = (CardLayout)(switchllista.getLayout());
			cl.show(switchllista, "consultaplantilla");
		}
		else if (arg0.getSource() == acceptardllista) {
			CardLayout cl = (CardLayout)(switchllista.getLayout());
			cl.show(switchllista, "consultaplantilla");
		}
		else if (arg0.getSource() == assignardoctorplantilla) {
			CardLayout cl = (CardLayout)(switchllista.getLayout());
	        cl.show(switchllista, "gestiodoctorplantilla");
		}
		else if (arg0.getSource() == treuredoctorplantilla) {
			CardLayout cl = (CardLayout)(switchllista.getLayout());
	        cl.show(switchllista, "gestiodoctorplantilla");
		}
		else if(arg0.getSource() == acceptarcreacioplantilla) {
			String nom = textcrearplantilla.getText();
			textcrearplantilla.setText("");
	        ctrlVistaPlantilla.crearPlantilla(nom);
	        inicialitza_plt();
			CardLayout cl = (CardLayout)(switchgestio.getLayout());
	        cl.show(switchgestio, "gestioplantilla");
		}
		
		else if(arg0.getSource() == eliminarplantilla) {
			String nom = nomplantilla.getText();
			ctrlVistaPlantilla.eliminarPlantilla(nom);
			inicialitza_plt();
			CardLayout cl = (CardLayout)(switchgestio.getLayout());
	        cl.show(switchgestio, "gestioplantilla");
		}
		else if (arg0.getSource() == enrerecreacioplantilla) {
			CardLayout cl = (CardLayout)(switchgestio.getLayout());
	        cl.show(switchgestio, "gestioplantilla");
		}
		else if (arg0.getSource() == buttoncrearplantilla) {
			CardLayout cl = (CardLayout)(switchgestio.getLayout());
	        cl.show(switchgestio, "crearplantilla");
		}
	}
	
	public void itemStateChanged(ItemEvent evt) {
		
    }
	
	
	public JPanel tornapanel() {
		return plantilla;
	}

	
    public void ompleDoctorDni(String p){
        String content = ctrlVistaPlantilla.getPlantillaespecifica(p);
        llistaCPlt.removeAllElements();
        if(!content.equals("")) {
        	if(content.length() > 0) {
        		String separadors = "[ \n]";
        		String[] separat = content.split(separadors);
        		for (int i = 0; i < separat.length; i += 1) {
        			llistaCPlt.addElement(separat[i] + " " + separat[i + 1] + " " + separat[i + 2] + " " + separat[i + 3]);
        		}
        	}
        }
    }
	
    public void ompleValuesPlantilla(){
        //agafem els valors
        String selected = textplantilla.getSelectedValue().toString();
        //String separadors = "[ \n]";
        String[] separat = selected.split(" ");
        String d = separat[0]; //Dni
        //omplim segons el dni
        ompleDoctorDni(d);
    }
	
    public class raton implements MouseListener {
        public void mousePressed(MouseEvent e) {
        }

        public void mouseReleased(MouseEvent e) {
        }

        public void mouseEntered(MouseEvent e) {
        }

        public void mouseExited(MouseEvent e) {
        }

        public void mouseClicked(MouseEvent e) {
            ompleValuesPlantilla();
            CardLayout cl = (CardLayout)(switchgestio.getLayout());
            cl.show(switchgestio, "modificarplantilla");

        }
    }
}
