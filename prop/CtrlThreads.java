/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prop;

/**
 *
 * @author Xisco
 */
public class CtrlThreads{

    private static Thread[] threads = new Thread[3];
    private static Thread s;
    private static boolean[] vius = {false, false, false};
    private static boolean supervisor = false;
    
    public static void FordFulquerson(Algorisme algo) {
        Thread ford = new Thread(algo);
        threads[0] = ford;
        ford.start();
        vius[0] = true;
        if (!supervisor) {
            s = new Thread(new Supervisor());
            s.start();
        }
    }

    public static void EdmondsKarp(Algorisme algo) {
        Thread edmonds = new Thread(algo);
        threads[1] = edmonds;
        edmonds.start();
        vius[1] = true;
        if (!supervisor) {
            s = new Thread(new Supervisor());
            s.start();
        }
    }

    public static void Dijkstra(Algorisme algo) {
        Thread dij = new Thread(algo);
        threads[2] = dij;
        dij.start();
        vius[2] = true;
        if (!supervisor) {
            s = new Thread(new Supervisor());
            s.start();
        }
    }
    
    public static Thread getThread(int pos){
        return threads[pos];
    }
    
    public static void setSupervisor(boolean estat){
        supervisor = estat;
    }
    
    public static boolean getViu(int pos){
        return vius[pos];
    }
    
    public static void setViu(int pos, boolean estat){
        vius[pos] = estat;
    }
}
