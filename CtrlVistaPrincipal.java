
public class CtrlVistaPrincipal {
	private static VistaPrincipal vistaprincipal;
	public CtrlVistaPrincipal(){
		CtrlDiscCarregar cdc = new CtrlDiscCarregar(this);
		CtrlDiscGuardar cdg = new CtrlDiscGuardar(this);
		vistaprincipal = new VistaPrincipal(this, cdg, cdc);
	}
}
