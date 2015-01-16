package prop;

import java.io.File;

public class CtrlDiscCarregar {
private static DiscCarregar disccarregar;
	
	public CtrlDiscCarregar(CtrlVistaPrincipal cvp) {
		disccarregar = new DiscCarregar(this);
	}
	
	public static DiscCarregar getDiscCarregar() {
		return disccarregar;
	}

    /**
     * Carrega l'hospital
     * @param f
     */
	public static void carregarh(File f) {
		CtrlHospital.carregar(f);
	}

    /**
     * Carrega les plantilles
     * @param f
     */
	public static void carregarp(File f) {
		CtrlPlantilla.carregar(f);
	}

    /**
     * Carrega els calendaris
     * @param f
     */
	public static void carregarc(File f) {
		CtrlCalendari.carregar(f);
	}

    /**
     * Carrega les restriccions
     * @param f
     */
	public static void carregarr(File f) {
		CtrlRestriccio.carregar(f);
	}

    /**
     * Carrega les relacions entre doctors i restriccions
     * @param f
     * @throws NumberFormatException
     * @throws Error
     */
	public static void carregardr(File f) throws NumberFormatException, Error {
		Doc_Res.carregar(f);
	}
}
