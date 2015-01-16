package prop;


import java.util.Comparator;

public class compNom implements Comparator<Doctor> {
    @Override
    /**
     * Compara els doctors per Cognom1. Si el primer cognom és igual,
     * llavors compara pel segon. Si el segon cognom és igual,
     * llavors compara pel nom de pila. En cas que també sigui igual,
     * ordena per dni
     */
    public int compare(Doctor doc1, Doctor doc2){
        if (doc1 == doc2) return 0;
        if (doc1.getCognom1() == null) return 1;
        if (doc2.getCognom1() == null) return -1;
        if (doc1.getCognom1().equals(doc2.getCognom1())){

            if(doc1.getCognom2().equals(doc2.getCognom2())){
                if(doc1.getNom().equals(doc2.getNom())){
                        //Ordenem per DNI si tenen el mateix nom, cognom1 i cognom2
                        return doc1.getdni().compareTo(doc2.getdni());
                    }
                return doc1.getNom().compareTo(doc2.getNom());
            }
            return doc1.getCognom2().compareTo(doc2.getCognom2());

        }
        return doc1.getCognom1().compareTo(doc2.getCognom1());
    }
}
