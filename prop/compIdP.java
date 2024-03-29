package prop;

import java.util.Comparator;

public class compIdP implements Comparator<Calendari> {

    /**
     * Compara dos calendaris pel nom de la plantilla associada
     * @param c1
     * @param c2
     * @return
     */
	public int compare(Calendari c1, Calendari c2) {
		if (c1 == c2) return 0;
		if (c1.getPlantillaAssociada() == null) return 1;
		if (c2.getPlantillaAssociada() == null) return -1;
		return c1.getPlantillaAssociada().compareTo(c2.getPlantillaAssociada());
	}
}