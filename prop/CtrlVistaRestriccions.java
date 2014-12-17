package prop;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;


public class CtrlVistaRestriccions {
    private static CtrlVistaPrincipal ctrlVistaPrincipal;
    private static VistaRestriccio vistaRestriccio;

    public CtrlVistaRestriccions(CtrlVistaPrincipal vpc){
        ctrlVistaPrincipal = vpc;
        vistaRestriccio = new VistaRestriccio(this);
    }

    public static VistaRestriccio getVistaRestriccio(){
        return vistaRestriccio;
    }
    
    public ArrayList<String> getLlistaRestriccions(){
        ArrayList<String> llista = new ArrayList<String>();
        for(int i=0; i<CtrlRestriccio.consulta_llista_res().size(); i++){
            llista.add(CtrlRestriccio.consulta_explesio_res(i));
        }
        return llista;
    }
    
    public void creaRestriccio(String expresio){
        CtrlRestriccio.nova_res(expresio);
    }
    
    public void eliminaRestriccio(String expresio){
        int pos = CtrlRestriccio.consulta_pos(expresio);
        CtrlRestriccio.elimina_res(pos);
    }
    
    public String[] carregaRestriccions(File f) throws FileNotFoundException{
        String dades = CtrlRestriccio.carrega(f);
        String separadors = "[ \n]";
        String[] llista = dades.split(separadors);
        return llista;
    }
    
    public void grardaRestriccions(File f){
        CtrlRestriccio.guardar(f);
    }
}
