package prop;

import javax.swing.*;
import java.awt.*;


public class PanelLlistatError extends PanelLlistat{
	protected ImageIcon load = new ImageIcon("Cercle.gif");
	protected JLabel error = new JLabel("Error: ", load, JLabel.LEFT);
	
	public PanelLlistatError() {
        inicialitza_icona();
		add(error, BorderLayout.SOUTH);

	}

    private void inicialitza_icona(){
        error.setHorizontalTextPosition(JLabel.LEFT);
        error.setVisible(true);
        error.setForeground(Color.RED);
    }
}
