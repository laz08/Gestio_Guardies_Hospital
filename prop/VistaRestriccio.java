package prop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class VistaRestriccio {

    private static CtrlVistaRestriccions ctrlVistaRestriccions;
    private JPanel restriccions = new JPanel();
    private JPanel buttonsrestriccio = new JPanel();
    private JPanel insertrestriccio = new JPanel();
    private JPanel llistatrestriccio = new JPanel();
    private JPanel gestiorestriccio = new JPanel();
    private JButton crearrestriccio = new JButton("Crear");
    private JButton eliminarrestriccio = new JButton("Eliminar");
    private JButton carregarrestriccio = new JButton("Carregar");
    private JButton guardarrestriccio = new JButton("Guardar");
    private DefaultListModel DefLlistaRestriccions = new DefaultListModel();
    private JList llistaRestriccions = new JList(DefLlistaRestriccions);
    private JScrollPane scrollpane = new JScrollPane(llistaRestriccions);
    private JTextField introduirrestriccio = new JTextField(40);
    private JPanel insertaracceptarrestriccio = new JPanel();
    private JPanel insertartextrestriccio = new JPanel();
    private JLabel mostraErrors = new JLabel();
    String[] tres = {"H", "D"};
    private JList tipusrestriccio = new JList(tres);

    public VistaRestriccio(CtrlVistaRestriccions cvr) {
        ctrlVistaRestriccions = cvr;
        restriccions.setLayout(new BorderLayout());
        mostraErrors.setText("");
        mostraErrors.setForeground(Color.red);
        inicialitza_llistat();
        inicialitza_gestio();
        inicialitza_insercio();

    }

    public void inicialitza_llistat() {
        scrollpane.setPreferredSize(new Dimension(600, 460));
        scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        llistatrestriccio.setLayout(new BoxLayout(llistatrestriccio, BoxLayout.Y_AXIS));
        llistatrestriccio.add(scrollpane);//, BorderLayout.NORTH);
        carregaRestriccions();
        //llistatrestriccio.add(llistaRestriccions);
        restriccions.add(llistatrestriccio, BorderLayout.WEST);
    }

    private void carregaRestriccions() {
        ArrayList<String> llistaERestriccions = ctrlVistaRestriccions.getLlistaRestriccions();
        DefLlistaRestriccions.removeAllElements();
        for (int i = 0; i < llistaERestriccions.size(); i++) {
            DefLlistaRestriccions.addElement(llistaERestriccions.get(i));
        }
    }

    public void inicialitza_insercio() {
        insertaracceptarrestriccio.setLayout(new BorderLayout());
        insertartextrestriccio.setLayout(new FlowLayout());
        insertrestriccio.setLayout(new BorderLayout());
        insertartextrestriccio.add(tipusrestriccio);
        insertartextrestriccio.add(introduirrestriccio);

        insertrestriccio.add(insertartextrestriccio, BorderLayout.NORTH);
        insertrestriccio.add(insertaracceptarrestriccio, BorderLayout.SOUTH);
        insertrestriccio.setPreferredSize(new Dimension(600, 300));

        tipusrestriccio.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        gestiorestriccio.add(insertrestriccio, BorderLayout.NORTH);
    }

    public void inicialitza_gestio() {

        buttonsrestriccio.setLayout(new GridBagLayout());
        buttonsrestriccio.setPreferredSize(new Dimension(600, 250));
        gestiorestriccio.setLayout(new BorderLayout());
        gestiorestriccio.add(buttonsrestriccio, BorderLayout.SOUTH);
        crearrestriccio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crearRestriccio();
            }
        });
        buttonsrestriccio.add(crearrestriccio);
        eliminarrestriccio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarRestriccio();
            }
        });
        buttonsrestriccio.add(eliminarrestriccio);
        guardarrestriccio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarRestriccio();
            }
        });
        buttonsrestriccio.add(guardarrestriccio);
        carregarrestriccio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carregarRestriccio();
            }
        });
        buttonsrestriccio.add(carregarrestriccio);
        restriccions.add(gestiorestriccio, BorderLayout.EAST);
    }

    public JPanel tornapanel() {
        return restriccions;
    }

    private void crearRestriccio() {
        if (!introduirrestriccio.getText().isEmpty()) {
            ctrlVistaRestriccions.creaRestriccio(introduirrestriccio.getText());
        }
        carregaRestriccions();
    }

    private void eliminarRestriccio() {
        String rSeleccionada = (String) llistaRestriccions.getSelectedValue();
        ctrlVistaRestriccions.eliminaRestriccio(rSeleccionada);
        carregaRestriccions();
    }

    private void carregarRestriccio() {
        try {
            JFileChooser chooser = new JFileChooser();
            File f = null;
            boolean seleccionat = chooser.accept(f);
            String[] sRestriccions = null;
            if (seleccionat) {
                sRestriccions = ctrlVistaRestriccions.carregaRestriccions(f.getAbsolutePath());
            }
            for (int i = 0; i < sRestriccions.length; i++) {
                ctrlVistaRestriccions.creaRestriccio(sRestriccions[i]);
            }
            carregaRestriccions();
        } catch (FileNotFoundException e) {
            mostraErrors.setText("S'ha produit un error quan es carregaven les restriccions");
        }
        carregaRestriccions();
    }

    private void guardarRestriccio() {
        JFileChooser chooser = new JFileChooser();
        File f = null;
        boolean seleccionat = chooser.accept(f);
        ctrlVistaRestriccions.grardaRestriccions(f);
    }
//    @Override
//    public void actionPerformed(ActionEvent ev) {
//        JFileChooser obrirdirectori = new JFileChooser();
//        obrirdirectori.showOpenDialog(restriccions);
//    }
}
