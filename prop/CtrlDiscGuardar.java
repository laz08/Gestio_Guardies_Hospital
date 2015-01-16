package prop;

import java.io.File;

public class CtrlDiscGuardar {
	private static DiscGuardar discguardar;
	
	public CtrlDiscGuardar(CtrlVistaPrincipal cvp) {
		discguardar = new DiscGuardar(this);
	}
	
	public static DiscGuardar getDiscGuardar() {
		return discguardar;
	}

    /**
     * Guarda l'hospital
     * @param f
     */
	public static void guardarh(File f) {
		CtrlHospital.guardar(f);
	}

    /**
     * Guarda les plantilles amb els seus doctors
     * @param f
     */
	public static void guardarp(File f) {
		CtrlPlantilla.guardar(f);
	}

    /**
     * Guarda els calendaris
     * @param f
     */
	public static void guardarc(File f) {
		CtrlCalendari.guardar(f);
	}

    /**
     * Guarda les restriccions
     * @param f
     */
	public static void guardarr(File f) {
		CtrlRestriccio.guardar(f);
	}

    /**
     * Guarda els lligams entre els doctors i les restriccions
     * @param f
     */
	public static void guardardr(File f) {
		Doc_Res.guardar(f);
	}
}
