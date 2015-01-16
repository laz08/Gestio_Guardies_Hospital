/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prop;

/**
 *
 * @author Xisco
 */
public class CtrlAlgorisme {
    public static Graf graf = new Graf();
    private static Algorisme[] algorismes = new Algorisme[3];


    /**
     * Retorna el núm. de vertex que té el graf
     * @return
     */
    public static int getNumVertex(){
        return graf.numV();
    }

    /**
     * Inicialitza el graf
     */
    public static void initGraf(){
        graf = Entrada.crea_graf();
    }

    /**
     * Inicialitza el graf per a la plantilla plt
     * @param plt
     */
    public static void iniciarGraf(String plt) {
    	graf = Entrada.crea_graf_plt(plt);
    }


    /**
     * Retorna el núm. d'arestes que té el graf
     * @return
     */
    public static int getNumArestes(){
        return graf.getNumA();
    }

    /**
     * Executa Ford Fulkerson
     * selSou determina si s'executa seleccionant per sou o no
     * (En cas que no, només selecciona per mínim de doctors).
     * @param selSou
     */
    public static void executaFordFulkerson(boolean selSou){
        algorismes[0] = new FordFulkerson(selSou);
        CtrlThreads.FordFulquerson(algorismes[0]);
    }

    /**
     * Executa Edmonds Karp
     * selSou determina si s'executa seleccionant per sou o no
     * (En cas que no, només selecciona per mínim de doctors).
     * @param selSou
     */
    public static void executaEdmondsKarp(boolean selSou){
        algorismes[1] = new EdmondsKarp(selSou);
        CtrlThreads.EdmondsKarp(algorismes[1]);
    }

    /**
     * Executa Dijkstra.
     * selSou determina si s'executa seleccionant per sou o no
     * (En cas que no, només selecciona per mínim de doctors).
     * @param selSou
     */
    public static void executaDijkstra(boolean selSou){
        algorismes[2] = new Dijkstra(selSou);
        CtrlThreads.Dijkstra(algorismes[2]);
    }

    /**
     * Retorna l'algorisme en la posició pos
     * @param pos
     * @return
     */
    public static Algorisme getAlgorisme(int pos){
        return algorismes[pos];
    }

    /**
     * Retorna el graf
     * @return
     */
    public static Graf getGraf(){
        return graf;
    }
}
