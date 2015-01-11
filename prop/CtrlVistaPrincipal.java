package prop;

public class CtrlVistaPrincipal {
	private static VistaPrincipal vistaprincipal;
	public CtrlVistaPrincipal(){
		CtrlDiscCarregar cdc = new CtrlDiscCarregar(this);
		CtrlDiscGuardar cdg = new CtrlDiscGuardar(this);
		CtrlVistaHospital cvh = new CtrlVistaHospital(this);
		CtrlVistaPlantilla cvp = new CtrlVistaPlantilla(this);
		CtrlVistaCalendari cvc = new CtrlVistaCalendari(this);
		CtrlVistaAssignacio cva = new CtrlVistaAssignacio(this);
		CtrlVistaResultats cvrs = new CtrlVistaResultats(this);
		vistaprincipal = new VistaPrincipal(this, cvrs, cdg, cdc, cva, cvh, cvp, cvc);
	}
}
