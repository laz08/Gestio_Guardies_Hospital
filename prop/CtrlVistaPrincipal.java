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

    private static VistaHospital vistaHospital;
    private static VistaPlantilla vistaPlantilla;
    private static VistaCalendari vistaCalendari;
    private static VistaRestriccio vistaRestriccio;
    private static VistaAlgorismes vistaAlgorismes;
    private static VistaAssignacio vistaAssignacio;
    private static VistaGuardar vistaGuardar;
    private static VistaCarregar vistaCarregar;



    public CtrlVistaPrincipal(){
        creaCtrlVistes();
        vistaHospital = ctrlVistaHospital.getVistaHospital();
        vistaPlantilla = ctrlVistaPlantilla.getVistaPlantilla();
        vistaCalendari = ctrlVistaCalendaris.getVistaCalendari();
        vistaRestriccio = ctrlVistaRestriccions.getVistaRestriccio();
        vistaAlgorismes = ctrlVistaAlgorismes.getVistaAlgorismes();
        vistaAssignacio = ctrlVistaAssignacio.getVistaAssignacio();
        vistaGuardar = ctrlVistaGuardar.getVistaGuardar();
        vistaCarregar = ctrlVistaCarregar.getVistaCarregar();
        vistaPrincipal = new VistaPrincipal(this, vistaHospital, vistaPlantilla, vistaCalendari, vistaRestriccio, vistaAlgorismes, vistaAssignacio, vistaGuardar, vistaCarregar);

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
