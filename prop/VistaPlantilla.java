package prop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class VistaPlantilla implements ActionListener/*ListSelectionListener */{
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
	
	private JPanel gestioseleccioplantilla = new JPanel();
    // Caracteristiques plantilla
    private DefaultListModel llistaDocsPlt = new DefaultListModel();
	private JList llistacplantilla = new JList(llistaDocsPlt);
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
    //ESQUERRA
	private JPanel gestiodoctorplantilla = new JPanel();
    private DefaultListModel llistaDocs = new DefaultListModel();
	private JList doctorsllista = new JList(llistaDocs);
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

        acceptardllista.setEnabled(true);
		acceptardllista.addActionListener(this);

		//doctorsllista.addListSelectionListener(this);
		doctorsllista.addMouseListener(new ratonDocs());
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
		llistaplantilla.addMouseListener(new ratonPlant());
		//listaplantilla.addListSelectionListener(this);

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

    public void assignaDocAPlt(String d, String p){
        //Llegirem DNI i nom plantilla
        //i l'assignarem a

        ctrlVistaPlantilla.assignaDocAPlt(d, p);
    }

    public String getDNIseleccionatdePlt(){
        String content = (String) doctorsllista.getSelectedValue();
        String separadors = "[ \n]";
        String[] separat = content.split(separadors);
        return separat[0];
    }
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == enrerellista) {
            netejaNomPlant();
            //Enrere doctors
            CardLayout cl = (CardLayout)(switchllista.getLayout());
            cl.show(switchllista, "consultaplantilla");

			CardLayout cl2 = (CardLayout)(switchgestio.getLayout());
	        cl2.show(switchgestio, "gestioplantilla");
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
            /*
            Assignem doctor a plantilla.
            Hem d'haver seleccionat un value de la llista "llistaDocs"
             */
            //System.out.println("He premut l'OK corqui");


            String d = getDNIseleccionatdePlt();
            String p = textnomplantilla.getText();
            assignaDocAPlt(d, p);
            //Tinc dni
            inicialitzaDocsPlant();
            inicialitza_Docs();

            CardLayout cl = (CardLayout)(switchllista.getLayout());
			cl.show(switchllista, "consultaplantilla");
		}

		else if (arg0.getSource() == assignardoctorplantilla) {
            inicialitza_Docs();
			CardLayout cl = (CardLayout)(switchllista.getLayout());
	        cl.show(switchllista, "gestiodoctorplantilla");
		}
		else if (arg0.getSource() == treuredoctorplantilla) {
            inicialitzar_docsPlt();
			CardLayout cl = (CardLayout)(switchllista.getLayout());
	        cl.show(switchllista, "gestiodoctorplantilla");
		}
		else if(arg0.getSource() == acceptarcreacioplantilla) {
			String nom = textcrearplantilla.getText();
			netejaNomPlant();
	        ctrlVistaPlantilla.crearPlantilla(nom);
	        inicialitza_plt();
			CardLayout cl = (CardLayout)(switchgestio.getLayout());
	        cl.show(switchgestio, "gestioplantilla");
		}
		
		else if(arg0.getSource() == eliminarplantilla) {
            String nom = textnomplantilla.getText();
			ctrlVistaPlantilla.eliminarPlantilla(nom);
			inicialitza_plt();
			CardLayout cl = (CardLayout)(switchgestio.getLayout());
	        cl.show(switchgestio, "gestioplantilla");
		}
		else if (arg0.getSource() == enrerecreacioplantilla) {
            netejaNomPlant();
		    CardLayout cl2 = (CardLayout)(switchgestio.getLayout());
	        cl2.show(switchgestio, "gestioplantilla");
		}
		else if (arg0.getSource() == buttoncrearplantilla) {
			CardLayout cl = (CardLayout)(switchgestio.getLayout());
	        cl.show(switchgestio, "crearplantilla");
		}
	}

    public void inicialitzar_docsPlt() {
        String plt = textnomplantilla.getText();
        String content = ctrlVistaPlantilla.getPlantillaespecifica(plt);

    }

    public void inicialitza_Docs() {
        String content = ctrlVistaPlantilla.getDoctorsSensePlt();
        llistaDocs.removeAllElements();
        if (!content.equals("")) {
            if (content.length() > 0) {
                String separadors = "[ \n]";
                String[] separat = content.split(separadors);
                for (int i = 0; i < separat.length; i += 4) {
                    llistaDocs.addElement(separat[i] + " " + separat[i + 1] + " " + separat[i + 2] + " " + separat[i + 3]);
                }
            }
        }
    }

    public void inicialitzaDocsPlant(){
        String p = textnomplantilla.getText();
        String content = ctrlVistaPlantilla.getPlantillaespecifica(p);
        llistaDocsPlt.removeAllElements();
        if(!content.equals("")){
            //No està buida
            String separadors =  "[ \n]";
            String[] separat = content.split(separadors);
            for (int i = 0; i < separat.length; i += 4) {
                llistaDocsPlt.addElement(separat[i] + " " + separat[i + 1] + " " + separat[i + 2] + " " + separat[i + 3]);
            }
        }
    }
	
	public JPanel tornapanel() {
		return plantilla;
	}

	
    public void ompleDoctorDni(String p){
        String content = ctrlVistaPlantilla.getPlantillaespecifica(p);
        llistaDocsPlt.removeAllElements();
        if(!content.equals("")) {
        	if(content.length() > 0) {
        		String separadors = "[ \n]";
        		String[] separat = content.split(separadors);
        		for (int i = 0; i < separat.length; i += 1) {
        			llistaDocsPlt.addElement(separat[i] + " " + separat[i + 1] + " " + separat[i + 2] + " " + separat[i + 3]);
        		}
        	}
        }
    }

    public void netejaNomPlant(){
        textcrearplantilla.setText("");
    }
	
    public void ompleValuesPlantilla(){
        //agafem els valors
        String selected = (String)textplantilla.getSelectedValue();
        //String separadors = "[ \n]";
        String[] separat = selected.split(" ");
        String d = separat[0]; //Dni
        //omplim segons el dni
        ompleDoctorDni(d);
    }

	
    public class ratonPlant implements MouseListener {
        public void mousePressed(MouseEvent e) {
        }

        public void mouseReleased(MouseEvent e) {
        }

        public void mouseEntered(MouseEvent e) {
        }

        public void mouseExited(MouseEvent e) {
        }

        public void mouseClicked(MouseEvent e) {
            if (e.getSource() == llistaplantilla) {
                String s = (String) llistaplantilla.getSelectedValue();
                textnomplantilla.setText(s);
                ompleDoctorDni(s);
                CardLayout cl = (CardLayout)(switchgestio.getLayout());
                cl.show(switchgestio, "gestioseleccioplantilla");
            }
            if (e.getSource() == textplantilla) {
                String s = (String) llistaplantilla.getSelectedValue();
                textnomplantilla.setText(s);
                CardLayout cl = (CardLayout)(switchllista.getLayout());
                cl.show(switchllista, "gestiodoctorplantilla");
            }
        }
    }


    public class ratonDocs implements MouseListener {
        public void mousePressed(MouseEvent e) {
        }

        public void mouseReleased(MouseEvent e) {
        }

        public void mouseEntered(MouseEvent e) {
        }

        public void mouseExited(MouseEvent e) {
        }

        public void mouseClicked(MouseEvent e) {
            if (e.getSource() == llistaplantilla) {
                inicialitzaDocsPlant();
                String s = (String) llistaplantilla.getSelectedValue();
                textnomplantilla.setText(s);
                ompleDoctorDni(s);
                CardLayout cl = (CardLayout)(switchgestio.getLayout());
                cl.show(switchgestio, "gestioseleccioplantilla");
            }
            if (e.getSource() == textplantilla) {
                String s = (String) llistaplantilla.getSelectedValue();
                textnomplantilla.setText(s);
                CardLayout cl = (CardLayout)(switchllista.getLayout());
                cl.show(switchllista, "gestiodoctorplantilla");
            }

        }

    }



}
