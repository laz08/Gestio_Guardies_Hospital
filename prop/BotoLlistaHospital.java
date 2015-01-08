package prop;
import java.awt.event.ActionEvent;

public class BotoLlistaHospital extends BotoLlista {
	
	public BotoLlistaHospital() {
		remove(b1);
		remove(b2);
		add(scroll1);
		add(b1);
		add(b2);
	}
	
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == b1) {
			
		}
		
		else if (arg0.getSource() == b2) {
			
		}
	}

}
