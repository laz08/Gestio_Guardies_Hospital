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
	
	public static void guardarh(File f) {
		CtrlHospital.guardar(f);
	}
	
	public static void guardarp(File f) {
		CtrlPlantilla.guardar(f);
	}
	
	public static void guardarc(File f) {
		CtrlCalendari.guardar(f);
	}
	
	public static void guardarr(File f) {
		CtrlRestriccio.guardar(f);
	}
}
