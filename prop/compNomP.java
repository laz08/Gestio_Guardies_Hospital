package prop;


import java.util.Comparator;

public class compNomP implements Comparator<Plantilla> {
    @Override
    public int compare(Plantilla p1, Plantilla p2){
        if (p1 == p2) return 0;
        if(p1.getNomPlantilla()== null) return 1;
        if(p2.getNomPlantilla() == null) return -1;
        return p1.getNomPlantilla().compareTo(p2.getNomPlantilla());
    }
}
