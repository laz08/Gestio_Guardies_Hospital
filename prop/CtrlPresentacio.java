package prop;


public class CtrlPresentacio {

    private VistaPrincipal vistaPrincipal;
    private CtrlDomini controlDomini;

    public CtrlPresentacio(){
        vistaPrincipal = new VistaPrincipal(this);
        controlDomini = new CtrlDomini();
    }

    public static void inicialitzaPresentacio(){

    }
}
