package prop;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.GregorianCalendar;

public class CtrlVistaCalendari {
	private CtrlVistaPrincipal ctrlvp;
	private JPanel calendari = new JPanel();
	private JPanel dret = new JPanel();
	private JPanel esquerre = new JPanel();
	private LlistatErrorCalendari llistatcalendari = new LlistatErrorCalendari(this);
	private BotoMesTextCalendari diacalendari = new BotoMesTextCalendari(this);
	private CGCalendari gestiocalendari = new CGCalendari(this);
	private BotoTextCalendari crearcalendari = new BotoTextCalendari(this, llistatcalendari);
	private ModelCalendari modelcalendari = new ModelCalendari(this);
	
	public CtrlVistaCalendari(CtrlVistaPrincipal cvp) {
		ctrlvp = cvp;
		calendari.setLayout(new BorderLayout());
		dret.setLayout(new CardLayout());
		esquerre.setLayout(new CardLayout());
		dret.setSize(450, 500);
        esquerre.setBorder(BorderFactory.createEmptyBorder(10, 15, 5, 60));


        esquerre.setPreferredSize(new Dimension(415, 250));
        esquerre.setMaximumSize(new Dimension(415, 250));
        //esquerre.setSize(450,600);
		llistatcalendari.setSize(450,500);
		esquerre.add(llistatcalendari,"1-1");
		esquerre.add(diacalendari, "1-2");
		dret.add(gestiocalendari, "2-1");
		dret.add(crearcalendari, "2-2");
		dret.add(modelcalendari, "2-3");
		calendari.add(esquerre, BorderLayout.WEST);
		calendari.add(dret, BorderLayout.EAST);
	}
	
	public void swap(int banda, int numpanelins) {
		if (banda == 1) {
			CardLayout cl = (CardLayout) esquerre.getLayout();
			cl.show(esquerre, String.valueOf(banda)+"-"+String.valueOf(numpanelins));
		}
		else {
			CardLayout cl = (CardLayout) dret.getLayout();
			cl.show(dret, String.valueOf(banda)+"-"+String.valueOf(numpanelins));
		}
	}

	public JPanel tornavista() {
		return calendari;
	}
	
	public String getllistaplantilles() {
    	return CtrlPlantilla.getLlista_plantilles();
    }
    
    public void guardar(File f) {
    	CtrlCalendari.guardar(f);
    }
    
    public boolean existeixCalendari(String plt) {
    	return CtrlCalendari.existeixCalendari(plt);
    }
    
    public static boolean diafestiu(String plt, GregorianCalendar dia) {
		return CtrlCalendari.consultarDiaFestiu(plt, dia);
    	
    }
    
    public static int getAny(String plt) {
    	return CtrlCalendari.consultarCalendari(plt).getAny();
    }
    
    public static int getAnyfi(String plt) {
    	return CtrlCalendari.consultarCalendari(plt).getAnyFi();
    }
    
    public static float consultarpercenttorn(GregorianCalendar dia, String plt, int horari) {
    	return CtrlCalendari.consultarPercentatgeTorn(dia, plt, horari);
    }
    
    public static int consultaminimtorn(GregorianCalendar dia, String plt, int horari) {
    	return CtrlCalendari.consultarMinimTorn(dia, plt, horari);
    }
    
    public static void modificarPercentatgeTorn(float p, GregorianCalendar dia, String plt, int horari) {
    	CtrlCalendari.modificarPercentatgeTorn(p, dia, plt, horari);
	}
    
    public static void modificarMinimTorn(int m, GregorianCalendar dia, String plt, int horari) {
    	CtrlCalendari.modificarMinimTorn(m, dia, plt, horari);
    }
    
    public void carregar(File f) {
    	CtrlCalendari.carregar(f);
    }
    
    public void crearcalendari(String plt, int anyi, int anyfi) {
    	CtrlCalendari.afegirCalendari(plt, anyi, anyfi);
    }
    
    public void eliminarcalendari(String plt) {
    	CtrlCalendari.eliminarCalendari(plt);
    }
    
    public void actualitza() {
    	llistatcalendari.actualitzar();
    }
    
    public static void modificadiafestiu(String plt, GregorianCalendar dia, boolean b) {
    	CtrlCalendari.modificarDiaFestiu(plt, dia, b);
    }
    
    public String seleccio() {
		return llistatcalendari.getselected();
    }
    
    public void removeselection() {
    	llistatcalendari.removeselection();
    }

	public void carregardia(int rany, int mes, int dia,
			Object selectedItem, int selectedIndex, Object object) {
		diacalendari.carregardia(rany, mes, dia, selectedItem, selectedIndex, object);
		
	}
	
	public void ompleanys(int anyi, int anyf) {
		modelcalendari.ompleanys(anyi, anyf);
	}
	
	public void modificable(Boolean b) {
		modelcalendari.modificable(b);
	}
	
	public GregorianCalendar getdata() {
		return modelcalendari.getdata();
	}

	public void texterror(String string) {
		llistatcalendari.texterror(string);
	}
	
}
