package prop;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;

import java.util.*;

public class VPC {
	private CtrlPresentacion iCtrlPresentacion;
	private JFrame frameVista = new JFrame("Projecte PROP");
	private JTabbedPane pestanyes = new JTabbedPane();
	
	public void inicialitzar() {
		inicializar_frameVista();
	}
	
	public VPC(CtrlPresentacion pCtrlPresentacion) {
	    System.out.println
	      ("isEventDispatchThread: " + SwingUtilities.isEventDispatchThread());
	    iCtrlPresentacion = pCtrlPresentacion;
	    inicialitzar();
	}

	public void hacerVisible() {
	    System.out.println
	      ("isEventDispatchThread: " + SwingUtilities.isEventDispatchThread());
	    frameVista.pack();
	    frameVista.setVisible(true);

	  }

	  public void activar() {
	    frameVista.setEnabled(true);
	  }

	  public void desactivar() {
	    frameVista.setEnabled(false);
	  }
	  
	  public void inicializar_frameVista() {
		    // Tamanyo
		    frameVista.setMinimumSize(new Dimension(1200,600));
		    frameVista.setPreferredSize(frameVista.getMinimumSize());
		    frameVista.setResizable(false);
		    // Posicion y operaciones por defecto
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
		    hacerVisible();
		    
	  }
}
