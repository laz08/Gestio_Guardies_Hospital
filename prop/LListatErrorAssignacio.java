package prop;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

public class LListatErrorAssignacio extends PanelLlistatError {

    private CtrlVistaAssignacio ctrlva;
    private JLabel titoldoctor = new JLabel("LLISTA DOCTORS", SwingConstants.CENTER);

    public LListatErrorAssignacio(CtrlVistaAssignacio cva) {
        ctrlva = cva;
        remove(scroll1);
        remove(error);
        add(titoldoctor, BorderLayout.NORTH);
        add(scroll1);
        add(error, BorderLayout.SOUTH);
        scroll1.setSize(getSize());
        error.setText("");
        llista1.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                String Doctor = (String) llista1.getSelectedValue();
                ctrlva.mostraTornsAssociats(Doctor);
            }
        });
    }

}
