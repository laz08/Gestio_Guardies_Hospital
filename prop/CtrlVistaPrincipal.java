package prop;

public class CtrlVistaPrincipal {
	private static VistaPrincipal vistaprincipal;
	public CtrlVistaPrincipal(){
		CtrlDiscCarregar cdc = new CtrlDiscCarregar(this);
		CtrlDiscGuardar cdg = new CtrlDiscGuardar(this);
		CtrlVistaHospital cvh = new CtrlVistaHospital(this);
		vistaprincipal = new VistaPrincipal(this, cdg, cdc, cvh);
	}
	public void actualitza() {
		vistaprincipal.actualitza();
	}
}
