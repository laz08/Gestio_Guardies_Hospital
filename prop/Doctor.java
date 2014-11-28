package prop;

import java.util.ArrayList;

public class Doctor {

    private String dni;
    private String correu;
    private int telefon;
    private String nom;
    private String cognom1;
    private String cognom2;
    private int sou;
    private boolean actiu;

    public Doctor(String d) {
        dni = d;
    }

    public void afegir_res(Restriccio r){
        int pos = CtrlRestriccio.consulta_pos(r);
        if (pos != -1) Doc_Res.relaciona(dni, pos);
     }
    
    public void eliminar_res(Restriccio r){
        int pos = CtrlRestriccio.consulta_pos(r);
        if (pos != -1) Doc_Res.elimina(dni, pos);
    }
    
    public String getdni() {
        return dni;
    }

    public void setdni(String d) {
        dni = d;
    }

    public int getTelefon() {
        return telefon;
    }

    public void setTelefon(int telf) {
        telefon = telf;
    }

    public String getCorreu() {
        return correu;
    }

    public void setCorreu(String email) {
        correu = email;
    }

    public int getSou() {
        return sou;
    }

    public void setSou(int s) {
        sou = s;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nm) {
        nom = nm;
    }

    public String getCognom1() {
        return cognom1;
    }

    public void setCognom1(String cg1) {
        cognom1 = cg1;
    }

    public String getCognom2() {
        return cognom2;
    }

    public void setCognom2(String cg2) {
        cognom2 = cg2;
    }

    public boolean isActiu() {
        return actiu;
    }

    public void setActiu(boolean act) {
        actiu = act;
    }
    
    public ArrayList<Integer> getRestriccions(){
        return Doc_Res.getRestriccions(dni);
    }
}
