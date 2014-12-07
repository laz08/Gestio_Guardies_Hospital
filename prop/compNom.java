package prop;


import java.util.Comparator;

public class compNom implements Comparator<Doctor> {
    @Override
    public int compare(Doctor doc1, Doctor doc2){
        if (doc1 == doc2) return 0;
        if(doc1.getNom() == null) return 1;
        if(doc2.getNom() == null) return -1;
        return doc1.getNom().compareTo(doc2.getNom());
    }
}
