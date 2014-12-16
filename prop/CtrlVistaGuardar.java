package prop;

public class CtrlVistaGuardar {
    private static CtrlVistaPrincipal ctrlVistaPrincipal;
    private static VistaGuardar vistaGuardar;

    public CtrlVistaGuardar(CtrlVistaPrincipal vpc){
        ctrlVistaPrincipal = vpc;
        vistaGuardar = new VistaGuardar(this);
    }

    public static VistaGuardar getVistaGuardar(){
        return vistaGuardar;
    }


}