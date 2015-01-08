package prop;

import java.util.ArrayList;


public class OLDCtrlVistaHospital {
    private static OLDCtrlVistaPrincipal ctrlVistaPrincipal;
    private static VistaHospital vh;

    public OLDCtrlVistaHospital(OLDCtrlVistaPrincipal cvp){
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
    
    public ArrayList<String> carregaLlistaRestriccions(){
        ArrayList<String> lr = new ArrayList<String>();
        for(int i=0; i<CtrlRestriccio.consulta_llista_res().size(); i++){
            lr.add(CtrlRestriccio.consulta_explesio_res(i));
        }
        return lr;
    }
    
    public void associaRestriccio(String r, String dni) throws Error{
        Doc_Res.relaciona(dni, CtrlRestriccio.consulta_pos(r));
    }

//    public String getRestriccions(){
//        return "HOLA";
//    }

}
