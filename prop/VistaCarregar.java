package prop;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class VistaCarregar implements ActionListener, ListSelectionListener {
	private static OLDCtrlVistaCarregar ctrlVistaCarregar;

	private JPanel carregar = new JPanel();
	private JButton carregarDocs = new JButton("Carregar doctors");
    private JButton carregarRes = new JButton("Carregar restriccions");
    private JButton carregarPlan = new JButton("Carregar plantilles");
    private JButton carregarCal = new JButton("Carregar calendaris");

    private JFileChooser obrirdirectori = new JFileChooser();

	public VistaCarregar(OLDCtrlVistaCarregar cvc) {
        ctrlVistaCarregar = cvc;
		carregar.setLayout(null);

        /*
        guardarDocs.setLocation(250, 150);
        guardarRes.setLocation(700, 150);
        guardarPlan.setLocation(250, 350);
        guardarCal.setLocation(700, 350);
         */
        carregarDocs.setLocation(250, 150);
        carregarRes.setLocation(700, 150);
        carregarPlan.setLocation(250, 350);
        carregarCal.setLocation(700, 350);
		inicialitza_gestio();
	}

	
	private void inicialitza_gestio() {
		carregarDocs.addActionListener(this);
		carregarDocs.setSize(200, 100);
		carregar.add(carregarDocs);

        carregarCal.addActionListener(this);
        carregarCal.setSize(200, 100);
        carregar.add(carregarCal);

        carregarRes.addActionListener(this);
        carregarRes.setSize(200, 100);
        carregar.add(carregarRes);

        carregarPlan.addActionListener(this);
        carregarPlan.setSize(200, 100);
        carregar.add(carregarPlan);
	}
	
	public void valueChanged(ListSelectionEvent e) {

	}
	
	public void actionPerformed(ActionEvent arg0) {

		if (arg0.getSource() == carregarDocs) {
            int ret = obrirdirectori.showOpenDialog(carregar);
            //L'usuari ha escollit un fitxer
            if(ret == obrirdirectori.APPROVE_OPTION){
                File f = obrirdirectori.getSelectedFile();
                CtrlHospital.carregar(f);
            }

		}
        else if (arg0.getSource() == carregarRes) {
            int ret = obrirdirectori.showOpenDialog(carregar);
            //L'usuari ha escollit un fitxer
            if(ret == obrirdirectori.APPROVE_OPTION){
                File f = obrirdirectori.getSelectedFile();
                //CtrlRestriccio.carregar(f);
            }
        }
        else if (arg0.getSource() == carregarPlan) {
            int ret = obrirdirectori.showOpenDialog(carregar);
            //L'usuari ha escollit un fitxer
            if(ret == obrirdirectori.APPROVE_OPTION){
                File f = obrirdirectori.getSelectedFile();
               CtrlPlantilla.carregar(f);
            }
        }

        else if (arg0.getSource() == carregarCal) {
            int ret = obrirdirectori.showOpenDialog(carregar);
            //L'usuari ha escollit un fitxer
            if(ret == obrirdirectori.APPROVE_OPTION){
                File f = obrirdirectori.getSelectedFile();
                CtrlCalendari.carregar(f);
            }
        }

	}
	

	
	public JPanel tornapanel() {
		return carregar;
	}


}
