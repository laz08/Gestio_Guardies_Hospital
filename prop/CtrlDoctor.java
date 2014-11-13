
package prop;

public class CtrlDoctor {

    public CtrlDoctor(){

    }

    public Doctor crearDoctor(String d, String n, String cg1, String cg2, int s, int telf, String mail){
        Doctor doc = new Doctor();
        doc.setdni(d);
        doc.setNom(n);
        doc.setCognom1(cg1);
        doc.setCognom2(cg2);
        doc.setSou(s);
        doc.setTelefon(telf);
        doc.setCorreu(mail);
        doc.setActiu(false); //Primer el posem com a inactiu

        return doc;
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

    public String consultarNom(Doctor doc){
            return doc.getNom();
    }

    public String consultarCognom1(Doctor doc){
        return doc.getCognom1();

    }

    public String consultarCognom2(Doctor doc){
        return doc.getCognom2();
    }

    public String consultarDni(Doctor doc){
        return doc.getdni();
    }

    public int consultarSou(Doctor doc){
         return doc.getSou();
    }

    public int consultarTelefon(Doctor doc){
        return doc.getTelefon();
    }

    public String consultarCorreu(Doctor doc){
        return doc.getCorreu();
    }

    public boolean consultarActivitat(Doctor doc){
        return doc.isActiu();
    }



}
