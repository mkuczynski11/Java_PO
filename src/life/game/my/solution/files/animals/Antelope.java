package life.game.my.solution.files.animals;

import life.game.my.solution.files.Animal;
import life.game.my.solution.files.Organism;
import life.game.my.solution.files.Position;
import life.game.my.solution.files.World;

import java.awt.*;

public class Antelope extends Animal
{
    private static final int ANTYLOPA_SILA = 4;
    private static final int ANTYLOPA_INICJATYWA = 4;

    public Antelope(Position position, World world){
        super(ANTYLOPA_SILA, ANTYLOPA_INICJATYWA,world.define.SYMBOL_ANTYLOPA,position, world);
    }

    @Override
    public String getName(){
        return "Antylopa";
    }
    @Override
    public boolean isSameSpecies(Organism organism){
        return (organism instanceof Antelope);
    }
    @Override
    public Organism copy(Position position){
        return new Antelope(position, getWorld());
    }
    @Override
    public void save(){

    }
    @Override
    public Color getColor(){
        return Color.blue;
    }
}
