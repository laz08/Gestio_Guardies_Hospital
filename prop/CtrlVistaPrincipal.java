package prop;


public class CtrlVistaPrincipal {

    private static VistaPrincipal VP;
    private static CtrlVistaHospital cVH;
    private static CtrlVistaPlantilla cVPl;
    private static CtrlVistaCalendaris cVCal;
    private static CtrlVistaRestriccions cVRes;
    private static CtrlVistaAlgorismes cVAlg;
    private static CtrlVistaAssignacio cVAss;
    private static CtrlVistaGuardar cVG;
    private static CtrlVistaCarregar cVC;

    private static VistaHospital vh;
    private static VistaPlantilla vp;
    private static VistaCalendari vcal;
    private static VistaRestriccio vr;
    private static VistaAlgorismes va;
    private static VistaAssignacio vass;
    private static VistaGuardar vg;
    private static VistaCarregar vcarr;



    public CtrlVistaPrincipal(){
        creaCtrlVistes();

        VP = new VistaPrincipal();

    }
    public static void creaCtrlVistes(){
        cVH = new CtrlVistaHospital();
        cVPl = new CtrlVistaPlantilla();
        cVCal = new CtrlVistaCalendaris();
        cVRes = new CtrlVistaRestriccions();
        cVAlg = new CtrlVistaAlgorismes();
        cVAss = new CtrlVistaAssignacio();
        cVG = new CtrlVistaGuardar();
        cVC = new CtrlVistaCarregar();
    }

}

/*
    controladorvistahospital
    controladorvistaplantilla
            vh = controladorvistahospital.vh();
    vp = controladorvistaplantilla.vp();
    vistaprincipal(vh,vp)
}



public class controladorvistahospital {
    vistahospital new
}

*/