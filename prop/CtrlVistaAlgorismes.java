package prop;

public class CtrlVistaAlgorismes {
        private static CtrlVistaPrincipal ctrlVistaPrincipal;
        private static VistaAlgorismes vistaAlgorismes;

        public CtrlVistaAlgorismes(CtrlVistaPrincipal cvp){
            ctrlVistaPrincipal = cvp;
            vistaAlgorismes = new VistaAlgorismes(this);
        }

        public static VistaAlgorismes getVistaAlgorismes(){
            return vistaAlgorismes;
        }

}
