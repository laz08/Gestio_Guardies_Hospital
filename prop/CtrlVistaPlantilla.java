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
}
