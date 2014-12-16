package prop;


public class CtrlVistaHospital {
    private static VistaHospital vh;
    public CtrlVistaHospital(){
        vh = new VistaHospital(this);
    }
}
