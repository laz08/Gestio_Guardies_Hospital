import javax.swing.JButton;
import javax.swing.JFileChooser;


public class TresBotons extends DosBotons{
	protected JButton b3 = new JButton();
	protected JFileChooser directori = new JFileChooser();
	
	public TresBotons() {
		this.add(b3);
		b3.addActionListener(this);
	}
}
