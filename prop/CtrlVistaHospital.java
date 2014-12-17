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

    public String getLlistaDocs_nom(){
        return CtrlHospital.getLlistatDoctorsenString_nom();
    }

    public String getDoctorEspecific(String dni){
        return CtrlHospital.getDoctorEspecificString(dni);
    }

    public String getRestriccions(){
        return "HOLA";
    }

}
