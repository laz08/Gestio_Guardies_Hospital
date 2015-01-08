package prop;
import javax.swing.JButton;
import javax.swing.JFileChooser;


public class PanelBotons extends DosBotons{
	protected JButton b1 = new JButton();
	protected JButton b2 = new JButton();
	protected JButton b3 = new JButton();
	protected JFileChooser directori = new JFileChooser();
	
	public PanelBotons() {
		this.add(b1);
		this.add(b2);
		this.add(b3);
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
	}
}
