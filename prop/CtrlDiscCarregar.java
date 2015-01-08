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
	
	public static void carregarh(File f) {
//		CtrlHospital.carregar(f);
	}
	
	public static void carregarp(File f) {
	//	CtrlPlantilla.carregar(f);
	}
	
	public static void carregarc(File f) {
		//CtrlCalendari.carregar(f);
	}
	
	public static void carregarr(File f) {
		//CtrlRestriccions.carregar(f);
	}
}
