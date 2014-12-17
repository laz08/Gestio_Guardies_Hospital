package prop;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.io.File;

public class VistaGuardar implements ActionListener {
	private static CtrlVistaGuardar ctrlVistaGuardar;

	private JPanel guardar = new JPanel();
	private JButton guardarDocs = new JButton("Guardar doctors");
    private JButton guardarRes = new JButton("Guardar restriccions");
    private JButton guardarPlan = new JButton("Guardar plantilles");
    private JButton guardarCal = new JButton("Guardar calendaris");
	private JFileChooser obrirdirectori = new JFileChooser();

	public VistaGuardar(CtrlVistaGuardar cvg) {
        ctrlVistaGuardar = cvg;
		guardar.setLayout(null);
		guardarDocs.setLocation(250, 150);
        guardarRes.setLocation(700, 150);
        guardarPlan.setLocation(250, 350);
        guardarCal.setLocation(700, 350);
		inicialitza_gestio();
	}

	
	private void inicialitza_gestio() {
		guardarDocs.addActionListener(this);
		guardarDocs.setSize(200, 100);

        guardarPlan.addActionListener(this);
        guardarPlan.setSize(200,100);

        guardarCal.addActionListener(this);
        guardarCal.setSize(200, 100);

        guardarRes.addActionListener(this);
        guardarRes.setSize(200, 100);

		guardar.add(guardarDocs);
        guardar.add(guardarPlan);
        guardar.add(guardarCal);
        guardar.add(guardarRes);
	}
	

	
	public void actionPerformed(ActionEvent arg0) {

		if (arg0.getSource() == guardarDocs) {
            int ret = obrirdirectori.showSaveDialog(guardar);
            if (ret == obrirdirectori.APPROVE_OPTION){
                File f = obrirdirectori.getSelectedFile();
                CtrlHospital.guardar(f);
            }
		}
        else if (arg0.getSource() == guardarPlan) {
            int ret = obrirdirectori.showSaveDialog(guardar);
            if (ret == obrirdirectori.APPROVE_OPTION){
                File f = obrirdirectori.getSelectedFile();
                //CtrlPlantilla.guardar(f);
            }
        }
        else if (arg0.getSource() == guardarCal) {
            int ret = obrirdirectori.showSaveDialog(guardar);
            if (ret == obrirdirectori.APPROVE_OPTION){
                File f = obrirdirectori.getSelectedFile();
                CtrlCalendari.guardar(f);
            }
        }
        if (arg0.getSource() == guardarRes) {
            int ret = obrirdirectori.showSaveDialog(guardar);
            if (ret == obrirdirectori.APPROVE_OPTION){
                File f = obrirdirectori.getSelectedFile();
                CtrlRestriccio.guardar(f);
            }
        }

	}
	
	public void itemStateChanged(ItemEvent evt) {
		
    }
	
	public JPanel tornapanel() {
		return guardar;
	}


}
