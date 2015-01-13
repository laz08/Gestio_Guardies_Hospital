package prop;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
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
        llista1.addListSelectionListener(this);
        /*
        llista1.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                String Doctor = (String) llista1.getSelectedValue();
                ctrlva.mostraTornsAssociats(Doctor);
            }
        });
        */


    }
    public void valueChanged(ListSelectionEvent arg0) {
        if (arg0.getSource() == this.llista1) {
            if (!arg0.getValueIsAdjusting()) {
                String Doctor = (String) llista1.getSelectedValue();
                String[] splitejat = Doctor.split(" ");
                System.out.println(splitejat[0]);
                ctrlva.mostraTornsAssociats(splitejat[0]);
            }
        }
    }

}

