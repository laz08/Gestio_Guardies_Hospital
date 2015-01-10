package prop;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.util.GregorianCalendar;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

public class ModelCalendari extends TresBotons implements ListSelectionListener{
	private CtrlVistaCalendari ctrlvc;
	private DefaultComboBoxModel<String> modelcombo = new DefaultComboBoxModel<String>();
	private JComboBox<String> any = new JComboBox<String>(modelcombo);
	private String[] exemplemesos = {"Gener", "Febrer", "Març", "Abril", "Maig", "Juny", "Juliol", "Agost", "Setembre", "Octubre", "Novembre", "Desembre"};
	private JList<String> mesos = new JList<String>(exemplemesos);
	private JScrollPane scrollmesos = new JScrollPane(mesos);
	private CalendarModel model = new CalendarModel();
	private JTable table = new JTable(model);
	private GridBagConstraints c = new GridBagConstraints();

	public ModelCalendari(CtrlVistaCalendari cvc) {
		ctrlvc = cvc;
		setLayout(new GridBagLayout());
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b1.setText("Enrere");
		b2.setText("Eliminar Calendari");
		b3.setText("Acceptar");
		remove(b1);
		remove(b2);
		remove(b3);
		mesos.addListSelectionListener(this);
		any.setBounds(10, 10, 100, 30);
		any.addActionListener(this);
		scrollmesos.setBounds(100, 10, 150, 100);
		mesos.addListSelectionListener(this);
		table.setBounds(10, 150, 550, 200);
		table.setShowGrid(true);
		table.setGridColor(Color.BLACK);
		table.setColumnSelectionAllowed(false);
		table.setRowSelectionAllowed(false);
		table.setCellSelectionEnabled(true);
		ListSelectionModel cellSelectionModel = table.getSelectionModel();
		cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		cellSelectionModel.addListSelectionListener(this);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		add(any,c);
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
		c.gridx = 2;
		c.gridy = 4;
		c.gridwidth = 1;
		add(b2, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 4;
		c.gridwidth = 1;
		add(b3, c);

	}

	class CalendarModel extends AbstractTableModel {
		String[] exempledies= { "Dissabte", "Diumenge", "Dilluns", "Dimarts", "Dimecres", "Dijous", "Divendres" };

		int[] numdies = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

		String[][] calendar = new String[7][7];

		public CalendarModel() {
			for (int i = 0; i < exempledies.length; ++i)
				calendar[0][i] = exempledies[i];
			for (int i = 1; i < 7; ++i)
				for (int j = 0; j < 7; ++j)
					calendar[i][j] = " ";
		}

		public Object getValueAt(int fila, int columna) {
			return calendar[fila][columna];
		}

		public void setValueAt(Object valor, int fila, int columna) {
			calendar[fila][columna] = (String) valor;
		}

		public void setMonth(int year, int month) {
			for (int i = 1; i < 7; ++i)
				for (int j = 0; j < 7; ++j)
					calendar[i][j] = " ";
			java.util.GregorianCalendar cal = new java.util.GregorianCalendar();
			cal.set(year, month, 1);
			int offset = cal.get(java.util.GregorianCalendar.DAY_OF_WEEK);
			offset += 7;
			int num = daysInMonth(year, month);
			for (int i = 0; i < num; ++i) {
				calendar[offset / 7][offset % 7] = Integer.toString(i + 1);
				++offset;
			}
		}

		public boolean isLeapYear(int year) {
			if (year % 4 == 0)
				return true;
			return false;
		}

		public int daysInMonth(int year, int month) {
			int days = numdies[month];
			if (month == 1 && isLeapYear(year))
				++days;
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

		}
		else if (arg0.getSource() == b2) {
			ctrlvc.swap(1, 1);
		}
		else {
			
		}
		ctrlvc.swap(2,1);
	}

	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getSource() == mesos) {
			if (!arg0.getValueIsAdjusting()) {
				model.setMonth(Integer.parseInt((String)any.getSelectedItem()), mesos.getSelectedIndex());
				repaint();
			}
		}
		else {
			//RETORNA LA FILA CLICADA
			int fila = table.getSelectedRow();
			// RETORNA LA COLUMNA CLICADA
			int columna = table.getSelectedColumn();
			if (fila > 0 && columna >= 0 && table.getValueAt(fila, columna) != " " && any.getSelectedItem() != null) {
				//RETORNA DIA 
				int rany = Integer.parseInt((String)any.getSelectedItem());
				int mes = mesos.getSelectedIndex();
				int dia = Integer.parseInt((String)table.getValueAt(fila, columna));
				GregorianCalendar data = new GregorianCalendar(rany,mes, dia);
				//System.out.println(CtrlCalendari.consultarDiaFestiu(llistaplantilla.getSelectedValue(),data));
				//festiu.setSelected(CtrlCalendari.consultarDiaFestiu(llistaplantilla.getSelectedValue(),data));            	
				//percentmati.setText(String.valueOf(CtrlVistaCalendaris.consultarpercenttorn(new GregorianCalendar(Integer.parseInt((String) any.getSelectedItem()), mesos.getSelectedIndex(), Integer.parseInt((String) model.getValueAt(table.getSelectedRow(), table.getSelectedColumn()))), llistaplantilla.getSelectedValue(), 0)));
				//minimmati.setText(String.valueOf(CtrlVistaCalendaris.consultaminimtorn(new GregorianCalendar(Integer.parseInt((String) any.getSelectedItem()), mesos.getSelectedIndex(), Integer.parseInt((String) model.getValueAt(table.getSelectedRow(), table.getSelectedColumn()))), llistaplantilla.getSelectedValue(), 0)));
				//percentarda.setText(String.valueOf(CtrlVistaCalendaris.consultarpercenttorn(new GregorianCalendar(Integer.parseInt((String) any.getSelectedItem()), mesos.getSelectedIndex(), Integer.parseInt((String) model.getValueAt(table.getSelectedRow(), table.getSelectedColumn()))), llistaplantilla.getSelectedValue(), 1)));
				//minimtarda.setText(String.valueOf(CtrlVistaCalendaris.consultaminimtorn(new GregorianCalendar(Integer.parseInt((String) any.getSelectedItem()), mesos.getSelectedIndex(), Integer.parseInt((String) model.getValueAt(table.getSelectedRow(), table.getSelectedColumn()))), llistaplantilla.getSelectedValue(), 1)));
				//percentnit.setText(String.valueOf(CtrlVistaCalendaris.consultarpercenttorn(new GregorianCalendar(Integer.parseInt((String) any.getSelectedItem()), mesos.getSelectedIndex(), Integer.parseInt((String) model.getValueAt(table.getSelectedRow(), table.getSelectedColumn()))), llistaplantilla.getSelectedValue(), 2)));
				//minimnit.setText(String.valueOf(CtrlVistaCalendaris.consultaminimtorn(new GregorianCalendar(Integer.parseInt((String) any.getSelectedItem()), mesos.getSelectedIndex(), Integer.parseInt((String) model.getValueAt(table.getSelectedRow(), table.getSelectedColumn()))), llistaplantilla.getSelectedValue(), 2)));
				//diatorn.setText(Integer.toString(dia);
				
			}
			ctrlvc.swap(1,2);
		}
	}

	public void itemStateChanged(ItemEvent e) {
		if (e.getSource() == any) {
			model.setMonth(Integer.parseInt((String)any.getSelectedItem()), mesos.getSelectedIndex());
			table.repaint();
			repaint();
			//RETORNA L'ANY
		}

	}
}