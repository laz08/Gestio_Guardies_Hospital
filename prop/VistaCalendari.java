package prop;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
public class VistaCalendari implements ListSelectionListener, ItemListener, ActionListener {
    private static CtrlVistaCalendaris ctrlVistaCalendaris;
    private JPanel calendari = new JPanel();
    private JPanel switchllista = new JPanel();
    private JPanel switchgestio = new JPanel();
    private JFileChooser obrirdirectori = new JFileChooser();

    //Part llistat plantilla
    private JTextField error = new JTextField(20);
    private JPanel llistatplantilla = new JPanel();
    private DefaultListModel<String> modelplantilla = new DefaultListModel<String>();
    private JList<String> llistaplantilla = new JList<String>(modelplantilla);
    private JScrollPane scrollplantilla = new JScrollPane(llistaplantilla);

    //Part Torn-Calendari
    private JPanel torncalendari = new JPanel();
    private JLabel labeldiatorn = new JLabel("Dia");
    private JTextField diatorn = new JTextField(3);
    private JLabel labelfestiu = new JLabel("Festiu:");
    private JCheckBox festiu = new JCheckBox();
    private JLabel labeltorns = new JLabel("Torns:");
    private JLabel labelmati = new JLabel("Matí:");
    private JLabel labeltarda = new JLabel("Tarda:");
    private JLabel labelnit = new JLabel("Nit:");
    private JTextField inicimati = new JTextField(3);
    private JTextField fimati = new JTextField(3);
    private JTextField percentmati = new JTextField(3);
    private JTextField minimmati = new JTextField(3);
    private JTextField inicitarda = new JTextField(3);
    private JTextField fitarda = new JTextField(3);
    private JTextField percentarda = new JTextField(3);
    private JTextField minimtarda = new JTextField(3);
    private JTextField inicinit = new JTextField(3);
    private JTextField finit = new JTextField(3);
    private JTextField percentnit = new JTextField(3);
    private JTextField minimnit = new JTextField(3);
    private JButton enreretorn = new JButton("Enrere");
    private JButton acceptartorn = new JButton("Acceptar");

    //Part Carregar-Guardar dret
    private JPanel gestiocg = new JPanel();
    private JButton carregarcalendari = new JButton("Carregar Calendaris");
    private JButton guardarcalendari = new JButton("Guardar Calendaris");

    //Part Calendari en si
    private JPanel agenda = new JPanel();
    private String[] exempleanys = {"2014", "2015", "2016"};
    private JComboBox<String> any = new JComboBox<String>(exempleanys);
    private String[] exemplemesos = {"Gener", "Febrer", "Març", "Abril", "Maig", "Juny", "Juliol", "Agost", "Setembre", "Octubre", "Novembre", "Desembre"};
    private JList<String> mesos = new JList<String>(exemplemesos);
    private JScrollPane scrollmesos = new JScrollPane(mesos);
    private CalendarModel model = new CalendarModel();
    private JTable table = new JTable(model);
    private GridBagConstraints c = new GridBagConstraints();
    private JButton enrerecalendari = new JButton("Enrere");
    private JButton acceptarcalendari = new JButton("Acceptar");
    private JButton eliminarcalendari = new JButton("Eliminar Calendari");

    //Part CrearCalendari
    private JPanel crearcalendari = new JPanel();
    private JButton creacalendari = new JButton("Crear Calendari");
    private JLabel labelanyfi = new JLabel("Any fi");
    private JTextField anyfi = new JTextField(6);
    private JLabel labelanyinici = new JLabel("Any inici");
    private JTextField anyinici = new JTextField(6);
    private JButton acceptarcrear = new JButton("Acceptar");
    private JButton enrerecrear = new JButton("Enrere");

    public VistaCalendari(CtrlVistaCalendaris cvc) {
            ctrlVistaCalendaris = cvc;
        //Afegir switches ara la principal
        calendari.setLayout(new BorderLayout());
        inicialitza_llistatplantilla();
        inicialitza_calendari();
        inicialitza_crearcalendari();
        inicialitza_carregar_guardar();
        inicialitza_torncalendari();
        switchgestio.setLayout(new CardLayout());
        switchllista.setLayout(new CardLayout());
        switchllista.add(llistatplantilla, "llistatplantilla");
        switchllista.add(torncalendari, "torncalendari");
        switchgestio.add(gestiocg, "gestiocg");
        switchgestio.add(agenda, "agenda");
        switchgestio.add(crearcalendari, "crearcalendari");
        calendari.add(switchgestio, BorderLayout.EAST);
        calendari.add(switchllista, BorderLayout.WEST);

    }

