package life.game.my.solution.files.animals;

import life.game.my.solution.files.Animal;
import life.game.my.solution.files.Organism;
import life.game.my.solution.files.Position;
import life.game.my.solution.files.World;

import java.awt.*;

public class Sheep extends Animal
{
    private static final int OWCA_SILA = 4;
    private static final int OWCA_INICJATYWA = 4;

    public Sheep(Position position, World world){
        super(OWCA_SILA, OWCA_INICJATYWA, world.define.SYMBOL_OWCA, position, world);
    }

    @Override
    public String getName(){
        return "Owca";
    }
    @Override
    public boolean isSameSpecies(Organism organism){
        return (organism instanceof Sheep);
    }
    @Override
    public Organism copy(Position position){
        return new Sheep(position, getWorld());
    }
    @Override
    public void save(){

    }
    @Override
    public Color getColor(){
        return Color.white;
    }
}
