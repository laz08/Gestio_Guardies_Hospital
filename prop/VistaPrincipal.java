package prop;
import javax.swing.*;
import java.awt.*;

public class VistaPrincipal {
	private JFrame frameVista = new JFrame("Gestió de guàrdies d'un hospital");
	private JTabbedPane pestanyes = new JTabbedPane();

    public VistaPrincipal(){
        inicialitzar();
    }
	public void inicialitzar() {
		inicializar_frameVista();
	}
	
	public void ferVisible() {
	    frameVista.pack();
	    frameVista.setVisible(true);

	  }

	  public void inicializar_frameVista() {
            //Mesures
		    frameVista.setMinimumSize(new Dimension(1200,600));
		    frameVista.setPreferredSize(frameVista.getMinimumSize());
		    frameVista.setResizable(false);

		    frameVista.setLocationRelativeTo(null);
		    frameVista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    frameVista.setLayout(new BorderLayout());
		    pestanyes.addTab("Hospital", new VistaHospital().tornapanel());
		    pestanyes.addTab("Plantilla", new VistaPlantilla().tornapanel());
		    pestanyes.addTab("Calendari", new JPanel());
		    pestanyes.addTab("Restriccions", new VistaRestriccio().tornapanel());
		    pestanyes.addTab("Algorisme", new JPanel());
		    pestanyes.addTab("Guardar", new JPanel());
		    pestanyes.addTab("Carregar", new JPanel());
		    frameVista.getContentPane().add(pestanyes);
		    ferVisible();
		    
	  }
}
