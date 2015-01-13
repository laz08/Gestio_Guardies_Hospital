/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prop;

/**
 *
 * @author Xisco
 */
public class Supervisor implements Runnable{
    
    @Override
    public void run() {
        System.out.println("arranca");
        while (algunViu()) {
            comprova_vius();
        }
    }

    private boolean algunViu() {
        boolean viu = false;
        int pos = 0;
        while (!viu && pos < 3) {
            if (CtrlThreads.getViu(pos)) {
                viu = true;
            }
            pos++;
        }
        return viu;
    }

    private void comprova_vius() {
        for (int i = 0; i < 3; i++) {
            if (CtrlThreads.getViu(i) && CtrlThreads.getThread(i)!=null && !CtrlThreads.getThread(i).isAlive()) {
                CtrlThreads.setViu(i, false);
                actualitza(i);
            }
        }
    }

    private void actualitza(int nThread) {
        long temps;
        Algorisme algorisme;
        switch (nThread) {
            case 0:
                algorisme = CtrlAlgorisme.getAlgorisme(0);
                temps = algorisme.getTemps();
                System.out.println("EF");
                CtrlVistaPrincipal.posaResultat(0, temps);
                break;
            case 1:
                algorisme = CtrlAlgorisme.getAlgorisme(1);
                temps = algorisme.getTemps();
                                System.out.println("EE");

                CtrlVistaPrincipal.posaResultat(1, temps);
                break;
            case 2:
                algorisme = CtrlAlgorisme.getAlgorisme(2);
                temps = algorisme.getTemps();
                                System.out.println("ED");

                CtrlVistaPrincipal.posaResultat(2, temps);
                break;
        }
        
    }
    
}
