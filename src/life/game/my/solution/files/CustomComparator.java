package life.game.my.solution.files;

import java.util.Comparator;

public class CustomComparator implements Comparator<Organism> {
    public int compare(Organism organism1, Organism organism2){
        if(organism1.getIniciative() != organism2.getIniciative())
            return Integer.compare(organism2.getIniciative(),organism1.getIniciative());
        else
        {
            return Integer.compare(organism2.getAge(),organism1.getAge());
        }
    }
}