package prop;


public class CtrlVistaPrincipal {

    private static VistaPrincipal vistaPrincipal;
    private static CtrlVistaHospital ctrlVistaHospital;
    private static CtrlVistaPlantilla ctrlVistaPlantilla;
    private static CtrlVistaCalendaris ctrlVistaCalendaris;
    private static CtrlVistaRestriccions ctrlVistaRestriccions;
    private static CtrlVistaAlgorismes ctrlVistaAlgorismes;
    private static CtrlVistaAssignacio ctrlVistaAssignacio;
    private static CtrlVistaGuardar ctrlVistaGuardar;
    private static CtrlVistaCarregar ctrlVistaCarregar;


    public CtrlVistaPrincipal(){
        creaCtrlVistes();

    }
    public  void creaCtrlVistes(){
        ctrlVistaHospital = new CtrlVistaHospital(this);
        ctrlVistaPlantilla = new CtrlVistaPlantilla(this);
        ctrlVistaCalendaris= new CtrlVistaCalendaris(this);
        ctrlVistaRestriccions = new CtrlVistaRestriccions(this);
        ctrlVistaAlgorismes = new CtrlVistaAlgorismes(this);
        ctrlVistaAssignacio = new CtrlVistaAssignacio(this);
        ctrlVistaGuardar = new CtrlVistaGuardar(this);
        ctrlVistaCarregar = new CtrlVistaCarregar(this);
    }

}
