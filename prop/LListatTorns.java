package prop;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;

public class LListatTorns extends PanelLlistat {

    private CtrlVistaAssignacio ctrlva;
    private JLabel titoltorns = new JLabel("LLISTA DE TORNS", SwingConstants.CENTER);

    public LListatTorns(CtrlVistaAssignacio cva) {
        ctrlva = cva;
        remove(scroll1);
        add(titoltorns, BorderLayout.NORTH);
        add(scroll1);
        super.setMaximumSize(new Dimension(350, 300));
        scroll1.setMaximumSize(new Dimension(350, 300));
        llista1.addListSelectionListener(this);
        /*
        llista1.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                String Torn = (String) llista1.getSelectedValue();
                ctrlva.mostraDoctorsAssociats(Torn);
            }
        });
        */
    }
    public void valueChanged(ListSelectionEvent arg0) {
        if (arg0.getSource() == this.llista1) {
            if (!arg0.getValueIsAdjusting()) {
                String Torn = (String) llista1.getSelectedValue();
                ctrlva.mostraDoctorsAssociats(Torn);
            }
        }
    }
}
