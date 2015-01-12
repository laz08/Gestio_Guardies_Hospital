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
    private static boolean supervisor = false;
    private static boolean[] vius = {false, false, false};
    
    public static void FordFulquerson(Algorisme algo) {
        System.out.println("Ford");
        Thread ford = new Thread(algo);
        threads[0] = ford;
        ford.start();
        vius[0] = true;
        if (!supervisor) {
            Thread supervisor0 = new Thread((Runnable) new Supervisor());
            supervisor0.start();
        }
    }

    public static void EdmondsKarp(Algorisme algo) {
        System.out.println("Edmonds");
        Thread edmonds = new Thread(algo);
        threads[0] = edmonds;
        edmonds.start();
        vius[1] = true;
        if (!supervisor) {
            Thread supervisor1 = new Thread((Runnable) new Supervisor());
            supervisor1.start();
        }
    }

    public static void Dijkstra(Algorisme algo) {
        System.out.println("Dij");
        Thread dij = new Thread(algo);
        threads[0] = dij;
        dij.start();
        vius[2] = true;
        if (!supervisor) {
            Thread supervisor2 = new Thread((Runnable) new Supervisor());
            supervisor2.start();
        }
    }
    
    public static boolean getVius(int pos){
        return vius[pos];
    }
    
    public static void setVius(int pos, boolean viu){
        vius[pos] = viu;
    }
    
    public static Thread getThread(int pos){
        return threads[pos];
    }
    
    public static void setSupervisor(boolean estat){
        supervisor = estat;
    }
}
