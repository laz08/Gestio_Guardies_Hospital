package prop;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;

public class CalendariRestriccio extends DosBotons implements ListSelectionListener {

    private CtrlVistaRestriccio ctrlvr;
    private DefaultComboBoxModel<String> modelcombo = new DefaultComboBoxModel<String>();
    private JComboBox<String> any = new JComboBox<String>(modelcombo);
    private String[] tipustorn = {"Matí", "Tarda", "Nit"};
    private JComboBox<String> torn = new JComboBox<String>(tipustorn);
    private String[] exemplemesos = {"Gener", "Febrer", "Març", "Abril", "Maig", "Juny", "Juliol", "Agost", "Setembre", "Octubre", "Novembre", "Desembre"};
    private JList<String> mesos = new JList<String>(exemplemesos);
    private JScrollPane scrollmesos = new JScrollPane(mesos);
    private CalendarModel model = new CalendarModel();
    private JTable table = new JTable(model);
    private GridBagConstraints c = new GridBagConstraints();
    ListSelectionModel cellSelectionModel;
    ListSelectionModel modelcolumna;
    //necessari per evitar la repeticio a la seleccio al calendari
    private int[] data = new int[2];

    public CalendariRestriccio(CtrlVistaRestriccio cvr) {
        ctrlvr = cvr;
        remove(b1);
        remove(b2);
        b1.setText("Enrere");
        b2.setText("Acceptar");
        b1.addActionListener(this);
        b2.addActionListener(this);
        setLayout(new GridBagLayout());
        any.setBounds(10, 10, 100, 30);
        any.addActionListener(this);
        scrollmesos.setBounds(100, 10, 150, 100);
        torn.addActionListener(this);
        mesos.addListSelectionListener(this);
        table.setBounds(10, 150, 550, 200);
        table.setShowGrid(true);
        table.setGridColor(Color.BLACK);
        modelcombo.addElement("2015");
        modelcolumna = table.getColumnModel().getSelectionModel();
        modelcolumna.addListSelectionListener(this);
        table.setRowSelectionAllowed(true);
        table.setColumnSelectionAllowed(true);
        table.setCellSelectionEnabled(true);
        cellSelectionModel = table.getSelectionModel();
        cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        cellSelectionModel.addListSelectionListener(this);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        add(torn, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 0;
        c.gridwidth = 1;
        add(scrollmesos, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 3;
        add(table, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 1;
        add(b1, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 4;
        c.gridwidth = 1;
        add(b2, c);
    }

    class CalendarModel extends AbstractTableModel {

        String[] exempledies = {"Dilluns", "Dimarts", "Dimecres", "Dijous", "Divendres", "Dissabte", "Diumenge"};
        int[] numdies = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        String[][] calendar = new String[7][7];

        public CalendarModel() {
            for (int i = 0; i < exempledies.length; ++i) {
                calendar[0][i] = exempledies[i];
            }
            for (int i = 1; i < 7; ++i) {
                for (int j = 0; j < 7; ++j) {
                    calendar[i][j] = " ";
                }
            }
        }

        public Object getValueAt(int fila, int columna) {
            return calendar[fila][columna];
        }

        public void setValueAt(Object valor, int fila, int columna) {
            calendar[fila][columna] = (String) valor;
        }

        public void setMonth(int year, int month) {
            for (int i = 1; i < 7; ++i) {
                for (int j = 0; j < 7; ++j) {
                    calendar[i][j] = " ";
                }
            }
            java.util.GregorianCalendar cal = new java.util.GregorianCalendar();
            cal.set(year, month, 1);
            int offset = cal.get(java.util.GregorianCalendar.DAY_OF_WEEK) - 2;
            if (offset < 0) {
                offset += 7;
            }
            offset += 7;
            int num = daysInMonth(year, month);
            for (int i = 0; i < num; ++i) {
                calendar[(offset / 7)][offset % 7] = Integer.toString(i + 1);
                ++offset;
            }
        }

        public boolean isLeapYear(int year) {
            if (year % 4 == 0) {
                return true;
            }
            return false;
        }

        public int daysInMonth(int year, int month) {
            int days = numdies[month];
            if (month == 1 && isLeapYear(year)) {
                ++days;
            }
            return days;
        }

        public int getColumnCount() {
            return 7;
        }

        public int getRowCount() {
            return 7;
        }
    }

    public void actionPerformed(ActionEvent arg0) {
        if (arg0.getSource() == b1) {
            ctrlvr.habilitaoperacions(false);
            ctrlvr.swap(2, 1);
        } else if (arg0.getSource() == b2) {
            ctrlvr.habilitaoperacions(true);
            ctrlvr.swap(2, 1);
            if(ctrlvr.seleccioPerTorns())ctrlvr.seleccioTorn(torn.getSelectedIndex());
            else ctrlvr.seleccioDia(data[0], data[1]);
            
        } else if (arg0.getSource() == torn) {
            b2.setEnabled(true);
        }
    }

    public void valueChanged(ListSelectionEvent arg0) {
        if (arg0.getSource() == mesos) {
            if (!arg0.getValueIsAdjusting()) {
                model.setMonth(Integer.parseInt((String) any.getSelectedItem()), mesos.getSelectedIndex());
                repaint();
            }
        } else if (arg0.getSource() == this.modelcolumna) {
            //RETORNA LA FILA CLICADA

            int fila = table.getSelectedRow();
            // RETORNA LA COLUMNA CLICADA
            int columna = table.getSelectedColumn();
            if (fila > 0 && columna >= 0 && table.getValueAt(fila, columna) != " " && any.getSelectedItem() != null) {
                //RETORNA DIA 
                b2.setEnabled(true);
                int rany = Integer.parseInt((String) any.getSelectedItem());
                int mes = mesos.getSelectedIndex();
                int dia = Integer.parseInt((String) table.getValueAt(fila, columna));
                data[0] = dia;
                data[1] = mes;

            } else {
                b2.setEnabled(false);
            }
        }
    }

    /**
     * Desactiva el botó d'acceptar
     */
    public void desactivaacceptar() {
        b2.setEnabled(false);
    }

    /**
     * Habilita els botons de torns
     * @param torns
     */
    public void habilitatorns(boolean torns) {
        if (torns) {
            torn.setEnabled(true);
            any.setEnabled(false);
            mesos.setEnabled(false);
            table.setEnabled(false);
        } else {
            torn.setEnabled(false);
            any.setEnabled(true);
            mesos.setEnabled(true);
            table.setEnabled(true);
            b2.setEnabled(false);
        }
    }
}
