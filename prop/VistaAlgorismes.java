package prop;

import javax.swing.JPanel;

public class VistaAlgorismes {
    private CtrlVistaAlgorismes ctrlVistaAlgorismes;
    private JPanel panel_algorisme = new JPanel();
    
    
    public VistaAlgorismes(CtrlVistaAlgorismes cvalg){
        ctrlVistaAlgorismes = cvalg;
    }
    
    
    public JPanel tornapanel(){
        return panel_algorisme;
    }
}
