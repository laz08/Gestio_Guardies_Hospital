package prop;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class BotoLlista extends DosBotons{
	
	protected DefaultListModel<String> model1 = new DefaultListModel<String>();
	protected JList<String> llista1 = new JList<String>();
	protected JScrollPane scroll1 = new JScrollPane(llista1);
	
}
