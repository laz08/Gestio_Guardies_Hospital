package prop;


public class CtrlVistaAssignacio {
    private static CtrlVistaPrincipal ctrlVistaPrincipal;
    private static VistaAssignacio vistaAssignacio;

    public CtrlVistaAssignacio(CtrlVistaPrincipal vpc){
        ctrlVistaPrincipal = vpc;
        vistaAssignacio = new VistaAssignacio(this);
    }
    
    public Graf consultaGraf(){
        return CtrlVistaAlgorismes.getGraf();
    }
    
    public Calendari consultaCalendari(){
        Plantilla p = CtrlPlantilla.getPlantillaActual();
        return CtrlCalendari.consultarCalendari(p.getNomPlantilla());
    }

    public static VistaAssignacio getVistaAssignacio(){
        return vistaAssignacio;
    }
}
