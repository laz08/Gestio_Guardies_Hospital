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
    private boolean[] vius = {false, false, false};
    
    @Override
    public void run() {
        while (true) {
            comprova_vius();
        }
    }

    private boolean algunViu() {
        boolean viu = false;
        int pos = 0;
        while (!viu && pos < 3) {
            if (vius[pos]) {
                viu = true;
            }
        }
        return viu;
    }

    private void comprova_vius() {
        for (int i = 0; i < 3; i++) {
            if (vius[i] && CtrlThreads.getThread(i)!=null && !CtrlThreads.getThread(i).isAlive()) {
                vius[i] = false;
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
    
    public void setVius(int i, boolean viu){
        vius[i] = viu;
    }
    
}
