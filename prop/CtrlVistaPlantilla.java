package prop;


public class CtrlVistaPlantilla {
    private static VistaPlantilla vp;

    public CtrlVistaPlantilla(){
        vp = new VistaPlantilla();
    }

    public static VistaPlantilla getVistaPlantilla(){
        return vp;
    }
}
