package prop;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class VistaAlgorismes {

    private static final int W_WIDTH = 1200, W_HEIGHT = 530;
    private CtrlVistaAlgorismes ctrlVistaAlgorismes;
    private static JPanel panel_algorisme = new JPanel();
    //boolean
    private static boolean grafCreat = false;
    private static boolean plantillaSelec = false;
    //plantilles
    private static JLabel label_plantilles = new JLabel();
    private static JPanel panel_plantilles = new JPanel();
    private static DefaultListModel default_llista_plantilles = new DefaultListModel();
    private static JList llista_plantilles = new JList(default_llista_plantilles);
    private static JScrollPane scroll_llistaP;
    private static JPanel panel_execucio = new JPanel();
    private static JButton btnGraf = new JButton("Crea Graf");
    private static JLabel graf_info = new JLabel("Informació del Graf: ");
    private static JLabel vertex_info = new JLabel();
    private static JLabel arestes_info = new JLabel();
    private static JPanel panel_graf = new JPanel();
    private static JRadioButton algorisme1 = new JRadioButton();
    private static JRadioButton algorisme2 = new JRadioButton();
    private static JButton btn_executa_algorisme = new JButton();
    private static JPanel panel_algorismes = new JPanel();

    public VistaAlgorismes(CtrlVistaAlgorismes cvalg) {
        ctrlVistaAlgorismes = cvalg;
        panel_algorisme.setLayout(null);

        //Mostram les plantilles
        initPanelMostraPlantilla();
        panel_algorisme.add(panel_plantilles);

        initPanelExecucio();
        panel_algorisme.add(panel_execucio);
    }

    private void initPanelMostraPlantilla() {
        panel_plantilles.setBounds(0, 0, W_WIDTH / 2, W_HEIGHT);
        panel_plantilles.setLayout(null);

        label_plantilles.setText("PLANTILLES");
        label_plantilles.setBounds(250, 0, 110, 20);
        panel_plantilles.add(label_plantilles);
        llista_plantilles.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                seleccioPlantilla();
                grafCreat = false;
            }
        });

        insereixPlantilles();
        scroll_llistaP = new JScrollPane(llista_plantilles);
        scroll_llistaP.setBounds(20, 20, W_WIDTH / 2 - 40, W_HEIGHT - 40);
        panel_plantilles.add(scroll_llistaP);
    }

    private void initPanelExecucio() {
        panel_execucio.setLayout(null);
        panel_execucio.setBounds(W_WIDTH / 2, 0, W_WIDTH / 2, W_HEIGHT);
        //GRAF
        panel_graf.setLayout(null);
        panel_graf.setBounds(0, 0, W_WIDTH / 2, W_HEIGHT / 2);
        graf_info.setBounds(30, 60, 200, 20);
        panel_graf.add(graf_info);
        vertex_info.setBounds(40, 100, 250, 20);
        vertex_info.setText("Nombre de vertex: " + ctrlVistaAlgorismes.grafNumVertex());
        arestes_info.setBounds(40, 130, 250, 20);
        arestes_info.setText("Nombre de arestes: " + ctrlVistaAlgorismes.grafNumArestes());
        panel_graf.add(vertex_info);
        panel_graf.add(arestes_info);
        btnGraf.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ctrlVistaAlgorismes.creaGraf();
                grafCreat = true;
            }
        });
        btnGraf.setEnabled(plantillaSelec);
        btnGraf.setBounds(400, 100, 150, 50);
        panel_graf.add(btnGraf);

        //ALGORISMES
        panel_algorismes.setLayout(null);
        panel_algorismes.setBounds(0, W_HEIGHT / 2, W_WIDTH / 2, W_HEIGHT / 2);
        algorisme1.setText("Ford-Fulkerson");
        algorisme1.setBounds(50, 20, 200, 30);
        algorisme2.setText("Edmond's Karp");
        algorisme2.setBounds(50, 70, 200, 30);
        ButtonGroup btn_group = new ButtonGroup();
        btn_group.add(algorisme1);
        btn_group.add(algorisme2);
        panel_algorismes.add(algorisme1);
        panel_algorismes.add(algorisme2);
        btn_executa_algorisme.setText("Executa");
        btn_executa_algorisme.setEnabled(grafCreat && (algorisme2.isEnabled() || algorisme1.isEnabled()));
        btn_executa_algorisme.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                if (algorisme1.isSelected()) {
                    ctrlVistaAlgorismes.executaAlgorisme(algorisme1.getText());
                } else if (algorisme2.isSelected()) {
                    ctrlVistaAlgorismes.executaAlgorisme(algorisme2.getText());
                }
                }catch(Error error){
                    
                }
            }
        });
        btn_executa_algorisme.setBounds(400, 40, 150, 50);
        panel_algorismes.add(btn_executa_algorisme);
        panel_execucio.add(panel_graf);
        panel_execucio.add(panel_algorismes);
    }

    public void insereixPlantilles() {
        ArrayList<Plantilla> llistaP = ctrlVistaAlgorismes.consulta_llista_plantilles();
        default_llista_plantilles.removeAllElements();
        for (int i = 0; i < llistaP.size(); i++) {
            default_llista_plantilles.addElement(llistaP.get(i).getNomPlantilla());
        }
    }

    private void seleccioPlantilla() {
        String sPlantilla = (String) llista_plantilles.getSelectedValue();
        ctrlVistaAlgorismes.seleccionaPlantilla(sPlantilla);
        plantillaSelec = true;
    }

    public JPanel tornapanel() {
        return panel_algorisme;
    }
}
