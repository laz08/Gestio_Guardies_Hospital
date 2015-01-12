package prop;

import javax.swing.*;
import java.awt.*;

public class CtrlVistaAlgorisme {

    private CtrlVistaPrincipal ctrlvp;
    private JPanel algorisme = new JPanel();
    private LlistatErrorAlgorisme llistatalgorisme = new LlistatErrorAlgorisme(this);
    private BotonsAlgorisme botonsalgorisme = new BotonsAlgorisme(this);

    public CtrlVistaAlgorisme(CtrlVistaPrincipal cvp) {
        ctrlvp = cvp;
        algorisme.setLayout(new BorderLayout());
        algorisme.setBorder(BorderFactory.createEmptyBorder(10, 15, 5, 60));
        algorisme.add(llistatalgorisme, BorderLayout.WEST);
        algorisme.add(botonsalgorisme, BorderLayout.EAST);
    }

    public JPanel tornavista() {
        return algorisme;
    }
}
