package prop;

import java.util.*;

////////////////////////

public class CtrlPresentacion {

  private CtrlDominio ctrlDominio;
  private VPC vistaPrincipal;    // (= null) innecesario

//////////////////////// Constructor y metodos de inicializacion


  public CtrlPresentacion() {
    ctrlDominio = new CtrlDominio();
    if (vistaPrincipal == null)  // innecesario
      vistaPrincipal = new VPC(this);
  }

  public void inicializarPresentacion() {
    ctrlDominio.inicializarCtrlDominio();
    vistaPrincipal.hacerVisible();
  }

//////////////////////// Llamadas al controlador de dominio
  
  public ArrayList<String> llamadaDominio1 (String selectedItem) {
    return ctrlDominio.llamadaDominio1(selectedItem);
  }

  public ArrayList<String> llamadaDominio2() {
    return ctrlDominio.llamadaDominio2();
  }

}
