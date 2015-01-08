package prop;

public class OLDCtrlVistaGuardar {
    private static OLDCtrlVistaPrincipal ctrlVistaPrincipal;
    private static VistaGuardar vistaGuardar;

    public OLDCtrlVistaGuardar(OLDCtrlVistaPrincipal vpc){
        ctrlVistaPrincipal = vpc;
        vistaGuardar = new VistaGuardar(this);
    }

    public static VistaGuardar getVistaGuardar(){
        return vistaGuardar;
    }


}
