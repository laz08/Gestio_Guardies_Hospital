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

    public static String getLlistaDocs_nom(){
        return CtrlHospital.getLlistatDoctorsenString_nom();
    }

    public static String getDoctorEspecific(String dni){
        return CtrlHospital.getDoctorEspecificString(dni);
    }

}
