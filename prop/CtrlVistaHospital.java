package prop;


public class CtrlVistaHospital {
    private static CtrlVistaPrincipal ctrlVistaPrincipal;
    private static VistaHospital vh;

    public CtrlVistaHospital(CtrlVistaPrincipal cvp){
        ctrlVistaPrincipal = cvp;
        vh = new VistaHospital(this);
    }

    public static VistaHospital getVistaHospital(){
        return vh;
    }
}