package prop;



public class MainGUI {
  public static void main (String[] args) {
    javax.swing.SwingUtilities.invokeLater (
      new Runnable() {
        public void run() {
          CtrlVistaPrincipal vp = new CtrlVistaPrincipal();
    }});
  }
}
