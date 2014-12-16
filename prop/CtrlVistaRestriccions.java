package prop;


public class CtrlVistaRestriccions {
    private static CtrlVistaPrincipal ctrlVistaPrincipal;
    private static VistaRestriccio vistaRestriccio;

    public CtrlVistaRestriccions(CtrlVistaPrincipal vpc){
        ctrlVistaPrincipal = vpc;
        vistaRestriccio = new VistaRestriccio(this);
    }

    public static VistaRestriccio getVistaRestriccio(){
        return vistaRestriccio;
    }
}
