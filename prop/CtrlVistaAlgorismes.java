package prop;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;

public class CtrlVistaAlgorismes {
        private static CtrlVistaPrincipal ctrlVistaPrincipal;
        private static VistaAlgorismes vistaAlgorismes;
        private Graf g = null; 
        
        public CtrlVistaAlgorismes(CtrlVistaPrincipal cvp){
            ctrlVistaPrincipal = cvp;
            vistaAlgorismes = new VistaAlgorismes(this);
        }

        public static VistaAlgorismes getVistaAlgorismes(){
            return vistaAlgorismes;
        }
        
        public static ArrayList<Plantilla> consulta_llista_plantilles(){
            ArrayList<Plantilla> llistaP = new ArrayList<Plantilla>();
            TreeSet<Plantilla> treeP = CtrlPlantilla.getCjt_plantilles();
            Iterator<Plantilla> it = treeP.iterator();
            while (it.hasNext()){
                llistaP.add(it.next());
            }
            return llistaP;
        }
        
        public void seleccionaPlantilla(String plantila){
            CtrlPlantilla.setPlantillaActual(plantila);
        }
        
        public void creaGraf(){
            g = CtrlEntrada.crea_graf();
        }
        
        public int grafNumVertex(){
            if(g!=null) return g.numV();
            else return 0;
        }
        
        public int grafNumArestes(){
            if (g!= null) return g.getNumA();
            else return 0;
        }
        
        public void executaAlgorisme(String algorisme) throws Error{
            Algorisme al = null;
            switch(algorisme){
                case "Ford-Fulkerson":
                    al = new FordFulkerson();
                    break;
                case "Edmond's-Karp":
                    al = new EdmondsKarp();
                    break;
            }
            if(al != null){
                al.setGraf(g);
                al.maxFlow();
                g = CtrlEntrada.calculaInvers(g);
                al.setGraf(g);
                al.maxFlow();
            }
        }

}
