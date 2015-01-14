package prop;

public class Supervisor implements Runnable{

	/**
	 * Supervisa els threads.
	 */

	public void run() {
		while (algunViu()) {
			comprova_vius();
		}
	}

	/**
	 * Comprova si hi ha algun thread actiu.
	 * @return
	 */

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

	/**
	 * Comprova si els threads estan actius.
	 */

	private void comprova_vius() {
		for (int i = 0; i < 3; i++) {
			if (CtrlThreads.getViu(i) && CtrlThreads.getThread(i)!=null && !CtrlThreads.getThread(i).isAlive()) {
				CtrlThreads.setViu(i, false);
				actualitza(i);
			}
		}
	}

	/**
	 * Actualitza els resultats de l'execució.
	 * @param nThread
	 */

	private void actualitza(int nThread) {
		long temps;
		Algorisme algorisme;
		switch (nThread) {
		case 0:
			algorisme = CtrlAlgorisme.getAlgorisme(0);
			temps = algorisme.getTemps();
			CtrlVistaPrincipal.posaResultat(0, temps);
			break;
		case 1:
			algorisme = CtrlAlgorisme.getAlgorisme(1);
			temps = algorisme.getTemps();
			CtrlVistaPrincipal.posaResultat(1, temps);
			break;
		case 2:
			algorisme = CtrlAlgorisme.getAlgorisme(2);
			temps = algorisme.getTemps();
			CtrlVistaPrincipal.posaResultat(2, temps);
			break;
		}

	}

}
