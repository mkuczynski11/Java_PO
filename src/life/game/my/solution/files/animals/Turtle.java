package life.game.my.solution.files.animals;

import life.game.my.solution.files.Animal;
import life.game.my.solution.files.Organism;
import life.game.my.solution.files.Position;
import life.game.my.solution.files.World;

import java.awt.*;

public class Turtle extends Animal
{
    private static final int ZOLW_SILA = 2;
    private static final int ZOLW_INICJATYWA = 1;

    public Turtle(Position position, World world){
        super(ZOLW_SILA, ZOLW_INICJATYWA,world.define.SYMBOL_ZOLW,position, world);
    }
    public Turtle(int age, int strength, Position position, World world){
        super(age, strength,ZOLW_INICJATYWA,world.define.SYMBOL_ZOLW,position, world);
    }

    @Override
    public String getName(){
        return "Zolw";
    }
    @Override
    public boolean isSameSpecies(Organism organism){
        return (organism instanceof Turtle);
    }
    @Override
    public Organism copy(Position position){
        return new Turtle(getAge(), getStrength(), position, getWorld());
    }
    @Override
    public Organism child(Position position) {return new Turtle(position, getWorld());}
    @Override
    public void save(){

    }
    @Override
    public Color getColor(){
        return Color.pink;
    }
}