    public void inicialitza_llistatplantilla() {
        modelplantilla.addElement("Plantilla1");
        modelplantilla.addElement("Plantilla2");
        modelplantilla.addElement("Plantilla3");
        modelplantilla.addElement("Plantilla4");
        llistatplantilla.setLayout(new BorderLayout());
        llistaplantilla.addListSelectionListener(this);
        llistatplantilla.add(scrollplantilla, BorderLayout.NORTH);
        scrollplantilla.setPreferredSize(new Dimension(600, 460));
        llistatplantilla.add(error, BorderLayout.SOUTH);
    }

    public void inicialitza_torncalendari() {
        enreretorn.addActionListener(this);
        acceptartorn.addActionListener(this);
        torncalendari.setLayout(new GridBagLayout());
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        torncalendari.add(labeldiatorn, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 1;
        torncalendari.add(diatorn, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        torncalendari.add(labelfestiu, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 1;
        torncalendari.add(festiu, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
        torncalendari.add(labeltorns, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 1;
        torncalendari.add(labelmati, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 3;
        c.gridwidth = 1;
        torncalendari.add(inicimati, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 3;
        c.gridwidth = 1;
        torncalendari.add(fimati, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 3;
        c.gridy = 3;
        c.gridwidth = 1;
        torncalendari.add(percentmati, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 4;
        c.gridy = 3;
        c.gridwidth = 1;
        torncalendari.add(minimmati, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 1;
        torncalendari.add(labeltarda, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 4;
        c.gridwidth = 1;
        torncalendari.add(inicitarda, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 4;
        c.gridwidth = 1;
        torncalendari.add(fitarda, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 3;
        c.gridy = 4;
        c.gridwidth = 1;
        torncalendari.add(percentarda, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 4;
        c.gridy = 4;
        c.gridwidth = 1;
        torncalendari.add(minimtarda, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 5;
        c.gridwidth = 1;
        torncalendari.add(labelnit, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 5;
        c.gridwidth = 1;
        torncalendari.add(inicinit, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 5;
        c.gridwidth = 1;
        torncalendari.add(finit, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 3;
        c.gridy = 5;
        c.gridwidth = 1;
        torncalendari.add(percentnit, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 4;
        c.gridy = 5;
        c.gridwidth = 1;
        torncalendari.add(minimnit, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 6;
        c.gridwidth = 1;
        torncalendari.add(enreretorn, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 4;
        c.gridy = 6;
        c.gridwidth = 1;
        torncalendari.add(acceptartorn, c);
    }

    public void inicialitza_calendari() {
        eliminarcalendari.addActionListener(this);
        acceptarcalendari.addActionListener(this);
        enrerecalendari.addActionListener(this);
        agenda.setLayout(new GridBagLayout());
        any.setBounds(10, 10, 100, 30);
        any.setSelectedIndex(0);
        any.addItemListener(this);
        scrollmesos.setBounds(100, 10, 150, 100);
        mesos.setSelectedIndex(3);
        mesos.addListSelectionListener(this);
        table.setBounds(10, 150, 550, 200);
        table.setShowGrid(true);
        table.setGridColor(Color.BLACK);
        ListSelectionModel cellSelectionModel = table.getSelectionModel();
        cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        cellSelectionModel.addListSelectionListener(this);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        agenda.add(any,c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 0;
        c.gridwidth = 1;
        agenda.add(scrollmesos, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 3;
        agenda.add(table, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 1;
        agenda.add(enrerecalendari, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 4;
        c.gridwidth = 1;
        agenda.add(acceptarcalendari, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 4;
        c.gridwidth = 1;
        agenda.add(eliminarcalendari, c);

    }

    public void inicialitza_carregar_guardar() {
        carregarcalendari.addActionListener(this);
        guardarcalendari.addActionListener(this);
        gestiocg.setLayout(new GridBagLayout());
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        gestiocg.add(carregarcalendari, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        gestiocg.add(guardarcalendari, c);
    }

    public void inicialitza_crearcalendari() {
        enrerecrear.addActionListener(this);
        acceptarcrear.addActionListener(this);
        crearcalendari.setLayout(new GridBagLayout());
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        crearcalendari.add(labelanyinici, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        crearcalendari.add(anyinici, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
        crearcalendari.add(labelanyfi, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 1;
        crearcalendari.add(anyfi, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 1;
        crearcalendari.add(enrerecrear, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 5;
        c.gridwidth = 1;
        crearcalendari.add(acceptarcrear, c);
    }

    public void itemStateChanged(ItemEvent e) {
        model.setMonth(any.getSelectedIndex() + 1998, mesos.getSelectedIndex());
        agenda.repaint();
    }

    public void valueChanged(ListSelectionEvent arg0) {
        if (arg0.getSource() == llistaplantilla) {
            if (!arg0.getValueIsAdjusting()) {
                CardLayout cl = (CardLayout)(switchgestio.getLayout());
                cl.show(switchgestio, "agenda");
            }
        }
        else {
            model.setMonth(any.getSelectedIndex()+1998, mesos.getSelectedIndex());
            agenda.repaint();
            //RETORNA LA FILA CLICADA
            int fila = table.getSelectedRow();
            // RETORNA LA COLUMNA CLICADA
            int columna = table.getSelectedColumn();
            if (fila > 0 && columna >= 0 && table.getValueAt(fila, columna) != " ") {
                CardLayout cl = (CardLayout)(switchllista.getLayout());
                cl.show(switchllista, "torncalendari");
            }
        }
    }

    class CalendarModel extends AbstractTableModel {
        String[] exempledies= { "Dissabte", "Diumenge", "Dilluns", "Dimarts", "Dimecres", "Dijous", "Divendres" };

        int[] numdies = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

        String[][] calendar = new String[7][7];

        public CalendarModel() {
            for (int i = 0; i < exempledies.length; ++i)
                calendar[0][i] = exempledies[i];
            for (int i = 1; i < 7; ++i)
                for (int j = 0; j < 7; ++j)
                    calendar[i][j] = " ";
        }

        public Object getValueAt(int fila, int columna) {
            return calendar[fila][columna];
        }

        public void setValueAt(Object valor, int fila, int columna) {
            calendar[fila][columna] = (String) valor;
        }

        public void setMonth(int year, int month) {
            for (int i = 1; i < 7; ++i)
                for (int j = 0; j < 7; ++j)
                    calendar[i][j] = " ";
            java.util.GregorianCalendar cal = new java.util.GregorianCalendar();
            cal.set(year, month, 1);
            int offset = cal.get(java.util.GregorianCalendar.DAY_OF_WEEK)-1;
            offset += 7;
            int num = daysInMonth(year, month);
            for (int i = 0; i < num; ++i) {
                calendar[offset / 7][offset % 7] = Integer.toString(i + 1);
                ++offset;
            }
        }

        public boolean isLeapYear(int year) {
            if (year % 4 == 0)
                return true;
            return false;
        }

        public int daysInMonth(int year, int month) {
            int days = numdies[month];
            if (month == 1 && isLeapYear(year))
                ++days;
            return days;
        }

        public int getColumnCount() {
            return 7;
        }

        public int getRowCount() {
            return 7;
        }
    }

    public Component tornapanel() {
        return calendari;
    }

    // Botons
    public void actionPerformed(ActionEvent ev) {
        Object comp = ev.getSource();
        if (comp == enreretorn) {
            CardLayout cl = (CardLayout)(switchllista.getLayout());
            cl.show(switchllista, "llistatplantilla");
        }
        else if (comp == enrerecalendari) {
            CardLayout cl = (CardLayout)(switchgestio.getLayout());
            cl.show(switchgestio, "gestiocg");
        }
        else if (comp == enrerecrear) {
            CardLayout cl = (CardLayout)(switchgestio.getLayout());
            cl.show(switchgestio, "gestiocg");
        }
        else if (comp == acceptartorn) {
            CardLayout cl = (CardLayout)(switchllista.getLayout());
            cl.show(switchllista, "llistatplantilla");
        }
        else if (comp == acceptarcalendari) {
            CardLayout cl = (CardLayout)(switchgestio.getLayout());
            cl.show(switchgestio, "gestiocg");
        }
        else if (comp == acceptarcrear) {
            CardLayout cl = (CardLayout)(switchgestio.getLayout());
            cl.show(switchgestio, "gestiocg");
        }
        else if (comp == eliminarcalendari) {
            CardLayout cl = (CardLayout)(switchgestio.getLayout());
            cl.show(switchgestio, "gestiocg");
        }
        else if (comp == creacalendari) {
            CardLayout cl = (CardLayout)(switchgestio.getLayout());
            cl.show(switchgestio, "gestiocg");
        }
        else if (comp == carregarcalendari) {
            obrirdirectori.showOpenDialog(calendari);
        }
        else if (comp == guardarcalendari) {
            obrirdirectori.showSaveDialog(calendari);
        }
    }

}