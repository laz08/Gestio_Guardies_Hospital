
package prop;

import prop.*;

public class CtrlDoctor {

    public void crearDoctor(String d, String n, String cg1, String cg2, int s, int telf, String mail){
        Doctor doc;
        doc.setdni(d);
        doc.setNom(n);
        doc.setCognom1(cg1);
        doc.setCognom2(cg2);
        doc.setSou(s);
        doc.setTelefon(telf);
        doc.setCorreu(mail);
        doc.setActiu(false); //Primer el posem com a inactiu
    }

    public void modificarTelf(Doctor doc, int telf){
        doc.setTelefon(telf);
    }

    public void modificarCorreu(Doctor doc, String mail){
        doc.setCorreu(mail);
    }

    public void modificarSou(Doctor doc, int sou){
        doc.setSou(sou);
    }

    public void modificarActivitat(Doctor doc, boolean actiu){
        doc.setActiu(actiu);
    }



}
