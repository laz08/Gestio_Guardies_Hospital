package prop;


public class CtrlVistaPlantilla {
	
    private static CtrlVistaPrincipal ctrlVistaPrincipal;
    private static VistaPlantilla vp;

    public CtrlVistaPlantilla(CtrlVistaPrincipal vpc){
        ctrlVistaPrincipal = vpc;
        vp = new VistaPlantilla(this);
    }

    public static VistaPlantilla getVistaPlantilla(){
        return vp;
    }
    
    public String getLlista_plt() {
    	return CtrlPlantilla.getLlista_plantilles();
    }
    
    public static void crearPlantilla(String plt) {
    	if(!CtrlPlantilla.existeixPlantilla(plt)) {
    		CtrlPlantilla.creariAfegirPlantilla(plt);
    	}
    }

    public static void assignarDocPlt(String d, String plt) {
        CtrlPlantilla.afegirDoctorAPlantilla(d,plt);
    }
    
    public static void eliminarPlantilla(String plt) {
    	CtrlPlantilla.esborrarPlantilla(plt);
    }
    
    public static boolean existeixCalendari(String plt) {
    	return CtrlPlantilla.existeixCalendari(plt);
    }
    
    public static void crearCalendari(String plt, int any, int anyf) {
    	CtrlPlantilla.crearCalendari(plt, any, anyf);
    }
    
    public static void eliminarCalendari(String plt) {
    	CtrlPlantilla.eliminarCalendari(plt);
    }

    public static String getPlantillaespecifica(String plt) {
    	return CtrlPlantilla.getPlantillaespecifica(plt);
    }


    public static String getDoctorsSensePlt() {
    	return CtrlHospital.getDoctorsSensePlt();
    }

    public static void assignaDocAPlt(String d, String p){
        CtrlPlantilla.afegirDoctorAPlantilla(d, p);
    }
    
    //Vull tots els doctors que no estan actius. Crida a CtrlHospital???
}
