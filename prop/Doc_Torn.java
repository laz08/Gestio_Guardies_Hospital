/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prop;

import java.util.ArrayList;

public class Doc_Torn {

    private static ArrayList<Relacio> torns = new ArrayList<Relacio>();
    private static ArrayList<Relacio> doctors = new ArrayList<Relacio>();

    public static void addRel(Doctor d, Torn t) {
        Relacio rt = new Relacio(t);
        Relacio rd = new Relacio(d);

        boolean existeixt = false;
        int post = 0;
        while (post < torns.size() && !existeixt) {
            if (t.equals(torns.get(post).getObj())) {
                existeixt = true;
            }
            if (!existeixt) {
                post++;
            }
        }


        boolean existeixd = false;
        int posd = 0;
        while (posd < torns.size() && !existeixd) {
            if (t.equals(torns.get(posd).getObj())) {
                existeixd = true;
            }
            if (!existeixd) {
                posd++;
            }
        }


        if (existeixd) {
            doctors.get(posd).addRelacio(rt);
        } else {
            rd.addRelacio(rt);
            doctors.add(rd);
        }


    }

    public static void addRel(String d, String t) {
        Dia[] any = CtrlCalendari.consultarCalendari(CtrlPlantilla.getPlantillaActual().getNomPlantilla()).getCalendari();
        boolean trobat = false;
        int posDia = 0;
        Torn torn = null;
        while (posDia < any.length && !trobat) {
            Dia dia = any[posDia];
            Torn[] torns = dia.getTorns();
            int posTorn = 0;
            while (posTorn < 3) {
                if(torns[posTorn].toString().equals(t)){
                    trobat = true;
                    torn = torns[posTorn];
                }
                posTorn ++;
            }
            posDia++;
        }
        Relacio rt = new Relacio(torn);
        
        Doctor doc = CtrlHospital.getDoctor(d);
        Relacio rd = new Relacio(doc);

        boolean existeixt = false;
        int post = 0;
        while (post < torns.size() && !existeixt) {
            if (t.equals(torns.get(post).getObj())) {
                existeixt = true;
            }
            if (!existeixt) {
                post++;
            }
        }


        boolean existeixd = false;
        int posd = 0;
        while (posd < torns.size() && !existeixd) {
            if (t.equals(torns.get(posd).getObj())) {
                existeixd = true;
            }
            if (!existeixd) {
                posd++;
            }
        }


        if (existeixd) {
            doctors.get(posd).addRelacio(rt);
        } else {
            rd.addRelacio(rt);
            doctors.add(rd);
        }


    }

    public static void removeRel(Doctor d, Torn t) {
        boolean existeixt = false;
        int post = 0;
        while (post < torns.size() && !existeixt) {
            if (t.equals(torns.get(post).getObj())) {
                existeixt = true;
            }
            if (!existeixt) {
                post++;
            }
        }
        if (existeixt) {
            torns.get(post).rmRelacio(d);
        }

        boolean existeixd = false;
        int posd = 0;
        while (posd < torns.size() && !existeixd) {
            if (t.equals(torns.get(post).getObj())) {
                existeixd = true;
            }
            if (!existeixd) {
                posd++;
            }
        }
        if (existeixd) {
            doctors.get(post).rmRelacio(t);
        }
    }

    public static void removeAll() {
        torns.clear();
        doctors.clear();
    }

    public static ArrayList<Torn> getRel(Doctor d) {
        ArrayList<Torn> ts = new ArrayList<Torn>();
        boolean existeixd = false;
        int posd = 0;
        while (posd < torns.size() && !existeixd) {
            if (d.equals(torns.get(posd).getObj())) {
                existeixd = true;
            }
            if (!existeixd) {
                posd++;
            }
        }
        if (existeixd) {
            ArrayList<Relacio> rt = torns.get(posd).getRelacionats();
            for (int i = 0; i < rt.size(); i++) {
                ts.add((Torn) rt.get(i).getObj());
            }
        }
        return ts;
    }

    public static ArrayList<Doctor> getRel(Torn t) {
        ArrayList<Doctor> ts = new ArrayList<Doctor>();
        boolean existeix = false;
        int pos = 0;
        while (pos < doctors.size() && !existeix) {
            if (t.equals(doctors.get(pos).getObj())) {
                existeix = true;
            }
            if (!existeix) {
                pos++;
            }
        }
        if (existeix) {
            ArrayList<Relacio> rd = torns.get(pos).getRelacionats();
            for (int i = 0; i < rd.size(); i++) {
                ts.add((Doctor) rd.get(i).getObj());
            }
        }
        return ts;
    }
    
    public static void mostraRelacions(){
        System.out.println("________DOCTORS______");
        for(int i=0; i<doctors.size(); i++){
            Relacio rel = doctors.get(i);
            System.out.print("Doctor "+((Doctor)rel.getObj()).getdni()); 
            System.out.print(" relacionat amb ");
            ArrayList<Relacio> relacionats = rel.getRelacionats();
            for(int e=0; e<relacionats.size(); e++){
                Relacio r = relacionats.get(e);
                System.out.print(((Torn)r.getObj()).toString()+" ");
            }
            System.out.println();
        }
        
        
        System.out.println("________TORNS______");
        for(int i=0; i<torns.size(); i++){
            Relacio rel = torns.get(i);
            System.out.print("Doctor "+((Torn)rel.getObj()).toString()); 
            System.out.print(" relacionat amb ");
            ArrayList<Relacio> relacionats = rel.getRelacionats();
            for(int e=0; e<relacionats.size(); e++){
                Relacio r = relacionats.get(e);
                System.out.print(((Doctor)r.getObj()).getdni()+" ");
            }
            System.out.println();
        }
    }
}
