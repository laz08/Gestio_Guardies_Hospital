package prop;

public class mainGUI {
    public static void main(String argv[]){
        javax.swing.SwingUtilities.invokeLater(
                new Runnable(){
                    public void run(){
                        CtrlPresentacio cp = new CtrlPresentacio();
                        cp.inicialitzaPresentacio();
                    }
                }
        );
    }
}
