package prop;


public class CtrlVistaAssignacio {
    private static CtrlVistaPrincipal ctrlVistaPrincipal;
    private static VistaAssignacio vistaAssignacio;

    public CtrlVistaAssignacio(CtrlVistaPrincipal vpc){
        ctrlVistaPrincipal = vpc;
        vistaAssignacio = new VistaAssignacio(this);
    }

    public static VistaAssignacio getVistaAssignacio(){
        return vistaAssignacio;
    }
}
