/*package prop;


public class OLDCtrlVistaAssignacio {
    private static OLDCtrlVistaPrincipal ctrlVistaPrincipal;
    private static VistaAssignacio vistaAssignacio;

    public OLDCtrlVistaAssignacio(OLDCtrlVistaPrincipal vpc){
        ctrlVistaPrincipal = vpc;
        vistaAssignacio = new VistaAssignacio(this);
    }
    
    public Graf consultaGraf(){
        return OLDCtrlVistaAlgorismes.getGraf();
    }
    
    public Calendari consultaCalendari(){
        Plantilla p = CtrlPlantilla.getPlantillaActual();
        return CtrlCalendari.consultarCalendari(p.getNomPlantilla());
    }

    public static VistaAssignacio getVistaAssignacio(){
        return vistaAssignacio;
    }
}

*/