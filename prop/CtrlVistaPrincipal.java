package prop;


public class CtrlVistaPrincipal {

    private static VistaPrincipal vistaPrincipal;
    private static CtrlVistaHospital ctrlVistaHospital;
    private static CtrlVistaPlantilla ctrlVistaPlantilla;
    private static CtrlVistaCalendaris ctrlVistaCalendaris;
    private static CtrlVistaRestriccions ctrlVistaRestriccions;
    private static CtrlVistaAlgorismes ctrlVistaAlgorismes;
    private static CtrlVistaAssignacio ctrlVistaAssignacio;
    private static CtrlDiscCarregar ctrlDiscCarregar;
    private static CtrlDiscGuardar ctrlDiscGuardar;


    public CtrlVistaPrincipal(){
        creaCtrlVistes();
        vistaPrincipal = new VistaPrincipal(this, ctrlDiscGuardar, ctrlDiscCarregar);
    }
    public  void creaCtrlVistes(){
        ctrlVistaHospital = new CtrlVistaHospital(this);
        ctrlVistaPlantilla = new CtrlVistaPlantilla(this);
        ctrlVistaCalendaris= new CtrlVistaCalendaris(this);
        ctrlVistaRestriccions = new CtrlVistaRestriccions(this);
        ctrlVistaAlgorismes = new CtrlVistaAlgorismes(this);
        ctrlVistaAssignacio = new CtrlVistaAssignacio(this);
        ctrlDiscGuardar = new CtrlDiscGuardar(this);
        ctrlDiscCarregar = new CtrlDiscCarregar(this);
    }

}
