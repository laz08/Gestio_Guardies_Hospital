package prop;


public class CtrlVistaCarregar {
    private static CtrlVistaPrincipal ctrlVistaPrincipal;
    private static VistaCarregar vistaCarregar;

    public CtrlVistaCarregar(CtrlVistaPrincipal vpc){
        ctrlVistaPrincipal = vpc;
        vistaCarregar = new VistaCarregar(this);
    }

    public VistaCarregar getVistaCarregar(){
        return vistaCarregar;
    }
}
