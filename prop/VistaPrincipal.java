package prop;


import javax.swing.*;
import java.awt.*;

public class VistaPrincipal {

    //Ens guardem el CtrlPresentació des d'on ens han cridat
    private CtrlPresentacio ctrlPresentacio;

    //Tots els components de la interfície gràfica
    private JFrame vistaPrin = new JFrame("Guàrdies de Doctors d'un Hospital");


    public VistaPrincipal(CtrlPresentacio cp){
        ctrlPresentacio = cp;
        inicialitzarVista();
    }

    /**
     * Donat que sempre estarà activa la VistaPrincipal, simplement la fem
     * visible i l'activem.
     * No donem opció a desactivar-la.
     */
    public void ferVisibleiActivar(){

    }

    public void inicialitzarVista(){
        //Mida de la finestra
        vistaPrin.setMinimumSize(new Dimension(800, 500));
        vistaPrin.setPreferredSize(new Dimension(1000, 500));
        vistaPrin.setResizable(false);

        vistaPrin.setLocationRelativeTo(null);
        vistaPrin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }




}
