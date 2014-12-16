package prop;


public class CtrlVistaCalendaris {
    private CtrlVistaPrincipal ctrlVistaPrincipal;
    private VistaCalendari vistaCalendari;

    public CtrlVistaCalendaris(CtrlVistaPrincipal cpv){
        ctrlVistaPrincipal = cpv;
    }

    public VistaCalendari getVistaCalendari(){
        return vistaCalendari;
    }
}
