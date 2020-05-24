package life.game.my.solution.files.animals;

import life.game.my.solution.files.Animal;
import life.game.my.solution.files.Organism;
import life.game.my.solution.files.Position;
import life.game.my.solution.files.World;

import java.awt.*;

public class Fox extends Animal
{
    private static final int LIS_SILA = 3;
    private static final int LIS_INICJATYWA = 4;

    public Fox(Position position, World world){
        super(LIS_SILA, LIS_INICJATYWA,world.define.SYMBOL_LIS,position, world);
    }
    public Fox(int age, int strength, Position position, World world){
        super(age,strength,LIS_INICJATYWA,world.define.SYMBOL_LIS,position, world);
    }

    @Override
    public String getName(){
        return "Lis";
    }
    @Override
    public boolean isSameSpecies(Organism organism){
        return (organism instanceof Fox);
    }
    @Override
    public Organism copy(Position position){
        return new Fox(getAge(), getStrength(), position, getWorld());
    }
    @Override
    public Organism child(Position position) {return new Fox(position, getWorld());}
    @Override
    public void save(){

    }
    @Override
    public Color getColor(){
        return Color.orange;
    }
}
