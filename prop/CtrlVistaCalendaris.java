package prop;

import java.io.File;
import java.util.GregorianCalendar;


public class CtrlVistaCalendaris {
    private CtrlVistaPrincipal ctrlVistaPrincipal;
    private VistaCalendari vistaCalendari;

    public CtrlVistaCalendaris(CtrlVistaPrincipal cpv){
        ctrlVistaPrincipal = cpv;
        vistaCalendari = new VistaCalendari(this);
    }

    public VistaCalendari getVistaCalendari(){
        return vistaCalendari;
    }
    
    public String getllistaplantilles() {
    	return CtrlPlantilla.getLlista_plantilles();
    }
    
    public static void guardar(File f) {
    	CtrlCalendari.guardar(f);
    }
    
    public static boolean existeixCalendari(String plt) {
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
    
    public static void carregar(File f) {
    	CtrlCalendari.carregar(f);
    }
    
    public static void crearcalendari(String plt, int anyi, int anyfi) {
    	CtrlCalendari.crearIafegirCalendari(plt, anyi, anyfi);
    }
    
    public static void eliminarcalendari(String plt) {
    	CtrlCalendari.eliminarCalendari(plt);
    }
    public static void modificadiafestiu(String plt, GregorianCalendar dia, boolean b) {
    	CtrlCalendari.modificarDiaFestiu(plt, dia, b);
    }
}
