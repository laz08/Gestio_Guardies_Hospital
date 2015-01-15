package prop;


import java.util.Comparator;

public class compDni implements Comparator<Doctor> {
    @Override
    /**
     * Compara els dos dni
     */
    public int compare(Doctor doc1, Doctor doc2){
        if(doc1 == doc2) return 0;
        if(doc1.getdni() == null) return 1;
        if(doc2.getdni() == null) return -1;
        return doc1.getdni().compareTo(doc2.getdni());
    }
}
